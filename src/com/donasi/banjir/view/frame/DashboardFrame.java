package com.donasi.banjir.view.frame;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("Dashboard Donasi Banjir");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/app_icon.png"));
        setIconImage(icon.getImage());

        JButton btnList = new JButton("Daftar Donasi");
        JButton btnInput = new JButton("Input Donasi");
        JButton btnLaporan = new JButton("Laporan Donasi");

        btnList.addActionListener(e -> new DonasiListFrame());
        btnInput.addActionListener(e -> new DonasiFormFrame());
        btnLaporan.addActionListener(e -> new LaporanFrame());

        JPanel panel = new JPanel();
        panel.add(btnList);
        panel.add(btnInput);
        panel.add(btnLaporan);

        add(panel);
        setVisible(true);
    }
}

