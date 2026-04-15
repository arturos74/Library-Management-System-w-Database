package org.example.UI;

import org.example.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserUI extends JFrame {

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public SearchUserUI() {
        setTitle("Search Users");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Search Users");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(230, 230, 230));

        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BoxLayout(topSectionPanel, BoxLayout.Y_AXIS));
        topSectionPanel.setBackground(new Color(230, 230, 230));

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "User Search",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        idLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");

        JLabel searchLabel = new JLabel("Enter ID / Email:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        searchField = new JTextField(25);
        searchField.setPreferredSize(new Dimension(300, 35));

        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        searchButton.setPreferredSize(new Dimension(110, 35));
        clearButton.setPreferredSize(new Dimension(110, 35));
        backButton.setPreferredSize(new Dimension(110, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        searchPanel.add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchField, gbc);

        JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchButtonPanel.setBackground(new Color(240, 240, 240));
        searchButtonPanel.add(searchButton);
        searchButtonPanel.add(clearButton);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchButtonPanel, gbc);

        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBackground(new Color(240, 240, 240));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Member Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints infoGbc = new GridBagConstraints();
        infoGbc.insets = new Insets(10, 15, 10, 15);
        infoGbc.anchor = GridBagConstraints.WEST;

        Font infoFont = new Font("Arial", Font.PLAIN, 16);
        idLabel.setFont(infoFont);
        nameLabel.setFont(infoFont);
        emailLabel.setFont(infoFont);

        infoGbc.gridx = 0;
        infoGbc.gridy = 0;
        userInfoPanel.add(idLabel, infoGbc);

        infoGbc.gridy = 1;
        userInfoPanel.add(nameLabel, infoGbc);

        infoGbc.gridy = 2;
        userInfoPanel.add(emailLabel, infoGbc);

        topSectionPanel.add(searchPanel);
        topSectionPanel.add(Box.createVerticalStrut(15));
        topSectionPanel.add(userInfoPanel);

        centerPanel.add(topSectionPanel, BorderLayout.NORTH);

        String[] columnNames = {"Book ID", "Title", "Author", "ISBN", "Borrow Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(25);
        resultsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Rented Books",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        searchButton.addActionListener(e -> {
            if (searchField.getText().contains("@")) {
                searchEmail();
            } else {
                searchUser();
            }
        });

        clearButton.addActionListener(e -> {
            searchField.setText("");
            tableModel.setRowCount(0);
            idLabel.setText("ID: ");
            nameLabel.setText("Name: ");
            emailLabel.setText("Email: ");
        });

        backButton.addActionListener(e -> {
            MainMenuUI mainMenuUI = new MainMenuUI();
            this.dispose();
        });

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void searchUser() {
        String search = searchField.getText().trim();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter search criteria",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM members WHERE id = ?";


        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,search);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                String memberId = rs.getString("id");
                String memberName = rs.getString("name");
                String memberEmail = rs.getString("email");

                idLabel.setText("ID: " + memberId);
                nameLabel.setText("Name: " + memberName);
                emailLabel.setText("Email: " + memberEmail);

                loadRentedBooks(memberId);


            } else {
                JOptionPane.showMessageDialog(this,
                        "Member not found",
                        "Not Found",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void searchEmail() {
        String search = searchField.getText().trim();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter search criteria",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM members WHERE email = ?";


        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,search);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                String memberId = rs.getString("id");
                String memberName = rs.getString("name");
                String memberEmail = rs.getString("email");

                idLabel.setText("ID: " + memberId);
                nameLabel.setText("Name: " + memberName);
                emailLabel.setText("Email: " + memberEmail);

                loadRentedBooks(memberId);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Member not found",
                        "Not Found",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadRentedBooks(String memberId) {
        tableModel.setRowCount(0);

        String sql = """
            SELECT books.id, books.title, books.author, books.isbn, loans.borrow_date
            FROM loans
            JOIN books ON loans.book_id = books.id
            WHERE loans.member_id = ? AND loans.returned = 0
            """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String bookId = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                String borrowDate = rs.getString("borrow_date");

                tableModel.addRow(new Object[]{
                        bookId,
                        title,
                        author,
                        isbn,
                        borrowDate
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
