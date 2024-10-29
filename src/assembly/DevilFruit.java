package assembly;

import Item.ItemName;
import Item.RandomItem;
import io.Message;
import server.Manager;
import server.Service;
import template.ItemTemplate;

public class DevilFruit {
    
    public static void allFruitUse(Player p, Item item, byte index) {
        if (p.c.isNhanban) {
            p.sendAddchatYellow(Language.NOT_FOR_PHAN_THAN);
            return;
        }
        int indexfruit = item.id - ItemName.BOMB;
        if (p.c.Fruit[indexfruit] == null) {
            p.c.removeItemBag(index, 1);
            p.c.Fruit[indexfruit] = ItemTemplate.itemDefault(checkIdFruit(indexfruit));
            p.sendAddchatYellow(ItemTemplate.ItemTemplateId(p.c.Fruit[indexfruit].id).name + " đã được thêm vào bộ Devil fruit.");
        } else {
            p.conn.sendMessageLog("Trái ác quỷ này đã có trong bộ devil fruit.");
        }
    }
    
    public static void awaken(Player p) {
        try {
            if (p.c.isNhanban) {
                p.sendAddchatYellow(Language.NOT_FOR_PHAN_THAN);
                return;
            }
            ItemTemplate item = ItemTemplate.ItemTemplateId(p.c.Fruit[p.c.FruitUse].id);
            int indexfruit = item.id - ItemName.BOMB;
            if (p.c.Fruit[indexfruit].upgrade >= 16) {
                p.sendAddchatYellow("Trái ác quả này đã được thức tỉnh từ trước rồi.");
                return;
            }
            if (p.c.quantityItemyTotal(item.id) < 10) {
                p.sendAddchatYellow("Bạn không đủ 10 trải cùng loại để thức tỉnh.");
                return;
            }
            p.c.Fruit[indexfruit].AwakeningDevilFruit((byte) 16);
            p.conn.sendMessageLog("Thức tỉnh thành công.");
            p.c.removeItemBags(item.id, 10);
            Service.CharViewInfo(p, false);
            p.endLoad(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void RandomFruit(Player p) {
        int itemID = RandomItem.RANDOMFRUIT.next();
        Item itm = ItemTemplate.itemDefault(itemID);
        ItemTemplate item = ItemTemplate.ItemTemplateId(itemID);
        if (item.description.equals("Uncommon")) {
            Service.chatNPC(p, (short) 51, "Bạn nhân được trái Uncommon");
        } else if (item.description.equals("Rare")) {
            Service.chatNPC(p, (short) 51, "Bạn nhân được trái Rare");
        } else if (item.description.equals("Epic")) {
            Service.chatNPC(p, (short) 51, "Bạn nhân được trái Epic");
        } else if (item.description.equals("Legendary")) {
            Service.chatNPC(p, (short) 51, "Bạn nhân được trái Legendary");
        } else if (item.description.equals("Mythical")) {
            Service.chatNPC(p, (short) 51, "Bạn nhân được trái Mythical");
            Manager.chatKTG(p.c.name + " đã random nhận được trái ác quỷ Mythical " + ItemTemplate.ItemTemplateId(itemID).name);
        }
        p.c.addItemBag(false, itm);
    }
    
    public static void openMenuFruit(Player p) {
        Message m = null;
        try {
            p.menuCaiTrang = 6;
            Service.sendTileAction(p, (byte) 4, "Trái ác qủy", "Sử dụng");
            m = new Message(31);
            m.writer().writeInt(p.c.xuBox);
            m.writer().writeByte(p.c.Fruit.length);
            for (Item itemCT : p.c.Fruit) {
                if (itemCT != null) {
                    m.writer().writeShort(itemCT.id);
                    m.writer().writeBoolean(itemCT.isLock);
                    if (ItemTemplate.isTypeBody(itemCT.id) || ItemTemplate.isTypeNgocKham(itemCT.id)) {
                        m.writer().writeByte(itemCT.upgrade);
                    }
                    m.writer().writeBoolean(itemCT.isExpires);
                    m.writer().writeShort(itemCT.quantity);
                } else {
                    m.writer().writeShort(-1);
                }
            }
            m.writer().flush();
            p.conn.sendMessage(m);
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }
    
    public static int checkIdFruit(int id) {
        switch (id) {
            case 0:
                return ItemName.BOMB;
            case 1:
                return ItemName.CONTROL;
            case 2:
                return ItemName.DARK;
            case 3:
                return ItemName.DRAGON;
            case 4:
                return ItemName.FALCON;
            case 5:
                return ItemName.GRAVITY;
            case 6:
                return ItemName.ICE;
            case 7:
                return ItemName.LEOPARD;
            case 8:
                return ItemName.MAMMOTH;
            case 9:
                return ItemName.QUAKE;
            case 10:
                return ItemName.RUMBLE;
            case 11:
                return ItemName.SPIRIT;
            case 12:
                return ItemName.BLIZZARD;
            case 13:
                return ItemName.DIAMOND;
            case 14:
                return ItemName.DOUGHIN;
            case 15:
                return ItemName.FLAME;
            case 16:
                return ItemName.KITSUNE;
            case 17:
                return ItemName.LIGHT;
            case 18:
                return ItemName.LOVE;
            case 19:
                return ItemName.MAGMA;
            case 20:
                return ItemName.PORTAL;
            case 21:
                return ItemName.REVIVE;
            case 22:
                return ItemName.ROCKET;
            case 23:
                return ItemName.RUBBER;
            case 24:
                return ItemName.SAND;
            case 25:
                return ItemName.SHADOW;
            case 26:
                return ItemName.SOUND;
            case 27:
                return ItemName.SPIDER;
            case 28:
                return ItemName.T_REX;
            case 29:
                return ItemName.VENOM;
        }
        return -1;
    }
}
