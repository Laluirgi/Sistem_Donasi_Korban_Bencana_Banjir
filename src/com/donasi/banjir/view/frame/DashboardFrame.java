package com.donasi.banjir.view.frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("Sistem Donasi Korban Bencana Banjir");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setAppIcon();     // ← icon aplikasi
        initComponents(); // ← UI
    }

    private void setAppIcon() {
        try {
            List<Image> icons = new ArrayList<>();
            icons.add(new ImageIcon(getClass().getResource("/icons/app_icon.png")).getImage());
            setIconImages(icons);
        } catch (Exception e) {
            System.out.println("Icon aplikasi tidak ditemukan");
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("Sistem Donasi Korban Bencana Banjir", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnDataKorban = new JButton("Data Korban");
        JButton btnInputDonasi = new JButton("Input Donasi");
        JButton btnLaporan = new JButton("Laporan / Riwayat");
        JButton btnKeluar = new JButton("Keluar");

        menuPanel.add(btnDataKorban);
        menuPanel.add(btnInputDonasi);
        menuPanel.add(btnLaporan);
        menuPanel.add(btnKeluar);

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // Event sementara (placeholder tahap berikutnya)
        btnDataKorban.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Halaman Data Korban akan dibuat pada tahap berikutnya")
        );

        btnInputDonasi.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Halaman Input Donasi akan dibuat pada tahap berikutnya")
        );

        btnLaporan.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Halaman Laporan akan dibuat pada tahap berikutnya")
        );

        btnKeluar.addActionListener(e -> System.exit(0));

        add(mainPanel);
    }
}
