package thiendiabang;

import Item.ItemName;
import assembly.Item;
import assembly.TileMap;
import assembly.Map;
import assembly.Option;
import assembly.Body;
import assembly.Effect;
import assembly.Party;
import assembly.Npc;
import assembly.PartyPlease;
import assembly.Level;
import assembly.BuNhin;
import assembly.Char;
import assembly.ItemMap;
import assembly.Player;
import assembly.UseSkill;
import assembly.Skill;
import io.Message;
import io.Util;
import server.GameCanvas;
import server.GameSrc;
import server.Manager;
import server.Service;
import stream.ChienTruong;
import stream.Client;
import stream.Server;
import template.ItemTemplate;
import template.SkillOptionTemplate;
import template.SkillTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ThienDiaBangTileMap {

    public ThienDiaBangMap map;
    public byte id;
    public byte numplayers = 0;
    public int numParty = 0;
    public Char ninjaBot;
    public Char ninjaReal;
    public ArrayList<Player> players = new ArrayList();
    public ArrayList<ItemMap> itemMap = new ArrayList();
    public ArrayList<Party> aParty = new ArrayList();
    public ArrayList<BuNhin> buNhins = new ArrayList();
    public Object LOCK = new Object();
    public long Delay = System.currentTimeMillis() + Util.nextInt(11000, 15000);
    public boolean isUseSkill = false;
    private short MOVE_LIMIT = 80;
    private short RESET_LIMIT = 90;

    public ThienDiaBangTileMap(ThienDiaBangMap map, byte id, Char ninjaReal, Char ninjaBot) {
        this.map = map;
        this.id = id;
        this.ninjaBot = ninjaBot;
        this.ninjaReal = ninjaReal;
    }

    public void sendMessage(Message m) {
        try {
            if (this.ninjaReal != null && this.ninjaReal.p != null && this.ninjaReal.p.conn != null) {
                this.ninjaReal.p.conn.sendMessage(m);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public Char getNinja(int id) {
        synchronized (this) {
            int i;
            for (i = this.players.size() - 1; i >= 0; i--) {
                if (this.players.get(i) != null && this.players.get(i).c != null && (this.players.get(i)).c.id == id) {
                    return (this.players.get(i)).c;
                }
            }
            return null;
        }
    }

    public short getItemMapNotId() {
        short itemmapid = 0;
        while (true) {
            boolean isset = false;
            for (int i = this.itemMap.size() - 1; i >= 0; --i) {
                if (this.itemMap.get(i) != null && (this.itemMap.get(i)).itemMapId == itemmapid) {
                    isset = true;
                }
            }
            if (!isset) {
                return itemmapid;
            }
            itemmapid++;
        }
    }

    public void leave(Player p) {
        synchronized (this) {
            if (p.c.buNhin != null) {
                p.c.buNhin = null;
            }
            if (this.ninjaReal != null && this.map.thienDiaBang != null) {
                this.ninjaReal = null;
                if (!this.map.thienDiaBang.rest) {
                    this.map.thienDiaBang.ninjaReal = null;
                    this.map.thienDiaBang.rest();
                }
            }
            if (this.players.contains(p)) {
                this.players.remove(p);
                this.removeMessage(p.c.id);
                if (p.c.clone != null) {
                    this.removeMessage(p.c.clone.id);
                }
                this.numplayers--;
                if (p.c.party != null && p.c.party.charID == p.c.id && this.aParty.contains(p.c.party)) {
                    this.numParty--;
                    this.aParty.remove(p.c.party);
                }
            }
        }
    }

    public void sendCharInfo(Player p2) {
        if (p2 != null && p2.c != null && p2.conn != null) {
            Message m = null;
            try {
                m = new Message(3);
                m.writer().writeInt(ninjaBot.get().id);
                m.writer().writeUTF(ninjaBot.clan.clanName);
                if (!ninjaBot.clan.clanName.isEmpty()) {
                    m.writer().writeByte(ninjaBot.clan.typeclan);
                }
                m.writer().writeBoolean(false);
                m.writer().writeByte(ninjaBot.get().typepk);
                m.writer().writeByte(ninjaBot.get().nclass);
                m.writer().writeByte(ninjaBot.gender);
                m.writer().writeShort(ninjaBot.get().partHead());
                m.writer().writeUTF(ninjaBot.name);
                m.writer().writeInt(ninjaBot.get().hp);
                m.writer().writeInt(ninjaBot.get().getMaxHP());
                m.writer().writeByte(ninjaBot.get().level);
                m.writer().writeShort(ninjaBot.get().Weapon());
                m.writer().writeShort(ninjaBot.get().Body());
                m.writer().writeShort(ninjaBot.get().Leg());
                m.writer().writeByte(-1);
                m.writer().writeShort(ninjaBot.get().x);
                m.writer().writeShort(ninjaBot.get().y);
                m.writer().writeShort(ninjaBot.get().eff5buffHP());
                m.writer().writeShort(ninjaBot.get().eff5buffMP());
                m.writer().writeByte(0);
                m.writer().writeBoolean(ninjaBot.isHuman);
                m.writer().writeBoolean(ninjaBot.isNhanban);
                m.writer().writeShort(ninjaBot.get().partHead());
                m.writer().writeShort(ninjaBot.get().Weapon());
                m.writer().writeShort(ninjaBot.get().Body());
                m.writer().writeShort(ninjaBot.get().Leg());

                m.writer().writeShort(ninjaBot.get().ID_HAIR);
                m.writer().writeShort(ninjaBot.get().ID_Body);
                m.writer().writeShort(ninjaBot.get().ID_LEG);
                m.writer().writeShort(ninjaBot.get().ID_WEA_PONE);
                m.writer().writeShort(ninjaBot.get().ID_PP);
                m.writer().writeShort(ninjaBot.get().ID_NAME);
                m.writer().writeShort(ninjaBot.get().ID_HORSE);
                m.writer().writeShort(ninjaBot.get().ID_RANK);
                m.writer().writeShort(ninjaBot.get().ID_MAT_NA);
                m.writer().writeShort(ninjaBot.get().ID_Bien_Hinh);

                m.writer().flush();
                p2.conn.sendMessage(m);
                m.cleanup();

                if (ninjaBot.get().mobMe != null) {
                    m = new Message(-30);
                    m.writer().writeByte(-68);
                    m.writer().writeInt(ninjaBot.get().id);
                    m.writer().writeByte(ninjaBot.get().mobMe.templates.id);
                    m.writer().writeByte(ninjaBot.get().mobMe.isboss ? 1 : 0);
                    m.writer().flush();
                    p2.conn.sendMessage(m);
                    m.cleanup();
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            } finally {
                if (m != null) {
                    m.cleanup();
                }
            }
        }

    }

    public void removeMessage(int id) {
        Message m = null;
        try {
            m = new Message(2);
            m.writer().writeInt(id);
            m.writer().flush();
            this.sendMessage(m);
        } catch (IOException var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void handleAfterAttackNinja(Player p, Char[] arNinja) {
        int i;
        Char fightChar;
        for (i = 0; i < arNinja.length; ++i) {
            fightChar = arNinja[i];
            if (fightChar != null) {
                if (fightChar.get().percentIce() >= Util.nextInt(1, 100)) {
                    p.removeEffect(15);
                    p.removeEffect(16);
                    if (p.c.getEffId(20) != null) {
                        if (p.c.get().nclass == 6) {
                            p.setEffect(6, 0, -1, 0);
                        } else {
                            p.setEffect(6, 0, fightChar.get().timeIce() - 1000 - p.c.get().getPramSkill(38) * 100, 0);
                        }
                    } else {
                        p.setEffect(6, 0, fightChar.get().timeIce() - p.c.get().getPramSkill(38) * 100, 0);
                    }
                } else {
                    int damegoc = Util.nextInt((int) p.c.get().dameMin(), (int) p.c.get().dameMax());
                    int dame = Util.nextInt(damegoc * 9 / 10, damegoc) / 5;
                    switch (fightChar.c.get().Sys()) {
                        case 1: {
                            dame += dame * p.c.get().getPramSkill(54) / 100;
                            break;
                        }
                        case 2: {
                            dame += dame * p.c.get().getPramSkill(55) / 100;
                            break;
                        }
                        case 3: {
                            dame += dame * p.c.get().getPramSkill(56) / 100;
                            break;
                        }
                    }
                    dame += p.c.get().getPramSkill(103);
                    int oldhp;
                    switch (p.c.get().Sys()) {
                        case 1: {
                            oldhp = fightChar.c.get().ResFire() * 11 / 100;
                            oldhp += 795;
                            dame /= oldhp;
                            dame *= 100;
                            dame += p.c.get().getPramItem(51);
                            dame -= fightChar.c.get().getPramItem(48);
                            break;
                        }
                        case 2: {
                            oldhp = fightChar.c.get().ResIce() * 11 / 100;
                            oldhp += 795;
                            dame /= oldhp;
                            dame *= 100;
                            dame += p.c.get().getPramItem(52);
                            dame -= fightChar.c.get().getPramItem(49);
                            break;
                        }
                        case 3: {
                            oldhp = fightChar.c.get().ResWind() * 11 / 100;
                            oldhp += 795;
                            dame /= oldhp;
                            dame *= 100;
                            dame += p.c.get().getPramItem(53);
                            dame -= fightChar.c.get().getPramItem(50);
                            break;
                        }
                    }

                    dame -= fightChar.c.get().dameDown();
                    if (p.c.get().Fatal() > Util.nextInt(1500)) {
                        dame = (int) (dame * 2 + dame * (p.c.get().percentFantalDame() - fightChar.c.get().percentFantalDameDown()) / 100);
                        dame += p.c.get().FantalDame();
                    }
                    if (fightChar.c.get().getEffId(5) != null) {
                        dame *= 2;
                    }
                    oldhp = fightChar.hp;
                    if (dame <= 0) {
                        dame = 1;
                    }
                    //Miễn thương
                    int mienst = (int) fightChar.get().getPramItem(98);
                    boolean ismienst = mienst > Util.nextInt(1, 500);
                    if (ismienst) {
                        dame = 0;
                        fightChar.p.sendAddchatYellow("Miễn sát thương");
                    }
                    //né
                    int miss = fightChar.Exactly() * 15000 / (int) fightChar.Miss();
                    miss -= fightChar.get().getPramSkill(31) * 100;
                    if (miss < Util.nextInt(15000)) {
                        dame = 0;
                    } else {
                        if (p.c.percentFire2() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(5, 0, -1, 0);
                                } else {
                                    fightChar.p.setEffect(5, 0, -1, 0);
                                }
                            } else {
                                fightChar.p.setEffect(5, 0, 2000 - fightChar.c.get().getPramSkill(37) * 100, 0);
                            }
                        }
                        if (p.c.percentFire4() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(5, 0, 1000, 0);
                                } else {
                                    fightChar.p.setEffect(5, 0, 2000 - fightChar.c.get().getPramSkill(37) * 100, 0);
                                }
                            } else {
                                fightChar.p.setEffect(5, 0, 4000 - fightChar.c.get().getPramSkill(37) * 100, 0);
                            }
                        }
                        if (p.c.percentIce1_5() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(6, 0, -1, 0);
                                } else {
                                    fightChar.p.setEffect(6, 0, 500 - fightChar.c.get().getPramSkill(38) * 100, 0);
                                }
                            } else {
                                fightChar.p.setEffect(6, 0, 1500 - fightChar.c.get().getPramSkill(38) * 100, 0);
                            }
                        }
                        if (p.c.percentIce3() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(6, 0, 1000, 0);
                                } else {
                                    fightChar.p.setEffect(6, 0, 2000 - fightChar.c.get().getPramSkill(38) * 100, 0);
                                }
                            } else {
                                fightChar.p.setEffect(6, 0, 3000 - fightChar.c.get().getPramSkill(38) * 100, 0);
                            }
                        }
                        if (p.c.percentWind1() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(7, 0, -1, 0);
                                } else {
                                    fightChar.p.setEffect(7, 0, 500 - fightChar.c.get().getPramSkill(39) * 100, 0);
                                }
                            } else {
                                fightChar.p.setEffect(7, 0, 1000 - fightChar.c.get().getPramSkill(39) * 100, 0);
                            }
                        }
                        if (p.c.percentWind2() >= Util.nextInt(1, 100)) {
                            if (fightChar.c.getEffId(20) != null) {
                                if (fightChar.c.get().nclass == 6) {
                                    fightChar.p.setEffect(7, 0, 1000, 0);
                                } else {
                                    fightChar.p.setEffect(7, 0, 1500 - fightChar.c.get().getPramSkill(39) * 100, 0);
                                }
                            } else {
                                fightChar.p.setEffect(7, 0, 2000 - fightChar.c.get().getPramSkill(39) * 100, 0);
                            }
                        }
                    }
                    int j;
                    for (j = p.c.veff.size() - 1; j >= 0; --j) {
                        if ((p.c.veff.get(j)).template.type == 11) {
                            dame *= (p.c.get().getPramSkill(61) + 100) / 100;
                        }
                    }
                    int odhp = p.c.hp;
                    if (fightChar.c.get().ReactDame() > Util.nextInt(1000)) {
                        p.c.upHP(-dame / 1000);
                        this.attached(odhp - p.c.hp, p.c.id);
                    }
                    fightChar.upHP(-dame);
                    this.attached(oldhp - fightChar.hp, fightChar.id);
                    if (fightChar.hp == 0) {
                        this.sendDie(fightChar);
                        if (this.map.thienDiaBang != null) {
                            if (fightChar.isBot) {
                                this.map.thienDiaBang.isWin = true;
                            }
                            this.map.thienDiaBang.finish();
                        }
                    }
                }
            }
        }
        p.removeEffect(15);
        p.removeEffect(16);
        p.c.setTimeKickSession();
    }

    public void FightNinja(Player p, Message m) throws IOException {
        if (m.reader().available() > 0) {
            int idP = m.reader().readInt();
            Char c = this.getNinja(idP);
            if (c != null) {
                if ((!GameSrc.mapNotPK(this.map.id) || p.c.isTest || c.isTest || p.c.testCharID == c.id) && p.c.get().getEffId(14) == null && p.c.get().getEffId(6) == null && p.c.get().getEffId(7) == null) {
                    synchronized (this) {
                        if (p.c.get().ItemBody[1] != null && c.get() != null && (p.c.get().typepk == 1 && c.get().typepk == 1 || p.c.get().typepk == 3 || c.get().typepk == 3 || c.get().typepk == 4 || p.c.get().typepk == 4 || c.get().typepk == 5 || p.c.get().typepk == 5 || p.c.isTest && c.isTest && p.c.testCharID == c.id || c.isCuuSat && p.c.id == c.KillCharId || p.c.isCuuSat && p.c.KillCharId == c.id)) {
                            label425:
                            {
                                if (p.c.get().CSkill == -1 && p.c.get().skill.size() > 0) {
                                    p.c.get().CSkill = (short) ((Skill) p.c.get().skill.get(0)).id;
                                }
                                Skill skill = p.c.get().getSkill(p.c.get().CSkill);
                                if (skill != null && !c.get().isDie && c.get().getEffId(15) == null && c.get().getEffId(16) == null) {
                                    Char[] arNinja = new Char[10];
                                    arNinja[0] = c;
                                    SkillOptionTemplate temp = SkillTemplate.Templates(skill.id, skill.point);
                                    if (skill.coolDown <= System.currentTimeMillis() && Math.abs(p.c.get().x - c.get().x) <= temp.dx && Math.abs(p.c.get().y - c.get().y) <= temp.dy && p.c.get().mp >= temp.manaUse) {
                                        p.c.get().upMP(-temp.manaUse);
                                        skill.coolDown = System.currentTimeMillis() + temp.coolDown;
                                        if (skill.id == 24) {
                                            c.p.setEffect(18, 0, p.c.get().getPramSkill(55) * 1000, 0);
                                            return;
                                        }
                                        if (skill.id == 42) {
                                            this.setXYPlayers(c.get().x, c.get().y, p, c.p);
                                            c.p.setEffect(18, 0, p.c.get().getPramSkill(62), 0);
                                            return;
                                        }
                                        byte n = 1;
                                        try {
                                            Char nj2;
                                            int idn;
                                            while (m.reader().available() > 0) {
                                                idn = m.reader().readInt();
                                                nj2 = this.getNinja(idn);
                                                if (nj2 != null && !nj2.isDie && nj2.getEffId(15) == null && c.get().id != p.c.get().id && nj2.id != p.c.get().id && Math.abs(c.get().x - nj2.x) <= temp.dx && Math.abs(c.get().y - nj2.y) <= temp.dy) {
                                                    if (temp.maxFight <= n) {
                                                        break;
                                                    }

                                                    if (nj2.typepk == 3 || p.c.get().typepk == 3 || p.c.get().typepk == 1 && nj2.typepk == 1 || nj2.typepk == 4 || p.c.get().typepk == 4 || nj2.typepk == 5 || p.c.get().typepk == 5 || p.c.isTest && nj2.isTest && p.c.testCharID == nj2.id || nj2.isCuuSat && p.c.id == nj2.KillCharId || p.c.isCuuSat && p.c.KillCharId == nj2.id) {
                                                        arNinja[n] = nj2;
                                                    }
                                                    n++;
                                                }
                                            }
                                        } catch (IOException var17) {
                                            var17.printStackTrace();
                                        }

                                        m.cleanup();
                                        m = new Message(61);
                                        m.writer().writeInt(p.c.get().id);
                                        m.writer().writeByte(skill.id);

                                        byte i;
                                        for (i = 0; i < arNinja.length; ++i) {
                                            if (arNinja[i] != null) {
                                                m.writer().writeInt(arNinja[i].id);
                                            }
                                        }

                                        m.writer().flush();
                                        this.sendMyMessage(p, m);
                                        m.cleanup();

                                        this.handleAfterAttackNinja(p, arNinja);
                                        break label425;
                                    }
                                    return;
                                }
                                return;
                            }
                        }
                    }
                    m.cleanup();
                }
            }
        }
    }

    public synchronized void AutoUseSkill(Player p) {
        try {
            if (Delay < System.currentTimeMillis()) {
                isUseSkill = true;
                for (short idSk : p.c.idSkillBot) {
                    UseSkill.useSkillBot(p, idSk);
                }
                Delay = System.currentTimeMillis() + Util.nextInt(3000, 6000);
            } else {
                isUseSkill = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AutoFightNinja(Player p) {
        try {
            Char c = this.ninjaReal;
            if (c != null) {
                if (p.c.get().getEffId(6) == null && p.c.get().getEffId(7) == null) {
                    synchronized (this) {
                        if (p.c.get().ItemBody[1] != null && c.get() != null && (p.c.get().typepk == 1 && c.get().typepk == 1 || p.c.get().typepk == 3 || c.get().typepk == 3 || c.get().typepk == 4 || p.c.get().typepk == 4 || c.get().typepk == 5 || p.c.get().typepk == 5 || p.c.isTest && c.isTest && p.c.testCharID == c.id || c.isCuuSat && p.c.id == c.KillCharId || p.c.isCuuSat && p.c.KillCharId == c.id)) {
                            label425:
                            {
                                if (p.c.get().CSkill == -1 && p.c.get().skill.size() > 0) {
                                    p.c.get().CSkill = (p.c.get().skill.get(0)).id;
                                }
                                Skill skill = p.c.get().getSkill(p.c.get().CSkill);

                                if (skill != null && !c.get().isDie && c.get().getEffId(15) == null && c.get().getEffId(16) == null) {

                                    Char[] arNinja = new Char[10];
                                    arNinja[0] = c;
                                    SkillOptionTemplate temp = SkillTemplate.Templates(skill.id, skill.point);
                                    if (skill.coolDown <= System.currentTimeMillis()) {
                                        if (Math.abs(p.c.get().x - c.get().x) <= temp.dx && c.get().getEffId(14) == null) {
                                            p.c.get().upMP(-temp.manaUse);
                                            skill.coolDown = System.currentTimeMillis() + (long) temp.coolDown;
                                            if (skill.id == 24) {
                                                c.p.setEffect(18, 0, p.c.get().getPramSkill(55) * 1000, 0);
                                                return;
                                            }
                                            if (skill.id == 42) {
                                                this.setXYPlayers(c.get().x, c.get().y, p, c.p);
                                                c.p.setEffect(18, 0, p.c.get().getPramSkill(62), 0);
                                            }

                                            Message m = new Message(61);
                                            m.writer().writeInt(p.c.get().id);
                                            m.writer().writeByte(skill.id);
                                            byte i;
                                            for (i = 0; i < arNinja.length; ++i) {
                                                if (arNinja[i] != null) {
                                                    m.writer().writeInt(arNinja[i].id);
                                                }
                                            }
                                            m.writer().flush();
                                            this.sendMyMessage(p, m);
                                            m.cleanup();
                                            this.handleAfterAttackNinja(p, arNinja);
                                            break label425;
                                        } else {
                                            if (p.c.get().getEffId(16) == null) {
                                                this.AutoMoveFight(p, temp.dx);
                                            }
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AutoMoveFight(Player p, int dx) {
        Char c = this.ninjaReal;
        if (c != null) {
            if (p.c.get().getEffId(18) == null) {
                if (Math.abs(c.get().x - p.c.get().x) > (dx - 30)) {
                    if (c.get().x < p.c.get().x) {
                        p.c.get().x = (short) (p.c.get().x - 75);
                        if (p.c.get().x > 661) {
                            p.c.get().x = 661;
                        }
                        this.move(p.c.id, p.c.get().x, p.c.get().y);
                    } else if (p.c.get().x < c.get().x) {
                        p.c.get().x = (short) (p.c.get().x + 75);
                        if (p.c.get().x > 661) {
                            p.c.get().x = 661;
                        }
                        this.move(p.c.id, p.c.get().x, p.c.get().y);
                    }
                }
            }
        }
    }

    private void setXYPlayers(short x, short y, Player p1, Player p2) {
        if (p1 != null && p2 != null && p1.c != null && p2.c != null) {
            p2.c.get().x = x;
            p1.c.get().x = x;
            p2.c.get().y = y;
            p1.c.get().y = y;
            Message m = null;
            try {
                m = new Message(64);
                m.writer().writeInt(p1.c.get().id);
                m.writer().writeShort(p1.c.get().x);
                m.writer().writeShort(p1.c.get().y);
                m.writer().writeInt(p2.c.get().id);
                m.writer().flush();
                this.sendMessage(m);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (m != null) {
                    m.cleanup();
                }
            }
        }

    }

    private void attached(int dame, int nid) {
        Message m = null;
        try {
            Char n = this.getNinja(nid);
            if (n != null) {
                m = new Message(62);
                m.writer().writeInt(nid);
                m.writer().writeInt(n.hp);
                m.writer().writeInt(dame);
                m.writer().writeInt(n.mp);
                m.writer().writeInt(0);
                m.writer().flush();
                this.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void sendDie(Char c) {
        Message m = null;
        try {
            if (c.p.conn != null) {
                if (c.get().exp > Level.getMaxExp(c.get().level)) {
                    m = new Message(-11);
                    m.writer().writeByte(c.get().pk);
                    m.writer().writeShort(c.get().x);
                    m.writer().writeShort(c.get().y);
                    m.writer().writeLong(c.get().exp);
                    m.writer().flush();
                    c.p.conn.sendMessage(m);
                    m.cleanup();
                } else {
                    c.get().exp = Level.getMaxExp(c.get().level);
                    m = new Message(72);
                    m.writer().writeByte(c.get().pk);
                    m.writer().writeShort(c.get().x);
                    m.writer().writeShort(c.get().y);
                    m.writer().writeLong(c.get().expdown);
                    m.writer().flush();
                    c.p.conn.sendMessage(m);
                    m.cleanup();
                }
            }

            m = new Message(0);
            m.writer().writeInt(c.get().id);
            m.writer().writeByte(c.get().pk);
            m.writer().writeShort(c.get().x);
            m.writer().writeShort(c.get().y);
            m.writer().flush();
            this.sendMyMessage(c.p, m);
            m.cleanup();
            m = new Message(-9);
            m.writer().writeInt(c.get().id);
            m.writer().writeByte(c.get().pk);
            m.writer().writeShort(c.get().x);
            m.writer().writeShort(c.get().y);
            m.writer().flush();
            this.sendMyMessage(c.p, m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void sendMyMessage(Player p, Message m) {
        try {
            int i;
            Player player;
            for (i = this.players.size() - 1; i >= 0; --i) {
                player = this.players.get(i);
                if (player != null) {
                    if (p.id != player.id && player.conn != null) {
                        player.conn.sendMessage(m);
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

    public void EnterMap0WithXY(Char n, short x, short y) {
        if (x != -1) {
            n.get().x = x;
            if (n.clone != null) {
                n.clone.x = n.x;
            }
        } else {
            n.get().x = this.map.template.x0;
            if (n.clone != null) {
                n.clone.x = n.x;
            }
        }

        if (y != -1) {
            n.get().y = 264;
            if (n.clone != null) {
                n.clone.y = n.y;
            }
        } else {
            n.get().y = 264;
            if (n.clone != null) {
                n.clone.y = n.y;
            }
        }
        n.mapid = this.map.id;
        this.Enter(n.p);
    }

    public void Enter(Player p) {
        try {
            synchronized (this) {
                this.players.add(p);
                p.c.tdbTileMap = this;
                p.c.tileMap = null;
                p.c.mapid = this.map.id;
                if (p.c.party != null && p.c.party.charID == p.c.id) {
                    this.aParty.add(p.c.party);
                    this.numParty++;
                }
                this.numplayers++;
                p.c.mobAtk = -1;
                p.c.eff5buff = System.currentTimeMillis() + 5000L;

                if (this.map.timeMap != -1L) {
                    if (this.map.thienDiaBang != null) {
                        p.setTimeMap((int) (this.map.thienDiaBang.time - System.currentTimeMillis()) / 1000);
                    }
                }

                if (!p.c.isBot && p.conn != null) {
                    Message m = new Message(57);
                    m.writer().flush();
                    p.conn.sendMessage(m);
                    m.cleanup();
                    m = new Message(-18);
                    int mapId = this.map.id;
                    if (this.map.id == 118) {
                        mapId = 98;
                    } else if (this.map.id == 119) {
                        mapId = 104;
                    }
                    m.writer().writeByte(mapId);
                    m.writer().writeByte(this.map.template.tileID);
                    m.writer().writeByte(this.map.template.bgID);
                    m.writer().writeByte(this.map.template.typeMap);
                    m.writer().writeUTF(this.map.template.name);
                    m.writer().writeByte(this.id);
                    m.writer().writeShort(p.c.get().x);
                    m.writer().writeShort(p.c.get().y);
                    m.writer().writeByte(this.map.template.vgo.length);

                    int i;
                    for (i = 0; i < this.map.template.vgo.length; i++) {
                        m.writer().writeShort(this.map.template.vgo[i].minX);
                        m.writer().writeShort(this.map.template.vgo[i].minY);
                        m.writer().writeShort(this.map.template.vgo[i].maxX);
                        m.writer().writeShort(this.map.template.vgo[i].maxY);
                    }

                    m.writer().writeByte(0);
                    m.writer().writeByte(this.buNhins.size());
                    for (i = this.buNhins.size() - 1; i >= 0; i--) {
                        if (this.buNhins.get(i) != null) {
                            m.writer().writeUTF((this.buNhins.get(i)).name);
                            m.writer().writeShort((this.buNhins.get(i)).x);
                            m.writer().writeShort((this.buNhins.get(i)).y);
                        }
                    }

                    if (Server.manager.event == 3) {
                        m.writer().writeByte(this.map.template.npc.length);
                        int var8 = this.map.template.npc.length;
                        Npc npc;
                        for (int var9 = 0; var9 < var8; var9++) {
                            npc = this.map.template.npc[var9];
                            m.writer().writeByte(npc.type);
                            m.writer().writeShort(npc.x);
                            m.writer().writeShort(npc.y);
                            m.writer().writeByte(npc.id);
                        }
                    } else {
                        int var8 = this.map.template.npc.length;
                        Npc npc;
                        int length = this.map.template.npc.length;
                        for (int var9 = 0; var9 < var8; var9++) {
                            npc = this.map.template.npc[var9];
                            if (npc.id == 34) {
                                length = length - 1;
                            }
                        }
                        m.writer().writeByte(length);

                        int var9;
                        for (var9 = 0; var9 < var8; var9++) {
                            npc = this.map.template.npc[var9];
                            if (npc.id != 34) {
                                m.writer().writeByte(npc.type);
                                m.writer().writeShort(npc.x);
                                m.writer().writeShort(npc.y);
                                m.writer().writeByte(npc.id);
                            }
                        }
                    }

                    m.writer().writeByte(this.itemMap.size());

                    int k;
                    ItemMap im;
                    for (k = this.itemMap.size() - 1; k >= 0; k--) {
                        im = this.itemMap.get(k);
                        if (im != null) {
                            m.writer().writeShort(im.itemMapId);
                            m.writer().writeShort(im.item.id);
                            m.writer().writeShort(im.x);
                            m.writer().writeShort(im.y);
                        }
                    }

                    m.writer().writeUTF(this.map.template.name);
                    m.writer().writeByte(0);
                    m.writer().flush();
                    p.conn.sendMessage(m);
                    m.cleanup();

                    if (ChienTruong.finish) {
                        p.c.pheCT = -1;
                    }

                    if (Util.compare_Day(Date.from(Instant.now()), p.c.newlogin)) {
                        p.menuIdAuction = -1;
                        p.c.pointCave = 0;
                        p.c.nCave = 1;
                        p.c.useCave = 2;
                        p.c.ddClan = false;
                        p.c.newlogin = Date.from(Instant.now());
                        p.c.newlogin = Date.from(Instant.now());
                        switch (Util.getDay(p.c.newlogin)) {
                            case "Saturday": {
                                p.c.countTDB = 5;
                                break;
                            }
                            case "Sunday": {
                                p.c.countTDB = 5;
                                break;
                            }
                            default: {
                                p.c.countTDB = 999999;
                                break;
                            }
                        }
                        p.c.countWin = 0;
                        p.c.isDiemDanh = 0;
                        p.c.isQuaHangDong = 0;
                        p.c.countHangDong = 0;
                        p.c.useTaThuLenh = 1;
                        p.c.useDanhVongPhu = 6;
                        p.c.isTaskHangNgay = 0;
                        p.c.isTaskTaThu = 0;
                        p.c.isTaskDanhVong = 0;
                        p.c.countTaskHangNgay = 0;
                        p.c.countTaskTaThu = 0;
                        p.c.countTaskDanhVong = 20;
                        p.c.taskHangNgay = new int[]{-1, -1, -1, -1, -1, 0, 0};
                        p.c.taskTaThu = new int[]{-1, -1, -1, -1, -1, 0, 0};
                        p.c.taskDanhVong = new int[]{-1, -1, -1, 0, 20};

                        p.c.pheCT = -1;
                        p.c.pointCT = 0;
                    }
                }

                if (p != null) {
                    if (p.c != null && !p.c.isBot) {
                        this.sendCharInfo(p);
                        this.sendCoat(ninjaBot.get(), p);
                        this.sendGlove(ninjaBot.get(), p);
                        if (ninjaBot.clone != null && !ninjaBot.isNhanban && !ninjaBot.clone.isDie) {
                            Service.sendclonechar(ninjaBot.p, p);
                        }
                        if (p.c.clone != null && !p.c.isNhanban && !p.c.clone.isDie) {
                            Service.sendclonechar(p, p);
                        }
                        this.sendMounts(p);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCoat(Body b, Player pdo) {
        if (b != null && b.c != null && pdo != null && pdo.c != null && pdo.conn != null) {
            Message m = null;
            try {
                if (b.ItemBody[12] != null) {
                    m = new Message(-30);
                    m.writer().writeByte(-56);
                    m.writer().writeInt(b.id);
                    m.writer().writeInt(b.hp);
                    m.writer().writeInt(b.getMaxHP());
                    m.writer().writeShort(b.ItemBody[12].id);
                    m.writer().flush();
                    pdo.conn.sendMessage(m);
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            } finally {
                if (m != null) {
                    m.cleanup();
                }
            }
        }

    }

    public void sendGlove(Body b, Player pdo) {
        if (b != null && b.c != null && pdo != null && pdo.c != null && pdo.conn != null) {
            try {
                if (b.ItemBody[13] != null) {
                    Message m = new Message(-30);
                    m.writer().writeByte(-55);
                    m.writer().writeInt(b.id);
                    m.writer().writeInt(b.hp);
                    m.writer().writeInt(b.getMaxHP());
                    m.writer().writeShort(b.ItemBody[13].id);
                    m.writer().flush();
                    pdo.conn.sendMessage(m);
                    m.cleanup();
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }

    public void sendMounts(Body b, Player pdo) {
        if (b != null && b.c != null && pdo != null && pdo.c != null && pdo.conn != null) {
            Message m = null;
            try {
                m = new Message(-30);
                m.writer().writeByte(-54);
                m.writer().writeInt(b.id);
                Item item;
                byte i;
                for (i = 0; i < b.ItemMounts.length; i++) {
                    if (b.ItemMounts[i] != null) {
                        item = b.ItemMounts[i];
                        m.writer().writeShort(item.id);
                        m.writer().writeByte(item.upgrade);
                        m.writer().writeLong(item.expires);
                        m.writer().writeByte(item.sys);
                        m.writer().writeByte(item.options.size());
                        for (Option op : item.options) {
                            m.writer().writeByte(op.id);
                            m.writer().writeInt(op.param);
                        }
                    } else {
                        m.writer().writeShort(-1);
                    }
                }
                m.writer().flush();
                pdo.conn.sendMessage(m);
            } catch (Exception var8) {
                var8.printStackTrace();
            } finally {
                if (m != null) {
                    m.cleanup();
                }
            }
        }

    }

    public void sendMounts(Player pdo) {
        if (pdo != null && pdo.c != null && pdo.conn != null) {
            try {
                Message m = new Message(-30);
                m.writer().writeByte(-54);
                m.writer().writeInt(ninjaBot.get().id);

                Item item;
                byte i;
                for (i = 0; i < ninjaBot.get().ItemMounts.length; i++) {
                    if (ninjaBot.get().ItemMounts[i] != null) {
                        item = ninjaBot.get().ItemMounts[i];
                        m.writer().writeShort(item.id);
                        m.writer().writeByte(item.upgrade);
                        m.writer().writeLong(item.expires);
                        m.writer().writeByte(item.sys);
                        m.writer().writeByte(item.options.size());
                        for (Option op : item.options) {
                            m.writer().writeByte(op.id);
                            m.writer().writeInt(op.param);
                        }
                    } else {
                        m.writer().writeShort(-1);
                    }
                }
                m.writer().flush();
                pdo.conn.sendMessage(m);
                m.cleanup();
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }

    public void move(int id, short x, short y) {
        Message m = null;
        try {
            m = new Message(1);
            m.writer().writeInt(id);
            m.writer().writeShort(x);
            m.writer().writeShort(264);
            m.writer().flush();
            this.sendMessage(m);
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void removeItemMapMessage(short itemmapid) {
        Message m = null;
        try {
            m = new Message(-15);
            m.writer().writeShort(itemmapid);
            m.writer().flush();
            this.sendMessage(m);
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void removeBuNhin(short id) {
        Message m = null;
        try {
            if (this.buNhins.get(id) != null) {
                this.buNhins.remove(id);
                m = new Message((byte) 77);
                m.writer().writeShort(id);
                m.writer().flush();
                this.sendMessage(m);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void DieReturn(Player p) {
        Message m = null;
        try {
            if (p.c.isInDun && p.c.dunId != -1) {
                p.restCave();
            }
            this.leave(p);
            p.c.get().isDie = false;
            Map ma = Manager.getMapid(p.c.mapLTD);

            TileMap area;
            int var5;
            for (var5 = 0; var5 < ma.area.length; var5++) {
                area = ma.area[var5];
                if (area.numplayers < ma.template.maxplayers) {
                    area.EnterMap0(p.c);
                    p.c.get().hp = p.c.get().getMaxHP();
                    p.c.get().mp = p.c.get().getMaxMP();
                    m = new Message(-30);
                    m.writer().writeByte(-123);
                    m.writer().writeInt(p.c.xu);
                    m.writer().writeInt(p.c.yen);
                    m.writer().writeInt(p.luong);
                    m.writer().writeInt(p.c.get().getMaxHP());
                    m.writer().writeInt(p.c.get().getMaxMP());
                    m.writer().writeByte(0);
                    m.writer().flush();
                    p.conn.sendMessage(m);
                    m.cleanup();
                    m = new Message(57);
                    m.writer().flush();
                    p.conn.sendMessage(m);
                    m.cleanup();
                    return;
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

    public void updatePlayer() {
        int i;
        Player p;
        for (i = this.players.size() - 1; i >= 0; i--) {
            p = this.players.get(i);
            if (p != null && p.c != null) {
                p.c.timeKickSession -= 100L;
                if (p.c.timeKickSession <= System.currentTimeMillis() && p.conn != null) {
                    Client.gI().kickSession(p.conn);
                } else {
                    short s;
                    PartyPlease var20;
                    if (p.c.isBot && !p.c.get().isDie) {
                        this.AutoUseSkill(p);
                        if (!isUseSkill && p.c.get().getEffId(15) == null && p.c.get().getEffId(16) == null) {
                            this.AutoFightNinja(p);
                        }
                    }
                    if (p.c.aPartyInvate != null && p.c.aPartyInvate.size() > 0) {
                        synchronized (p.c.aPartyInvate) {
                            if (p.c.party != null) {
                                p.c.aPartyInvate.clear();
                            } else {
                                for (s = 0; s < p.c.aPartyInvate.size(); s++) {
                                    var20 = p.c.aPartyInvate.get(s);
                                    if (var20 != null) {
                                        var20.timeLive -= 500;
                                        if ((p.c.aPartyInvate.get(s)).timeLive <= 0) {
                                            p.c.aPartyInvate.remove(s);
                                            s--;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (p.c.aPartyInvite != null && p.c.aPartyInvite.size() > 0) {
                        synchronized (p.c.aPartyInvite) {
                            if (p.c.party == null) {
                                p.c.aPartyInvite.clear();
                            } else {
                                for (s = 0; s < p.c.aPartyInvite.size(); s++) {
                                    if (p.c.aPartyInvite.get(s) != null) {
                                        var20 = p.c.aPartyInvite.get(s);
                                        var20.timeLive -= 500;
                                        if ((p.c.aPartyInvite.get(s)).timeLive <= 0) {
                                            p.c.aPartyInvite.remove(s);
                                            s--;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    int k;
                    Effect effect;
                    for (k = p.c.get().veff.size() - 1; k >= 0; k--) {
                        effect = p.c.get().veff.get(k);
                        if (System.currentTimeMillis() >= effect.timeRemove) {
                            p.removeEffect(effect.template.id);
                            k--;
                        } else if (effect.template.type != 0 && effect.template.type != 12) {
                            if (effect.template.type != 4 && effect.template.type != 17) {
                                if (effect.template.type == 13) {
                                    p.c.get().upHP(-(p.c.get().getMaxHP() * 3 / 100));
                                    if (p.c.get().isDie) {
                                        p.c.get().upDie();
                                    }
                                }
                            } else {
                                p.c.get().upHP(effect.param);
                            }
                        } else {
                            p.c.get().upHP(effect.param);
                            p.c.get().upMP(effect.param);
                        }
                    }

                    if ((p.c.eff5buffHP() > 0 || p.c.get().eff5buffMP() > 0) && p.c.eff5buff <= System.currentTimeMillis()) {
                        p.c.eff5buff = System.currentTimeMillis() + 5000L;
                        p.c.get().upHP(p.c.get().eff5buffHP());
                        p.c.get().upMP(p.c.get().eff5buffMP());
                    }

                    if (p.c.buNhin != null && p.c.buNhin.timeRemove <= System.currentTimeMillis()) {
                        p.c.buNhin = null;
                    }
                    this.AddEffectItem(p);
                    byte l;
                    if (!p.c.isBot) {
                        for (l = 0; l < p.c.ItemBag.length; l++) {
                            if (p.c.ItemBag[l] != null && p.c.ItemBag[l].isExpires && System.currentTimeMillis() >= p.c.ItemBag[l].expires) {
                                p.c.removeItemBag(l, p.c.ItemBag[l].quantity);
                            }
                        }

                        for (l = 0; l < p.c.get().ItemBody.length; l++) {
                            if (p.c.get().ItemBody[l] != null && p.c.get().ItemBody[l].isExpires && System.currentTimeMillis() >= p.c.get().ItemBody[l].expires) {
                                p.c.removeItemBody(l);
                            }
                        }

                        if (p.c.get().isHuman && p.c.clone != null) {
                            for (l = 0; l < p.c.clone.ItemBody.length; l++) {
                                if (p.c.clone.ItemBody[l] != null && p.c.clone.ItemBody[l].isExpires && System.currentTimeMillis() >= p.c.clone.ItemBody[l].expires) {
                                    p.c.clone.removeItemBody(l);
                                }
                            }
                        }

                        for (l = 0; l < p.c.ItemBox.length; l++) {
                            if (p.c.ItemBox[l] != null && p.c.ItemBox[l].isExpires && System.currentTimeMillis() >= p.c.ItemBox[l].expires) {
                                p.c.removeItemBox(l);
                            }
                        }
                    }
                    if (System.currentTimeMillis() > p.c.deleyRequestClan) {
                        p.c.requestclan = -1;
                    }
                    if (p.c.clone != null && p.c.clone.islive) {
                        if (Math.abs(p.c.x - p.c.clone.x) > 80 || Math.abs(p.c.y - p.c.clone.y) > 30) {
                            p.c.clone.move((short) Util.nextInt(p.c.x - 35, p.c.x + 35), p.c.y);
                        }
                        if (p.c.clone.nclass == 6 && p.c.get().isHuman) {
                            for (short idSk : p.c.clone.getWinBuffSkills()) {
                                UseSkill.useSkillCloneBuff(p.c.clone, idSk);
                            }
                        }
                    }
                    if (p.c.clone != null && p.c.timeRemoveClone != -1 && (!p.c.clone.islive || p.c.timeRemoveClone < System.currentTimeMillis())) {
                        p.c.clone.off();
                    }
                }
            }
        }
    }

    public void updateBuNhin() {
        int i;
        BuNhin buNhin;
        Char _char;
        for (i = this.buNhins.size() - 1; i >= 0; i--) {
            buNhin = this.buNhins.get(i);
            if (buNhin != null) {
                if ((buNhin.timeRemove > 0L && System.currentTimeMillis() >= buNhin.timeRemove) || buNhin.hp <= 0) {
                    _char = Client.gI().getNinja(buNhin.idChar);
                    if (_char != null) {
                        _char.buNhin = null;
                    }
                    this.removeBuNhin((short) i);
                }
            }
        }
    }

    public void updateItemMap() {
        int i;
        ItemMap itemMap;
        for (i = this.itemMap.size() - 1; i >= 0; i--) {
            itemMap = this.itemMap.get(i);
            if (itemMap != null) {
                if (System.currentTimeMillis() >= itemMap.removedelay) {
                    this.removeItemMapMessage(itemMap.itemMapId);
                    this.itemMap.remove(i);
                    i--;
                } else if (itemMap.removedelay - System.currentTimeMillis() < 5000L && itemMap.master != -1 && itemMap.item.id != 218) {
                    itemMap.master = -1;
                }
            }
        }
    }

    public void update() {
        synchronized (this) {
            try {
                this.updatePlayer();
                this.updateBuNhin();
                this.updateItemMap();
                if (this.map.thienDiaBang != null && (System.currentTimeMillis() > this.map.thienDiaBang.time || this.ninjaBot.hp == 0 || this.ninjaBot.isDie)) {
                    this.map.thienDiaBang.finish();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }
    }

    public void addParty(Party party) {
        synchronized (this.LOCK) {
            if (!this.aParty.contains(party)) {
                this.numParty++;
                this.aParty.add(party);
            }
        }
    }

    public void removeParty(int partyId) {
        try {
            synchronized (this.LOCK) {
                short i;
                for (i = 0; i < this.numParty; i++) {
                    if (this.aParty.get(i) != null && (this.aParty.get(i)).partyId == partyId) {
                        --this.numParty;
                        this.aParty.remove(i);
                        return;
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public int getNumPlayerParty(int partyId) {
        synchronized (this.LOCK) {
            int num = 0;
            try {
                short i;
                Player pl;
                for (i = 0; i < this.numplayers; i++) {
                    pl = this.players.get(i);
                    if (pl != null && pl.c != null && pl.c.party != null && pl.c.party.partyId == partyId) {
                        num++;
                    }
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }
            return num;
        }
    }

    public void AddEffectItem(Player p) {
        int k;
        if (p.c.fullTL() >= 9) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 24, 500, 0, false);
            }
        }
        if (System.currentTimeMillis() > p.c.delayEffect) {
            int min = 0;
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Item item = p.c.ItemBody[j];
                if (item != null) {
                    list.add(item.getNgocMax());
                }
            }
            if (list.size() == 10) {
                list.sort((o1, o2) -> o1 - 02);
                min = list.get(0);
                if (min >= 6) {
                    byte effId = 0;
                    switch (GameSrc.SysClass(p.c.nclass)) {
                        case 1:
                            effId = 9;
                            break;
                        case 2:
                            effId = 3;
                            break;
                        case 3:
                            effId = 6;
                            break;
                    }
                    if (min >= 7) {
                        effId += 1;
                    }
                    if (min >= 8) {
                        effId += 1;
                    }
                    for (int j = 0; j < players.size(); j++) {
                        GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, effId, 1, 1, false);
                    }
                    p.c.delayEffect = System.currentTimeMillis() + 5000L;
                }
            }
        }
        // sửa
        int Eff = 0;
        if (p.c.get().ItemBody[30] != null && p.c.get().ItemBody[30].id == 866) {
            switch (GameSrc.SysClass(p.c.nclass)) {
                case 1:
                    Eff = 178;
                    break;
                case 2:
                    Eff = 177;
                    break;
                case 3:
                    Eff = 179;
                    break;
            }
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) Eff, 2000, 10, false);
            }
        }
        if (p.c.get().ItemBody[29] != null && p.c.get().ItemBody[29].id == 839 && p.c.get().ItemBody[29].upgrade >= 12) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 116, 500, 5, false);
            }
        }
         
        if (p.c.get().ItemMounts[4] != null && p.c.get().ItemMounts[4].id == ItemName.BACH_HO && p.c.get().ItemMounts[4].sys == 4) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 73, 500, 10, false);
            }
        }
        if (p.c.get().caiTrang == 0 && p.c.ItemCaiTrang[0].upgrade >= 6) { // santa
            for (k = 0; k < this.players.size(); k++) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 23, 1, 1, false);
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 22, 1, 1, false);
            }
        }
        if (p.c.get().caiTrang == 1 && p.c.ItemCaiTrang[1].upgrade >= 6) { //  lửa
            for (k = 0; k < this.players.size(); k++) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 25, 1, 1, false);
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 24, 1, 1, false);
            }
        }
        if (p.c.get().caiTrang == 2 && p.c.ItemCaiTrang[2].upgrade >= 6) { // gió
            for (k = 0; k < this.players.size(); k++) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 27, 1, 1, false);
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 26, 1, 1, false);
            }
        }
        switch (p.c.danhhieu) {
            case 1:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 100, 1000, 0, false);
                }
                break;
            case 2:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 101, 1000, 0, false);
                }
                break;
            case 3:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 102, 1000, 0, false);
                }
                break;
            case 4:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 103, 1000, 0, false);
                }
                break;
            case 5:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 104, 1000, 0, false);
                }
                break;
            case 6:
                for (int j = 0; j < players.size(); j++) {
                    GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (byte) 105, 1000, 0, false);
                }
                break;
            default:
                break;
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 847) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 87, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 848) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 88, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 849) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 89, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 850) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 90, 1000, 0, false);
            }
        }

        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 851) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 91, 1000, 0, false);
            }
        }

        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 852) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 92, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 853) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 93, 1000, 0, false);
            }
        }

        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 854) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 94, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[10] != null && p.c.get().ItemBody[10].id == 855) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 95, 1000, 0, false);
            }
        }
//        if (p.c.name.equals("admin")) {
//            for (int j = 0; j < players.size(); j++) {
//                GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.id, (short) 105, 1000, 0, false);
//            }
//        }
        if (p.c.name.equals("sh350i")) {
            for (int j = 0; j < players.size(); j++) {
                GameCanvas.addEffect(players.get(j).conn, (byte) 0, p.c.get().id, (short) 75, 1000, 0, false);
            }
        }
        if (p.c.get().ItemBody[31] != null && p.c.get().ItemBody[31].id == ItemName.FAN_CUNG) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 184, 1000, 1000, false);
            }
        }
        if (p.c.get().ItemBody[31] != null && p.c.get().ItemBody[31].id == ItemName.TOP_SERVER) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 189, 1000, 1000, false);
            }
        }
        if (p.c.get().ItemBody[31] != null && p.c.get().ItemBody[31].id == ItemName.V_VIP) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 190, 1000, 1000, false);
            }
        }
        if (p.c.get().ItemBody[31] != null && p.c.get().ItemBody[31].id == ItemName.DE_NHI_NINJA) {
            for (k = this.players.size() - 1; k >= 0; k--) {
                GameCanvas.addEffect((this.players.get(k)).conn, (byte) 0, p.c.get().id, (short) 201, 1000, 1000, false);
            }
        }
    }

    public void pickItem(Player p, Message m) {
        try {
            synchronized (this.itemMap) {
                short itemmapid = m.reader().readShort();
                m.cleanup();
                short i;
                ItemMap itemmap;
                Item item;
                ItemTemplate data;
                for (i = 0; i < this.itemMap.size(); i++) {
                    itemmap = this.itemMap.get(i);
                    if (itemmap != null) {
                        if (this.itemMap.get(i).itemMapId == itemmapid) {
                            item = itemmap.item;
                            data = ItemTemplate.ItemTemplateId(item.id);
                            if (itemmap.master != -1 && itemmap.master != p.c.id) {
                                p.sendAddchatYellow("Vật phẩm của người khác.");
                                return;
                            }
                            if (Math.abs(itemmap.x - p.c.get().x) > 50 || Math.abs(itemmap.y - p.c.get().y) > 30) {
                                p.sendAddchatYellow("Khoảng cách quá xa.");
                                return;
                            }
                            if (data.type == 21 || data.type == 19 || p.c.getBagNull() > 0 || (p.c.getIndexBagid(item.id, item.isLock) != -1 && data.isUpToUp)) {
                                if (this.itemMap.contains(itemmap)) {
                                    this.itemMap.remove(itemmap);
                                    m = new Message(-13);
                                    m.writer().writeShort(itemmap.itemMapId);
                                    m.writer().writeInt(p.c.get().id);
                                    m.writer().flush();
                                    this.sendMyMessage(p, m);
                                    m.cleanup();
                                    m = new Message(-14);
                                    m.writer().writeShort(itemmap.itemMapId);
                                    if (ItemTemplate.ItemTemplateId(item.id).type == 19) {
                                        p.c.upyen(1);
                                        m.writer().writeShort(1);
                                        int yenup = 0;
                                        switch (itemmap.checkMob) {
                                            case 0: {
                                                yenup = 50000;
                                                break;
                                            }
                                            case 1: {
                                                yenup = 100000;
                                                break;
                                            }
                                            case 2: {
                                                yenup = 300000;
                                                break;
                                            }
                                            case 4: {
                                                yenup = 1000000;
                                                break;
                                            }
                                        }
//                                        if (p.c.ItemBody[11] != null && p.c.ItemBody[11].id == 774) {
//                                            yenup += yenup * 50 / 100;
//                                        }
                                        p.c.upyenMessage(yenup);
                                        p.sendAddchatYellow("Bạn nhận được " + Util.getFormatNumber(yenup) + " yên");
                                    } else if (ItemTemplate.ItemTemplateId(item.id).type == 21) {
                                        p.c.get().upHP(p.c.getPramSkill(50));
                                    }
                                    m.writer().flush();
                                    p.conn.sendMessage(m);
                                    m.cleanup();
                                    if (ItemTemplate.ItemTemplateId(item.id).type != 19 && ItemTemplate.ItemTemplateId(item.id).type != 21) {
                                        p.c.addItemBag(true, itemmap.item);
                                        break;
                                    }
                                }
                                break;
                            } else {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống.");
                                return;
                            }
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

    public void close() {
    }

}
