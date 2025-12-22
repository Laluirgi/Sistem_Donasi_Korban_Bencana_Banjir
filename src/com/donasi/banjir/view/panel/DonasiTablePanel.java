package com.donasi.banjir.view.panel;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DonasiTablePanel extends JPanel {

    public DonasiTablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] kolom = {
                "Nama Donatur",
                "Jumlah Donasi",
                "Jenis Donasi",
                "Tanggal"
        };

        DefaultTableModel tableModel = new DefaultTableModel(kolom, 0);
        JTable table = new JTable(tableModel);

        ArrayList<Donasi> dataDonasi = DonasiRepository.getSemuaDonasi();

        for (Donasi d : dataDonasi) {
            tableModel.addRow(new Object[]{
                    d.getNamaDonatur(),
                    d.getJumlahDonasi(),
                    d.getJenisDonasi(),
                    d.getTanggalDonasi()
            });
        }

        add(new JScrollPane(table));
    }
}
