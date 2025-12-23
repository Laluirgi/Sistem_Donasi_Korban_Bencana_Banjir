package com.donasi.banjir.view.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardAdminFrame extends JFrame {

    public DashboardAdminFrame() {
        setTitle("Admin Dashboard");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try { setIconImage(new ImageIcon("Resource/Icons/app_icon.png").getImage()); } catch (Exception ignored) {}

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new Color(245, 247, 250));

        // Welcome Header
        JLabel lblWelcome = new JLabel("Selamat Datang, Admin", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Button Container
        JPanel menuPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        menuPanel.setOpaque(false);

        JButton btnLaporan = createMenuButton("Buka Laporan Donasi", "📋");
        JButton btnLogout = createMenuButton("Keluar Sistem", "🚪");

        btnLaporan.addActionListener(e -> {
            new LaporanFrame();
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new TampilanAwalFrame();
            dispose();
        });

        menuPanel.add(btnLaporan);
        menuPanel.add(btnLogout);

        mainPanel.add(lblWelcome, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createMenuButton(String text, String emoji) {
        JButton btn = new JButton(emoji + "  " + text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(55, 71, 79));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return btn;
    }
}