package org.example.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReturnBookUI extends JFrame {

    private JTextField bookIdField;

    private JLabel memberIdLabel;
    private JLabel memberNameLabel;
    private JLabel memberEmailLabel;
    private JLabel bookTitleLabel;
    private JLabel bookAuthorLabel;
    private JLabel borrowDateLabel;

    private JButton searchBookButton;
    private JButton returnButton;
    private JButton clearButton;
    private JButton backButton;

    private JTable rentedBooksTable;
    private DefaultTableModel tableModel;

    public ReturnBookUI() {
        setTitle("Return Book");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Return Book");
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
                "Return Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel bookIdLabel = new JLabel("Book ID:");
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        bookIdLabel.setFont(labelFont);

        bookIdField = new JTextField(18);
        bookIdField.setPreferredSize(new Dimension(220, 32));

        searchBookButton = new JButton("Search Book");
        searchBookButton.setPreferredSize(new Dimension(140, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
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

        memberIdLabel = new JLabel("Member ID: ");
        memberNameLabel = new JLabel("Name: ");
        memberEmailLabel = new JLabel("Email: ");

        memberIdLabel.setFont(labelFont);
        memberNameLabel.setFont(labelFont);
        memberEmailLabel.setFont(labelFont);

        memberGbc.gridx = 0;
        memberGbc.gridy = 0;
        memberInfoPanel.add(memberIdLabel, memberGbc);

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

        bookTitleLabel = new JLabel("Title: ");
        bookAuthorLabel = new JLabel("Author: ");
        borrowDateLabel = new JLabel("Borrow Date: ");

        bookTitleLabel.setFont(labelFont);
        bookAuthorLabel.setFont(labelFont);
        borrowDateLabel.setFont(labelFont);

        bookGbc.gridx = 0;
        bookGbc.gridy = 0;
        bookInfoPanel.add(bookTitleLabel, bookGbc);

        bookGbc.gridy = 1;
        bookInfoPanel.add(bookAuthorLabel, bookGbc);

        bookGbc.gridy = 2;
        bookInfoPanel.add(borrowDateLabel, bookGbc);

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        infoPanel.setBackground(new Color(230, 230, 230));
        infoPanel.add(memberInfoPanel);
        infoPanel.add(bookInfoPanel);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        actionPanel.setBackground(new Color(230, 230, 230));

        returnButton = new JButton("Return Book");
        clearButton = new JButton("Clear Fields");

        returnButton.setPreferredSize(new Dimension(130, 35));
        clearButton.setPreferredSize(new Dimension(130, 35));

        actionPanel.add(returnButton);
        actionPanel.add(clearButton);

        topSectionPanel.add(inputPanel);
        topSectionPanel.add(Box.createVerticalStrut(15));
        topSectionPanel.add(infoPanel);
        topSectionPanel.add(Box.createVerticalStrut(15));
        topSectionPanel.add(actionPanel);

        centerPanel.add(topSectionPanel, BorderLayout.NORTH);

        String[] columnNames = {"Book ID", "Title", "Author", "ISBN", "Borrow Date", "Member ID"};
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

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

}
