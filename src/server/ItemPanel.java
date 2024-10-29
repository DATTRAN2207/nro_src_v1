package server;

import io.SQLManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.ResultSet;

    public class ItemPanel extends JPanel {
        private JTextField searchInput;
        private JTable itemTable;
        private DefaultTableModel model;
        private TableRowSorter<DefaultTableModel> sorter;

        public ItemPanel() {
            setLayout(new BorderLayout());

            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel titleLabel = new JLabel("🍑️Danh sách Item🍑️");
            titleLabel.setForeground(new Color(255, 132, 0));
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            headerPanel.add(titleLabel);
            add(headerPanel, BorderLayout.NORTH);

            JPanel searchPanel = new JPanel(new BorderLayout());
            searchInput = new JTextField();
            searchInput.setPreferredSize(new Dimension(200, 30));
            searchInput.getDocument().addDocumentListener(new SearchDocumentListener());
            searchPanel.add(searchInput, BorderLayout.CENTER);
            add(searchPanel, BorderLayout.CENTER);

            JPanel tablePanel = new JPanel(new BorderLayout());
            itemTable = new JTable();
            model = new DefaultTableModel();
            itemTable.setModel(model);
            sorter = new TableRowSorter<>(model);
            itemTable.setRowSorter(sorter);
            JScrollPane scrollPane = new JScrollPane(itemTable);
            tablePanel.add(scrollPane, BorderLayout.CENTER);
            JTableHeader header = itemTable.getTableHeader();
            tablePanel.add(header, BorderLayout.NORTH);
            add(tablePanel, BorderLayout.SOUTH);

            loadItemData();
        }

        private void loadItemData() {
            model.addColumn("ID");
            model.addColumn("Tên");
            model.addColumn("Mô tả");
            model.addColumn("Cấp độ");
            model.addColumn("Giới tính");
            model.addColumn("Xếp chồng");
            model.addColumn("Type");

            try {
                String query = "SELECT * FROM `" + Manager.mysql_database_data + "`.`item` ORDER BY id";
                try (ResultSet rs = SQLManager.stat.executeQuery(query)) {
                    if (rs != null) {
                        while (rs.next()) {
                            int gender = rs.getInt("gender");
                            String genderText = "";
                            if (gender == 2) {
                                genderText = "Cả 2";
                            } else if (gender == 1) {
                                genderText = "Nam";
                            } else if (gender == 0) {
                                genderText = "Nữ";
                            }

                            int uptoup = rs.getInt("uptoup");
                            String uptoupText = (uptoup == 0) ? "✘" : "✓";

                            model.addRow(new Object[]{
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getInt("level"),
                                    genderText,
                                    uptoupText,
                                    rs.getString("type")
                            });
                        }
                    } else {
                        System.out.println("ResultSet is null");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private class SearchDocumentListener implements javax.swing.event.DocumentListener {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
            String searchText = searchInput.getText().toLowerCase();
            String[] keywords = searchText.split("\\s+");

            RowFilter<DefaultTableModel, Object> rowFilter = null;
            try {
                StringBuilder regexBuilder = new StringBuilder();
                for (String keyword : keywords) {
                    if (regexBuilder.length() > 0) {
                        regexBuilder.append("|");
                    }
                    regexBuilder.append("(?i)").append(keyword);
                }
                String regex = regexBuilder.toString();
                rowFilter = RowFilter.regexFilter(regex);
            } catch (java.util.regex.PatternSyntaxException ex) {
                return;
            }

            sorter.setRowFilter(rowFilter);
        }

    }

    public static void item() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Item Panel");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ItemPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
