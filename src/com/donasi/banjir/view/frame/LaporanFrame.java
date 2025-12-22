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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponent();

        // UPDATE: load semua data saat frame dibuka
        loadData(DonasiRepository.getAll());

        setVisible(true); // UPDATE: pastikan frame muncul
    }

    private void initComponent() {
        setLayout(new BorderLayout());

        // ===============================
        // PANEL ATAS (SEARCH + SORT)
        // ===============================
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

        // ===============================
        // TABEL DATA
        // ===============================
        tableModel = new DefaultTableModel(
                new String[]{"Tanggal & Waktu", "Donatur", "Jenis", "Jumlah"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // ===============================
        // PANEL BAWAH (TOTAL)
        // ===============================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total Donasi: Rp 0");
        bottomPanel.add(lblTotal);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // ===============================
        // EVENT FILTER
        // ===============================
        btnFilter.addActionListener(e -> applyFilter());
    }

    // ===============================
    // UPDATE: load data + total donasi
    // ===============================
    private void loadData(List<Donasi> data) {
        tableModel.setRowCount(0);
        int total = 0;

        for (Donasi d : data) {

            // ===============================
            // UPDATE: format tanggal + waktu
            // ===============================
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

    // ===============================
    // UPDATE: searching & sorting
    // ===============================
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
