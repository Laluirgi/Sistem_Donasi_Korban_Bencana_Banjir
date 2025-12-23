package com.donasi.banjir.view.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TampilanAwalFrame extends JFrame {

    public TampilanAwalFrame() {
        setTitle("Sistem Donasi Banjir");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            setIconImage(new ImageIcon("Resource/Icons/app_icon.png").getImage());
        } catch (Exception e) {
            System.err.println("Icon jendela tidak ditemukan");
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblIcon = new JLabel();
        try {
            ImageIcon originalIcon = new ImageIcon("Resource/Icons/app_icon.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblIcon.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblIcon.setText("[Icon]");
        }
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("Sistem Donasi Banjir");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(25, 118, 210)); // Biru yang Anda suka
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnDonatur = new JButton("Masuk sebagai Donatur");
        JButton btnAdmin = new JButton("Masuk sebagai Admin");

        styleButton(btnDonatur, new Color(25, 118, 210));
        styleButton(btnAdmin, new Color(33, 150, 243));

        btnDonatur.addActionListener(e -> {
            new DonasiFormFrame();
            dispose();
        });

        btnAdmin.addActionListener(e -> {
            new AdminLoginFrame();
            dispose();
        });

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lblIcon);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(btnDonatur);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(btnAdmin);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
        setVisible(true);
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT); // Agar rata tengah di BoxLayout

        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        btn.setPreferredSize(new Dimension(300, 55));

        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}