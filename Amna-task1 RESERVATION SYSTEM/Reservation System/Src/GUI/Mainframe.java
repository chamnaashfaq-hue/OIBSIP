package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mainframe extends JFrame {

    private final Color GREEN = new Color(0, 100, 0);
    private final Color CREAM = new Color(255, 253, 240);

    public Mainframe() {
        setTitle("Railway Reservation System - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(CREAM);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Welcome to Railway Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(GREEN);
        gbc.gridx = 0; gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBackground(GREEN);
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 1;
        add(bookButton, gbc);

        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBackground(GREEN);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 2;
        add(cancelButton, gbc);

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reservationframe().setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Cancellationframe().setVisible(true);
            }
        });

        setVisible(true);
    }
}