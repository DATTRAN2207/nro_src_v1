 package server;

import History.LichSu;
import Item.ItemName;
import Menu.CayThong;
import Menu.MenuAdmin;
import Menu.Send;
import Menu.Swap;
import Menu.SwapEvent;
import assembly.Char;
import stream.Server;
import assembly.ClanManager;
import assembly.Language;
import assembly.Player;
import io.Message;
import io.SQLManager;
import io.Util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import stream.Client;

public class Draw {

    public static void Draw(Player p, Message m) throws IOException, SQLException, InterruptedException {
        short menuId = m.reader().readShort();
        String str = m.reader().readUTF();
        m.cleanup();
        //   System.out.println("menuId "+menuId+" str "+str);
        byte b = -1;
        try {
            b = m.reader().readByte();
        } catch (IOException e) {
        }
        m.cleanup();

        switch (menuId) {
            case -1: {
                SwapEvent.Lamhopbanhthuong(p, str);
                break;
            }
            case -2: {
                SwapEvent.Lamhopbanhthuonghang(p, str);
                break;
            }
            case -3: {
                SwapEvent.thapcam(p, str);
                break;
            }
            case -4: {
                SwapEvent.banhdeo(p, str);
                break;
            }
            case -5: {
                SwapEvent.dauxanh(p, str);
                break;
            }
            case -6: {
                SwapEvent.banhpia(p, str);
                break;
            }
            case -19: {
                SwapEvent.Lamkeotao(p, str);
                break;
            }
            case -20: {
                SwapEvent.Lamhopmaquy(p, str);
                break;
            }
            case -21: {
                SwapEvent.GiayThongHanh(p, str);
                break;
            }
            case -22: {
                SwapEvent.DaMaThuat(p, str);
                break;
            }
            case -23: {
                SwapEvent.LamBanhDauTay(p, str);
                break;
            }
            case -24: {
                SwapEvent.LamBanhChocolate(p, str);
                break;
            }
            case -25: {
                SwapEvent.LamBanhTet(p, str);
                break;
            }
            case -26: {
                SwapEvent.LamBanhChung(p, str);
                break;
            }
            case -27: {
                try {
                    int usequantity = 0;
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 10000) {
                        p.conn.sendMessageLog("Tối Đa 10000 Cái 1 Lần");
                        return;
                    }
                    long soluong = Integer.parseInt(str);
                    if (p.c.quantityItemyTotal(ItemName.QUA_TRANG_TRI) < soluong) {
                        p.conn.sendMessageLog("Hành trang không đủ " + soluong + " Quà trang trí.");
                        return;
                    }
                    for (int i = 0; i < soluong; i++) {
                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống.\nĐã trang trí "+ usequantity +" lần.");
                            return;
                        }
                        CayThong.HandleCayThong(p);
                        usequantity++;
                    }
                    p.conn.sendMessageLog("Đã trang trí "+ usequantity +" lần.");
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            }
            case -28: {
                SwapEvent.LamDieuGiay(p, str);
                break;
            }
            case -29: {
                SwapEvent.LamDieuVai(p, str);
                break;
            }
            //chẵn xu
            case -70: {
                try {
                    int coin = Integer.parseInt(str);
                    if (coin < 10000 || coin > 100000000) {
                        p.conn.sendMessageLog("Mức cược chỉ được trong khoảng 10.000 xu - 100.000.000 xu");
                        return;
                    }
                    if(coin > p.c.xu){
                        p.conn.sendMessageLog("Bạn không đủ tiền!");
                        return;  
                    }
                    int rand = Util.nextInt(0,9);
                    long now = System.currentTimeMillis();
                    long timeDatCuoc = now;
                    int num = (int) ((System.currentTimeMillis()- timeDatCuoc) / 1000);
                    if (10 - num >= 0) {
                        Server.manager.sendTB(p,"Đang chờ kết quả", "Bạn đang chọn Chẵn\n-----------\n"
                        + "Bạn đang đặt "+ coin +" xu\n-----------\n"
                        + "Vui lòng chờ sau  "+ (10 - num) + "s\n-----------\n"
                        + "CHÚ Ý: TẮT TAB NÀY ĐỂ DỪNG CUỘC CHƠI!");
                        Thread.sleep(10000);
                        switch (rand) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 9:
                            {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Lẽ (" + rand + ")\nBạn chọn Chẵn\nBạn thua!\n-----------\n"
                                + "Bạn bị trừ "+ coin +" xu");
                                p.c.upxuMessage(-coin);
                                p.sendAddchatYellow("Bạn bị trừ " + coin + " xu");
                                LichSu.LichSuChanLeXu(p.c.name, " Đặt vào chẵn thua Mất " + coin, p.c.xu + coin, p.c.xu,coin);
                                String text = "Chúc mừng " + p.c.name + " đã bị ADMIN luộc lại " + coin + " xu trong trò chơi chẵn lẽ. Quá non";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            case 0:
                            case 2:
                            case 4:
                            case 6:
                            case 8: {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Chẵn ("+ rand +")\nBạn chọn Chẵn\nBạn thắng!\n-----------\n"
                                + "Bạn nhận được " + (coin / 100 * 80) + " xu");
                                p.c.upxuMessage((coin / 100 * 80));
                                p.sendAddchatYellow("Bạn nhận được "+ (coin / 100 * 80) + " xu");
                                LichSu.LichSuChanLeXu(p.c.name, " Đặt vào chẵn " + coin + " thắng được " + (coin / 100 * 80), p.c.xu - (coin / 100 * 80), p.c.xu,(coin / 100 * 80));
                                String text = "Chúc mừng " + p.c.name + " đã lấy được " + (coin / 100 * 80) + " xu của ADMIN vì đã chọn chẵn trong trò chơi chẵn lẽ!";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            default: {
//                                Server.manager.sendTB(p,"Kết quả", "Kết quả: (" + rand + ")\nBạn thua!\n-----------\n"
//                                        + "Bạn bị trừ " + coin + " xu");
//                                p.c.upxuMessage(-coin);
//                                p.sendAddchatYellow("Bạn bị trừ " + coin + " xu");
//                                String text = "Chúc mừng " + p.c.name + " đã bị ADMIN luộc lại " + coin + " xu trong trò chơi chẵn lẽ. Quá non";
//                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                        }
                    }
                } catch (Exception ex) {
                    p.conn.sendMessageLog("Không hợp lệ");
//                    ex.printStackTrace();
                } 
                break;
            }
            //chẵn lượng
            case -71: {
                try {
                    int luongcuoc = Integer.parseInt(str);
                    if (luongcuoc < 10 || luongcuoc > 100000) {
                        p.conn.sendMessageLog("Mức cược chỉ được trong khoảng 10 lượng - 100.000 lượng");
                        return;
                    }
                    if(luongcuoc > p.luong){
                        p.conn.sendMessageLog("Bạn không đủ tiền!");
                        return;  
                    }
                    int rand = Util.nextInt(0,9);
                    long now = System.currentTimeMillis();
                    long timeDatCuoc = now;
                    int num = (int) ((System.currentTimeMillis()- timeDatCuoc) / 1000);
                    if (10 - num >= 0) {
                        Server.manager.sendTB(p,"Đang chờ kết quả", "Bạn đang chọn Chẵn\n-----------\n"
                        + "Bạn đang đặt " + luongcuoc + " lượng\n-----------\n"
                        + "Vui lòng chờ sau  "+ (10 - num) + "s\n-----------\n"
                        + "CHÚ Ý: TẮT TAB NÀY ĐỂ DỪNG CUỘC CHƠI!");
                        Thread.sleep(10000);
                        switch (rand) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 9: 
                            {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Lẽ (" + rand + ")\nBạn chọn Chẵn\nBạn thua!\n-----------\n"
                                + "Bạn bị trừ " + luongcuoc + " lượng");
                                p.upluongMessage(-luongcuoc);
                                p.sendAddchatYellow("Bạn bị trừ " + luongcuoc + " lượng");
                                LichSu.LichSuChanLeLuong(p.c.name, " Đặt vào chẵn thua Mất " + luongcuoc, p.luong + luongcuoc, p.luong,luongcuoc);
                                String text = "Cụ " + p.c.name + " đã bị ADMIN luộc " + luongcuoc + " lượng. hihi";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            case 0:
                            case 2:
                            case 4:
                            case 6:
                            case 8: {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Chẵn (" + rand + ")\nBạn chọn Chẵn\nBạn thắng!\n-----------\n"
                                + "Bạn nhận được " + (luongcuoc / 100 * 80) + " lượng");
                                p.upluongMessage((luongcuoc / 100 * 80));
                                p.sendAddchatYellow("Bạn nhận được " + (luongcuoc / 100 * 80) + " lượng");
                                LichSu.LichSuChanLeLuong(p.c.name, " Đặt vào chẵn " + luongcuoc + " thắng được " + (luongcuoc / 100 * 80), p.luong - (luongcuoc / 100 * 80), p.luong, (luongcuoc / 100 * 80));
                                String text = "Chúc mừng " + p.c.name + " đã chiến thắng và nhận được " + (luongcuoc / 100 * 80) + " lượng trong trò chơi chẵn lẽ!";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            default: {
//                                Server.manager.sendTB(p,"Kết quả", "Kết quả: (" + rand + ")\nBạn thua!\n-----------\n"
//                                        + "Bạn bị trừ " + luongcuoc + " lượng");
//                                p.upluongMessage(-luongcuoc);
//                                p.sendAddchatYellow("Bạn bị trừ " + luongcuoc + " lượng");
//                                String text = "Chúc mừng " + p.c.name + " đã bị ADMIN luộc lại " + luongcuoc + " lượng trong trò chơi chẵn lẽ. Quá non";
//                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                        }
                    }   
                } catch (Exception ex) {
                    p.conn.sendMessageLog("Không hợp lệ");
//                    ex.printStackTrace();
                }  
                break;
            }
            //lẽ lượng
            case -72: {
                try {
                    int luongcuoc = Integer.parseInt(str);
                    if (luongcuoc < 10 || luongcuoc > 100000) {
                        p.conn.sendMessageLog("Mức cược chỉ được trong khoảng 10 lượng - 100.000 lượng");
                        return;
                    }
                    if(luongcuoc > p.luong){
                        p.conn.sendMessageLog("Bạn không đủ tiền!");
                        return;  
                    }
                    int rand = Util.nextInt(0,9);
                    long now = System.currentTimeMillis();
                    long timeDatCuoc = now;
                    int num = (int) ((System.currentTimeMillis()- timeDatCuoc) / 1000);
                    if (10 - num >= 0) {
                        Server.manager.sendTB(p,"Đang chờ kết quả", "Bạn đang chọn Lẽ\n-----------\n"
                        + "Bạn đang đặt " + luongcuoc + " lượng\n-----------\n"
                        + "Vui lòng chờ sau  "+ (10 - num) + "s\n-----------\n"
                        + "CHÚ Ý: TẮT TAB NÀY ĐỂ DỪNG CUỘC CHƠI!");
                        Thread.sleep(10000);
                        switch (rand) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 9: 
                            {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Lẽ (" + rand + ")\nBạn chọn Lẽ\nBạn thắng!\n-----------\n"
                                + "Bạn nhận được " + (luongcuoc / 100 * 80) + " lượng");
                                p.upluongMessage((luongcuoc / 100 * 80));
                                LichSu.LichSuChanLeLuong(p.c.name, " Đặt vào lẽ " + luongcuoc + " thắng được " + (luongcuoc / 100 * 80), p.luong - (luongcuoc / 100 * 80), p.luong,(luongcuoc / 100 * 80));
                                p.sendAddchatYellow("Bạn nhận được " + luongcuoc + " lượng");
                                String text = "Chúc mừng " + p.c.name + " đã chiến thắng và nhận được " + (luongcuoc / 100 * 80) + " lượng từ ADMIN trong trò chơi chẵn lẽ!";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            case 0:
                            case 2:
                            case 4:
                            case 6:
                            case 8: {
                                Server.manager.sendTB(p,"Kết quả", "Kết quả: Chẵn (" + rand + ")\nBạn chọn Lẽ\nBạn thua!\n-----------\n"
                                + "Bạn bị trừ " + luongcuoc + " lượng");
                                p.upluongMessage(-luongcuoc);
                                p.sendAddchatYellow("Bạn bị trừ " + luongcuoc + " lượng");
                                LichSu.LichSuChanLeLuong(p.c.name, " Đặt vào lẽ thua Mất " + luongcuoc, p.luong + luongcuoc, p.luong,luongcuoc);
                                String text = "Cụ " + p.c.name + " đã bị ADMIN luộc " + luongcuoc + " lượng. hihi";
                                Manager.serverChat("Hệ thống", text);
                                break;
                            }
                            default: {
//                                Server.manager.sendTB(p,"Kết quả", "Kết quả: (" + rand + ")\nBạn thua!\n-----------\n"
//                                        + "Bạn bị trừ " + luongcuoc + " lượng");
//                                p.upluongMessage(-luongcuoc);
//                                p.sendAddchatYellow("Bạn bị trừ " + luongcuoc + " lượng");
//                                String text = "Chúc mừng " + p.c.name + " đã bị ADMIN luộc lại " + luongcuoc + " lượng trong trò chơi chẵn lẽ. Quá non";
//                                Manager.serverChat("Hệ thống", text);
                                break;
                            } 
                        }
                    }
                } catch (Exception ex) {
                    p.conn.sendMessageLog("Không hợp lệ");
//                    ex.printStackTrace();
                }
                break;
            }
            // vòng xoay
            case 100: {
                if (p.c.isNhanban) {
                    p.sendAddchatYellow(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                try {
                    String num = str.replaceAll(" ", "").trim();
                    if (num.length() > 10 || !Util.checkNumInt(num) || b < 0 || b >= Server.manager.rotationluck.length) {
                        return;
                    }
                    int xujoin = Integer.parseInt(num);
                    Server.manager.rotationluck[b].joinLuck(p, xujoin);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            }
            case 101: {
                if (p.c.isNhanban) {
                    p.sendAddchatYellow(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (b < 0) {
                    return;
                }
                if (b == 0 && p.c.isTaskDanhVong == 1 && p.c.taskDanhVong[0] == 0 && p.c.taskDanhVong[1] < p.c.taskDanhVong[2]) {
                    p.c.taskDanhVong[1]++;
                }
                if (b == 1 && p.c.isTaskDanhVong == 1 && p.c.taskDanhVong[0] == 1 && p.c.taskDanhVong[1] < p.c.taskDanhVong[2]) {
                    p.c.taskDanhVong[1]++;
                }
                Server.manager.rotationluck[b].luckMessage(p);
                break;
            }
            case 102: {
                if (p.c.isNhanban) {
                    p.sendAddchatYellow(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                p.typemenu = 92;
                Menu.doMenuArray(p, new String[]{"Vòng Xoay VIP", "Vòng Xoay Thường"});
                break;
            }
            case 1: {
                Swap.VanBienLenh(p, m, str);
                break;
            }
            case 2: {
                Swap.MoiLoiDai(p, m, str);
                break;
            }
            case 3: {
                Swap.DatCuocLoiDai(p, m, str);
                break;
            }
            case 4: {
                Swap.GiftCode(p, m, str);
                break;
            }
            //Mời gia tộc chiến
            case 5: {
                Swap.MoiGiaTocChien(p, m, str);
                break;
            }
            //Đặt cược gia tộc chiến
            case 8: {
                Swap.DatCuocGiaTocChien(p, m, str);
                break;
            }
            //Đổi coin => lượng
            case 9: {
                Swap.DoiCoinRaLuong(p, m, str);
                break;
            }
             case 10: {
                Swap.DoiCoinRaXu(p, m, str);
                break;
            }
//            case 10: {
//                Swap.DoiLuongRaXu(p, m, str);
//                break;
//            }
            case 11: {
                Swap.DoiLuongRaYen(p, m, str);
                break;
            }
            case 50: {
                try {
                    ClanManager.createClan(p, str);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            }

            case 9990: {
                MenuAdmin.ThayDoiExp(p, m, str);
                break;
            }
            //Thong bao
            case 9991: {
                MenuAdmin.DangThongBao(p, m, str);
                break;
            }
            //kỹ năng
            case 9992: {
                MenuAdmin.CongKyNang(p, m, str);
                break;
            }
            //tiềm năng
            case 9993: {
                MenuAdmin.CongTiemNang(p, m, str);
                break;
            }
            //tăng level
            case 9994: {
                MenuAdmin.TangLevel(p, m, str);
                break;
            }

            //tăng lượng
            case 9995: {
                MenuAdmin.TangLuong(p, m, str);
                break;
            }

            //tăng xu
            case 9996: {
                MenuAdmin.TangXu(p, m, str);
                break;
            }
            //tăng yên
            case 9997: {
                MenuAdmin.TangYen(p, m, str);
                break;
            }

            //bảo trì
            case 9998: {
                MenuAdmin.BaoTri(p, m, str);
                break;
            }

            //khoá tài khoản
            case 9999: {
                MenuAdmin.KhoaTaiKhoan(p, m, str);
                break;
            }
            
            case 555: {
                try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 100000000) {
                        //thông báo
                        p.conn.sendMessageLog("Cược Tối Đa 100.000.000 Xu");
                        return;
                    }
                    int jointai = Integer.parseInt(str);
                    if (jointai % 10 != 0) {
                        p.conn.sendMessageLog("Số Tiền Cược Phải Chia Hết Cho 10.");
                        return;
                    }
                    Server.manager.TaiXiu[0].joinTai(p, jointai);
                } catch (NumberFormatException e) {
                    p.conn.sendMessageLog("Không Xác Định.");
                }
                break;
            }
            case 556: {
                try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 100000000) {
                        //thông báo
                        p.conn.sendMessageLog("Cược Tối Đa 100.000.000 Xu");
                        return;
                    }
                    int joinxiu = Integer.parseInt(str);
                    if (joinxiu % 10 != 0) {
                        p.conn.sendMessageLog("Số Tiền Cược Phải Chia Hết Cho 10.");
                        return;
                    }
                    Server.manager.TaiXiu[0].joinXiu(p, joinxiu);
                } catch (NumberFormatException e) {
                    p.conn.sendMessageLog("Không Xác Định.");
                }
                break;
            }
            case 1010:
                try {
                    p.nameUS = str;
                    Char a1 = Client.gI().getNinja(str);
                    if (a1 != null) {
                        Menu.sendWrite(p, (short) 1011, "Nhập lượng:");
                    } else {
                        p.sendAddchatYellow("Nhân vật này không tồn tại hoặc không online.");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            case 1011:
                try {
                    p.luongGF = str;
                    Send.sendLuong(p);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            case 1012:
                try {
                    p.nameUS = str;
                    Char a2 = Client.gI().getNinja(str);
                    if (a2 != null) {
                        Menu.sendWrite(p, (short) 1013, "Nhập xu:");
                    } else {
                        p.sendAddchatYellow("Nhân vật này không tồn tại hoặc không online.");
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
            case 1013:
                try {
                    p.xuGF = str;
                    Send.sendXu(p);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Không hợp lệ");
                }
                break;
                case -100: {
                if(p.c.isNhanban) {
                    p.conn.sendMessageLog("Chức năng này không dành cho phân thân");
                    return;
                }
                if(str.indexOf(" ")!=-1 || str.indexOf("[")!=-1 || str.indexOf("]")!=-1 || str.indexOf("(")!=-1 || str.indexOf(")")!=-1) {
                    p.conn.sendMessageLog("Tên không được có dấu cách và các dấu ngoặc");
                    return;
                }
                if(!str.matches("^[a-z A-Z 0-9]{1,50}$")) {
                    p.conn.sendMessageLog("Tên không được có dấu và ký tự đặc biệt");
                    return;
                }
                if(str.indexOf(";")!=-1) {
                return;
                }
                if (str.length() >= 2 && str.length() <= 15) {
                    synchronized(Server.LOCK_MYSQL) {
                        ResultSet red = SQLManager.stat.executeQuery("SELECT `id` FROM `ninja` WHERE `name`LIKE'" + str + "';");
                        if (red != null && red.first()) {
                            p.conn.sendMessageLog("Tên nhân vật đã tồn tại!");
                            return;
                        }
                        red.close();
                        LichSu.Lichsudoiten(str, p);
                        try
                            {
                            if(p.c.clan!=null) {
                                ClanManager clan = ClanManager.getClanName(p.c.clan.clanName);
                                if(clan!=null) {
                                    if(p.c.clan.typeclan==4) {
                                    clan.dissolution();
                                    } else {
                                        clan.OutClan(p);
                                    }
                                }
                            }
                            } catch (Exception e) {
                            }
                            SQLManager.stat.executeUpdate("UPDATE `ninja` SET `name` = \""+ str+"\" WHERE `name` = '"+p.c.name+"';");
                            SQLManager.stat.executeUpdate("UPDATE `player` SET `ninja` = \"[\\\""+ str+"\\\"]\" WHERE `id` = "+p.id+";");
                            SQLManager.stat.executeUpdate("UPDATE `clone_ninja` SET `name` = \""+ str+"\" WHERE `name` = '"+p.c.name+"';");
                            p.c.name=str;
                            p.removeAllEffect();
                            p.c.tileMap.leave(p);
                            p.sendInfo();
                            p.c.removeItemBags(888, 1);
                            p.conn.sendMessageLog("Đổi tên thành công, bạn sẽ tự out trong 5s nữa");
                            TimeUnit.MILLISECONDS.sleep(5000);
                            Client.gI().kickSession(p.conn);
                        }
                    } else {
                        p.conn.sendMessageLog("Tên nhân vật phải có chiều dài từ 2 đến 15 ký tự!");
                    }
                    break;
                }
            default: {
                break;
            }
        }

    }
}
