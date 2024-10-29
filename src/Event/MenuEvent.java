/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import History.LichSu;
import Item.ItemName;
import Item.RandomItem;
import assembly.Item;
import assembly.Language;
import assembly.Option;
import assembly.Player;
import static assembly.UseItem.HanSuDung;
import io.Util;
import server.Manager;
import server.Rank;
import server.Service;
import stream.Server;
import template.ItemTemplate;

/**
 *
 * @author voqua
 */
public class MenuEvent {

    public static void npcTienNu(Player p, byte npcid, byte menuId, byte b3) {
        switch (Server.manager.event) {
            case EventName.SU_KIEN_DUA_HAU: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0: {
                            Service.sendInputDialog(p, (short) -3, "Nhập số lượng");
                            break;
                    }
                    case 1: {
                            Service.sendInputDialog(p, (short) -4, "Nhập số lượng");
                            break;
                    }
                    case 2: {
                            Service.sendInputDialog(p, (short) -5, "Nhập số lượng");
                            break;
                    }
                    case 3: {
                            Service.sendInputDialog(p, (short) -6, "Nhập số lượng");
                            break;
                    }
                    case 4: {
                            Service.sendInputDialog(p, (short) -1, "Nhập số lượng");
                            break;
                    }
                    case 5: {
                            Service.sendInputDialog(p, (short) -2, "Nhập số lượng");
                            break;
                    }
                    case 6:
                        Server.manager.sendTB(p, "Top EVEN", Rank.getStringBXH(4)
                                + "\n- Điểm của bạn: " + p.c.eventPoint);
                        break;
                    case 7:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "- Bánh thập cẩm = 1 Bột + 1 Trứng + 1 Hạt sen + 1 Đường + 1 Mứt + 10k yên.\n"
                                + "- Bánh Dẻo = 1 Bột + 1 Hạt sen + 1 Đường + 1 Mứt + 10k yên.\n"
                                + "- Bánh Đậu xanh = 1 Bột + 1 Trứng + 1 Đường + 1 Đậu xanh + 10k yên.\n"
                                + "- Bánh pía = 1 Bột + 1 Trứng + 1 Đường + 1 Đậu xanh + 10k yên.\n"
                                + "- Hộp bánh thường = 2 Bánh thập cẩm + 2 bánh dẻo + 2 bánh dậu xanh + 2 bánh pía + 1 giấy gói thường.\n"
                                + "- Hộp bánh thượng hạng = khoá = 2 Bánh thập cẩm + 2 bánh dẻo + 2 bánh dậu xanh + 2 bánh pía + 1 giấy gói cao cấp.\n"
                        );
                        break;

                }
                break;
            }
            case EventName.SK_HE: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0:
                        Service.sendInputDialog(p, (short) -28, "Nhập số lượng");
                        break;
                    case 1:
                        Service.sendInputDialog(p, (short) -29, "Nhập số lượng");
                        break;
                    case 2:
                        Server.manager.sendTB(p, "Top", Rank.getStringBXH(4)
                                + "\n- Điểm của bạn: " + p.c.eventPoint);
                        break;
                    case 3:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "- 1 MÀU VẼ THÔ SƠ + 3 TRE + 2 DÂY + 3 GIẤY + 10K xu = 1 DIỀU GIẤY (không khóa)\n"
                                + "- 1 MÀU VẼ CAO CẤP + 2 TRE + 3 DÂY + 2 GIẤY + 10 LƯỢNG = 1 DIỀU GIẤY (không khóa)\n"
                        );
                        break;
                    default: {
                        break;
                    }
                }
                break;
            }
            case EventName.HALLOWEEN: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0:
                        Service.sendInputDialog(p, (short) -20, "Nhập số lượng");
                        break;
                    case 1:
                        Service.sendInputDialog(p, (short) -19, "Nhập số lượng");
                        break;
                    case 2:
                        Server.manager.sendTB(p, "Top Điểm Sự Kiện", Rank.getStringBXH(4)
                                + "\n- Điểm Của Bạn : " + p.c.eventPoint);
                        break;
                    case 3:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "  - Kẹo Táo : Quả Táo + Mật Ong + 20 Lượng.\n"
                                + "- Hộp Ma Qủy  : Xương Thú + Tàn Linh + 50 Lượng ( 1 Điểm Đua Top Hộp Ma Qủy + Tỉ Lệ Ra Đồ Hiếm Cao ).\n"
                                + "- Dùng Hộp Ma Qủy Cần Chìa Khóa Ở Ghoso Với Giá 20K XU "
                        );
                        break;
                    default: {
                        break;
                    }
                }
                break;
            }
            case EventName.DA_MA_THUAT: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0:
                        Service.sendInputDialog(p, (short) -21, "Nhập số lượng");
                        break;
                    case 1:
                        Service.sendInputDialog(p, (short) -22, "Nhập số lượng");
                        break;
                    case 2:
                        Server.manager.sendTB(p, "Top Điểm Sự Kiện", Rank.getStringBXH(4)
                                + "\n- Điểm Của Bạn : " + p.c.eventPoint);
                        break;
                    case 3:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "3 (Đá Năng Lượng Băng, Đá Năng Lượng Hỏa, Đá Năng Lượng Gió)\n"
                                + "+ 50.000 XU = Giấy thông hành không khóa\n\n"
                                + "5 (Đá Năng Lượng Băng, Đá Năng Lượng Hỏa, Đá Năng Lượng Gió)\n"
                                + "+ 50 Lượng = Đá Ma Thuật đã khóa"
                        );
                        break;
                    default: {
                        break;
                    }
                }
                break;
            }
            case EventName.SK_NOEL: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0:
                        Service.sendInputDialog(p, (short) -23, "Nhập số lượng");
                        break;
                    case 1:
                        Service.sendInputDialog(p, (short) -24, "Nhập số lượng");
                        break;
                    case 2:
                        Server.manager.sendTB(p, "Top Điểm Sự Kiện", Rank.getStringBXH(4)
                                + "\n- Điểm Của Bạn : " + p.c.eventPoint);
                        break;
                    case 3:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "- 5 (Bơ, Kem, Đường Bột)\n"
                                + "+ 1 Chocolate = 1 Bánh Khúc Chocolate [Khóa]\n\n"
                                + "- 5 (Bơ, Kem, Đường Bột)\n"
                                + "+ 1 Dâu Tây = 1 Bánh Khúc Dâu Tây [Không Khóa]\n\n"
                                + "Dâu Tây với Chocolate Mua ở Goosho"
                        );
                        break;
                    default: {
                        break;
                    }
                }
                break;
            }
            case EventName.SK_TET_NGUYEN_DAN: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (menuId) {
                    case 0:
                        Service.sendInputDialog(p, (short) -25, "Nhập số lượng");
                        break;
                    case 1:
                        Service.sendInputDialog(p, (short) -26, "Nhập số lượng");
                        break;
                    case 2:
                        Server.manager.sendTB(p, "Top Điểm Sự Kiện", Rank.getStringBXH(4)
                                + "\n- Điểm Của Bạn : " + p.c.eventPoint);
                        break;
                    case 3:
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "- 5 (Lá dong, Nếp, Đậu xanh, Lạt tre)\n"
                                + "+ 3 Thịt heo + 20 Lượng = 1 Bánh chưng [Khóa]\n\n"
                                + "- 2 (Lá dong, Nếp, Đậu xanh, Lạt tre)\n"
                                + "+ 50k xu = 1 Bánh tét [Không khóa]\n\n"
                                + "Thịt heo Mua ở Goosho Với Giá 10 Lượng\n\n"
                        );
                        break;
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void NpcDuaBe(Player p, byte npcid, byte menuId, byte b3) {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                } else {
                    if (p.c.nvtruyentin == 0) {
                        Service.chatNPC(p, (short) npcid, "Con Muốn Có Quà.");
                        return;
                    }
                    if (p.c.nvtruyentin == 1) {
                        p.c.nvtruyentin = 2;
                        Service.chatNPC(p, (short) npcid, "Con Cám Ơn ! Giáng Sinh An Lành ! Giờ Hãy về gặp Ông Già Noel nhận lấy phấn thưởng.");
                        break;
                    }
                }
            }
        }
    }

    public static void NpcOngGiaNoel(Player p, byte npcid, byte menuId, byte b3) {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }
        switch (b3) {
            case 0: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    break;
                } else {
                    if (p.c.nvtruyentin == 1 || p.c.nvtruyentin == 2) {
                        Service.chatNPC(p, (short) npcid, "Con đã nhận nhiệm vụ trước đó rồi mà.");
                        return;
                    }
                    if (p.c.quantityItemyTotal(484) < 1) {
                        Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ túi quà");
                        break;
                    } else {
                        Service.chatNPC(p, (short) npcid, "Con hãy đi tìm Đứa Bé Ở Vách Ichidai Để Phát Quà.");
                        p.c.nvtruyentin = 1;
                        p.c.removeItemBags(484, 1);
                    }
                }
                break;
            }
            case 1: {
                if (p.c.nvtruyentin == 0) {
                    Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ.");
                    return;
                } else {
                    Service.chatNPC(p, (short) npcid, "Con đã hủy nhiệm vụ lần này.");
                    p.c.nvtruyentin = 0;
                }
                break;
            }
            case 2: {
                if (p.c.nvtruyentin == 0) {
                    Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ.");
                    return;
                }
                if (p.c.nvtruyentin == 1) {
                    Service.chatNPC(p, (short) npcid, "Con chưa hoàn thành nhiệm vụ để nhận thưởng.");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.c.nvtruyentin == 2) {
                    p.c.nvtruyentin = 0;
                    p.updateExp(5000000);
                    p.c.upxuMessage(Util.nextInt(5000, 10000));
                    if (Util.nextInt(10) < 5) {
                        p.updateExp(Util.nextInt(5000000, 10000000));
                    } else if (Util.percent(20, 1)) {
                        p.c.addItemBag(true, ItemTemplate.itemDefault(838, false));
                    } else if (Util.nextInt(30000) < 1) {
                        p.c.addItemBag(true, ItemTemplate.itemDefault(383, false));
                        LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(383).name);
                        Manager.chatKTG(p.c.name + " Phát Quà Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(383).name);
                    } else if (Util.nextInt(40000) < 1) {
                        p.c.addItemBag(true, ItemTemplate.itemDefault(384, false));
                        LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(384).name);
                        Manager.chatKTG(p.c.name + " Phát Quà Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(384).name);
                    } else if (Util.nextInt(50000) < 1) {
                        p.c.addItemBag(true, ItemTemplate.itemDefault(385, false));
                        LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(385).name);
                        Manager.chatKTG(p.c.name + " Phát Quà Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(385).name);
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
                        int itemID = RandomItem.ITEM_DOCOGIATRI.next();
                        Item itm = ItemTemplate.itemDefault(itemID);
                        if (itemID == ItemName.MAT_NA_HO || itemID == ItemName.MAT_NA_HO_1) {
                            if (Util.percent(120, 1)) {
                                itm.options.add(new Option(58, 10));
                                itm.options.add(new Option(6, 3000));
                                itm.options.add(new Option(7, 3000));
                                itm.options.add(new Option(94, 10));
                                itm.options.add(new Option(47, 50));
                                itm.options.add(new Option(127, 10));
                                itm.options.add(new Option(128, 10));
                                itm.options.add(new Option(129, 10));
                                Manager.chatKTG(p.c.name + " Phát Qùa Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                                LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                            } else {
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
                        }
                        if (itemID == ItemName.TUAN_LOC) {
                            if (Util.percent(120, 1)) {
                                itm.options.add(new Option(87, 5000));
                                itm.options.add(new Option(6, 5000));
                                itm.options.add(new Option(7, 5000));
                                itm.options.add(new Option(92, 10));
                                itm.options.add(new Option(127, 5));
                                itm.options.add(new Option(128, 5));
                                itm.options.add(new Option(129, 5));
                                Manager.chatKTG(p.c.name + " Phát Qùa Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                                LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                            } else {
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
                        }
                        if (itemID == ItemName.HAKAIRO_YOROI) {
                            if (Util.percent(120, 1)) {
                                LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                                Manager.chatKTG(p.c.name + " Phát Qùa Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                            } else {
                                int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                                itm.isExpires = true;
                                itm.expires = Util.TimeDay(HSD);
                            }
                        }
                        if (itemID == ItemName.MAT_NA_SHIN_AH || itemID == ItemName.MAT_NA_VO_DIEN || itemID == ItemName.MAT_NA_ONI || itemID == ItemName.MAT_NA_KUMA || itemID == ItemName.MAT_NA_INU) {
                            if (Util.percent(100, 1)) {
                                LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                                Manager.chatKTG(p.c.name + " Phát Qùa Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                            } else {
                                int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                                itm.isExpires = true;
                                itm.expires = Util.TimeDay(HSD);
                            }
                        }
                        if (itemID == ItemName.BACH_HO) {
                            if (Util.percent(120, 1)) {
                                LichSu.LichSuMoItemVinhVien(p.c.name, ItemTemplate.ItemTemplateId(itemID).name);
                                Manager.chatKTG(p.c.name + " Phát Qùa Cho Đứa Bé nhận được " + ItemTemplate.ItemTemplateId(itemID).name + " Vĩnh Viễn");
                            } else {
                                int HSD = HanSuDung[Util.nextInt(HanSuDung.length)];
                                itm.isExpires = true;
                                itm.expires = Util.TimeDay(HSD);
                            }
                        }
                        p.c.addItemBag(true, itm);
                        Service.chatNPC(p, (short) npcid, "Cám ơn ! Chúc Giáng Sinh Vui Vẽ.");
                    }
                    break;
                }
            }
        }
    }
}
