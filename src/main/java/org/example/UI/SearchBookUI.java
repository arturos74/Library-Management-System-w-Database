package org.example.UI;

import org.example.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchBookUI extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public SearchBookUI() {
        setTitle("Search Books");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Search Books");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(230, 230, 230));

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Book Search",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel searchLabel = new JLabel("Enter Title / ID / Author:");
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

        centerPanel.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Book ID", "Title", "Author", "ISBN", "Available"};
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
                "Search Results",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        backButton.addActionListener(e -> {
            this.dispose();
            MainMenuUI mainMenuUI = new MainMenuUI();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchBookUI::new);
    }
}
