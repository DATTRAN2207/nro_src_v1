package Menu;

import History.LichSu;
import Item.ItemName;
import Item.RandomItem;
import assembly.Item;
import assembly.Language;
import assembly.Option;
import assembly.Player;
import static assembly.UseItem.HanSuDung;
import static assembly.UseItem.HanSuDung1Thg;
import io.Util;
import server.Manager;
import server.Service;
import template.ItemTemplate;

/**
 *
 * @author Administrator
 */
public class CayThong {

    public static void npcCayThong(Player p, byte npcid, byte menuId, byte b3) {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                Service.sendInputDialog(p, (short) -27, "Nhập số lượng");
                break;

            }
        }
    }
    
    public static void HandleCayThong(Player p) {
        p.c.removeItemBags(673, 1);
        p.updateExp(5000000);
        p.c.eventPoint += 1;
        if (Util.nextInt(10) < 5) {
            p.updateExp(Util.nextInt(5000000, 10000000));
        } else if (Util.percent(20, 1)) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(ItemName.RUONG_NGOC, false));
        } else if (Util.nextInt(30000) < 1) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(383, false));
            LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(383).name);
            Manager.chatKTG(p.c.name + " Trang Trí nhận được " + ItemTemplate.ItemTemplateId(383).name);
        } else if (Util.nextInt(40000) < 1) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(384, false));
            LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(384).name);
            Manager.chatKTG(p.c.name + " Trang Trí nhận được " + ItemTemplate.ItemTemplateId(384).name);
        } else if (Util.nextInt(50000) < 1) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(385, false));
            LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(385).name);
            Manager.chatKTG(p.c.name + " Trang Trí nhận được " + ItemTemplate.ItemTemplateId(385).name);
        } else if (Util.nextInt(500) < 3) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(ItemName.XICH_NHAN_NGAN_LANG, false));
        } else if (Util.nextInt(500) < 2) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(ItemName.HARLEY_DAVIDSON, false));
        } else if (Util.nextInt(500) < 1) {
            p.c.addItemBag(true, ItemTemplate.itemDefault(ItemName.XE_MAY, false));
        } else if (Util.nextInt(10000) < 1) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_VO_CUC_KIEM);
            itemup.sys = 1;
            p.c.addItemBag(true, itemup);
        } else if (Util.nextInt(10000) < 2) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_THIEN_HOA_TIEU);
            itemup.sys = 1;
            p.c.addItemBag(true, itemup);
        } else if (Util.nextInt(10000) < 3) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_TANG_HON_DAO);
            itemup.sys = 2;
            p.c.addItemBag(true, itemup);
        } else if (Util.nextInt(10000) < 4) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_BANG_THAN_CUNG);
            itemup.sys = 2;
            p.c.addItemBag(true, itemup);
        } else if (Util.nextInt(10000) < 5) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_CHIEN_LUC_DAO);
            itemup.sys = 3;
            p.c.addItemBag(true, itemup);
        } else if (Util.nextInt(10000) < 6) {
            Item itemup = ItemTemplate.itemDefault(ItemName.THAI_DUONG_HOANG_PHONG_PHIEN);
            itemup.sys = 3;
            p.c.addItemBag(true, itemup);
        } else {
            int itemID = RandomItem.TRANG_TRI.next();
            Item itm = ItemTemplate.itemDefault(itemID);
            if (itemID == ItemName.MAT_NA_HO || itemID == ItemName.MAT_NA_HO_1) {
                int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                itm.isExpires = true;
                itm.expires = Util.TimeDay(HSD);
                itm.options.add(new Option(58, Util.nextInt(5, 10)));
                itm.options.add(new Option(6, Util.nextInt(2000, 3000)));
                itm.options.add(new Option(7, Util.nextInt(2000, 3000)));
                itm.options.add(new Option(94, Util.nextInt(5, 10)));
                itm.options.add(new Option(47, Util.nextInt(25, 50)));
                itm.options.add(new Option(127, Util.nextInt(5, 10)));
                itm.options.add(new Option(128, Util.nextInt(5, 10)));
                itm.options.add(new Option(129, Util.nextInt(5, 10)));
            }
            if (itemID == ItemName.HOA_KY_LAN) {
                if ((Util.nextInt(100) < 1)) {
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                    Manager.chatKTG(p.c.name + " Trang Trí nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 40));
                    itm.options.add(new Option(79, 20));
                } else if ((Util.nextInt(100) < 3)) {
                    int HSD = HanSuDung1Thg[Util.nextInt(HanSuDung1Thg.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 40));
                    itm.options.add(new Option(79, 20));
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 40));
                    itm.options.add(new Option(79, 20));
                }
            }
            if (itemID == ItemName.PHUONG_HOANG_NEW) {
                if ((Util.nextInt(100) < 1)) {
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                    Manager.chatKTG(p.c.name + " Trang Trí Cây Thông nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 50));
                    itm.options.add(new Option(74, 100));
                    itm.options.add(new Option(79, 20));
                    itm.options.add(new Option(130, 10));
                    itm.options.add(new Option(131, 10));
                } else if ((Util.nextInt(100) < 3)) {
                    int HSD = HanSuDung1Thg[Util.nextInt(HanSuDung1Thg.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 50));
                    itm.options.add(new Option(74, 100));
                    itm.options.add(new Option(79, 20));
                    itm.options.add(new Option(130, 10));
                    itm.options.add(new Option(131, 10));
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(94, 50));
                    itm.options.add(new Option(74, 100));
                    itm.options.add(new Option(79, 20));
                    itm.options.add(new Option(130, 10));
                    itm.options.add(new Option(131, 10));
                }
            }
            if (itemID == ItemName.TUAN_LOC) {
                int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                itm.isExpires = true;
                itm.expires = Util.TimeDay(HSD);
                itm.options.add(new Option(87, 5000));
                itm.options.add(new Option(6, 5000));
                itm.options.add(new Option(7, 5000));
                itm.options.add(new Option(92, 10));
                itm.options.add(new Option(127, 5));
                itm.options.add(new Option(128, 5));
                itm.options.add(new Option(129, 5));
            }
            if (itemID == ItemName.HAKAIRO_YOROI) {
                if (Util.percent(120, 1)) {
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                    Manager.chatKTG(p.c.name + " Trang Trí Cây Thông nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                }
            }
            if (itemID == ItemName.GAY_MAT_TRANG || itemID == ItemName.GAY_TRAI_TIM) {
                if (Util.percent(120, 1)) {
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(92, 100));
                    itm.options.add(new Option(94, 150));
                    itm.options.add(new Option(121, 30));
                    itm.options.add(new Option(84, 100));
                    itm.options.add(new Option(80, 500));
                    itm.options.add(new Option(120, 1000));
                    itm.options.add(new Option(127, 10));
                    itm.options.add(new Option(128, 10));
                    itm.options.add(new Option(129, 10));
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, Util.nextInt(10, 20)));
                    itm.options.add(new Option(92, Util.nextInt(50, 100)));
                    itm.options.add(new Option(94, Util.nextInt(100, 150)));
                    itm.options.add(new Option(121, Util.nextInt(10, 30)));
                    itm.options.add(new Option(84, Util.nextInt(50, 100)));
                    itm.options.add(new Option(80, Util.nextInt(250, 500)));
                    itm.options.add(new Option(120, Util.nextInt(500, 1000)));
                    itm.options.add(new Option(127, Util.nextInt(5, 10)));
                    itm.options.add(new Option(128, Util.nextInt(5, 10)));
                    itm.options.add(new Option(129, Util.nextInt(5, 10)));
                }
            }
            if (itemID == ItemName.NHAT_TU_LAM_PHONG || itemID == ItemName.THIEN_NGUYET_CHI_NU) {
                if (Util.percent(120, 1)) {
                    itm.options.add(new Option(82, 5000));
                    itm.options.add(new Option(94, 100));
                    itm.options.add(new Option(80, 200));
                    itm.options.add(new Option(92, 100));
                    itm.options.add(new Option(58, 10));
                    itm.options.add(new Option(127, 10));
                    itm.options.add(new Option(128, 10));
                    itm.options.add(new Option(129, 10));
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(82, Util.nextInt(3000, 5000)));
                    itm.options.add(new Option(94, Util.nextInt(50, 100)));
                    itm.options.add(new Option(80, Util.nextInt(100, 200)));
                    itm.options.add(new Option(92, Util.nextInt(50, 100)));
                    itm.options.add(new Option(58, Util.nextInt(5, 10)));
                    itm.options.add(new Option(127, Util.nextInt(5, 10)));
                    itm.options.add(new Option(128, Util.nextInt(5, 10)));
                    itm.options.add(new Option(129, Util.nextInt(5, 10)));
                }
            }
            if (itemID == ItemName.AO_DAI_NAM || itemID == ItemName.AO_DAI_NU) {
                if (Util.percent(120, 1)) {
                    itm.options.add(new Option(82, 5000));
                    itm.options.add(new Option(94, 100));
                    itm.options.add(new Option(80, 200));
                    itm.options.add(new Option(92, 100));
                    itm.options.add(new Option(58, 10));
                    itm.options.add(new Option(127, 10));
                    itm.options.add(new Option(128, 10));
                    itm.options.add(new Option(129, 10));
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(82, Util.nextInt(3000, 5000)));
                    itm.options.add(new Option(94, Util.nextInt(50, 100)));
                    itm.options.add(new Option(80, Util.nextInt(100, 200)));
                    itm.options.add(new Option(92, Util.nextInt(50, 100)));
                    itm.options.add(new Option(58, Util.nextInt(5, 10)));
                    itm.options.add(new Option(127, Util.nextInt(5, 10)));
                    itm.options.add(new Option(128, Util.nextInt(5, 10)));
                    itm.options.add(new Option(129, Util.nextInt(5, 10)));
                }
            }
            if (itemID == ItemName.MAT_NA_THO || itemID == ItemName.MAT_NA_THO_1) {
                if (Util.percent(120, 1)) {
                    Manager.chatKTG(p.c.name + " sử dụng " + ItemTemplate.ItemTemplateId(itemID).name + " đã nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " vĩnh viễn");
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(92, 100));
                    itm.options.add(new Option(94, 100));
                    itm.options.add(new Option(82, 500));
                    itm.options.add(new Option(127, 10));
                    itm.options.add(new Option(128, 10));
                    itm.options.add(new Option(129, 10));
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, Util.nextInt(10, 20)));
                    itm.options.add(new Option(92, Util.nextInt(50, 100)));
                    itm.options.add(new Option(94, Util.nextInt(50, 100)));
                    itm.options.add(new Option(82, Util.nextInt(400, 500)));
                    itm.options.add(new Option(127, Util.nextInt(5, 10)));
                    itm.options.add(new Option(128, Util.nextInt(5, 10)));
                    itm.options.add(new Option(129, Util.nextInt(5, 10)));
                }
            }
            if (itemID == ItemName.MAT_NA_SHIN_AH || itemID == ItemName.MAT_NA_VO_DIEN || itemID == ItemName.MAT_NA_ONI || itemID == ItemName.MAT_NA_KUMA || itemID == ItemName.MAT_NA_INU) {
                if (Util.percent(120, 1)) {
                    Manager.chatKTG(p.c.name + " sử dụng " + ItemTemplate.ItemTemplateId(itemID).name + " đã nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " vĩnh viễn");
                    itm.options.add(new Option(58, 20));
                    itm.options.add(new Option(82, 2000));
                    itm.options.add(new Option(127, 10));
                    itm.options.add(new Option(128, 10));
                    itm.options.add(new Option(129, 10));
                    LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                } else {
                    int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                    itm.isExpires = true;
                    itm.expires = Util.TimeDay(HSD);
                    itm.options.add(new Option(58, Util.nextInt(10, 20)));
                    itm.options.add(new Option(82, Util.nextInt(1000, 2000)));
                    itm.options.add(new Option(127, Util.nextInt(5, 10)));
                    itm.options.add(new Option(128, Util.nextInt(5, 10)));
                    itm.options.add(new Option(129, Util.nextInt(5, 10)));
                }
            }
            p.c.addItemBag(true, itm);
        }
    }
}
