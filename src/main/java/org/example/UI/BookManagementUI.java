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
        backButton.addActionListener(e -> {
            MainMenuUI mainMenuUI = new MainMenuUI();
            this.dispose();
        });
        bottomPanel.add(backButton);

        addButton.addActionListener(e -> {
            addBook();
            tableModel.setRowCount(0);
            loadBooks();
        });

        updateButton.addActionListener(e -> {
            updateBook();
            tableModel.setRowCount(0);
            loadBooks();
        });

        removeButton.addActionListener(e -> {
            removeBook();
            tableModel.setRowCount(0);
            loadBooks();
        });

        clearButton.addActionListener(e -> {
            idField.setText("");
            titleField.setText("");
            authorField.setText("");
            isbnField.setText("");
        });

        loadBooks();

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void loadBooks() {
        String sql = "SELECT * FROM books";


        try(Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()) {
                String bookId = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int available = rs.getInt("available");

                tableModel.addRow(new Object[]{
                        bookId,
                        title,
                        author,
                        isbn,
                        available == 1 ? "Yes" : "No"
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

    private void addBook() {
        String bookId = idField.getText().trim();
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if(bookId.isEmpty() || title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "1 or More Fields are Empty",
                    "Missing Info",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO books (id, title, author, isbn, available) VALUES (?, ?, ?, ?, 1)";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,bookId);
            pstmt.setString(2,title);
            pstmt.setString(3,author);
            pstmt.setString(4,isbn);

            pstmt.executeUpdate();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void removeBook() {
        String bookId = idField.getText().trim();

        if(bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "ID Field is Empty",
                    "Missing Info",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "DELETE FROM books WHERE id = ?";

        try(Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,bookId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateBook() {
        String bookId = idField.getText().trim();
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if(bookId.isEmpty() || title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "1 or More Fields are Missing",
                    "Missing Info",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE books SET title = ?, author = ?, isbn = ? WHERE id = ?";

        try(Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,title);
            pstmt.setString(2,author);
            pstmt.setString(3,isbn);
            pstmt.setString(4,bookId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
