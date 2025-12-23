package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LaporanFrame extends JFrame {

    private JTextField txtSearch;
    private JComboBox<String> cmbSort;
    private JLabel lblTotal;

    private JTable table;
    private DefaultTableModel tableModel;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LaporanFrame() {
        setTitle("Laporan Donasi");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponent();

        loadData(DonasiRepository.getAll());

        setVisible(true);
    }

    private void updateData() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate");
            return;
        }

        String nama = JOptionPane.showInputDialog(this,
                "Nama Donatur",
                table.getValueAt(row, 1));

        String jenis = JOptionPane.showInputDialog(this,
                "Jenis Donasi",
                table.getValueAt(row, 2));

        String jumlahStr = JOptionPane.showInputDialog(this,
                "Jumlah Donasi",
                table.getValueAt(row, 3));

        if (nama == null || jenis == null || jumlahStr == null) return;

        try {
            int jumlah = Integer.parseInt(jumlahStr);

            Donasi donasiBaru = new Donasi(nama, jenis, jumlah);
            DonasiRepository.update(row, donasiBaru);

            loadData(DonasiRepository.getAll());
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus angka");
        }
    }

    private void deleteData() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            DonasiRepository.hapus(row);
            loadData(DonasiRepository.getAll());
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        }
    }

    private void initComponent() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtSearch = new JTextField(20);
        cmbSort = new JComboBox<>(new String[]{
                "Tanggal Terbaru",
                "Tanggal Terlama",
                "Jumlah Terbesar",
                "Jumlah Terkecil"
        });

        JButton btnFilter = new JButton("Terapkan");

        topPanel.add(new JLabel("Cari:"));
        topPanel.add(txtSearch);
        topPanel.add(new JLabel("Urutkan:"));
        topPanel.add(cmbSort);
        topPanel.add(btnFilter);

        tableModel = new DefaultTableModel(
                new String[]{"Tanggal & Waktu", "Donatur", "Jenis", "Jumlah"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnKembali = new JButton("Kembali");

        lblTotal = new JLabel("Total Donasi: Rp 0");

        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnKembali.addActionListener(e -> {
            new DashboardAdminFrame();
            dispose();
        });

        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnKembali);
        bottomPanel.add(lblTotal);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnFilter.addActionListener(e -> applyFilter());
    }

    private void loadData(List<Donasi> data) {
        tableModel.setRowCount(0);
        int total = 0;

        for (Donasi d : data) {

            LocalDateTime waktu = d.getTanggalDonasi();
            String waktuFormatted = waktu != null
                    ? waktu.format(formatter)
                    : "-";

            tableModel.addRow(new Object[]{
                    waktuFormatted,
                    d.getNamaDonatur(),
                    d.getJenisDonasi(),
                    d.getJumlahDonasi()
            });

            total += d.getJumlahDonasi();
        }

        lblTotal.setText("Total Donasi: Rp " + total);
    }

    private void applyFilter() {
        String keyword = txtSearch.getText().toLowerCase();

        List<Donasi> filtered = DonasiRepository.getAll().stream()
                .filter(d ->
                        d.getNamaDonatur().toLowerCase().contains(keyword) ||
                                d.getJenisDonasi().toLowerCase().contains(keyword)
                )
                .collect(Collectors.toList());

        Comparator<Donasi> comparator;

        switch (cmbSort.getSelectedIndex()) {
            case 1 -> comparator = Comparator.comparing(Donasi::getTanggalDonasi);
            case 2 -> comparator = Comparator.comparing(Donasi::getJumlahDonasi).reversed();
            case 3 -> comparator = Comparator.comparing(Donasi::getJumlahDonasi);
            default -> comparator = Comparator.comparing(Donasi::getTanggalDonasi).reversed();
        }

        filtered.sort(comparator);
        loadData(filtered);
    }

}
