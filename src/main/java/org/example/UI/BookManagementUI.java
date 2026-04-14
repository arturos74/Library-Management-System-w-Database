package org.example.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookManagementUI extends JFrame {

    private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;

    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton clearButton;
    private JButton backButton;

    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookManagementUI() {
        setTitle("Modify Books");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Modify Books");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(230, 230, 230));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Book Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel idLabel = new JLabel("Book ID:");
        JLabel titleFieldLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel isbnLabel = new JLabel("ISBN:");

        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        idLabel.setFont(labelFont);
        titleFieldLabel.setFont(labelFont);
        authorLabel.setFont(labelFont);
        isbnLabel.setFont(labelFont);

        idField = new JTextField(18);
        titleField = new JTextField(18);
        authorField = new JTextField(18);
        isbnField = new JTextField(18);

        Dimension fieldSize = new Dimension(220, 32);
        idField.setPreferredSize(fieldSize);
        titleField.setPreferredSize(fieldSize);
        authorField.setPreferredSize(fieldSize);
        isbnField.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(titleFieldLabel, gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(authorField, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(isbnLabel, gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(isbnField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        buttonPanel.setBackground(new Color(240, 240, 240));

        addButton = new JButton("Add Book");
        updateButton = new JButton("Update Book");
        removeButton = new JButton("Remove Book");
        clearButton = new JButton("Clear Fields");

        Dimension buttonSize = new Dimension(130, 35);
        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        removeButton.setPreferredSize(buttonSize);
        clearButton.setPreferredSize(new Dimension(140, 35));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        JPanel topSection = new JPanel(new BorderLayout(10, 10));
        topSection.setBackground(new Color(230, 230, 230));
        topSection.add(formPanel, BorderLayout.CENTER);
        topSection.add(buttonPanel, BorderLayout.SOUTH);

        centerPanel.add(topSection, BorderLayout.NORTH);

        String[] columns = {"Book ID", "Title", "Author", "ISBN", "Available"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookTable = new JTable(tableModel);
        bookTable.setRowHeight(25);
        bookTable.setFont(new Font("Arial", Font.PLAIN, 13));
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Book Records",
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
