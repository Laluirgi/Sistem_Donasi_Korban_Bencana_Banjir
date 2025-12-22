package com.donasi.banjir.view.frame;

import com.donasi.banjir.view.panel.DonasiTablePanel;

import javax.swing.*;

public class DonasiListFrame extends JFrame {

    public DonasiListFrame() {
        setTitle("Daftar Donasi Korban Banjir");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new DonasiTablePanel());
        setVisible(true);
    }
}
