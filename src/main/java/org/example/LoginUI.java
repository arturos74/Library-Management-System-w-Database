package org.example;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("Library Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 230));

        // Title
        JLabel titleLabel = new JLabel("Library Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(new EmptyBorder(10, 15, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel (form box)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(new TitledBorder(
                new LineBorder(Color.GRAY, 1),
                "Login",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Username"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Bottom buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 230, 230));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if(username.equalsIgnoreCase("John") && password.equalsIgnoreCase("password")) {
                this.hide();
                MainMenuUI mainMenuUI = new MainMenuUI();
            }
        });

        registerButton.setPreferredSize(new Dimension(100,30));
        registerButton.addActionListener(e -> {
            this.hide();
        });

        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(e -> {
            System.exit(0);
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);



        add(mainPanel);
        setVisible(true);
    }
}
