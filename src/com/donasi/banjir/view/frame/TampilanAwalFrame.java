package com.donasi.banjir.view.frame;

import javax.swing.*;
import java.awt.*;

public class TampilanAwalFrame extends JFrame {

    public TampilanAwalFrame() {
        setTitle("Sistem Donasi Banjir");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Pilih Peran", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnDonatur = new JButton("Masuk sebagai Donatur");
        JButton btnAdmin = new JButton("Masuk sebagai Admin");

        btnDonatur.addActionListener(e -> {
            new DonasiFormFrame();
            dispose();
        });

        btnAdmin.addActionListener(e -> {
            new AdminLoginFrame();
            dispose();
        });

        JPanel panelButton = new JPanel(new GridLayout(2, 1, 10, 10));
        panelButton.add(btnDonatur);
        panelButton.add(btnAdmin);

        setLayout(new BorderLayout(10, 10));
        add(lblTitle, BorderLayout.NORTH);
        add(panelButton, BorderLayout.CENTER);

        setVisible(true);
    }
}

