package assembly;

import Task.Task;
import server.Service;
import server.HandleController;
import server.Session;
import server.GameSrc;
import server.Manager;
import io.Message;
import io.SQLManager;
import io.Util;
import java.io.DataOutputStream;
import stream.Client;
import stream.Server;
import template.EffectTemplate;
import template.ItemTemplate;
import template.SkillTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

public class Player extends User {

    public String version = null;
    public Session conn;
    public Char c = null;
    public int typemenu = -1;
    public long chatKTGdelay = 0L;
    public Char viewChar = null;
    public int menuIdAuction = -1;
    public int menuCaiTrang = 0;
    public int coin;
    public String ipUser;
    public String luongGF;
    public String xuGF;
    public String yenGF;
    public String nameUS;

    public Player() {
    }

    public void SendTree(final Player p, final String url) throws IOException {
        final byte[] ab = GameSrc.loadFile(url).toByteArray();
        final Message msg = new Message(117);
        msg.writer().write(ab);
        msg.writer().flush();
        p.conn.sendMessage(msg);
        msg.cleanup();
    }

    public void cleanup() {
        this.conn = null;
    }

    public synchronized int upluong(long x) {
        long luongnew = (long) this.luong + x;
        if (luongnew > 2000000000L) {
            x = 2000000000 - this.luong;
        } else if (luongnew < -2000000000L) {
            x = -2000000000 - this.luong;
        }
        this.luong += (int) x;
        return (int) x;
    }

    public static Player login(final Session conn, String user, String pass) throws ParseException {
        try {
            synchronized (Server.LOCK_MYSQL) {
                ResultSet red = SQLManager.stat.executeQuery("SELECT * FROM `player` WHERE (`username`LIKE'" + user
                        + "' AND `password`LIKE'" + pass + "');");
                if (red != null && red.first()) {
                    int iddb = red.getInt("id");
                    String username = red.getString("username").toLowerCase();
                    int luong = red.getInt("luong");
                    int coin = red.getInt("coin");
                    int role = red.getInt("role");
                    int ban = red.getInt("ban");
                    int online = red.getInt("online");
                    int status = red.getInt("status");
                    int nap = red.getInt("nap");
                    if (ban >= 1) {
                        conn.sendMessageLog("Tài khoản của bạn đã bị khoá do vi phạm quy định của Server");
                        return null;
                    }
                    if (iddb != 1 && role <= 0) {
                        if (Server.manager.baotri >= 1) {
                            conn.sendMessageLog("Máy chủ đang bảo trì vui lòng thử lại sau");
                            return null;
                        }
                    }
                    /*
                     * if(!Util.CheckString(user, "^[A-Z]+$") || !Util.CheckString(pass,
                     * "^[A-Z]+$")){
                     * conn.sendMessageLog("Tài khoản hoặc mật khẩu có ký tự lạ!");
                     * return null;
                     * }
                     */
                    if (!Util.CheckString(user, "^[a-z0-9]+$") || !Util.CheckString(pass, "^[a-z0-9]+$")) {
                        conn.sendMessageLog("Thông tin tài khoản hoặc mật khẩu không chính xác !");
                        return null;
                    } else {
                        JSONArray jarr = (JSONArray) JSONValue.parse(red.getString("ninja"));
                        Player p = Client.gI().getPlayer(user);
                        if (p != null && p.conn != null) {
                            // if (p.c != null && p.c.tileMap != null) {
                            // p.c.tileMap.leave(p);
                            // }
                            p.conn.sendMessageLog("Có người đăng nhập vào tài khoản của bạn.");
                            Client.gI().kickSession(p.conn);
                            conn.sendMessageLog("Tài khoản đang được đăng nhập ở thiết bị khác. Hãy thử lại sau !");
                            return null;
                        } else {
                            // if (Client.timeWaitLogin.containsKey(username)) {
                            // if (System.currentTimeMillis() < Client.timeWaitLogin.get(username)) {
                            // conn.sendMessageLog("Bạn chỉ có thể đăng nhập lại vào tài khoản sau " +
                            // (Client.timeWaitLogin.get(username) - System.currentTimeMillis()) / 4000L +
                            // "s nữa");
                            // return null;
                            // }
                            // Client.timeWaitLogin.remove(username);
                            // }
                            p = new Player();
                            p.conn = conn;
                            p.id = iddb;
                            p.username = username;
                            p.luong = luong;
                            p.coin = coin;
                            p.role = role;
                            p.online = online;
                            p.status = status;
                            p.nap = nap;
                            for (byte i = 0; i < jarr.size(); ++i) {
                                p.sortNinja[i] = jarr.get(i).toString();
                            }
                            SQLManager.stat.executeUpdate("UPDATE `player` SET `online`=1 WHERE `id`=" + p.id + " ;");
                            Client.gI().put(p);
                            jarr.clear();
                            red.close();
                            return p;
                        }
                    }
                } else {
                    conn.sendMessageLog("Tài khoản hoặc mật khẩu không chính xác.");
                    return null;
                }
            }
        } catch (SQLException var17) {
            var17.printStackTrace();
            return null;
        }
    }

    public void sendInfo() {
        Message m = null;
        try {
            this.c.hp = this.c.getMaxHP();
            this.c.mp = this.c.getMaxMP();
            m = new Message(-30);
            m.writer().writeByte(-127);
            m.writer().writeInt(this.c.id);
            m.writer().writeUTF(this.c.clan.clanName);
            if (!this.c.clan.clanName.isEmpty()) {
                m.writer().writeByte(this.c.clan.typeclan);
            }
            m.writer().writeByte(this.c.taskId);
            m.writer().writeByte(this.c.gender);
            m.writer().writeShort(this.c.partHead());
            m.writer().writeByte(this.c.speed());
            m.writer().writeUTF(this.c.name);
            m.writer().writeByte(this.c.pk);
            m.writer().writeByte(this.c.typepk);
            m.writer().writeInt(this.c.getMaxHP());
            m.writer().writeInt(this.c.hp);
            m.writer().writeInt(this.c.getMaxMP());
            m.writer().writeInt(this.c.mp);
            m.writer().writeLong(this.c.exp);
            m.writer().writeLong(this.c.expdown);
            m.writer().writeShort(this.c.eff5buffHP());
            m.writer().writeShort(this.c.eff5buffMP());
            m.writer().writeByte(this.c.nclass);
            m.writer().writeShort(this.c.ppoint);
            m.writer().writeShort(this.c.potential0);
            m.writer().writeShort(this.c.potential1);
            m.writer().writeInt(this.c.potential2);
            m.writer().writeInt(this.c.potential3);
            m.writer().writeShort(this.c.spoint);
            m.writer().writeByte(this.c.skill.size());
            short i;
            for (i = 0; i < this.c.skill.size(); ++i) {
                m.writer().writeShort(SkillTemplate.Templates(((Skill) this.c.skill.get(i)).id,
                        ((Skill) this.c.skill.get(i)).point).skillId);
            }
            m.writer().writeInt(this.c.xu);
            m.writer().writeInt(this.c.yen);
            m.writer().writeInt(this.luong);
            m.writer().writeByte(this.c.maxluggage);
            byte j;
            for (j = 0; j < this.c.maxluggage; ++j) {
                if (this.c.ItemBag[j] == null) {
                    m.writer().writeShort(-1);
                } else {
                    m.writer().writeShort(this.c.ItemBag[j].id);
                    m.writer().writeBoolean(this.c.ItemBag[j].isLock);
                    if (ItemTemplate.isTypeBody(this.c.ItemBag[j].id) || ItemTemplate.isTypeMounts(this.c.ItemBag[j].id)
                            || ItemTemplate.isTypeNgocKham(this.c.ItemBag[j].id)) {
                        m.writer().writeByte(this.c.ItemBag[j].upgrade);
                    }
                    m.writer().writeBoolean(this.c.ItemBag[j].isExpires);
                    m.writer().writeShort(this.c.ItemBag[j].quantity);
                }
            }

            int k;
            Item item;
            for (k = 0; k < 16; ++k) {
                item = this.c.get().ItemBody[k];
                if (item != null) {
                    m.writer().writeShort(item.id);
                    m.writer().writeByte(item.upgrade);
                    m.writer().writeByte(item.sys);
                } else {
                    m.writer().writeShort(-1);
                }
            }

            m.writer().writeBoolean(this.c.isHuman);
            m.writer().writeBoolean(this.c.isNhanban);
            m.writer().writeShort(this.c.partHead());
            m.writer().writeShort(this.c.Weapon());
            m.writer().writeShort(this.c.Body());
            m.writer().writeShort(this.c.Leg());

            m.writer().writeShort(this.c.get().ID_HAIR);
            m.writer().writeShort(this.c.get().ID_Body);
            m.writer().writeShort(this.c.get().ID_LEG);
            m.writer().writeShort(this.c.get().ID_WEA_PONE);
            m.writer().writeShort(this.c.get().ID_PP);
            m.writer().writeShort(this.c.get().ID_NAME);
            m.writer().writeShort(this.c.get().ID_HORSE);
            m.writer().writeShort(this.c.get().ID_RANK);
            m.writer().writeShort(this.c.get().ID_MAT_NA);
            m.writer().writeShort(this.c.get().ID_Bien_Hinh);

            for (k = 16; k < 32; ++k) {
                item = this.c.get().ItemBody[k];
                if (item != null) {
                    m.writer().writeShort(item.id);
                    m.writer().writeByte(item.upgrade);
                    m.writer().writeByte(item.sys);
                } else {
                    m.writer().writeShort(-1);
                }
            }
            m.writer().flush();
            this.conn.sendMessage(m);
            m.cleanup();
            // vĩ thú
            this.setBijuu();
            this.getMobMe();
            this.c.clone = CloneCharacter.getClone(this.c);
            if (this.c.isTaskHangNgay == 1 && this.c.taskHangNgay[0] != -1) {
                Service.getTaskOrder(this.c, (byte) 0);
            }
            if (this.c.isTaskTaThu == 1 && this.c.taskTaThu[0] != -1) {
                Service.getTaskOrder(this.c, (byte) 1);
            }
            Map map;
            int var7;
            for (var7 = 0; var7 < Server.maps.length; ++var7) {
                map = Server.maps[var7];
                if (map.id == this.c.mapid) {
                    boolean isturn = false;
                    if (map.getXHD() != -1 || map.VDMQ() || map.Langshiba() || map.id == 188 || map.id == 113
                            || map.id == 160 || map.mapChienTruong() || map.mapLDGT() || map.mapGTC()) {
                        isturn = true;
                        map = Manager.getMapid(this.c.mapLTD);
                    }
                    int l;
                    for (l = 0; l < map.area.length; ++l) {
                        if (map.area[l].numplayers < map.template.maxplayers) {
                            if (!isturn) {
                                map.area[l].Enter(this);
                            } else {
                                map.area[l].EnterMap0(this.c);
                            }
                            byte n;
                            for (n = 0; n < this.c.veff.size(); ++n) {
                                this.addEffectMessage(this.c.veff.get(n));
                            }
                            return;
                        }
                    }

                    map.area[Util.nextInt(map.area.length)].Enter(this);

                    byte n2;
                    for (n2 = 0; n2 < this.c.veff.size(); ++n2) {
                        this.addEffectMessage(this.c.veff.get(n2));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void loadSkill() {
        Message m = null;
        try {
            m = new Message(-30);

            m.writer().writeByte(-125);
            m.writer().writeByte(this.c.get().speed());
            m.writer().writeInt(this.c.get().getMaxHP());
            m.writer().writeInt(this.c.get().getMaxMP());
            m.writer().writeShort(this.c.get().spoint);
            m.writer().writeByte(this.c.get().skill.size());
            for (Skill fs : this.c.get().skill) {
                m.writer().writeShort(SkillTemplate.Templates(fs.id, fs.point).skillId);
            }
            m.writer().flush();
            this.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void getMp() {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-121);
                m.writer().writeInt(this.c.get().mp);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void restPpoint() {
        if (this.c.get().nclass % 2 == 0) {
            this.c.get().potential0 = 5;
            this.c.get().potential1 = 5;
            this.c.get().potential2 = 5;
            this.c.get().potential3 = 15;
        } else {
            this.c.get().potential0 = 15;
            this.c.get().potential1 = 5;
            this.c.get().potential2 = 5;
            this.c.get().potential3 = 5;
        }
        this.c.get().ppoint = (short) (Level.totalpPoint(this.c.get().level)
                + 10 * (this.c.useTiemNang + this.c.useBanhBangHoa));
        this.loadPpoint();
    }

    public void restSpoint() {
        for (Skill skl : this.c.get().skill) {
            if (skl.id != 0) {
                if (this.c.isHuman && this.c.checkIdSkill90(skl.id)) {
                    continue;
                }
                skl.point = 1;
            }
        }
        this.c.get().spoint = (short) (Level.totalsPoint(this.c.get().level) + this.c.useKyNang
                + this.c.useBanhPhongLoi);
        this.loadSkill();
    }

    public void loadPpoint() {
        Message m = null;
        try {
            m = new Message(-30);
            m.writer().writeByte(-109);
            m.writer().writeByte(this.c.get().speed());
            m.writer().writeInt(this.c.get().getMaxHP());
            m.writer().writeInt(this.c.get().getMaxMP());
            m.writer().writeShort(this.c.get().ppoint);
            m.writer().writeShort(this.c.get().potential0);
            m.writer().writeShort(this.c.get().potential1);
            m.writer().writeInt(this.c.get().potential2);
            m.writer().writeInt(this.c.get().potential3);
            m.writer().flush();
            this.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void openBagLevel(byte index) {
        Message m = null;
        try {
            m = new Message(-30);
            m.writer().writeByte(-91);
            m.writer().writeByte(this.c.ItemBag.length);
            m.writer().writeByte(index);
            m.writer().flush();
            this.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void setTypeClan(int type) {
        Message m = null;
        try {
            this.c.clan.typeclan = (byte) type;
            m = new Message(-30);
            m.writer().writeByte(-62);
            m.writer().writeInt(this.c.id);
            m.writer().writeUTF(this.c.clan.clanName);
            m.writer().writeByte(this.c.clan.typeclan);
            m.writer().flush();
            this.c.tileMap.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void upExpClan(int exp) {
        ClanManager clan = ClanManager.getClanName(this.c.clan.clanName);
        if (clan != null && clan.getMem(this.c.name) != null) {
            this.c.clan.pointClan += exp;
            this.c.clan.pointClanWeek += exp;
            clan.upExp(exp);
            this.sendAddchatYellow("Gia tộc của bạn nhận được " + exp + " kinh nghiệm");
        }
    }

    public void sendAddchatYellow(String str) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-24);
                m.writer().writeUTF(str);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void endDlg(boolean isResetButton) {
        Message ms = null;
        try {
            if (this.conn != null) {
                ms = new Message(126);
                ms.writer().writeByte(isResetButton ? 0 : 1);
                ms.writer().flush();
                this.conn.sendMessage(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ms != null) {
                ms.cleanup();
            }
        }
    }

    public void luongMessage(long luongup) {
        Message m = null;
        try {
            if (this.conn != null) {
                this.upluong(luongup);
                m = new Message(-30);
                m.writer().writeByte(-72);
                m.writer().writeInt(this.luong);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void upluongMessage(long luongup) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-71);
                m.writer().writeInt(this.upluong(luongup));
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void requestItem(int typeUI) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(30);
                m.writer().writeByte(typeUI);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void liveFromDead() {
        Message m = null;
        try {
            if (this.c.tileMap.map.mapChienTruong()) {
                switch (this.c.pheCT) {
                    case 0: {
                        this.c.typepk = 4;
                        Service.ChangTypePkId(this.c, (byte) 4);
                        break;
                    }
                    case 1: {
                        this.c.typepk = 5;
                        Service.ChangTypePkId(this.c, (byte) 5);
                        break;
                    }
                    default: {
                        this.c.typepk = 0;
                        Service.ChangTypePkId(this.c, (byte) 0);
                        break;
                    }
                }
            }
            this.c.hp = this.c.getMaxHP();
            this.c.mp = this.c.getMaxMP();
            this.c.isDie = false;
            if (this.conn != null) {
                m = new Message(-10);
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            }
            m = new Message(88);
            m.writer().writeInt(this.c.id);
            m.writer().writeShort(this.c.x);
            m.writer().writeShort(this.c.y);
            m.writer().flush();
            this.c.tileMap.sendMyMessage(this, m);
            m.cleanup();
        } catch (Exception var2) {
            var2.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void endLoad(boolean canvas) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(126);
                m.writer().writeByte(canvas ? 0 : -1);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void flush() {
        JSONArray jarr = new JSONArray();
        try {
            synchronized (Server.LOCK_MYSQL) {
                if (this.c != null) {
                    this.c.flush();
                    String n = this.sortNinja[0];
                    this.sortNinja[0] = this.c.name;
                    for (byte k = 1; k < this.sortNinja.length; ++k) {
                        if (this.sortNinja[k] != null && this.sortNinja[k].equals(this.c.name)) {
                            this.sortNinja[k] = n;
                        }
                    }
                }
                for (byte j = 0; j < this.sortNinja.length; ++j) {
                    if (this.sortNinja[j] != null) {
                        jarr.add(this.sortNinja[j]);
                    }
                }
                SQLManager.stat.executeUpdate("UPDATE `player` SET`luong`=" + this.luong + ",`ninja`='"
                        + jarr.toJSONString() + "' WHERE `id`=" + this.id + " LIMIT 1;");
                if (jarr != null && !jarr.isEmpty()) {
                    jarr.clear();
                }
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

    }

    public void close() {
    }

    public void openBookSkill(byte index, byte sid) {
        if (this.c.get().getSkill(sid) != null) {
            this.sendAddchatYellow(Language.HAVE_LEARNED_SKILL);
        } else {
            Message m = null;
            try {
                this.c.ItemBag[index] = null;
                Skill skill = new Skill();
                skill.id = sid;
                skill.point = 1;
                this.c.get().skill.add(skill);
                Service.sendInfoPlayers(this, this);
                this.loadSkill();
                m = new Message(-30);
                m.writer().writeByte(-102);
                m.writer().writeByte(index);
                m.writer().writeShort(SkillTemplate.Templates(skill.id, skill.point).skillId);
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (m != null) {
                    m.cleanup();
                }
            }
        }
    }

    public void updateExp(long xpup) {
        Message m = null;
        int level;
        long xpold;
        int i;
        try {
            if (this.c.get().isHuman && this.c.get().exptype == 1 && !this.c.get().isNhanban) {
                if (this.c.expdown > 0L) {
                    this.c.expdown -= xpup;
                    m = new Message(71);
                    m.writer().writeLong(xpup);
                    m.writer().flush();
                    this.conn.sendMessage(m);
                    m.cleanup();
                } else {
                    this.c.expdown = 0L;
                    xpold = this.c.exp;
                    this.c.exp += xpup;
                    level = this.c.level;
                    if (this.c.level <= Manager.max_level_up) {
                        this.c.setLevel_Exp(this.c.exp, true);
                    }

                    if (this.c.level == Manager.max_level_up) {
                        try {
                            if (this.c.saveBXH != Manager.max_level_up) {
                                String sqlSET = "(" + this.c.id + ", '" + this.c.name + "', " + this.c.level + ", '"
                                        + Server.manager.NinjaS[this.c.nclass] + "', '"
                                        + Util.toDateString(Date.from(Instant.now())) + "');";
                                SQLManager.stat.executeUpdate(
                                        "INSERT INTO `xep_hang_level` (`ninja_id`,`name`,`level`,`class`,`time`) VALUES "
                                                + sqlSET);
                                this.c.saveBXH = Manager.max_level_up;
                                System.out.println("Update EXP");
                            }
                        } catch (Exception var8) {
                            var8.printStackTrace();
                        }
                    }

                    if (this.c.level > Manager.max_level_up) {
                        this.c.level = Manager.max_level_up;
                        this.c.exp = xpold;
                        xpup = 0L;
                    }

                    if (level < this.c.level) {
                        if (this.c.nclass != 0) {
                            for (i = level + 1; i <= this.c.level; ++i) {
                                this.c.ppoint += Level.getLevel(i).ppoint;
                                this.c.spoint += Level.getLevel(i).spoint;
                            }
                        } else {
                            for (i = level + 1; i <= this.c.level; ++i) {
                                this.c.potential0 += 5;
                                this.c.potential1 += 2;
                                this.c.potential2 += 2;
                                this.c.potential3 += 2;
                            }
                        }
                    }

                    m = new Message(5);
                    m.writer().writeLong(xpup);
                    m.writer().flush();
                    this.conn.sendMessage(m);
                    m.cleanup();
                    if (level != this.c.level) {
                        this.c.setXPLoadSkill(this.c.exp);
                        Task.requestLevel(c);
                    }
                }
            } else if (this.c.get().isNhanban && !this.c.get().isHuman && !this.c.clone.isDie) {
                if (this.c.get().exptype >= 0) {
                    if (this.c.clone.expdown > 0L) {
                        this.c.clone.expdown -= xpup;
                        m = new Message(71);
                        m.writer().writeLong(xpup);
                        m.writer().flush();
                        this.conn.sendMessage(m);
                        m.cleanup();
                    } else {
                        this.c.clone.expdown = 0L;
                        xpold = this.c.clone.exp;
                        this.c.clone.exp += xpup;
                        level = this.c.clone.level;
                        if (this.c.clone.level <= Manager.max_level_up) {
                            this.c.clone.setLevel_Exp(this.c.clone.exp, true);
                        }

                        if (this.c.clone.level > Manager.max_level_up) {
                            this.c.clone.exp = xpold;
                            xpup = 0L;
                        }

                        if (level < this.c.clone.level) {
                            if (this.c.clone.nclass != 0) {
                                for (i = level + 1; i <= this.c.clone.level; ++i) {
                                    this.c.clone.ppoint += Level.getLevel(i).ppoint;
                                    this.c.clone.spoint += Level.getLevel(i).spoint;
                                }
                            } else {
                                for (i = level + 1; i <= this.c.clone.level; ++i) {
                                    this.c.clone.potential0 += 5;
                                    this.c.clone.potential1 += 2;
                                    this.c.clone.potential2 += 2;
                                    this.c.clone.potential3 += 2;
                                }
                            }
                        }

                        m = new Message(5);
                        m.writer().writeLong(xpup);
                        m.writer().flush();
                        this.conn.sendMessage(m);
                        m.cleanup();
                        if (level != this.c.clone.level) {
                            this.c.clone.setXPLoadSkill(this.c.clone.exp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void setEffect(int id, int timeStart, int timeLength, int param) {
        try {
            EffectTemplate data = EffectTemplate.entrys.get(id);
            Effect eff = this.c.get().getEffType(data.type);
            if (eff == null) {
                eff = new Effect(id, timeStart, timeLength, param);
                this.c.get().veff.add(eff);
                this.addEffectMessage(eff);
            } else {
                eff.template = data;
                eff.timeLength = timeLength;
                eff.timeStart = timeStart;
                eff.param = param;
                eff.timeRemove = System.currentTimeMillis() - (long) eff.timeStart + (long) eff.timeLength;
                this.setEffectMessage(eff);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, (String) null, var7);
        }

    }

    public void addEffectMessage(Effect eff) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-101);
                m.writer().writeByte(eff.template.id);
                m.writer().writeInt(eff.timeStart);
                m.writer().writeInt((int) (eff.timeRemove - System.currentTimeMillis()));
                m.writer().writeShort(eff.param);
                if (eff.template.type == 2 || eff.template.type == 3 || eff.template.type == 14) {
                    m.writer().writeShort(this.c.get().x);
                    m.writer().writeShort(this.c.get().y);
                }
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            }
            m = new Message(-30);
            m.writer().writeByte(-98);
            m.writer().writeInt(this.c.get().id);
            m.writer().writeByte(eff.template.id);
            m.writer().writeInt(eff.timeStart);
            m.writer().writeInt((int) (eff.timeRemove - System.currentTimeMillis()));
            m.writer().writeShort(eff.param);
            if (eff.template.type == 2 || eff.template.type == 3 || eff.template.type == 14) {
                m.writer().writeShort(this.c.get().x);
                m.writer().writeShort(this.c.get().y);
            }
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMessage(m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMessage(m);
            }
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    private void setEffectMessage(Effect eff) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-100);
                m.writer().writeByte(eff.template.id);
                m.writer().writeInt(eff.timeStart);
                m.writer().writeInt(eff.timeLength);
                m.writer().writeShort(eff.param);
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            }
            m = new Message(-30);
            m.writer().writeByte(-97);
            m.writer().writeInt(this.c.get().id);
            m.writer().writeByte(eff.template.id);
            m.writer().writeInt(eff.timeStart);
            m.writer().writeInt(eff.timeLength);
            m.writer().writeShort(eff.param);
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMessage(m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMessage(m);
            }
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void removeEffect(int id) {
        try {
            byte i;
            Effect eff;
            for (i = 0; i < this.c.get().veff.size(); ++i) {
                eff = (Effect) this.c.get().veff.get(i);
                if (eff != null && eff.template.id == id) {
                    this.c.get().veff.remove(i);
                    this.removeEffectMessage(eff);
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void removeAllEffect() {
        try {
            byte i;
            Effect eff;
            for (i = 0; i < this.c.get().veff.size(); ++i) {
                eff = (Effect) this.c.get().veff.get(i);
                if (eff != null) {
                    this.c.get().veff.remove(i);
                    this.removeEffectMessage(eff);
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private void removeEffectMessage(Effect eff) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-99);
                m.writer().writeByte(eff.template.id);
                if (eff.template.type != 0 && eff.template.type != 12) {
                    if (eff.template.type != 4 && eff.template.type != 13 && eff.template.type != 17) {
                        if (eff.template.type == 23) {
                            m.writer().writeInt(this.c.get().hp);
                            m.writer().writeInt(this.c.get().getMaxHP());
                        }
                    } else {
                        m.writer().writeInt(this.c.get().hp);
                    }
                } else {
                    m.writer().writeInt(this.c.get().hp);
                    m.writer().writeInt(this.c.get().mp);
                }
                m.writer().flush();
                this.conn.sendMessage(m);
                m.writer().flush();
                m.cleanup();
            }
            m = new Message(-30);
            m.writer().writeByte(-96);
            m.writer().writeInt(this.c.get().id);
            m.writer().writeByte(eff.template.id);
            if (eff.template.type != 0 && eff.template.type != 12) {
                if (eff.template.type == 11) {
                    m.writer().writeShort(this.c.get().x);
                    m.writer().writeShort(this.c.get().y);
                } else if (eff.template.type != 4 && eff.template.type != 13 && eff.template.type != 17) {
                    if (eff.template.type == 23) {
                        m.writer().writeInt(this.c.get().hp);
                        m.writer().writeInt(this.c.get().getMaxHP());
                    }
                } else {
                    m.writer().writeInt(this.c.get().hp);
                }
            } else {
                m.writer().writeInt(this.c.get().hp);
                m.writer().writeInt(this.c.get().mp);
            }
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMessage(m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMessage(m);
            }

            m.cleanup();
        } catch (Exception var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public boolean updateSysMounts() {
        Item item = this.c.get().ItemMounts[4];
        if (item == null) {
            this.c.p.sendAddchatYellow("Bạn cần phải có thú cưỡi mới có thể sử dụng vật phẩm");
            return false;
        } else if (item.upgrade < 99) {
            this.c.p.sendAddchatYellow("Thú cưỡi chưa đạt cấp tối đa");
            return false;
        } else if (item.sys >= 4) {
            this.c.p.sendAddchatYellow("Không thể nâng thêm sao");
            return false;
        } else {
            if (20 / (item.sys + 1) > Util.nextInt(130)) {
                item.sys++;
                item.upgrade = 0;
                byte i;
                Option op;
                for (i = 0; i < item.options.size(); ++i) {
                    op = item.options.get(i);
                    if (op.id == 65) {
                        op.param = 0;
                    } else if (op.id != 66) {
                        byte j;
                        for (j = 0; j < UseItem.arrOp.length; ++j) {
                            if (UseItem.arrOp[j] == op.id) {
                                op.param -= UseItem.arrParam[j] * 8;
                                break;
                            }
                        }
                    }
                }
                this.loadMounts();
                this.c.p.sendAddchatYellow("Nâng cấp thành công, thú cưỡi tăng 1 sao");
            } else {
                this.c.p.sendAddchatYellow("Nâng cấp thất bại, hao phí 1 chuyển tinh thạch");
            }
            return true;
        }
    }

    public boolean updateXpMounts(int xpup, byte type) {
        Item item = this.c.get().ItemMounts[4];
        if (item == null) {
            this.c.p.sendAddchatYellow("Bạn cần phải có thú cưỡi mới có thể sử dụng vật phẩm");
            return false;
        } else if (item.isExpires) {
            this.c.p.sendAddchatYellow("Bạn cần phải có thú cưỡi vĩnh viễn");
            return false;
        } else if (type == 0 && item.id != 443 && item.id != 524) {
            this.c.p.sendAddchatYellow("Chỉ sử dụng cho thú cưỡi");
            return false;
        } else if (type == 1 && item.id != 485 && item.id != 524) {
            this.c.p.sendAddchatYellow("Chỉ sử dụng cho xe máy");
            return false;
        } else if (type == 2 && item.id != 776 && item.id != 831 && item.id != 777 && item.id != 798
                && item.id != 828) {
            this.c.p.sendAddchatYellow("Chỉ sử dụng cho trâu + bạch hổ + lân + phượng hoàng băng");
            return false;
        } else if (item.upgrade >= 99) {
            this.c.p.sendAddchatYellow("Thú cưỡi đã đạt cấp tối đa");
            return false;
        } else {
            boolean isuplv = false;

            byte i;
            Option op;
            for (i = 0; i < item.options.size(); ++i) {
                op = (Option) item.options.get(i);
                if (op.id == 65) {
                    op.param += xpup;
                    if (op.param >= 1000) {
                        isuplv = true;
                        op.param = 0;
                    }
                    break;
                }
            }

            if (isuplv) {
                item.upgrade++;
                int lv = item.upgrade + 1;
                if (lv == 10 || lv == 20 || lv == 30 || lv == 40 || lv == 50 || lv == 60 || lv == 70 || lv == 80
                        || lv == 90) {
                    byte j;
                    Option op2;
                    for (j = 0; j < item.options.size(); ++j) {
                        op2 = (Option) item.options.get(j);
                        if (op2.id != 65 && op2.id != 66) {
                            byte k;
                            for (k = 0; k < UseItem.arrOp.length; ++k) {
                                if (UseItem.arrOp[k] == op2.id) {
                                    op2.param += UseItem.arrParam[k];
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            this.loadMounts();
            return true;
        }
    }

    public void loadMounts() {
        Message m = null;
        try {
            m = new Message(-30);
            m.writer().writeByte(-54);
            m.writer().writeInt(this.c.get().id);
            byte i;
            Item item;
            for (i = 0; i < this.c.get().ItemMounts.length; ++i) {
                item = this.c.get().ItemMounts[i];
                if (item != null) {
                    m.writer().writeShort(item.id);
                    m.writer().writeByte(item.upgrade);
                    m.writer().writeLong(item.expires);
                    m.writer().writeByte(item.sys);
                    m.writer().writeByte(item.options.size());

                    byte j;
                    for (j = 0; j < item.options.size(); ++j) {
                        m.writer().writeByte(((Option) item.options.get(j)).id);
                        m.writer().writeInt(((Option) item.options.get(j)).param);
                    }
                } else {
                    m.writer().writeShort(-1);
                }
            }
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMessage(m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMessage(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public boolean dungThucan(byte id, int param, int thoigian) {
        Effect eff = this.c.get().getEffType((byte) 0);
        if (this.c.get().pk > 14) {
            this.sendAddchatYellow(Language.MAX_HIEU_CHIEN);
            return false;
        } else if (eff != null && (eff.param > param || eff.template.id == 36)) {
            this.sendAddchatYellow("Đã có hiệu quả thức ăn cao hơn");
            return false;
        } else {
            this.setEffect(id, 0, 1000 * thoigian, param);
            return true;
        }
    }

    public boolean buffHP(int param) {
        Effect eff = this.c.get().getEffType((byte) 17);
        if (eff != null) {
            return false;
        } else if (this.c.get().pk > 14) {
            this.sendAddchatYellow(Language.MAX_HIEU_CHIEN);
            return false;
        } else if (this.c.get().hp >= this.c.get().getMaxHP()) {
            this.sendAddchatYellow("HP đã đầy");
            return false;
        } else {
            this.setEffect(21, 0, 3000, param);
            return true;
        }
    }

    public boolean buffMP(int param) {
        if (this.c.get().pk > 14) {
            this.sendAddchatYellow(Language.MAX_HIEU_CHIEN);
            return false;
        } else if (this.c.get().mp >= this.c.get().getMaxMP()) {
            this.sendAddchatYellow("MP đã đầy");
            this.getMp();
            return false;
        } else {
            this.c.get().upMP(param);
            this.getMp();
            return true;
        }
    }

    public void mobMeMessage(int id, byte boss) {
        Message m = null;
        try {
            if (id > 0) {
                Mob mob = new Mob(-1, id, 0, this.c.tileMap);
                mob.sys = 1;
                mob.status = 5;
                int n = 0;
                mob.hpmax = n;
                mob.hp = n;
                mob.isboss = boss != 0;
                this.c.get().mobMe = mob;
            } else {
                this.c.get().mobMe = null;
            }
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-69);
                if (this.conn.version > 200) {
                    m.writer().writeShort(id);
                } else {
                    m.writer().writeByte(id);
                }
                m.writer().writeByte(boss);
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            }
            m = new Message(-30);
            m.writer().writeByte(-68);
            m.writer().writeInt(this.c.get().id);
            // if (this.conn != null) {
            // if (this.conn.version > 200) {
            // m.writer().writeShort(id);
            // } else {
            m.writer().writeByte(id);
            // }
            // } else {
            // m.writer().writeByte(id);
            // }
            m.writer().writeByte(boss);
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMyMessage(this, m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMyMessage(this, m);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void mobMeCloneMessage(int id, byte boss) {
        Message m = null;
        try {
            if (id > 0) {
                Mob mob = new Mob(-1, id, 0, this.c.tileMap);
                mob.sys = 1;
                mob.status = 5;
                int n = 0;
                mob.hpmax = n;
                mob.hp = n;
                mob.isboss = boss != 0;
                this.c.clone.mobMe = mob;
            } else {
                this.c.clone.mobMe = null;
            }
            m = new Message(-30);
            m.writer().writeByte(-68);
            m.writer().writeInt(this.c.clone.id);
            // if (this.conn != null) {
            // if (this.conn.version > 200) {
            // m.writer().writeShort(id);
            // } else {
            m.writer().writeByte(id);
            // }
            // } else {
            // m.writer().writeByte(id);
            // }
            m.writer().writeByte(boss);
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMyMessage(this, m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMyMessage(this, m);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void setTimeMap(int timeLength) {
        Message m = null;
        try {
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-95);
                m.writer().writeInt(timeLength);
                m.writer().flush();
                this.conn.sendMessage(m);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void setPointPB(int point) {
        Message m = null;
        try {
            m = new Message(-28);
            m.writer().writeByte(-84);
            m.writer().writeShort(point);
            m.writer().flush();
            this.conn.sendMessage(m);

        } catch (IOException var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void restCave() {
        Message m = null;
        try {
            m = new Message(-16);
            m.writer().flush();
            this.conn.sendMessage(m);
        } catch (IOException var2) {
            var2.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void getMobMe() {
        if (this.c.get().ItemBody[10] != null) {
            switch (this.c.get().ItemBody[10].id) {
                case 246:
                    this.mobMeMessage(70, (byte) 0);
                    break;
                case 419:
                    this.mobMeMessage(122, (byte) 0);
                    break;
                case 840:
                    this.mobMeMessage(97, (byte) 0);
                    break;
                case 568:
                    this.mobMeMessage(205, (byte) 0);
                    break;
                case 569:
                    this.mobMeMessage(206, (byte) 0);
                    break;
                case 570:
                    this.mobMeMessage(207, (byte) 0);
                    break;
                case 571:
                    this.mobMeMessage(208, (byte) 0);
                    break;
                case 583:
                    this.mobMeMessage(211, (byte) 1);
                    break;
                case 584:
                    this.mobMeMessage(212, (byte) 1);
                    break;
                case 585:
                    this.mobMeMessage(213, (byte) 1);
                    break;
                case 586:
                    this.mobMeMessage(214, (byte) 1);
                    break;
                case 587:
                    this.mobMeMessage(215, (byte) 1);
                    break;
                case 588:
                    this.mobMeMessage(216, (byte) 1);
                    break;
                case 589:
                    this.mobMeMessage(217, (byte) 1);
                    break;
                case 742:
                    this.mobMeMessage(229, (byte) 1);
                    break;
                case 744:
                    this.mobMeMessage(229, (byte) 1);
                    break;
                case 773:
                    this.mobMeMessage(234, (byte) 1);
                    break;
                case 781:
                    this.mobMeMessage(235, (byte) 1);
                    break;
                case 832:
                    this.mobMeMessage(238, (byte) 1);
                    break;
                case 847:
                    this.mobMeMessage(240, (byte) 1);
                    break;
                case 848:
                    this.mobMeMessage(239, (byte) 1);
                    break;
                case 849:
                    this.mobMeMessage(241, (byte) 1);
                    break;
                case 850:
                    this.mobMeMessage(250, (byte) 1);
                    break;
                case 851:
                    this.mobMeMessage(251, (byte) 1);
                    break;
                case 852:
                    this.mobMeMessage(243, (byte) 1);
                    break;
                case 853:
                    this.mobMeMessage(244, (byte) 1);
                    break;
                case 854:
                    this.mobMeMessage(245, (byte) 1);
                    break;
                case 855:
                    this.mobMeMessage(246, (byte) 1);
                    break;

            }
        } else {
            this.mobMeMessage(0, (byte) 0);
        }

    }

    public void getMobMeClone() {
        if (this.c.clone.ItemBody[10] != null) {
            switch (this.c.clone.ItemBody[10].id) {
                case 246:
                    this.mobMeCloneMessage(70, (byte) 0);
                    break;
                case 419:
                    this.mobMeCloneMessage(122, (byte) 0);
                    break;
                case 568:
                    this.mobMeCloneMessage(205, (byte) 0);
                    break;
                case 569:
                    this.mobMeCloneMessage(206, (byte) 0);
                    break;
                case 570:
                    this.mobMeCloneMessage(207, (byte) 0);
                    break;
                case 571:
                    this.mobMeCloneMessage(208, (byte) 0);
                    break;
                case 583:
                    this.mobMeCloneMessage(211, (byte) 1);
                    break;
                case 584:
                    this.mobMeCloneMessage(212, (byte) 1);
                    break;
                case 585:
                    this.mobMeCloneMessage(213, (byte) 1);
                    break;
                case 586:
                    this.mobMeCloneMessage(214, (byte) 1);
                    break;
                case 587:
                    this.mobMeCloneMessage(215, (byte) 1);
                    break;
                case 588:
                    this.mobMeCloneMessage(216, (byte) 1);
                    break;
                case 589:
                    this.mobMeCloneMessage(217, (byte) 1);
                    break;
                case 742:
                    this.mobMeCloneMessage(229, (byte) 1);
                    break;
                case 832:
                    this.mobMeMessage(238, (byte) 1);
                    break;
                case 773:
                    this.mobMeCloneMessage(234, (byte) 1);
                    break;
                case 781:
                    this.mobMeCloneMessage(235, (byte) 1);
                    break;
                case 847:
                    this.mobMeCloneMessage(240, (byte) 1);
                    break;
                case 848:
                    this.mobMeCloneMessage(239, (byte) 1);
                    break;
                case 849:
                    this.mobMeCloneMessage(241, (byte) 1);
                    break;
                case 850:
                    this.mobMeCloneMessage(250, (byte) 1);
                    break;
                case 851:
                    this.mobMeCloneMessage(251, (byte) 1);
                    break;
                case 852:
                    this.mobMeCloneMessage(243, (byte) 1);
                    break;
                case 853:
                    this.mobMeCloneMessage(244, (byte) 1);
                    break;
                case 854:
                    this.mobMeCloneMessage(245, (byte) 1);
                    break;
                case 855:
                    this.mobMeCloneMessage(246, (byte) 1);
                    break;
            }
        } else {
            this.mobMeCloneMessage(0, (byte) 0);
        }

    }

    public void toNhanBan() {
        try {
            if (!this.c.isNhanban) {
                if (this.c.party != null) {
                    HandleController.RoiNhom(this);
                }
                byte n;
                for (n = 0; n < this.c.get().veff.size(); ++n) {
                    if (this.c.get().veff.get(n) != null) {
                        this.removeEffectMessage(this.c.get().veff.get(n));
                    }
                }
                this.c.isNhanban = true;
                this.c.isHuman = false;
                this.c.clone.islive = true;
                this.c.clone.x = this.c.x;
                this.c.clone.y = this.c.y;
                this.c.tileMap.removeMessage(this.c.clone.id);
                this.c.tileMap.removeMessage(this.c.id);
                Service.CharViewInfo(this);
                GameSrc.sendSkill(this, "KSkill");
                GameSrc.sendSkill(this, "OSkill");
                GameSrc.sendSkill(this, "CSkill");
                this.c.get().x = this.c.clone.x;
                this.c.get().y = this.c.clone.y;
                int i;
                // vĩ thú
                this.onBijuuInfo(this.c.get().id, this.c.get().bijuu);
                Player player;
                for (i = this.c.tileMap.players.size() - 1; i >= 0; --i) {
                    player = this.c.tileMap.players.get(i);
                    if (player != null) {
                        if (player.id != this.id) {
                            this.c.tileMap.sendCharInfo(this, player);
                            this.c.tileMap.sendCoat(this.c.get(), player);
                            this.c.tileMap.sendGlove(this.c.get(), player);
                        }
                        this.c.tileMap.sendMounts(this.c.get(), player);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void exitNhanBan(boolean islive) {
        try {
            if (this.c.isNhanban) {
                if (this.c.clone != null) {
                    this.c.clone.flush();
                }
                if (this.c.clone.party != null) {
                    HandleController.RoiNhom(this);
                }
                for (byte n = 0; n < this.c.get().veff.size(); ++n) {
                    if (this.c.get().veff.get(n) != null) {
                        this.removeEffectMessage(this.c.get().veff.get(n));
                    }
                }
                this.c.isNhanban = false;
                this.c.isHuman = true;
                this.c.clone.islive = islive;
                this.c.x = this.c.clone.x;
                this.c.y = this.c.clone.y;
                this.c.clone.refresh();
                this.c.tileMap.removeMessage(this.c.clone.id);
                Service.CharViewInfo(this);
                GameSrc.sendSkill(this, "KSkill");
                GameSrc.sendSkill(this, "OSkill");
                GameSrc.sendSkill(this, "CSkill");
                Player player;
                int i;
                for (i = this.c.tileMap.players.size() - 1; i >= 0; i--) {
                    player = this.c.tileMap.players.get(i);
                    if (player != null) {
                        if (player.id != this.id) {
                            this.c.tileMap.sendCharInfo(this, player);
                            this.c.tileMap.sendCoat(this.c.get(), player);
                            this.c.tileMap.sendGlove(this.c.get(), player);
                        }
                        this.c.tileMap.sendMounts(this.c.get(), player);
                        if (islive) {
                            Service.sendclonechar(this.c.p, player);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMap(int id) {
        Map ma = Manager.getMapid(id);
        TileMap[] var3 = ma.area;
        int var4 = var3.length;
        TileMap area;
        int var5;
        for (var5 = 0; var5 < var4; ++var5) {
            area = var3[var5];
            if (area.numplayers < ma.template.maxplayers) {
                this.c.tileMap.leave(this);
                area.EnterMap0(this.c);
                return;
            }
        }

    }

    public void sendRequestBattleToAnother(Char friendNinja, Char _char) {
        Message m = null;
        try {
            m = new Message(-157);
            m.writer().writeInt(_char.id);
            friendNinja.p.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void acceptClanDun(Message m) {
        try {
            String name = m.reader().readUTF();
            Char _char = Client.gI().getNinja(name);
            if (_char != null && _char.tileMap != null && !_char.tileMap.map.mapGTC()
                    && _char.tileMap.map.getXHD() == -1 && !_char.tileMap.map.mapChienTruong()
                    && !_char.tileMap.map.mapBossTuanLoc() && !_char.isInDun) {
                Service.startYesNoDlg(_char.p, (byte) 4,
                        this.c.name + " mời bạn vào Gia Tộc Chiến. Bạn có muốn tham gia?");
            } else {
                this.sendAddchatYellow("Người chơi đang ở vị trí không thể mời vào GTC.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accpetDun(Message m) throws IOException {
        if (m.reader().available() > 0) {

        }
    }

    public void CuuSat(Message msg) throws IOException {
        if (msg.reader().available() > 0) {

        }
    }

    public void inviteToLDT(Message m) {
        try {
            String name = m.reader().readUTF();
            Char _char = Client.gI().getNinja(name);
            if (_char != null && _char.tileMap != null && !_char.tileMap.map.mapLDGT()
                    && _char.tileMap.map.getXHD() == -1 && !_char.tileMap.map.mapChienTruong()
                    && !_char.tileMap.map.mapBossTuanLoc() && !_char.isInDun) {
                Service.startYesNoDlg(_char.p, (byte) 3,
                        this.c.name + " mời bạn vào Lãnh Địa Gia Tộc. Bạn có muốn tham gia?");
            } else {
                this.sendAddchatYellow("Người chơi đang ở vị trí không thể mời vào LDGT.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void sendInfoMeNewItem() {
        Service.CharViewInfo(this, false);
        int i;
        if (this.c.tileMap != null) {
            for (i = this.c.tileMap.players.size() - 1; i >= 0; i--) {
                if (this.c.tileMap.players.get(i) != null) {
                    this.c.tileMap.sendMounts(this.c.get(), this.c.tileMap.players.get(i));
                    if (this.id != this.c.tileMap.players.get(i).id) {
                        Service.sendInfoChar(this, this.c.tileMap.players.get(i));
                    }
                }
            }
        } else if (this.c.tdbTileMap != null) {
            for (i = this.c.tdbTileMap.players.size() - 1; i >= 0; i--) {
                if (this.c.tdbTileMap.players.get(i) != null) {
                    this.c.tdbTileMap.sendMounts(this.c.get(), this.c.tdbTileMap.players.get(i));
                }
            }
        }
    }

    public void lockAcc() throws SQLException {
        SQLManager.stat.executeUpdate("UPDATE `player` SET `ban`=1 WHERE `id`=" + this.id + " limit 1;");
        Client.gI().kickSession(this.conn);
    }

    // vĩ thú
    public void onBijuuInfo(int playerID, Item[] arrItemViThu) {
        try {
            Message ms = messageBijuu(0);
            DataOutputStream ds = ms.writer();
            ds.writeInt(playerID);
            for (Item item : arrItemViThu) {
                if (item != null) {
                    ds.writeShort(item.id);
                    ds.writeByte(item.upgrade);
                    ds.writeLong(item.expires);
                    ds.writeByte(item.sys);
                    List<Option> options = item.getDisplayOptions();
                    ds.writeByte(options.size());
                    for (Option o : options) {
                        ds.writeByte(o.id);
                        ds.writeInt(o.param);
                    }
                } else {
                    ds.writeShort(-1);
                }
            }
            ds.flush();
            this.conn.sendMessage(ms);
            ms.cleanup();
        } catch (IOException ex) {

        }
    }

    public void onChangeBijuu(int playerID, Mob mob) {
        try {
            Message ms = messageBijuu(1);
            DataOutputStream ds = ms.writer();
            ds.writeInt(playerID);
            if (mob != null) {
                ds.writeShort(mob.templates.id);
                ds.writeBoolean(mob.isboss);
            } else {
                ds.writeShort(0);
                ds.writeBoolean(false);
            }
            ds.flush();
            if (conn != null) {
                conn.sendMessage(ms);
            }
            ms.cleanup();
        } catch (IOException ex) {
            System.out.print("change bijuu err");
            ex.printStackTrace();
        }
    }

    public Message messageBijuu(int command) {
        try {
            Message ms = new Message(117);
            DataOutputStream ds = ms.writer();
            ds.writeByte(-1);
            ds.writeByte(command);
            return ms;
        } catch (Exception ex) {
            System.out.print("write message sub comnand err");
            ex.printStackTrace();
        }
        return null;
    }

    public void setBijuu() {
        if (this.c.get().bijuu[4] != null) {
            switch (this.c.get().bijuu[4].id) {
                // Nhat vĩ
                case 1070:
                case 1079:
                case 1088:
                case 1097:
                case 1106:
                case 1115:
                    this.c.get().mobBijuu = new Mob((short) -1, 247, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(247, (byte) 1);
                    break;
                case 1124:
                case 1133:
                    this.c.get().mobBijuu = new Mob((short) -1, 240, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(240, (byte) 1);
                    break;
                // Nhị vĩ
                case 1071:
                case 1080:
                case 1089:
                case 1098:
                case 1107:
                case 1116:
                    this.c.get().mobBijuu = new Mob((short) -1, 248, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(248, (byte) 1);
                    break;
                case 1125:
                case 1134:
                    this.c.get().mobBijuu = new Mob((short) -1, 239, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(239, (byte) 1);
                    break;
                // Tam vĩ
                case 1072:
                case 1081:
                case 1090:
                case 1099:
                case 1108:
                case 1117:
                    this.c.get().mobBijuu = new Mob((short) -1, 249, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(249, (byte) 1);
                    break;
                case 1126:
                case 1135:
                    this.c.get().mobBijuu = new Mob((short) -1, 241, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(241, (byte) 1);
                    break;
                // Tứ vĩ
                case 1073:
                case 1082:
                case 1091:
                case 1100:
                case 1109:
                case 1118:
                    this.c.get().mobBijuu = new Mob((short) -1, 250, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(250, (byte) 1);
                    break;
                case 1127:
                case 1136:
                    this.c.get().mobBijuu = new Mob((short) -1, 242, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(242, (byte) 1);
                    break;
                // Ngủ vĩ
                case 1074:
                case 1083:
                case 1092:
                case 1101:
                case 1110:
                case 1119:
                    this.c.get().mobBijuu = new Mob((short) -1, 251, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(251, (byte) 1);
                    break;
                case 1128:
                case 1137:
                    this.c.get().mobBijuu = new Mob((short) -1, 236, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(236, (byte) 1);
                    break;
                // Lục vĩ
                case 1075:
                case 1084:
                case 1093:
                case 1102:
                case 1111:
                case 1120:
                    this.c.get().mobBijuu = new Mob((short) -1, 252, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(252, (byte) 1);
                    break;
                case 1129:
                case 1138:
                    this.c.get().mobBijuu = new Mob((short) -1, 243, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(243, (byte) 1);
                    break;
                // Thất vĩ
                case 1076:
                case 1085:
                case 1094:
                case 1103:
                case 1112:
                case 1121:
                    this.c.get().mobBijuu = new Mob((short) -1, 253, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(253, (byte) 1);
                    break;
                case 1130:
                case 1139:
                    this.c.get().mobBijuu = new Mob((short) -1, 244, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(244, (byte) 1);
                    break;
                // Bát vĩ
                case 1077:
                case 1086:
                case 1095:
                case 1104:
                case 1113:
                case 1122:
                    this.c.get().mobBijuu = new Mob((short) -1, 254, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(254, (byte) 1);
                    break;
                case 1131:
                case 1140:
                    this.c.get().mobBijuu = new Mob((short) -1, 245, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(245, (byte) 1);
                    break;
                // Cứu vĩ
                case 1078:
                case 1087:
                case 1096:
                case 1105:
                case 1114:
                case 1123:
                    this.c.get().mobBijuu = new Mob((short) -1, 246, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(246, (byte) 1);
                    break;
                case 1132:
                case 1141:
                    this.c.get().mobBijuu = new Mob((short) -1, 246, 0, this.c.tileMap);
                    this.mobbijuuMeMessage(246, (byte) 1);
                    break;
                default:
                    this.c.get().mobBijuu = null;
                    break;
            }
        } else {
            this.c.get().mobBijuu = null;
        }
        if (this.c != null) {
            this.onChangeBijuu(this.c.get().id, this.c.get().mobBijuu);
        }
    }

    public void mobbijuuMeMessage(int id, byte boss) {
        Message m = null;
        try {
            if (id > 0) {
                Mob mob = new Mob(-1, id, 0, this.c.tileMap);
                mob.sys = 1;
                mob.status = 5;
                int n = 0;
                mob.hpmax = n;
                mob.hp = n;
                mob.isboss = boss != 0;
                this.c.get().mobBijuu = mob;
            } else {
                this.c.get().mobBijuu = null;
            }
            if (this.conn != null) {
                m = new Message(-30);
                m.writer().writeByte(-69);
                if (this.conn.version > 200) {
                    m.writer().writeShort(id);
                } else {
                    m.writer().writeByte(id);
                }
                m.writer().writeByte(boss);
                m.writer().flush();
                this.conn.sendMessage(m);
                m.cleanup();
            }
            m = new Message(-30);
            m.writer().writeByte(-68);
            m.writer().writeInt(this.c.get().id);
            if (this.conn != null) {
                if (this.conn.version > 200) {
                    m.writer().writeShort(id);
                } else {
                    m.writer().writeByte(id);
                }
            } else {
                m.writer().writeByte(id);
            }
            m.writer().writeByte(boss);
            m.writer().flush();
            if (this.c.tileMap != null) {
                this.c.tileMap.sendMyMessage(this, m);
            } else if (this.c.tdbTileMap != null) {
                this.c.tdbTileMap.sendMyMessage(this, m);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public boolean addExpForVT(int exp) {
        Item bijuu = this.c.get().bijuu[4];
        if (bijuu == null) {
            this.conn.sendMessageLog("Bạn cần có Vĩ Thú");
            return false;
        } else if (bijuu.isExpires) {
            this.conn.sendMessageLog("Bạn cần có Vĩ Thú vính viến");
            return false;
        } else if (bijuu.upgrade < 100) {
            Option opExp = null;
            for (byte i = 0; i < bijuu.options.size(); i++) {
                Option op = bijuu.options.get(i);
                if (op.id == 151) {
                    opExp = op;
                    break;
                }
            }
            if (opExp != null) {
                opExp.param += exp;
                while (opExp.param >= 1000) {
                    int num = opExp.param - 1000;
                    opExp.param = num;
                    bijuu.upgrade++;
                    int level = bijuu.upgrade + 1;
                    if (level == 10 || level == 20 || level == 30 || level == 40 || level == 50 || level == 60
                            || level == 70 || level == 80 || level == 90 || level == 100) {
                        for (byte i = 0; i < bijuu.options.size(); i++) {
                            Option op = bijuu.options.get(i);
                            if (op.id != 151 && op.id != 150) {
                                for (byte k = 0; k < ItemTemplate.VT_OPTION_ID.length; k++) {
                                    if (ItemTemplate.VT_OPTION_ID[k] == op.id) {
                                        op.param += ItemTemplate.VT_OPTION_PARAM[k];
                                        break;
                                    }
                                }
                            }
                        }
                        Service.CharViewInfo(this, false);
                    }
                }
            }
            this.onBijuuInfo(this.c.get().id, this.c.get().bijuu);
        } else {
            this.c.p.sendAddchatYellow("vĩ thú đã đạt cấp tối đa");
            return false;
        }

        return true;
    }

    public boolean upgradeVT() {
        Item bijuu = this.c.get().bijuu[4];
        if (bijuu == null) {
            this.conn.sendMessageLog("Không có vĩ thú để tăng sao");
            return false;
        } else if (bijuu.isExpires) {
            this.conn.sendMessageLog("Bạn cần có Vĩ Thú vính viến");
            return false;
        } else if (bijuu.upgrade < 100) {
            this.conn.sendMessageLog("Thú cưới chưa đạt cấp độ tối đa");
            return false;
        } else if (bijuu.sys < 4) {
            if (30 / (bijuu.sys + 1) > Util.nextInt(100)) {
                bijuu.sys++;
                bijuu.upgrade = 0;
                for (byte i = 0; i < bijuu.options.size(); i++) {
                    Option op = bijuu.options.get(i);
                    if (op.id == 151) {
                        op.param = 0;
                    } else if (op.id != 150) {
                        for (byte j = 0; j < ItemTemplate.VT_OPTION_ID.length; j++) {
                            if (ItemTemplate.VT_OPTION_ID[j] == op.id) {
                                op.param -= (ItemTemplate.VT_OPTION_PARAM[j] * 8);
                            }
                        }
                    }
                    int[] targetOptionIds = { 157, 158, 159, 160 };
                    for (int targetOptionId : targetOptionIds) {
                        if (op.id == targetOptionId) {
                            op.param += 20;
                            break;
                        }
                    }
                }
                Service.CharViewInfo(this, false);
                this.onBijuuInfo(this.c.get().id, this.c.get().bijuu);
                this.conn.sendMessageLog("Nâng cấp thành công, Vĩ Thú được tặng 1 sao");
            } else {
                this.conn.sendMessageLog("Nâng cấp thất bại");
            }
        } else {
            this.conn.sendMessageLog("Không thể nâng thêm sao");
            return false;
        }
        return true;
    }

    public boolean evolveBijuu() {
        Item bijuu = this.c.get().bijuu[4];
        if (bijuu == null) {
            this.conn.sendMessageLog("Không có vĩ thú để tiến hóa");
            return false;
        } else if (bijuu.isExpires) {
            this.conn.sendMessageLog("Bạn cần có Vĩ Thú vính viến");
            return false;
        } else if (bijuu.upgrade < 100 || bijuu.sys < 4) {
            this.conn.sendMessageLog("Vĩ thú cần đạt cấp 100 và 5 sao để tiến hóa");
            return false;
        }
        int successRate = 50;
        int randomValue = Util.nextInt(100) + 1;

        if (bijuu.id >= 1070 && bijuu.id <= 1123) {
            if (randomValue <= successRate) {
                int newId = bijuu.id + 18;
                bijuu.id = (short) newId;
                bijuu.upgrade = 0;
                bijuu.sys = 0;
                ItemTemplate item2 = ItemTemplate.ItemTemplateId(newId - 18);
                for (byte i = 0; i < bijuu.options.size(); i++) {
                    Option op = bijuu.options.get(i);
                    if (op.id == 151) {
                        op.param = 0;
                    } else if (op.id != 150) {
                        for (byte j = 0; j < ItemTemplate.VT_OPTION_ID.length; j++) {
                            if (ItemTemplate.VT_OPTION_ID[j] == op.id) {
                                op.param -= (ItemTemplate.VT_OPTION_PARAM[j] * 8);
                            }
                        }
                    }
                }
                Service.CharViewInfo(this, false);
                this.conn.sendMessageLog(
                        "Vĩ thú đã tiến hóa thành công từ " + item2.name + " sang " + bijuu.getData().name);
                this.onBijuuInfo(this.c.get().id, this.c.get().bijuu);
            } else {
                this.conn.sendMessageLog("Tiến hóa thất bại");
            }
        } else {
            this.conn.sendMessageLog("Vĩ thú này không thể tiến hóa");
            return false;
        }
        return true;
    }
}
