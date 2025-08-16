package com.data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryManagement extends JFrame
{
	private java.util.List<Book> books = new ArrayList<>();
    private java.util.List<User> users = new ArrayList<>();
    private java.util.List<Transaction> transactions = new ArrayList<>();
    
    private JTable table;
    private DefaultTableModel tableModel;
    
    public LibraryManagement() 
    {
        setTitle("Library Management System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        String[] cols = {"ID", "Title", "Author", "Available"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        
        JButton addBookBtn = new JButton("Add Book");
        JButton deleteBookBtn = new JButton("Delete Book");
        JButton checkoutBtn = new JButton("Checkout Book");
        JButton returnBtn = new JButton("Return Book");
        JButton reportBtn = new JButton("Generate Report");

        JPanel panel = new JPanel();
        panel.add(addBookBtn);
        panel.add(deleteBookBtn);
        panel.add(checkoutBtn);
        panel.add(returnBtn);
        panel.add(reportBtn);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        users.add(new User(3, "John"));
        users.add(new User(4, "A.K.Shrama"));
        users.add(new User(5, "Robert"));

        addBookBtn.addActionListener(e -> addBook());
        deleteBookBtn.addActionListener(e -> deleteBook());
        checkoutBtn.addActionListener(e -> checkoutBook());
        returnBtn.addActionListener(e -> returnBook());
        reportBtn.addActionListener(e -> showReport());

        setVisible(true);
    }
   private void addBook()
    {
        String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
        String author = JOptionPane.showInputDialog(this, "Enter Author:");
        if (title != null && author != null) 
        {
            Book book = new Book(books.size() + 1, title, author);
            books.add(book);
            refreshTable();
        }
    }
    private void deleteBook() 
    {
        int row = table.getSelectedRow();
        if (row >= 0) 
        {
            books.remove(row);
            refreshTable();
        } else 
        {
            JOptionPane.showMessageDialog(this, "Select a book to delete.");
        }
    }
    private void checkoutBook() 
    {
        int row = table.getSelectedRow();
        if (row >= 0)
        {
            Book book = books.get(row);
            if (!book.available) 
            {
                JOptionPane.showMessageDialog(this, "Book already checked out!");
                return;
            }
         User user = (User) JOptionPane.showInputDialog(this, "Select User:",
                    "Checkout", JOptionPane.QUESTION_MESSAGE, null,
                    users.toArray(), users.get(0));
            
            if (user != null) 
            {
                book.available = false;
                transactions.add(new Transaction(user, book, "Checked Out"));
                refreshTable();
            }
        } else 
        {
          JOptionPane.showMessageDialog(this, "Select a book to checkout.");
        }
    }
     private void returnBook() 
   {
        int row = table.getSelectedRow();
        if (row >= 0) 
        {
            Book book = books.get(row);
            if (book.available) 
            {
                JOptionPane.showMessageDialog(this, "Book is not checked out.");
                return;
            }
            
            User user = (User) JOptionPane.showInputDialog(this, "Select User:",
                    "Return", JOptionPane.QUESTION_MESSAGE, null,
                    users.toArray(), users.get(0));
            
            if (user != null) 
            {
                book.available = true;
                transactions.add(new Transaction(user, book, "Returned"));
                refreshTable();
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "Select a book to return.");
        }
    }
     private void showReport() 
    {
        StringBuilder report = new StringBuilder("Transactions Report:\n\n");
        for (Transaction t : transactions) 
        {
            report.append(t).append("\n");
        }
        JOptionPane.showMessageDialog(this, report.toString());
    }
    
    private void refreshTable() 
    {
        tableModel.setRowCount(0);
        for (Book b : books) 
        {
            tableModel.addRow(new Object[]{b.id, b.title, b.author, b.available ? "Yes" : "No"});
        }
    }
     public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(LibraryManagement::new);
  }
}
