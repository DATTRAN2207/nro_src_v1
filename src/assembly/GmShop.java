package assembly;

import io.Message;
import java.util.ArrayList;
import server.Service;
import template.ItemTemplate;

public class GmShop {
    public int id;
    public Item[] item;
    public static ArrayList<Item> ItemGmShop = new ArrayList<Item>();
    
    public static void openMenuGmShop(Player p) {
        Message m = null;
        try {
            p.menuCaiTrang = 7;
            m = new Message(31);
            Service.sendTileAction(p, (byte) 4, "Shop Gm", "Mua");
            m.writer().writeInt(p.c.xuBox);
            m.writer().writeByte(GmShop.ItemGmShop.size());
            for (Item item : GmShop.ItemGmShop) {
                if (item != null) {
                    m.writer().writeShort(item.id);
                    m.writer().writeBoolean(item.isLock());
                    if (ItemTemplate.isTypeBody(item.id) || ItemTemplate.isTypeNgocKham(item.id)) {
                        m.writer().writeByte(item.getUpgrade());
                    }
                    m.writer().writeBoolean(item.isExpires);
                    m.writer().writeShort(item.quantity);
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
}
