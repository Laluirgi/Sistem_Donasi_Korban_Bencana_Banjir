package com.donasi.banjir.view.panel;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class DonasiTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public DonasiTablePanel() {

        setLayout(new BorderLayout(10, 10));

        // Kolom tabel
        tableModel = new DefaultTableModel(
                new String[]{"Nama Donatur", "Jumlah", "Jenis Donasi", "Tanggal"}, 0
        );

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnHapus = new JButton("Hapus Data Donasi");
        btnHapus.addActionListener(e -> hapusData());

        JPanel panelButton = new JPanel();
        panelButton.add(btnHapus);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Donasi d : DonasiRepository.getAll()) {
            tableModel.addRow(new Object[]{
                    d.getNamaDonatur(),
                    d.getJumlahDonasi(),
                    d.getJenisDonasi(),
                    d.getTanggalDonasi().format(formatter)
            });
        }
    }

    private void hapusData() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            int konfirmasi = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin menghapus data donasi ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (konfirmasi == JOptionPane.YES_OPTION) {
                DonasiRepository.hapus(selectedRow);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih data yang ingin dihapus!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
