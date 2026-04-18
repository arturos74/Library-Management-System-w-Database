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

public class RentBookUI extends JFrame {

    private JTextField memberIdField;
    private JTextField bookIdField;

    private JLabel memberIdDisplayLabel;
    private JLabel memberNameLabel;
    private JLabel memberEmailLabel;
    private JLabel bookIdDisplayLabel;
    private JLabel bookTitleLabel;
    private JLabel bookAuthorLabel;
    private JLabel bookAvailabilityLabel;

    private JButton searchMemberButton;
    private JButton searchBookButton;
    private JButton rentButton;
    private JButton clearButton;
    private JButton backButton;

    private JTable rentedBooksTable;
    private DefaultTableModel tableModel;

    public RentBookUI() {
        setTitle("Rent Book");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Rent Book");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(230, 230, 230));

        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BoxLayout(topSectionPanel, BoxLayout.Y_AXIS));
        topSectionPanel.setBackground(new Color(230, 230, 230));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(240, 240, 240));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Loan Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel memberIdLabel = new JLabel("Member ID:");
        JLabel bookIdLabel = new JLabel("Book ID:");

        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        memberIdLabel.setFont(labelFont);
        bookIdLabel.setFont(labelFont);

        memberIdField = new JTextField(18);
        bookIdField = new JTextField(18);

        Dimension fieldSize = new Dimension(220, 32);
        memberIdField.setPreferredSize(fieldSize);
        bookIdField.setPreferredSize(fieldSize);

        searchMemberButton = new JButton("Search Member");
        searchBookButton = new JButton("Search Book");

        searchMemberButton.setPreferredSize(new Dimension(140, 35));
        searchBookButton.setPreferredSize(new Dimension(140, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(memberIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(memberIdField, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(searchMemberButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(bookIdLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(bookIdField, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(searchBookButton, gbc);

        JPanel memberInfoPanel = new JPanel(new GridBagLayout());
        memberInfoPanel.setBackground(new Color(240, 240, 240));
        memberInfoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Member Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints memberGbc = new GridBagConstraints();
        memberGbc.insets = new Insets(10, 15, 10, 15);
        memberGbc.anchor = GridBagConstraints.WEST;

        memberIdDisplayLabel = new JLabel("Member ID: ");
        memberNameLabel = new JLabel("Name: ");
        memberEmailLabel = new JLabel("Email: ");

        memberIdDisplayLabel.setFont(labelFont);
        memberNameLabel.setFont(labelFont);
        memberEmailLabel.setFont(labelFont);

        memberGbc.gridx = 0;
        memberGbc.gridy = 0;
        memberInfoPanel.add(memberIdDisplayLabel, memberGbc);

        memberGbc.gridy = 1;
        memberInfoPanel.add(memberNameLabel, memberGbc);

        memberGbc.gridy = 2;
        memberInfoPanel.add(memberEmailLabel, memberGbc);

        JPanel bookInfoPanel = new JPanel(new GridBagLayout());
        bookInfoPanel.setBackground(new Color(240, 240, 240));
        bookInfoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Book Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints bookGbc = new GridBagConstraints();
        bookGbc.insets = new Insets(10, 15, 10, 15);
        bookGbc.anchor = GridBagConstraints.WEST;

        bookIdDisplayLabel = new JLabel("Book ID: ");
        bookTitleLabel = new JLabel("Title: ");
        bookAuthorLabel = new JLabel("Author: ");
        bookAvailabilityLabel = new JLabel("Available: ");

        bookIdDisplayLabel.setFont(labelFont);
        bookTitleLabel.setFont(labelFont);
        bookAuthorLabel.setFont(labelFont);
        bookAvailabilityLabel.setFont(labelFont);

        bookGbc.gridx = 0;
        bookGbc.gridy = 0;
        bookInfoPanel.add(bookIdDisplayLabel, bookGbc);

        bookGbc.gridy = 1;
        bookInfoPanel.add(bookTitleLabel, bookGbc);

        bookGbc.gridy = 2;
        bookInfoPanel.add(bookAuthorLabel, bookGbc);

        bookGbc.gridy = 3;
        bookInfoPanel.add(bookAvailabilityLabel, bookGbc);

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        infoPanel.setBackground(new Color(230, 230, 230));
        infoPanel.add(memberInfoPanel);
        infoPanel.add(bookInfoPanel);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        actionPanel.setBackground(new Color(230, 230, 230));

        rentButton = new JButton("Rent Book");
        clearButton = new JButton("Clear Fields");

        rentButton.setPreferredSize(new Dimension(130, 35));
        clearButton.setPreferredSize(new Dimension(130, 35));

        actionPanel.add(rentButton);
        actionPanel.add(clearButton);

        topSectionPanel.add(inputPanel);
        topSectionPanel.add(Box.createVerticalStrut(15));
        topSectionPanel.add(infoPanel);
        topSectionPanel.add(Box.createVerticalStrut(15));
        topSectionPanel.add(actionPanel);

        centerPanel.add(topSectionPanel, BorderLayout.NORTH);

        String[] columnNames = {"Book ID", "Title", "Author", "ISBN", "Borrow Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        rentedBooksTable = new JTable(tableModel);
        rentedBooksTable.setRowHeight(25);
        rentedBooksTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        rentedBooksTable.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(rentedBooksTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Currently Rented Books",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(230, 230, 230));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        bottomPanel.add(backButton);

        searchMemberButton.addActionListener(e -> {
            searchMember();
        });

        backButton.addActionListener(e -> {
            MainMenuUI mainMenuUI = new MainMenuUI();
            this.dispose();
        });

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void searchMember() {
        String memberId = memberIdField.getText();

        if(memberId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter search criteria",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM members WHERE id = ?";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,memberId);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int available;

                memberIdDisplayLabel.setText("Member ID: " + rs.getString("id"));
                memberNameLabel.setText("Name: " + rs.getString("name"));
                memberEmailLabel.setText("Email: " + rs.getString("email"));
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
