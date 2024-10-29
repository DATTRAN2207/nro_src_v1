package server;

import assembly.Char;
import assembly.GmShop;
import assembly.Item;
import assembly.ItemSell;
import assembly.Level;
import assembly.Option;
import assembly.Player;
import io.SQLManager;
import io.Util;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import stream.Admin;
import stream.ClearLogin;
import stream.Client;
import stream.RunTimeServer;
import stream.SaveData;
import stream.Server;
import template.ItemTemplate;

public class panel extends javax.swing.JFrame {

    public panel() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Bảo Trì");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cập Nhật BXH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Lưu Data");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("THÔNG BÁO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Clear Clone Login");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Clear Sesion");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Thay đổi exp");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("Ðăng Thông Báo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Ban Người Chơi");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Active Người Chơi");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("gửi Xu yen lg");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setText("Send Trang Bị");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Send Đồ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Mở Ban Người Chơi");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Đổi Event");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Thêm NPC");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Thông Tin");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Thêm Danh Hiệu");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Export SQL");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("add itm shop");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Hồi sinh tất cả boss");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setText("load shop");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("Kick Player");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton11.setText("Buff Vip");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Restart tài xỉu");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton25.setText("Quyền admin");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Danh sách item");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText("add item gm shop");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("load gm shop");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton10)
                    .addComponent(jToggleButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton14)
                    .addComponent(jButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton16)
                    .addComponent(jButton17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton18)
                    .addComponent(jButton20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton23)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton22)
                        .addComponent(jButton11))
                    .addComponent(jButton24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(jButton26)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton28))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Service.AutoSaveData();
        JOptionPane.showMessageDialog(null, "UPDATE BXH THÀNH CÔNG");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String str = JOptionPane.showInputDialog(null, "Nhập giá trị thay đổi:", "Thay đổi EXP", JOptionPane.INFORMATION_MESSAGE);
        if (str == null || str.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá trị thay đổi không hợp lệ");
            return;
        }
        String check = str.replaceAll("\\s+", "").trim();
        try {
            int expup = Integer.parseInt(check);
            if (expup <= 0) {
                expup = 1;
            }
            Manager.up_exp = expup;
            JOptionPane.showMessageDialog(null, "Thay đổi thành công - Giá trị thay đổi "+ expup);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String input = JOptionPane.showInputDialog("Nhập số phút muốn bảo trì 0->30 (0: ngay lập tức)");
        if (input != null && !input.isEmpty()) {
            try {
                int minutes = Integer.parseInt(input);
                if (minutes >= 0 && minutes <= 30) {
                    Thread t1 = new Thread(new Admin(minutes, Server.gI()));
                    t1.start();
                    Server.manager.baotri = 1;
                    JOptionPane.showMessageDialog(null, "Đã kích hoạt bảo trì Server sau " + minutes + " phút.");
                    Manager.serverChat("Thông Báo", "Máy Chủ sẻ bảo trì sau " + minutes + " phút nữa.");
                } else {
                    JOptionPane.showMessageDialog(null, "Số phút không hợp lệ. Vui lòng nhập lại từ 0 đến 30.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Số phút không hợp lệ. Vui lòng nhập lại một số nguyên.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số phút.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SaveData saveData = new SaveData();
        Thread t1 = new Thread(saveData);
        t1.start();
        if(!Manager.isSaveData) {
            t1 = null;
            saveData= null;
        }
        JOptionPane.showMessageDialog(null, "SAVE DATA THÀNH CÔNG");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            ResultSet resultSet = SQLManager.stat.executeQuery("SELECT * FROM `alert` WHERE `id` = 1;");
            if (resultSet != null && resultSet.first()) {
                String alert = resultSet.getString("content");
                Manager.alert.setAlert(alert);
                resultSet.close();
                JOptionPane.showMessageDialog(null, "Cập nhật thông báo thành công");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        synchronized (Client.gI().conns) {
            for (int i = 0; i < Client.gI().conns.size(); ++i) {
                Session conn = (Session) Client.gI().conns.get(i);
                if (conn != null) {
                    Player player = conn.player;
                    if (player != null) {
                        if (player.c == null) {
                            Client.gI().kickSession(conn);
                        }
                    } else if (player == null) {
                        Client.gI().kickSession(conn);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Clear Clone Login Thành Công");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        synchronized (Client.gI().conns) {
            for (int i = 0; i < Client.gI().conns.size(); ++i) {
                Player player = ((Session) Client.gI().conns.get(i)).player;
                if (player != null && player != player) {
                    Client.gI().kickSession(player.conn);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Clear Session Thành Công");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String str = JOptionPane.showInputDialog(null, "Nhập thông báo:", "Ðang thông báo", JOptionPane.INFORMATION_MESSAGE);
        if (str == null || str.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
            return;
        }
        Manager.serverChat("Server", str);
        JOptionPane.showMessageDialog(null, "Đăng Thông Báo Thành Công => " + str );
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
        Char temp = Client.gI().getNinja(str);
        if (temp != null) {
            Player banPlayer = Client.gI().getPlayer(temp.p.username);
            if (banPlayer != null && banPlayer.role != 9999) {
                Client.gI().kickSession(banPlayer.conn);
                try {
                    SQLManager.stat.executeUpdate("UPDATE `player` SET `ban`=1 WHERE `id`=" + banPlayer.id + " LIMIT 1;");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Đã khóa tài khoản: " + banPlayer.username + " - Nhân vật: " + temp.name);
            } else {
                JOptionPane.showMessageDialog(null, "Nhân Vật này là ADMIN hoặc không tìm thấy tài khoản này!");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
        try {
            String selectQuery = "SELECT * FROM `player` WHERE `username`='" + str + "' LIMIT 1;";
            ResultSet resultSet = SQLManager.stat.executeQuery(selectQuery);
            if (resultSet.next()) {
                String updateQuery = "UPDATE `player` SET `lock`=0 WHERE `username`='" + str + "' LIMIT 1;";
                SQLManager.stat.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Đã kích hoạt tài khoản của người chơi: " + str);
            } else {
                JOptionPane.showMessageDialog(null, "Không có tài khoản: " + str, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            JTextField nameField = new JTextField();
            JTextField xuField = new JTextField();
            JTextField luongField = new JTextField();
            JTextField yenField = new JTextField();
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Tên Nhân Vật:"));
            panel.add(nameField);
            panel.add(new JLabel("Số Xu Cần Tặng:"));
            xuField.setText("0");
            panel.add(xuField);
            panel.add(new JLabel("Số Lượng Cần Tăng:"));
            luongField.setText("0");
            panel.add(luongField);
            panel.add(new JLabel("Số Yên Cần Tăng:"));
            yenField.setText("0");
            panel.add(yenField);
            int option = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String nameUS = nameField.getText();
                int xuGFF = Integer.parseInt(xuField.getText());
                int yenGFF = Integer.parseInt(yenField.getText());
                int luongGFF = Integer.parseInt(luongField.getText());
                Char temp = Client.gI().getNinja(nameUS);
                if (temp != null) {
                    if (xuGFF != 0) {
                        temp.upxuMessage(xuGFF);
                    }
                    if (yenGFF != 0) {
                        temp.upyenMessage(yenGFF);
                    }
                    if (luongGFF != 0) {
                        temp.p.upluongMessage(luongGFF);
                    }
                    JOptionPane.showMessageDialog(null, "Đã cộng " + xuGFF + " xu " + luongGFF + " lượng " + yenGFF + " yên cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            JTextField nameTextField = new JTextField();
            JTextField idItemTextField = new JTextField();
            JTextField itemQuantityTextField = new JTextField();
            JTextField itemUpgradeTextField = new JTextField();
            JTextField itemSysTextField = new JTextField();
            panel.add(new JLabel("Nhập tên nhân vật:"));
            panel.add(nameTextField);
            panel.add(new JLabel("Nhập ID vật phẩm:"));
            panel.add(idItemTextField);
            panel.add(new JLabel("Nhập số lượng vật phẩm:"));
            panel.add(itemQuantityTextField);
            panel.add(new JLabel("Nhập cấp độ nâng cấp vật phẩm:"));
            panel.add(itemUpgradeTextField);
            panel.add(new JLabel("Nhập hệ thống vật phẩm:"));
            panel.add(itemSysTextField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm vật phẩm cho nhân vật", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String nameUS = nameTextField.getText();
                int idItemGFF = Integer.parseInt(idItemTextField.getText());
                int itemQuantityGFF = Integer.parseInt(itemQuantityTextField.getText());
                int itemUpgradeGFF = Integer.parseInt(itemUpgradeTextField.getText());
                byte itemSysGFF = Byte.parseByte(itemSysTextField.getText());
                Char userGF = Client.gI().getNinja(nameUS);
                if (userGF != null) {
                    Item itemGF;
                    for (byte i = 0; i < itemQuantityGFF; i++) {
                        itemGF = ItemTemplate.itemDefault(idItemGFF);
                        ItemTemplate data2 = ItemTemplate.ItemTemplateId(idItemGFF);
                        Item itemup;
                        if (data2.type < 10) {
                            if (data2.type == 1) {
                                itemup = ItemTemplate.itemDefault(idItemGFF);
                                itemup.sys = GameSrc.SysClass(data2.nclass);
                            } else {
                                byte sys = itemSysGFF;
                                itemup = ItemTemplate.itemDefault(idItemGFF, sys);
                            }
                        } else {
                            itemup = ItemTemplate.itemDefault(idItemGFF);
                        }
                        itemup.isLock = false;
                        int idOp2;
                        for (Option option : itemup.options) {
                            idOp2 = option.id;
                        }
                        itemup.upgradeNext((byte) itemUpgradeGFF);
                        userGF.addItemBag(true, itemup);
                    }
                    JOptionPane.showMessageDialog(null, "Đã thêm " + itemQuantityGFF + " vật phẩm có ID " + idItemGFF + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            JTextField nameTextField = new JTextField();
            JTextField idItemTextField = new JTextField();
            JTextField itemQuantityTextField = new JTextField();
            panel.add(new JLabel("Nhập tên nhân vật:"));
            panel.add(nameTextField);
            panel.add(new JLabel("Nhập ID vật phẩm:"));
            idItemTextField.setText("0");
            panel.add(idItemTextField);
            panel.add(new JLabel("Nhập số lượng vật phẩm:"));
            itemQuantityTextField.setText("1");
            panel.add(itemQuantityTextField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm vật phẩm cho nhân vật", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String nameUS = nameTextField.getText();
                int idItemGFF = Integer.parseInt(idItemTextField.getText());
                int itemQuantityGFF = Integer.parseInt(itemQuantityTextField.getText());
                Char userGF = Client.gI().getNinja(nameUS);
                if (userGF != null) {
                    Item itemGF;
                    if (itemQuantityGFF < 120) {
                        for (byte i = 0; i < itemQuantityGFF; i++) {
                            itemGF = ItemTemplate.itemDefault(idItemGFF);
                            ItemTemplate data2 = ItemTemplate.ItemTemplateId(idItemGFF);
                            Item itemup;
                            if (data2.type < 10) {
                                if (data2.type == 1) {
                                    itemup = ItemTemplate.itemDefault(idItemGFF);
                                    itemup.sys = GameSrc.SysClass(data2.nclass);
                                } else {
                                    byte sys = (byte) Util.nextInt(1, 3);
                                    itemup = ItemTemplate.itemDefault(idItemGFF, sys);
                                }
                            } else {
                                itemup = ItemTemplate.itemDefault(idItemGFF);
                            }
                            itemup.isLock = false;
                            int idOp2;
                            for (Option option : itemup.options) {
                                idOp2 = option.id;
                            }
                            userGF.addItemBag(true, itemup);
                        }
                    } else {
                        itemGF = ItemTemplate.itemDefault(idItemGFF);
                        itemGF.quantity = itemQuantityGFF;
                        userGF.addItemBag(true, itemGF);
                    }
                    JOptionPane.showMessageDialog(null, "Đã thêm " + itemQuantityGFF + " vật phẩm có ID " + idItemGFF + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        String[] eventNames = {"Không sự kiện", "Sự kiện hè", "Sự kiện trung thu", "Sự kiện 20 tháng 10", "Sự kiện 8/3", "Sự kiện haloween", "Sự kiện giỗ tổ", "Sự kiện Dưa Hấu", "Sự kiện Vu Lân", "Sự đá ma thuật",
        "Sự kiện noel", "Sự kiện tết bành chưng bánh tét", "Sự kiện tết mâm ngủ quá"};
        JList<String> eventList = new JList<>(eventNames);
        JScrollPane scrollPane = new JScrollPane(eventList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        JButton okButton = new JButton("chọn");
        panel.add(okButton, BorderLayout.SOUTH);
        okButton.addActionListener(e -> {
            int selectedEventIndex = eventList.getSelectedIndex();
            if (selectedEventIndex != -1) {
                byte eventID = (byte) selectedEventIndex;
                Server.manager.event = eventID;
                JOptionPane.showMessageDialog(null, "Thay Đổi Sự Kiện Thành Công: Sự kiện là " + eventNames[selectedEventIndex]);
            }
        });
        int option = JOptionPane.showOptionDialog(
                null,
                panel,
                "Chọn Sự Kiện",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                eventNames[0]
        );
        if (option == JOptionPane.CLOSED_OPTION) {
            return;
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String str = JOptionPane.showInputDialog(null, "Nhập Tên Tài Khoản", "Tên Tài Khoản", JOptionPane.INFORMATION_MESSAGE);
        try {
            String selectQuery = "SELECT * FROM `player` WHERE `username`='" + str + "' LIMIT 1;";
            ResultSet resultSet = SQLManager.stat.executeQuery(selectQuery);

            if (resultSet.next()) {
                String updateQuery = "UPDATE `player` SET `ban`=0 WHERE `username`='" + str + "' LIMIT 1;";
                SQLManager.stat.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Đã mở ban tài khoản của người chơi: " + str);
            } else {
                JOptionPane.showMessageDialog(null, "Không có tài khoản: " + str, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String databaseName = Manager.mysql_database_data;
        JTextField npcIdField = new JTextField();
        JTextField xCoordinateField = new JTextField();
        JTextField yCoordinateField = new JTextField();
        JTextField mapIdField = new JTextField();

        Object[] message = {
            "NPC ID:", npcIdField,
            "X Coordinate:", xCoordinateField,
            "Y Coordinate:", yCoordinateField,
            "Map ID:", mapIdField
        };

        int option = JOptionPane.showOptionDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int npcId = Integer.parseInt(npcIdField.getText());
                int xCoordinate = Integer.parseInt(xCoordinateField.getText());
                int yCoordinate = Integer.parseInt(yCoordinateField.getText());
                int mapId = Integer.parseInt(mapIdField.getText());

                try {
                    PreparedStatement selectStatement = SQLManager.conn.prepareStatement("SELECT NPC FROM `" + databaseName + "`.`map` WHERE id = ?");
                    selectStatement.setInt(1, mapId);
                    ResultSet resultSet = selectStatement.executeQuery();

                   String npcData = "";

                    if (resultSet.next()) {
                    npcData = resultSet.getString("NPC");
                }

                        if (npcData != null && !npcData.isEmpty()) {
                    npcData = npcData.substring(0, npcData.length() - 1);
                    npcData += ",";
                }
                  npcData += String.format("[1,%d,%d,%d]]", xCoordinate, yCoordinate, npcId, mapId);

                    PreparedStatement updateStatement = SQLManager.conn.prepareStatement("UPDATE `" + databaseName + "`.`map` SET NPC = ? WHERE id = ?");
                    updateStatement.setString(1, npcData);
                    updateStatement.setInt(2, mapId);
                    updateStatement.executeUpdate();
                    System.out.println("Cập nhật tọa độ NPC thành công.");

                    // Đóng câu lệnh
                    updateStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (NumberFormatException ex) {
                String errorMessage = "Định dạng nhập không hợp lệ. Vui lòng nhập giá trị số cho ID, Tọa độ X, Tọa độ Y, và ID Bản đồ.";
                JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
    String message = String.format(
        "<html><body style='color:red;'><p style='width: 200px;'>Tổng số kết nối: %d<br/>Số người đăng nhập: %d<br/>TỔNG SỐ NGƯỜI CHƠI THỰC TẾ: %d</p></body></html>",
        Client.gI().conns_size(), Client.gI().players_size(), Client.gI().ninja_size()
    );
    JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            JPanel panel = new JPanel(new GridLayout(0, 2));

            JTextField nameTextField = new JTextField();
            JTextField idbattatTextField = new JTextField();
            JTextField ideffTextField = new JTextField();
            panel.add(new JLabel("Nhập tên nhân vật:"));
            panel.add(nameTextField);
            panel.add(new JLabel("Nhập 1 là bật Nhập 0 tắt:"));
            panel.add(idbattatTextField);
            panel.add(new JLabel("Nhập id effect:"));
            panel.add(ideffTextField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm effect cho người chơi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nameUS = nameTextField.getText();
                int battat = Integer.parseInt(idbattatTextField.getText());
                int ideff = Integer.parseInt(ideffTextField.getText());
                Char userGF = Client.gI().getNinja(nameUS);
                if (userGF != null) {
                    userGF.p.c.addeff[0] = battat;
                    userGF.p.c.addeff[1] = ideff;
                    JOptionPane.showMessageDialog(null, "Đã thêm effect id " + ideff + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
//        DatabaseExporter.export();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        String databaseName = Manager.mysql_database_data;
        String checkidshop = "SELECT ListItem FROM `" + databaseName + "`.`itemsell` WHERE id = ?";
        String checkidnpc = "UPDATE `" + databaseName + "`.`itemsell` SET ListItem = ? WHERE id = ?";
        JTextField shopIdField = new JTextField();
        shopIdField.setText("1");
        JTextField IdItemField = new JTextField();
        IdItemField.setText("0");
        JTextField ExpiresField = new JTextField();
        ExpiresField.setText("-1");
        JTextField buyCoinField = new JTextField();
        buyCoinField.setText("0");
        JTextField buyCoinLockField = new JTextField();
        buyCoinLockField.setText("0");
        JTextField buyGoldField = new JTextField();
        buyGoldField.setText("0");
        JTextField isLockField = new JTextField();
        isLockField.setText("false");
        JTextField isExpiresField = new JTextField();
        isExpiresField.setText("false");
        JTextField OptionField = new JTextField();
        OptionField.setText("[]");

        Object[] message = {
            "Shop ID:", shopIdField,
            "Item ID:", IdItemField,
            "Expires:", ExpiresField,
            "Giá xu:", buyCoinField,
            "Giá yên:", buyCoinLockField,
            "Giá lượng:", buyGoldField,
            "isLock:", isLockField,
            "isExpires:", isExpiresField,
            "Option mặc định là []:", OptionField
        };

        int option = JOptionPane.showOptionDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int shopId = Integer.parseInt(shopIdField.getText());
                int itemId = Integer.parseInt(IdItemField.getText());
                int Expires = Integer.parseInt(ExpiresField.getText());
                int buyCoin = Integer.parseInt(buyCoinField.getText());
                int buyCoinLock = Integer.parseInt(buyCoinLockField.getText());
                int buyGold = Integer.parseInt(buyGoldField.getText());
                String isLock = isLockField.getText();
                String isExpires = isExpiresField.getText();
                String Option = OptionField.getText();
                try {
                    PreparedStatement selectStatement = SQLManager.conn.prepareStatement(checkidshop);
                    selectStatement.setInt(1, shopId);
                    ResultSet resultSet = selectStatement.executeQuery();
                    String itemsellData = "";
                    if (resultSet.next()) {
                        itemsellData = resultSet.getString("ListItem");
                    }
                    if (itemsellData != null && !itemsellData.isEmpty()) {
                        itemsellData = itemsellData.substring(0, itemsellData.length() - 1);
                        itemsellData += ",";
                    }
                    String itemsellData1 = "{\"id\":"+ itemId +",\"expires\":"+ Expires +",\"buyCoin\":"+ buyCoin +",\"buyCoinLock\":"+ buyCoinLock +","
                    + "\"buyGold\":"+ buyGold +",\"isLock\":"+ isLock +",\"sale\":0,\"quantity\":1,\"upgrade\":0,\"sys\":0,\"isExpires\":"+ isExpires +",\"option\":"+ Option +"}\n]";
                    itemsellData += itemsellData1;
                    PreparedStatement updateStatement = SQLManager.conn.prepareStatement(checkidnpc);
                    updateStatement.setString(1, itemsellData);
                    updateStatement.setInt(2, shopId);
                    updateStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Thêm Item Vào Shop Thành Công.");
                    updateStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (NumberFormatException ex) {
                String errorMessage = "Định dạng nhập không hợp lệ. Vui lòng nhập đầy đủ.";
                JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        RunTimeServer.refreshBoss();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        updateShopNPC();
        JOptionPane.showMessageDialog(null, "UPDATE SHOP THÀNH CÔNG");
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        ClearLogin ClearLogin = new ClearLogin();
        Thread t1 = new Thread(ClearLogin);
        t1.start();
        if (!Manager.isClearCloneLogin) {
            t1 = null;
            ClearLogin = null;
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            JPanel panel = new JPanel(new GridLayout(0, 2));
            JTextField nameTextField = new JTextField();
            JTextField vipTextField = new JTextField();
            panel.add(new JLabel("Nhập tên nhân vật:"));
            panel.add(nameTextField);
            panel.add(new JLabel("Nhập vip muốn buff:"));
            panel.add(vipTextField);
            vipTextField.setText("0");
            int result = JOptionPane.showConfirmDialog(null, panel, "Buff vip cho người chơi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String nameUS = nameTextField.getText();
                int vip = Integer.parseInt(vipTextField.getText());
                Char userGF = Client.gI().getNinja(nameUS);
                if (userGF != null) {
                    if (vip > 0) {
                        userGF.p.c.vip = vip;
                    } else {
                        JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Đã buff vip " + vip + " cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Server.manager.TaiXiu[0].Start();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        String[] menu = {"Bảo trì Server", "Khóa tài khoán", "Mở khóa người chơi", "Cập nhật BXH", "Cộng Yên Xu Lượng", "Tăng level", "Tăng điểm tiềm năng", "Tăng kỹ năng", "Lưu DATA", "Đăng thông báo", "Kiểm tra số người chơi", "Clear Clone Login", "Clear Session", "Thay đổi tăng exp", "CHECK Item", "Cập nhật Thông báo"};
        JList<String> menulist = new JList<>(menu);
        JScrollPane scrollPane = new JScrollPane(menulist);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane);
        JButton okButton = new JButton("ok");
        panel.add(okButton, BorderLayout.SOUTH);
        okButton.addActionListener(e -> {
            int selectedEventIndex = menulist.getSelectedIndex();
            if (selectedEventIndex != -1) {
                switch (selectedEventIndex) {
                    case 0: {
                        String input = JOptionPane.showInputDialog("Nhập số phút muốn bảo trì 0->30 (0: ngay lập tức)");
                        if (input != null && !input.isEmpty()) {
                            try {
                                int minutes = Integer.parseInt(input);
                                if (minutes >= 0 && minutes <= 30) {
                                    Thread t1 = new Thread(new Admin(minutes, Server.gI()));
                                    t1.start();
                                    Server.manager.baotri = 1;
                                    JOptionPane.showMessageDialog(null, "Đã kích hoạt bảo trì Server sau " + minutes + " phút.");
                                    Manager.serverChat("Thông Báo", "Máy Chủ sẻ bảo trì sau " + minutes + " phút nữa.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Số phút không hợp lệ. Vui lòng nhập lại từ 0 đến 30.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Số phút không hợp lệ. Vui lòng nhập lại một số nguyên.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số phút.");
                        }
                        break;
                    }
                    case 1: {
                        String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
                        Char temp = Client.gI().getNinja(str);
                        if (temp != null) {
                            Player banPlayer = Client.gI().getPlayer(temp.p.username);
                            if (banPlayer != null && banPlayer.role != 9999) {
                                Client.gI().kickSession(banPlayer.conn);
                                try {
                                    SQLManager.stat.executeUpdate("UPDATE `player` SET `ban`=1 WHERE `id`=" + banPlayer.id + " LIMIT 1;");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null,"Đã khóa tài khoản: " + banPlayer.username + " - Nhân vật: " + temp.name);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nhân Vật này là ADMIN hoặc không tìm thấy tài khoản này!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
                        }
                        break;
                    }
                    case 2: {
                        String str = JOptionPane.showInputDialog(null, "Nhập Tên Nhân Vật", "Nhập Tên Nhân Vật Người Chơi", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            String selectQuery = "SELECT * FROM `player` WHERE `username`='" + str + "' LIMIT 1;";
                            ResultSet resultSet = SQLManager.stat.executeQuery(selectQuery);
                            if (resultSet.next()) {
                                String updateQuery = "UPDATE `player` SET `ban`=0 WHERE `username`='" + str + "' LIMIT 1;";
                                SQLManager.stat.executeUpdate(updateQuery);
                                JOptionPane.showMessageDialog(null, "Đã tài khoản của người chơi: " + str);
                            } else {
                                JOptionPane.showMessageDialog(null, "Không có tài khoản: " + str, "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                    case 3: {
                        Service.AutoSaveData();
                        JOptionPane.showMessageDialog(null, "UPDATE BXH THÀNH CÔNG");
                        break;
                    }
                    case 4: {
                        try {
                            JTextField nameField = new JTextField();
                            JTextField xuField = new JTextField();
                            JTextField luongField = new JTextField();
                            JTextField yenField = new JTextField();
                            JPanel pane2 = new JPanel(new GridLayout(0, 1));
                            pane2.add(new JLabel("Tên Nhân Vật:"));
                            pane2.add(nameField);
                            pane2.add(new JLabel("Số Xu Cần Tặng:"));
                            xuField.setText("0");
                            pane2.add(xuField);
                            pane2.add(new JLabel("Số Lượng Cần Tăng:"));
                            luongField.setText("0");
                            pane2.add(luongField);
                            pane2.add(new JLabel("Số Yên Cần Tăng:"));
                            yenField.setText("0");
                            pane2.add(yenField);
                            int option = JOptionPane.showConfirmDialog(null, pane2, "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
                            if (option == JOptionPane.OK_OPTION) {
                                String nameUS = nameField.getText();
                                int xuGFF = Integer.parseInt(xuField.getText());
                                int yenGFF = Integer.parseInt(yenField.getText());
                                int luongGFF = Integer.parseInt(luongField.getText());
                                Char temp = Client.gI().getNinja(nameUS);
                                if (temp != null) {
                                    if (xuGFF != 0) {
                                        temp.upxuMessage(xuGFF);
                                    }
                                    if (yenGFF != 0) {
                                        temp.upyenMessage(yenGFF);
                                    }
                                    if (luongGFF != 0) {
                                        temp.p.upluongMessage(luongGFF);
                                    }
                                    JOptionPane.showMessageDialog(null, "Đã cộng " + xuGFF + " xu " + luongGFF + " lượng " + yenGFF + " yên cho người chơi " + nameUS, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân vật " + nameUS, "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Hãy nhập số nguyên hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                    case 5: {
                        try {
                            JPanel pane3 = new JPanel(new GridLayout(0, 2));
                            JTextField nameTextField = new JTextField();
                            JTextField levelTextField = new JTextField();
                            pane3.add(new JLabel("Nhập tên nhân vật:"));
                            pane3.add(nameTextField);
                            pane3.add(new JLabel("Nhập level muốn đặt tới:"));
                            levelTextField.setText("1");
                            pane3.add(levelTextField);
                            int result = JOptionPane.showConfirmDialog(null, pane3, "buff level", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                String nameUS = nameTextField.getText();
                                int levelGFF = Integer.parseInt(levelTextField.getText());
                                Char userGF = Client.gI().getNinja(nameUS);
                                if (userGF != null) {
                                    if (userGF.c.get().exptype == 1) {
                                        if (levelGFF >= 1 && levelGFF <= Server.manager.max_level_up) {
                                            userGF.p.updateExp(Level.getMaxExp(levelGFF) - userGF.p.c.exp);
                                            userGF.p.conn.sendMessageLog("admin đã chỉnh level bạn là " + levelGFF);
                                            userGF.p.restPpoint();
                                            userGF.p.restSpoint();
                                            JOptionPane.showMessageDialog(null,"người chơi " + userGF.p.c.name + " level hiện tại " + userGF.p.c.level);
                                        } else {
                                            JOptionPane.showMessageDialog(null,"level tối thiếu là 1 tối đa là " + Server.manager.max_level_up);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null,"người chơi " + userGF.p.c.name + " Đã bật không nhận exp");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
                                }
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Hãy nhập đúng định dạng");
                        }
                        break;
                    }
                    case 6: {
                        try {
                            JPanel pane4 = new JPanel(new GridLayout(0, 2));
                            JTextField nameTextField = new JTextField();
                            JTextField ppointTextField = new JTextField();
                            pane4.add(new JLabel("Nhập tên nhân vật:"));
                            pane4.add(nameTextField);
                            pane4.add(new JLabel("Nhập tiềm năng muốn cộng:"));
                            ppointTextField.setText("0");
                            pane4.add(ppointTextField);
                            int result = JOptionPane.showConfirmDialog(null, pane4, "buff điếm tiềm năng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                String nameUS = nameTextField.getText();
                                int ppointGFF = Integer.parseInt(ppointTextField.getText());
                                Char userGF = Client.gI().getNinja(nameUS);
                                if (userGF != null) {
                                    if (ppointGFF >= 1 && ppointGFF <= 10000) {
                                        userGF.p.c.get().ppoint += ppointGFF;
                                        userGF.p.loadPpoint();
                                        JOptionPane.showMessageDialog(null,"Đã công " + ppointGFF + " cho nhân vật " + userGF.p.c.name);
                                    } else {
                                        JOptionPane.showMessageDialog(null,"tối thiếu là 1 tối đa là 10000");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
                                }
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Hãy nhập đúng định dạng");
                        }
                        break;
                    }
                    case 7: {
                        try {
                            JPanel pane5 = new JPanel(new GridLayout(0, 2));
                            JTextField nameTextField = new JTextField();
                            JTextField spointTextField = new JTextField();
                            pane5.add(new JLabel("Nhập tên nhân vật:"));
                            pane5.add(nameTextField);
                            pane5.add(new JLabel("Nhập ký năng muốn cộng:"));
                            spointTextField.setText("0");
                            pane5.add(spointTextField);
                            int result = JOptionPane.showConfirmDialog(null, pane5, "buff điêm ký năng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if (result == JOptionPane.OK_OPTION) {
                                String nameUS = nameTextField.getText();
                                int spointGFF = Integer.parseInt(spointTextField.getText());
                                Char userGF = Client.gI().getNinja(nameUS);
                                if (userGF != null) {
                                    if (spointGFF >= 1 && spointGFF <= 10000) {
                                        userGF.p.c.get().spoint += spointGFF;
                                        userGF.p.loadSkill();
                                        JOptionPane.showMessageDialog(null,"Đã công " + spointGFF + " cho nhân vật " + userGF.p.c.name);
                                    } else {
                                        JOptionPane.showMessageDialog(null,"tối thiếu là 1 tối đa là 10000");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,"Người chơi này không tồn tại hoặc không đang online!");
                                }
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Hãy nhập đúng định dạng");
                        }
                        break;
                    }
                    case 8: {
                        SaveData saveData = new SaveData();
                        Thread t1 = new Thread(saveData);
                        t1.start();
                        if (!Manager.isSaveData) {
                            t1 = null;
                            saveData = null;
                        }
                        break;
                    }
                    case 9: {
                        String str = JOptionPane.showInputDialog(null, "Nhập thông báo:", "Ðang thông báo", JOptionPane.INFORMATION_MESSAGE);
                        if (str == null || str.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
                            return;
                        }
                        Manager.serverChat("Server", str);
                        JOptionPane.showMessageDialog(null, "Đăng Thông Báo Thành Công => " + str );
                        break;
                    }
                    case 10: {
                        String message = String.format(
                            "<html><body style='color:red;'><p style='width: 200px;'>Tổng số kết nối: %d<br/>Số người đăng nhập: %d<br/>TỔNG SỐ NGƯỜI CHƠI THỰC TẾ: %d</p></body></html>",
                            Client.gI().conns_size(), Client.gI().players_size(), Client.gI().ninja_size()
                        );
                        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case 11: {
                        synchronized (Client.gI().conns) {
                            for (int i = 0; i < Client.gI().conns.size(); ++i) {
                                Session conn = (Session) Client.gI().conns.get(i);
                                if (conn != null) {
                                    Player player = conn.player;
                                    if (player != null) {
                                        if (player.c == null) {
                                            Client.gI().kickSession(conn);
                                        }
                                    } else if (player == null) {
                                        Client.gI().kickSession(conn);
                                    }
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Clear Clone Login Thành Công");
                        break;
                    }
                    case 12: {
                        synchronized (Client.gI().conns) {
                            for (int i = 0; i < Client.gI().conns.size(); ++i) {
                                Player player = ((Session) Client.gI().conns.get(i)).player;
                                if (player != null && player != player) {
                                    Client.gI().kickSession(player.conn);
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Clear Session Thành Công");
                        break;
                    }
                    case 13: {
                        String str = JOptionPane.showInputDialog(null, "Nhập giá trị thay đổi:", "Thay đổi EXP", JOptionPane.INFORMATION_MESSAGE);
                        if (str == null || str.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Giá trị thay đổi không hợp lệ");
                            return;
                        }
                        String check = str.replaceAll("\\s+", "").trim();
                        try {
                            int expup = Integer.parseInt(check);
                            if (expup <= 0) {
                                expup = 1;
                            }
                            Manager.up_exp = expup;
                            JOptionPane.showMessageDialog(null, "Thay đổi thành công - Giá trị thay đổi "+ expup);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ");
                        }
                        break;
                    }
                    default: {
                        JOptionPane.showMessageDialog(null, "không hợp lệ");
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "không hợp lệ");
            }
        });
        menulist.setSelectedIndex(0);
        JDialog dialog = new JDialog();
        dialog.setTitle("Menu");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        ItemPanel.item();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        String databaseName = Manager.mysql_database_data;
        String checkidshop = "SELECT ListItem FROM `" + databaseName + "`.`gmshop` WHERE id = ?";
        String checkidnpc = "UPDATE `" + databaseName + "`.`gmshop` SET ListItem = ? WHERE id = ?";
        JTextField IdItemField = new JTextField();
        IdItemField.setText("0");
        JTextField buyCoinField = new JTextField();
        buyCoinField.setText("0");
        JTextField isLockField = new JTextField();
        isLockField.setText("false");
        JTextField OptionField = new JTextField();
        OptionField.setText("[]");

        Object[] message = {
            "Item ID:", IdItemField,
            "Giá coin:", buyCoinField,
            "isLock:", isLockField,
            "Option mặc định là []:", OptionField
        };

        int option = JOptionPane.showOptionDialog(null, message, "Nhập dữ liệu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int itemId = Integer.parseInt(IdItemField.getText());
                int buyCoin = Integer.parseInt(buyCoinField.getText());
                String isLock = isLockField.getText();
                String Option = OptionField.getText();
                try {
                    PreparedStatement selectStatement = SQLManager.conn.prepareStatement(checkidshop);
                    selectStatement.setInt(1, 1);
                    ResultSet resultSet = selectStatement.executeQuery();
                    String itemsellData = "";
                    if (resultSet.next()) {
                        itemsellData = resultSet.getString("ListItem");
                    }
                    if (itemsellData != null && !itemsellData.isEmpty()) {
                        itemsellData = itemsellData.substring(0, itemsellData.length() - 1);
                        itemsellData += "\n,";
                    }
                    String itemsellData1 = "{\"id\":"+ itemId +",\"expires\":-1,\"buyCoin\":"+ buyCoin +",\"isLock\":"+ isLock +",\"sale\":0,\"quantity\":1,\"upgrade\":0,\"sys\":0,\"isExpires\":false,\"option\":"+ Option +"}\n]";
                    itemsellData += itemsellData1;
                    PreparedStatement updateStatement = SQLManager.conn.prepareStatement(checkidnpc);
                    updateStatement.setString(1, itemsellData);
                    updateStatement.setInt(2, 1);
                    updateStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Thêm Item Vào Shop GM Thành Công.");
                    updateStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (NumberFormatException ex) {
                String errorMessage = "Định dạng nhập không hợp lệ. Vui lòng nhập đầy đủ.";
                JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        updateShopGm();
        JOptionPane.showMessageDialog(null, "UPDATE SHOP GM THÀNH CÔNG");
    }//GEN-LAST:event_jButton28ActionPerformed
    
    public void updateShopGm() {
        boolean fall = true;
        String databaseName = Manager.mysql_database_data;
        try (ResultSet res = SQLManager.stat.executeQuery("SELECT * FROM `" + databaseName + "`.`gmshop`;")) {
            GmShop.ItemGmShop.clear();
            while (res.next()) {
                JSONArray jar3 = (JSONArray) JSONValue.parse(res.getString("ListItem"));
                if (jar3 != null) {
                    for (byte j = 0; j < jar3.size(); ++j) {
                        JSONObject job = (JSONObject) jar3.get((int) j);
                        Item item2 = ItemTemplate.parseItem(jar3.get((int) j).toString());
                        item2.buyCoin = Integer.parseInt(job.get((Object) "buyCoin").toString());
                        GmShop.ItemGmShop.add(item2);
                    }
                }
            }
            fall = false;
            System.out.println("success load shop gm...");
            Manager.serverChat("Hệ Thống", "Shop GM đã đc update vui lòng đóng shop mở lại để cập nhật.");
        } catch (Exception e) {
            if (fall == true) {
                System.err.println("Fail load shop gm...");
                updateShopGm();
            }
        }
    }
    
    public static void panel(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new panel().setVisible(true);
            }
        });
    }
    
    public static void updateShopNPC() {
//        SQLManager sql = new SQLManager();
        boolean fall = true;
        String databaseName = Manager.mysql_database_data;
        try (ResultSet res = SQLManager.stat.executeQuery("SELECT * FROM `" + databaseName + "`.`itemsell`;")) {
            ItemSell.entrys.clear();
            while (res.next()) {
                ItemSell sell = new ItemSell();
                sell.id = Integer.parseInt(res.getString("id"));
                sell.type = Byte.parseByte(res.getString("type"));
                JSONArray jar3 = (JSONArray) JSONValue.parse(res.getString("ListItem"));
                if (jar3 != null) {
                    sell.item = new Item[jar3.size()];
                    for (byte j = 0; j < jar3.size(); ++j) {
                        JSONObject job = (JSONObject) jar3.get((int) j);
                        Item item2 = ItemTemplate.parseItem(jar3.get((int) j).toString());
                        item2.buyCoin = Integer.parseInt(job.get((Object) "buyCoin").toString());
                        item2.buyCoinLock = Integer.parseInt(job.get((Object) "buyCoinLock").toString());
                        item2.buyGold = Integer.parseInt(job.get((Object) "buyGold").toString());
                        sell.item[j] = item2;
                    }
                }
                ItemSell.entrys.add( sell);
            }
//            res.close();   
            fall = false;
            System.out.println("success load shop...");
        } catch (Exception e) {
            if (fall == true) {
                System.err.println("Fail load shop...");
                updateShopNPC();
            }
        }
//        finally {
////            sql.close();
//        }
//        sql.create(mysql_host, mysql_port, mysql_database_data, mysql_user, mysql_pass);
    }
    
    public static void backupsql() {
        try {
            String time = String.valueOf(System.currentTimeMillis());
            String backUpPart = "backupsql\\" + Manager.mysql_database_ninja + time + ".sql";

            ProcessBuilder pb1 = new ProcessBuilder(
                    "cmd", "/c",
                    Manager.mysql_part,
                    "--user=" + Manager.mysql_user,
                    "--password=" + Manager.mysql_pass,
                    "--host=" + Manager.mysql_host,
                    "--protocol=tcp",
                    "--port=" + Manager.mysql_port,
                    "--default-character-set=utf8",
                    "--single-transaction=TRUE",
                    "--routines",
                    "--events",
                    Manager.mysql_database_ninja,
                    ">", backUpPart
            );
            pb1.redirectErrorStream(true);
            pb1.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb1.start();
            if (!Manager.mysql_database_ninja.equals(Manager.mysql_database_data)) {
                String backUpPart1 = "backupsql\\" + Manager.mysql_database_data + time + "data.sql";
                ProcessBuilder pb2 = new ProcessBuilder(
                        "cmd", "/c",
                        Manager.mysql_part,
                        "--user=" + Manager.mysql_user,
                        "--password=" + Manager.mysql_pass,
                        "--host=" + Manager.mysql_host,
                        "--protocol=tcp",
                        "--port=" + Manager.mysql_port,
                        "--default-character-set=utf8",
                        "--single-transaction=TRUE",
                        "--routines",
                        "--events",
                        Manager.mysql_database_data,
                        ">", backUpPart1
                );
                pb2.redirectErrorStream(true);
                pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                pb2.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
