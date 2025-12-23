package com.donasi.banjir.view.frame;

import javax.swing.*;
import java.awt.*;

public class DashboardAdminFrame extends JFrame {

    public DashboardAdminFrame() {
        setTitle("Dashboard Admin");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnLaporan = new JButton("Laporan Donasi");
        JButton btnLogout = new JButton("Logout");

        btnLaporan.setEnabled(true);
        btnLogout.setEnabled(true);

        Dimension btnSize = new Dimension(200, 35);
        btnLaporan.setMaximumSize(btnSize);
        btnLogout.setMaximumSize(btnSize);

        btnLaporan.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLaporan.addActionListener(e -> new LaporanFrame());

        btnLogout.addActionListener(e -> {
            new TampilanAwalFrame();
            dispose();
        });

        panel.add(Box.createVerticalStrut(10));
        panel.add(btnLaporan);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnLogout);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }
}