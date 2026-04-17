package org.example.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Library Management System - Admin Menu");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(new EmptyBorder(0, 5, 10, 5));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel with admin actions
        JPanel actionPanel = new JPanel(new GridLayout(4, 3, 15, 15));
        actionPanel.setBackground(new Color(240, 240, 240));
        actionPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Admin Controls"
        ));

        JButton searchBookButton = new JButton("Search Book");
        JButton searchUserButton = new JButton("Search User");
        JButton modifyBooksButton = new JButton("Modify Books");
        JButton modifyUsers = new JButton("Modify Users");
        JButton rentBookButton = new JButton("Rent Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton viewBooksButton = new JButton("View All Books");
        JButton viewUsersButton = new JButton("View All Users");
        JButton viewRentalsButton = new JButton("View Rentals");
        JButton logoutButton = new JButton("Logout");

        actionPanel.add(searchBookButton);
        actionPanel.add(searchUserButton);
        actionPanel.add(modifyBooksButton);
        actionPanel.add(modifyUsers);
        actionPanel.add(rentBookButton);
        actionPanel.add(returnBookButton);
        actionPanel.add(viewBooksButton);
        actionPanel.add(viewUsersButton);
        actionPanel.add(viewRentalsButton);
        actionPanel.add(logoutButton);

        searchBookButton.addActionListener(e -> {
            SearchBookUI searchBookUI = new SearchBookUI();
            this.dispose();
        });
        searchUserButton.addActionListener(e -> {
            SearchUserUI searchUserUI = new SearchUserUI();
            this.dispose();
        });
        modifyBooksButton.addActionListener(e -> {
            BookManagementUI bookManagementUI = new BookManagementUI();
            this.dispose();
        });
        modifyUsers.addActionListener(e -> {
            UserManagementUI userManagementUI = new UserManagementUI();
            this.dispose();
        });
        logoutButton.addActionListener(e -> {
            LoginUI loginUI = new LoginUI();
            this.dispose();
        });

        mainPanel.add(actionPanel, BorderLayout.CENTER);

        // Optional bottom status area
        JLabel statusLabel = new JLabel("Admin access granted.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setBorder(new EmptyBorder(10, 5, 0, 5));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main (String[] args) {
        MainMenuUI mainMenuUI = new MainMenuUI();
    }
}
