package org.example.UI;

import org.example.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginUI() {
        setTitle("Library Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 230));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(220, 220, 220));
        titlePanel.setBorder(new EmptyBorder(20, 30, 20, 20));

        JLabel titleLabel = new JLabel("Library Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(235, 235, 235));
        loginPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Login",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18)
        ));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(235, 235, 235));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 22));
        emailField = new JTextField(18);
        emailField.setPreferredSize(new Dimension(250, 35));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 22));
        passwordField = new JPasswordField(18);
        passwordField.setPreferredSize(new Dimension(250, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        loginPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        buttonPanel.setBackground(new Color(235, 235, 235));

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        loginButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.setPreferredSize(new Dimension(120, 40));

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        add(mainPanel);

        loginButton.addActionListener(e -> handleLogin());
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both email and password.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM members WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String memberName = rs.getString("name");

                JOptionPane.showMessageDialog(this,
                        "Login successful. Welcome, " + memberName + "!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                        this.dispose();
                        MainMenuUI mainMenuUI = new MainMenuUI();

                // later:
                // new MainMenuUI();
                // dispose();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid email or password.",
                        "Login Failed",
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

}