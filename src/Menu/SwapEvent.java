/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import History.LichSu;
import Item.ItemName;
import assembly.Item;
import assembly.Player;
import server.Service;
import template.ItemTemplate;

/**
 *
 * @author Administrator
 */
public class SwapEvent {

     public static void thapcam(Player p, String str) {
        try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 10000) {
                        p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                        return;
                    }
                    long soluong = Integer.parseInt(str);
                    if (p.c.quantityItemyTotal(292) >= 1 * soluong
                            && p.c.quantityItemyTotal(293) >= 1 * soluong
                            && p.c.quantityItemyTotal(295) >= 1 * soluong
                            && p.c.quantityItemyTotal(294) >= 1 * soluong
                            && p.c.quantityItemyTotal(297) >= 1 * soluong) {

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        }
                        if (p.c.yen < 10000 * soluong) {
                            p.conn.sendMessageLog("Không Đủ Yên");
                        } else {
                            p.c.removeItemBags(292, (int) (1 * soluong));
                            p.c.removeItemBags(293, (int) (1 * soluong));
                            p.c.removeItemBags(295, (int) (1 * soluong));
                            p.c.removeItemBags(294, (int) (1 * soluong));
                            p.c.removeItemBags(297, (int) (1 * soluong));
                            p.c.upyenMessage(-(10000 * soluong));
                            Item it = ItemTemplate.itemDefault(298);
                            it.quantity = (int) (1 * soluong);
                            it.isLock = true;
                            p.c.addItemBag(true, it);
                        }
                        return;
                    } else {
                        Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
    }
    public static void banhdeo(Player p, String str) {
    try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 10000) {
                        p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                        return;
                    }
                    long soluong = Integer.parseInt(str);
                    if (p.c.quantityItemyTotal(292) >= 1 * soluong
                            && p.c.quantityItemyTotal(295) >= 1 * soluong
                            && p.c.quantityItemyTotal(294) >= 1 * soluong
                            && p.c.quantityItemyTotal(297) >= 1 * soluong) {

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        }
                        if (p.c.yen < 10000 * soluong) {
                            p.conn.sendMessageLog("Không Đủ Yên");
                        } else {
                            p.c.removeItemBags(292, (int) (1 * soluong));
                            p.c.removeItemBags(295, (int) (1 * soluong));
                            p.c.removeItemBags(294, (int) (1 * soluong));
                            p.c.removeItemBags(297, (int) (1 * soluong));
                            p.c.upyenMessage(-(10000 * soluong));
                            Item it = ItemTemplate.itemDefault(299);
                            it.quantity = (int) (1 * soluong);
                            it.isLock = true;
                            p.c.addItemBag(true, it);
                        }
                        return;
                    } else {
                        Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
    }
    public static void dauxanh(Player p, String str) {
    try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 10000) {
                        p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                        return;
                    }
                    long soluong = Integer.parseInt(str);
                    if (p.c.quantityItemyTotal(292) >= 1 * soluong
                            && p.c.quantityItemyTotal(295) >= 1 * soluong
                            && p.c.quantityItemyTotal(294) >= 1 * soluong
                            && p.c.quantityItemyTotal(297) >= 1 * soluong) {

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        }
                        if (p.c.yen < 10000 * soluong) {
                            p.conn.sendMessageLog("Không Đủ Yên");
                        } else {
                            p.c.removeItemBags(292, (int) (1 * soluong));
                            p.c.removeItemBags(295, (int) (1 * soluong));
                            p.c.removeItemBags(294, (int) (1 * soluong));
                            p.c.removeItemBags(297, (int) (1 * soluong));
                            p.c.upyenMessage(-(10000 * soluong));
                            Item it = ItemTemplate.itemDefault(300);
                            it.quantity = (int) (1 * soluong);
                            it.isLock = true;
                            p.c.addItemBag(true, it);
                        }
                        return;
                    } else {
                        Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
    }
    
    public static void banhpia(Player p, String str) {
    try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 10000) {
                        p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                        return;
                    }
                    long soluong = Integer.parseInt(str);
                    if (p.c.quantityItemyTotal(292) >= 1 * soluong
                            && p.c.quantityItemyTotal(295) >= 1 * soluong
                            && p.c.quantityItemyTotal(294) >= 1 * soluong
                            && p.c.quantityItemyTotal(297) >= 1 * soluong) {

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        }
                        if (p.c.yen < 10000 * soluong) {
                            p.conn.sendMessageLog("Không Đủ Yên");
                        } else {
                            p.c.removeItemBags(292, (int) (1 * soluong));
                            p.c.removeItemBags(295, (int) (1 * soluong));
                            p.c.removeItemBags(294, (int) (1 * soluong));
                            p.c.removeItemBags(297, (int) (1 * soluong));
                            p.c.upyenMessage(-(10000 * soluong));
                            Item it = ItemTemplate.itemDefault(301);
                            it.quantity = (int) (1 * soluong);
                            it.isLock = true;
                            p.c.addItemBag(true, it);
                        }
                        return;
                    } else {
                        Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
    }
    public static void Lamhopbanhthuong(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
                   if (p.c.quantityItemyTotal(ItemName.BANH_THAP_CAM) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.BANH_DEO) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.BANH_DAU_XANH) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.BANH_PIA) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.GIAY_GOI_THUONG) >= 1 * soluong) {
                if (p.c.getBagNull() == 0) {
                    Service.chatNPC(p, (short) 33, "Hành Trang Không Đủ Chổ Trống");
                    return;
                }
                p.c.removeItemBags(ItemName.BANH_THAP_CAM, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_DEO, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_DAU_XANH, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_PIA, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.GIAY_GOI_THUONG, (int) (1 * soluong));
                LichSu.LichSuXu(p.c.name, p.c.xu, (int) (p.c.xu - (0 * soluong)), "Làm " + soluong + " Hộp bánh thường", -(0 * soluong));
                Item it = ItemTemplate.itemDefault(ItemName.HOP_BANH_THUONG);
                it.quantity = (int) (1 * soluong);
                it.isLock = false;
                p.c.addItemBag(true, it);
            } else {
                Service.chatNPC(p, (short) 33, "Hành Trang Không Đủ Nguyên Liệu");
            }
        } catch (NumberFormatException e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    public static void Lamhopbanhthuonghang(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
                   if (p.c.quantityItemyTotal(ItemName.BANH_THAP_CAM) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.BANH_DEO) >= 2 * soluong 
                    && p.c.quantityItemyTotal(ItemName.BANH_DAU_XANH) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.BANH_PIA) >= 2 * soluong
                    && p.c.quantityItemyTotal(ItemName.GIAY_GOI_CAO_CAP) >= 1* soluong) {
                if (p.c.getBagNull() == 0) {
                    Service.chatNPC(p, (short) 33, "Hành Trang Không Đủ Chổ Trống");
                    return;
                }
               p.c.removeItemBags(ItemName.BANH_THAP_CAM, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_DEO, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_DAU_XANH, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.BANH_PIA, (int) (2 * soluong));
                p.c.removeItemBags(ItemName.GIAY_GOI_CAO_CAP, (int) (1 * soluong));
                LichSu.LichSuLuong(p.c.name, p.luong, (int) (p.luong - (0 * soluong)), "Làm " + soluong + " Bó Hộp bánh thượng hạng", -(0 * soluong));
                Item it = ItemTemplate.itemDefault(ItemName.HOP_BANH_THUONG_HANG);
                it.quantity = (int) (1 * soluong);
                it.isLock = true;
                p.c.addItemBag(true, it);
            } else {
                Service.chatNPC(p, (short) 33, "Hành Trang Không Đủ Nguyên Liệu");
            }
        } catch (NumberFormatException e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    
    public static void Lamkeotao(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.QUA_TAO) >= 1 * soluong && p.c.quantityItemyTotal(ItemName.MAT_ONG) >= 1) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.luong < 20 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Lượng");
                } else {
                    p.c.removeItemBags(ItemName.QUA_TAO, (int) (1 * soluong));
                    p.c.removeItemBags(ItemName.MAT_ONG, (int) (1 * soluong));

                    p.upluongMessage(-(20 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.KEO_TAO);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void Lamhopmaquy(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.XUONG_THU) >= 1 * soluong && p.c.quantityItemyTotal(ItemName.TAN_LINH) >= 1) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.luong < 50 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Lượng");
                } else {
                    p.c.removeItemBags(ItemName.XUONG_THU, (int) (1 * soluong));
                    p.c.removeItemBags(ItemName.TAN_LINH, (int) (1 * soluong));
                    p.upluongMessage(-(50 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.HOP_MA_QUY);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = true;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void GiayThongHanh(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_BANG) >= 3 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_HOA) >= 3 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_GIO) >= 3 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.c.xu < 50000 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Xu");
                } else {
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_BANG, (int) (3 * soluong));
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_HOA, (int) (3 * soluong));
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_GIO, (int) (3 * soluong));
                    p.c.upxuMessage(-(50000 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.GIAY_THONG_HANH);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void DaMaThuat(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_BANG) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_HOA) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DA_NANG_LUONG_GIO) >= 5 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                }
                if (p.luong < 50 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Xu");
                } else {
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_BANG, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_HOA, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DA_NANG_LUONG_GIO, (int) (5 * soluong));
                    p.upluongMessage(-(50 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.DA_MA_THUAT);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = true;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    public static void LamBanhDauTay(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.BO) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.KEM) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DUONG_BOT) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DAU_TAY) >= 2 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                } else {
                    p.c.removeItemBags(ItemName.BO, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.KEM, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DUONG_BOT, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DAU_TAY, (int) (2 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.BANH_KHUC_CAY_DAU_TAY);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void LamBanhChocolate(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.BO) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.KEM) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DUONG_BOT) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.CHOCOLATE) >= 1 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                } else {
                    p.c.removeItemBags(ItemName.BO, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.KEM, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DUONG_BOT, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.CHOCOLATE, (int) (1 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.BANH_KHUC_CAY_CHOCOLATE);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = true;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    public static void LamBanhTet(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.LA_DONG) >= 2 * soluong &&
                    p.c.quantityItemyTotal(ItemName.NEP) >= 2 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DAU_XANH_1) >= 2 * soluong &&
                    p.c.quantityItemyTotal(ItemName.LAT_TRE) >= 2 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.c.xu < 50000 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Xu");
                } else {
                    p.c.removeItemBags(ItemName.LA_DONG, (int) (2 * soluong));
                    p.c.removeItemBags(ItemName.NEP, (int) (2 * soluong));
                    p.c.removeItemBags(ItemName.DAU_XANH_1, (int) (2 * soluong));
                    p.c.removeItemBags(ItemName.LAT_TRE, (int) (2 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.BANH_TET);
                    p.c.upxuMessage(-(50000 * soluong));
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }

    public static void LamBanhChung(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.LA_DONG) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.NEP) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.DAU_XANH_1) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.LAT_TRE) >= 5 * soluong &&
                    p.c.quantityItemyTotal(ItemName.THIT_HEO) >= 3 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                } 
                if (p.luong < 20 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Lượng");
                } else {
                    p.c.removeItemBags(ItemName.LA_DONG, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.NEP, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.DAU_XANH_1, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.LAT_TRE, (int) (5 * soluong));
                    p.c.removeItemBags(ItemName.THIT_HEO, (int) (3 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.BANH_CHUNG);
                    p.upluongMessage(-(20 * soluong));
                    it.quantity = (int) (1 * soluong);
                    it.isLock = true;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void LamDieuGiay(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.MAU_VE_THO_SO) >= 1 * soluong && p.c.quantityItemyTotal(ItemName.TRE) >= 3 * soluong && p.c.quantityItemyTotal(ItemName.DAY) >= 2 * soluong && p.c.quantityItemyTotal(ItemName.GIAY_1) >= 3 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.c.xu < 10000 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Lượng");
                } else {
                    p.c.removeItemBags(ItemName.MAU_VE_THO_SO, (int) (1 * soluong));
                    p.c.removeItemBags(ItemName.TRE, (int) (3 * soluong));
                    p.c.removeItemBags(ItemName.DAY, (int) (2 * soluong));
                    p.c.removeItemBags(ItemName.GIAY_1, (int) (3 * soluong));
                    p.c.upxuMessage(-(10000 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.DIEU_GIAY);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
    
    public static void LamDieuVai(Player p, String str) {
        try {
            int num = Integer.parseInt(str);
            if (num < 1 || num > 10000) {
                p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                return;
            }
            long soluong = Integer.parseInt(str);
            if (p.c.quantityItemyTotal(ItemName.MAU_VE_CAO_CAP) >= 1 * soluong && p.c.quantityItemyTotal(ItemName.TRE) >= 2 * soluong && p.c.quantityItemyTotal(ItemName.DAY) >= 3 * soluong && p.c.quantityItemyTotal(ItemName.VAI) >= 2 * soluong) {
                if (p.c.getBagNull() == 0) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                    return;
                }
                if (p.luong < 10 * soluong) {
                    p.conn.sendMessageLog("Không Đủ Lượng");
                } else {
                    p.c.removeItemBags(ItemName.MAU_VE_CAO_CAP, (int) (1 * soluong));
                    p.c.removeItemBags(ItemName.TRE, (int) (2 * soluong));
                    p.c.removeItemBags(ItemName.DAY, (int) (3 * soluong));
                    p.c.removeItemBags(ItemName.VAI, (int) (2 * soluong));
                    p.upluongMessage(-(10 * soluong));
                    Item it = ItemTemplate.itemDefault(ItemName.DIEU_VAI);
                    it.quantity = (int) (1 * soluong);
                    it.isLock = false;
                    p.c.addItemBag(true, it);
                }
                return;
            } else {
                Service.chatNPC(p, (short) 33, "Không Đủ Nguyên Liệu");
            }
        } catch (Exception e) {
            p.conn.sendMessageLog("Không hợp lệ");
        }
    }
}
