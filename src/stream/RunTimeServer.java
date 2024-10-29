package stream;

import assembly.ClanManager;
import assembly.Map;
import io.Util;
import server.Manager;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import server.Service;
import server.panel;
import thiendiabang.ThienDiaBangManager;

public class RunTimeServer extends Thread {

    private static final int[] hoursRefreshBossThapVi = new int[]{21};
    private static final boolean[] isRefreshBossThapVi = new boolean[]{false, false, false, false,false, false, false, false};
    private static final short[] mapBossThapVi = new short[]{139, 140, 141, 142, 143, 144, 145, 146};
    private static final short[] LevelBossThapVi = new short[]{60, 70, 80, 90, 100, 110, 120, 130};
    
    private static final int[] hourRefreshBossTheGioi = new int[]{23, 12};
    private static final boolean[] isRefreshBossTheGioi = new boolean[]{false, false};
    private static final short[] MapBossTheGioiRandom = new short[]{23, 61, 47, 49, 42};

//    private static final int[] hoursRefreshBossEvent = new int[]{12};
//    private static final boolean[] isRefreshBossEvent = new boolean[]{false};
//    private static final short[] mapBossEvent = new short[]{26};
    private static final int[] hoursRefreshBossLangCo = new int[]{8, 13, 19, 24};
    private static final boolean[] isRefreshBossLangCo = new boolean[]{false, false, false, false};
    private static final short[] mapBossLangCo = new short[]{134, 135, 136, 137};
    // BOSS VDMQ
    private static final int[] hoursRefreshBossVDMQ = new int[]{7, 12, 18, 23};
    private static final boolean[] isRefreshBossVDMQ = new boolean[]{false, false, false, false};
    private static final short[] mapBossVDMQ = new short[]{141, 142, 143};
    // BOSS MAP THƯỜNG
    private static final int[] hoursRefreshBoss = new int[]{6, 11, 17, 22};
    private static final boolean[] isRefreshBoss = new boolean[]{false, false, false, false};
    private static final short[] mapBoss45 = new short[]{14, 15, 16, 34, 35, 52, 68};
    private static final short[] mapBoss55 = new short[]{44, 67};
    private static final short[] mapBoss65 = new short[]{24, 41, 45, 59};
    private static final short[] mapBoss75 = new short[]{18, 36, 54};
    public String[] Tips = new String[]{
        "Chúc Các Bạn Chơi Game Vui Vẻ Không Vui Cũng Phải Vui.",
        "Sông Nho nước chảy đôi bờ, Hai năm nghĩa vụ em chờ anh không?.",
        "Tham Gia Box Chat Zalo Để Nhận Các Thông Báo Sự Kiện Mới Nhất ",
        "Thần Thú sẽ xuất hiện vào lúc : 6h, 7h, 11h, 12h, 18h, 22h, 23h",
        "Tính năng bán hàng Chợ Đen NPC Shinwa đang gặp lỗi, nên hạn chế bán vật phẩm tránh thất lạc",
        "Sông Nho nước chảy đôi bờ, Hai năm nghĩa vụ em chờ anh không?",
        "Để Vật Phẩm Có Số Lượng Quá 30.000 Sẽ Bị Mất",
        "Hoa trinh nữ đã mang tấm lòng tự ái Yêu em rồi đừng hòng tán gái nha anh.",
        "Tình yêu như thể ngục tù. Người ngoài lại dại dột vượt rào vô thăm.",
        "Ở đâu trăng có nhớ người Ở đây đang có một người nhớ trăng",
        "Học không yêu yếu dần rồi chết Yêu không học không ngóc được lên",
        "Cần Hỗ Trợ Vui Lòng Liên Hệ ZALO ADMIN (white)"};

    @Override
    public void run() {
        try {
            ClanManager clan;
            int i;
            Calendar rightNow;
            int hour;
            int min;
            int sec;
            int j;
            byte k;
            Map map;
            while (Server.running) {
                rightNow = Calendar.getInstance();
                hour = rightNow.get(11);
                min = rightNow.get(12);
                sec = rightNow.get(13);
                synchronized (ClanManager.entrys) {
                    for (i = ClanManager.entrys.size() - 1; i >= 0; --i) {
                        if (ClanManager.entrys.get(i) != null) {
                            clan = ClanManager.entrys.get(i);
                            if (Util.compare_Week(Date.from(Instant.now()), Util.getDate(clan.week))) {
                                clan.payfeesClan();
                            }
                        }
                    }
                }
                // TDB 1 Tuần Reset 1 Lần
                synchronized (ThienDiaBangManager.thienDiaBangManager) {
                    if (ThienDiaBangManager.thienDiaBangManager[0] != null) {
                        if (Util.compare_Week(Date.from(Instant.now()), Util.getDate(ThienDiaBangManager.thienDiaBangManager[0].getWeek()))) {
                            ThienDiaBangManager.register = false;
                            ThienDiaBangManager.resetThienDiaBang();
                        }
                    }
                }
                // Chiến Trường
                ChienTruong.SetTimeChienTruongLv50();
                ChienTruong.SetTimeChienTruongLv100();
                ChienTruong.SetTimeResetHangNgay();
                if (ChienTruong.chienTruong != null) {
                    if (ChienTruong.bxhCT.size() > 0) {
                        ChienTruong.bxhCT = ChienTruong.sortBXH(ChienTruong.bxhCT);
                        Service.updateCT();
                    }
                }
                if (hour == 20 && min == 05 && sec == 0) {
                    Manager.serverChat("Thông Báo ", "Chiến trường Trên Level 100 sẽ mở cửa báo danh sau 5 phút. AE khẩn trương lại báo danh tại NPC Rikudo");
                } else if (hour == 12 && min == 05 && sec == 0) {
                    Manager.serverChat("Thông Báo ", "Chiến trường Dưới Level 50 sẽ mở cửa báo danh sau 5 phút. AE khẩn trương lại báo danh tại NPC Rikudo");
                }
                if (hour == 23 && min == 00 && sec == 0) {
                    panel.backupsql();
                    System.out.print("Đã backupsql");
                }
                if ((min % 30 == 0) && sec == 0) {
                    String str = Tips[Util.nextInt(0, 6)];
                    Manager.serverChat("Thông Báo ", "" + str);
                    SaveData saveData = new SaveData();
                    Thread t1 = new Thread(saveData);
                    t1.start();
                    if (!Manager.isSaveData) {
                        t1 = null;
                        saveData = null;
                    }
                }

                if ((sec % 1 == 0 || sec == 0)) {
                    try {
                        if ((Server.manager.TaiXiu[0]).totalxiu > 1 || (Server.manager.TaiXiu[0]).totaltai > 1) {
                            Server.manager.TaiXiu[0].Start();
                        } else {
                            Server.manager.TaiXiu[0].Wait();
                        }
                    } catch (Exception e) {
                    }
                }
                
                for (j = 0; j < RunTimeServer.hourRefreshBossTheGioi.length; ++j) {
                    if (RunTimeServer.hourRefreshBossTheGioi[j] == hour) {
                        if (!RunTimeServer.isRefreshBossTheGioi[j]) {
                            String textchat = "Boss Thế Giới đã xuất hiện ngẫu nhiên tại:";
                            for (k = 0; k < 1; ++k) {
                                map = Manager.getMapid(RunTimeServer.MapBossTheGioiRandom[Util.nextInt(MapBossTheGioiRandom.length - 1)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(1, 28));
                                    if (k == 0) {
                                        textchat = textchat + " " + map.template.name;
                                    } else {
                                        textchat = textchat + ", " + map.template.name;
                                    }
                                    RunTimeServer.isRefreshBossTheGioi[j] = true;
                                }
                            }
                            Manager.chatKTG(textchat);
                        }
                    } else {
                        RunTimeServer.isRefreshBossTheGioi[j] = false;
                    }
                }
                //
//                for (j = 0; j < RunTimeServer.hoursRefreshBossEvent.length; ++j) {
//                    if (RunTimeServer.hoursRefreshBossEvent[j] == hour) {
//                        if (!RunTimeServer.isRefreshBossEvent[j]) {
//                            String textchat = "Boss Sự Kiện đã xuất hiện tại :";
//                            for (k = 0; k < RunTimeServer.mapBossEvent.length; ++k) {
//                                map = Manager.getMapid(RunTimeServer.mapBossEvent[k]);
//                                if (map != null) {
//                                    map.refreshBoss(Util.nextInt(15, 28));
//                                    if (k == 0) {
//                                        textchat = textchat + " " + map.template.name;
//                                    } else {
//                                        textchat = textchat + ", " + map.template.name;
//                                    }
//                                    RunTimeServer.isRefreshBossEvent[j] = true;
//                                }
//                            }
//                            Manager.chatKTG(textchat);
//                        }
//                    } else {
//                        RunTimeServer.isRefreshBossEvent[j] = false;
//                    }
//                }

                for (j = 0; j < RunTimeServer.hoursRefreshBossThapVi.length; ++j) {
                    if (RunTimeServer.hoursRefreshBossThapVi[j] == hour) {
                        if (!RunTimeServer.isRefreshBossThapVi[j]) {
                            for (k = 0; k < RunTimeServer.mapBossThapVi.length; ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBossThapVi[k]);
                                int level = RunTimeServer.LevelBossThapVi[k];
                                if (map != null) {
                                    int khu = Util.nextInt(1,30);
                                    map.refreshBossThapVi(khu);
                                    String textchat = "BOSS thập vĩ level " + level + " đã xuất hiện tại:";
                                    System.out.println("BOSS thập vĩ level " + level + "  đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                                    if (k == 0) {
                                        textchat = textchat + " " + map.template.name;
                                    } else {
                                        textchat = textchat + ", " + map.template.name;
                                    }
                                    Manager.chatKTG(textchat);
                                    RunTimeServer.isRefreshBossThapVi[j] = true;
                                }
                            }
                            
                        }
                    } else {
                        RunTimeServer.isRefreshBossThapVi[j] = false;
                    }
                }
                
                for (j = 0; j < RunTimeServer.hoursRefreshBossLangCo.length; ++j) {
                    if (RunTimeServer.hoursRefreshBossLangCo[j] == hour) {
                        if (!RunTimeServer.isRefreshBossLangCo[j]) {
                            String textchat = "Boss Làng Cổ đã xuất hiện tại:";
                            for (k = 0; k < RunTimeServer.mapBossLangCo.length; ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBossLangCo[k]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(0, 2));
                                    if (k == 0) {
                                        textchat = textchat + " " + map.template.name;
                                    } else {
                                        textchat = textchat + ", " + map.template.name;
                                    }
                                    RunTimeServer.isRefreshBossLangCo[j] = true;
                                }
                            }
                            Manager.chatKTG(textchat);
                        }
                    } else {
                        RunTimeServer.isRefreshBossLangCo[j] = false;
                    }
                }
                for (j = 0; j < RunTimeServer.hoursRefreshBossVDMQ.length; ++j) {
                    if (RunTimeServer.hoursRefreshBossVDMQ[j] == hour) {
                        if (!RunTimeServer.isRefreshBossVDMQ[j]) {
                            String textchat = "Boss Vùng đất ma quỷ đã xuất hiện tại:";
                            for (k = 0; k < RunTimeServer.mapBossVDMQ.length; ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBossVDMQ[k]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    if (k == 0) {
                                        textchat = textchat + " " + map.template.name;
                                    } else {
                                        textchat = textchat + ", " + map.template.name;
                                    }
                                    RunTimeServer.isRefreshBossVDMQ[j] = true;
                                }
                            }
                            Manager.chatKTG(textchat);
                        }
                    } else {
                        RunTimeServer.isRefreshBossVDMQ[j] = false;
                    }
                }
                for (j = 0; j < RunTimeServer.hoursRefreshBoss.length; ++j) {
                    if (RunTimeServer.hoursRefreshBoss[j] == hour) {
                        if (!RunTimeServer.isRefreshBoss[j]) {
                            String textchat = "Thần thú đã xuất hiện tại:";
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBoss75[Util.nextInt(RunTimeServer.mapBoss75.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + " " + map.template.name;
                                    RunTimeServer.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBoss65[Util.nextInt(RunTimeServer.mapBoss65.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    RunTimeServer.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBoss55[Util.nextInt(RunTimeServer.mapBoss55.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    RunTimeServer.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(RunTimeServer.mapBoss45[Util.nextInt(RunTimeServer.mapBoss45.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    RunTimeServer.isRefreshBoss[j] = true;
                                }
                            }
                            Manager.chatKTG(textchat);
                        }
                    } else {
                        RunTimeServer.isRefreshBoss[j] = false;
                    }
                }
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void refreshBoss() {
        Map map;
        int k;
        String textchat;

        // Boss Thế Giới
        textchat = "Boss Thế Giới Đã Xuất Hiện Tại :";
        for (k = 0; k < 1; ++k) {
            map = Manager.getMapid(RunTimeServer.MapBossTheGioiRandom[Util.nextInt(MapBossTheGioiRandom.length - 1)]);
            if (map != null) {
                int khu = Util.nextInt(15, 29);
                map.refreshBoss(khu);
                System.err.println("Boss Thế Giới Đã Xuất Hiện Tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                textchat += (k == 0) ? " " + map.template.name : ", " + map.template.name;
                RunTimeServer.isRefreshBossTheGioi[k] = true;
            }
        }
        Manager.chatKTG(textchat);
        
        // BOSS Vùng đất ma quỷ
        textchat = "BOSS Vùng đất ma quỷ đã xuất hiện tại:";
        for (k = 0; k < RunTimeServer.mapBossVDMQ.length; ++k) {
            map = Manager.getMapid(RunTimeServer.mapBossVDMQ[k]);
            if (map != null) {
                int khu = Util.nextInt(15, 29);
                map.refreshBoss(khu);
                System.out.println("BOSS Vùng đất ma quỷ đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                if (k == 0) {
                    textchat = textchat + " " + map.template.name;
                } else {
                    textchat = textchat + ", " + map.template.name;
                }
                RunTimeServer.isRefreshBossVDMQ[k] = true;
            }
        }
        Manager.chatKTG(textchat);
        
        // Thần thú
        textchat = "Thần thú đã xuất hiện tại:";
        for (k = 0; k < Util.nextInt(1, 2); ++k) {
            map = Manager.getMapid(RunTimeServer.mapBoss75[Util.nextInt(RunTimeServer.mapBoss75.length)]);
            if (map != null) {
                int khu = Util.nextInt(15, 29);
                map.refreshBoss(khu);
                System.out.println("Thần thú Hỏa ngưu vương đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                textchat = textchat + " " + map.template.name;
                RunTimeServer.isRefreshBoss[k] = true;
            }
        }
        for (k = 0; k < Util.nextInt(1, 2); ++k) {
            map = Manager.getMapid(RunTimeServer.mapBoss65[Util.nextInt(RunTimeServer.mapBoss65.length)]);
            if (map != null) {
                int khu = Util.nextInt(15, 29);
                map.refreshBoss(khu);
                System.out.println("Thần thú Samurai chiến tướng đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                textchat = textchat + ", " + map.template.name;
                RunTimeServer.isRefreshBoss[k] = true;
            }
        }
        for (k = 0; k < Util.nextInt(1, 2); ++k) {
            map = Manager.getMapid(RunTimeServer.mapBoss55[Util.nextInt(RunTimeServer.mapBoss55.length)]);
            if (map != null) {
                int khu = Util.nextInt(15, 29);
                map.refreshBoss(khu);
                System.out.println("Thần thú Tướng giặc đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                textchat = textchat + ", " + map.template.name;
                RunTimeServer.isRefreshBoss[k] = true;
            }
        }
        for (k = 0; k < Util.nextInt(1, 2); ++k) {
            map = Manager.getMapid(RunTimeServer.mapBoss45[Util.nextInt(RunTimeServer.mapBoss45.length)]);
            if (map != null) {
                int khu = Util.nextInt(15, 30);
                map.refreshBoss(khu);
                System.out.println("Thần thú Xích phiến thiên long đã xuất hiện tại " + map.template.name + " khu " + khu + " Map id " + map.template.id);
                textchat = textchat + ", " + map.template.name;
                RunTimeServer.isRefreshBoss[k] = true;
            }
        }
        Manager.chatKTG(textchat);
    }       
}
