package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    public LaporanFrame() {
        setTitle("Laporan Manajemen Donasi");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try { setIconImage(new ImageIcon("Resource/Icons/app_icon.png").getImage()); } catch (Exception ignored) {}

        initComponent();
        loadData(DonasiRepository.getAll());
        setVisible(true);
    }

    private void initComponent() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- TOP PANEL (Search & Sort) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(240, 240, 240));

        txtSearch = new JTextField(15);
        cmbSort = new JComboBox<>(new String[]{
                "Tanggal Terbaru", "Tanggal Terlama", "Jumlah Terbesar", "Jumlah Terkecil"
        });
        JButton btnFilter = new JButton("Cari & Urutkan");
        btnFilter.setBackground(new Color(33, 150, 243));
        btnFilter.setForeground(Color.BLUE);

        topPanel.add(new JLabel("Keyword:"));
        topPanel.add(txtSearch);
        topPanel.add(new JLabel("Urutkan:"));
        topPanel.add(cmbSort);
        topPanel.add(btnFilter);

        // --- CENTER PANEL (Table) ---
        tableModel = new DefaultTableModel(
                new String[]{"Waktu Donasi", "Nama Donatur", "Jenis", "Jumlah (Rp/Unit)"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // --- BOTTOM PANEL (Action & Info) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton btnUpdate = new JButton("Edit Data");
        JButton btnDelete = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali");

        btnDelete.setForeground(Color.RED);

        actionPanel.add(btnUpdate);
        actionPanel.add(btnDelete);
        actionPanel.add(btnKembali);

        lblTotal = new JLabel("Total Donasi: Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setBorder(new EmptyBorder(0, 0, 0, 10));

        bottomPanel.add(actionPanel, BorderLayout.WEST);
        bottomPanel.add(lblTotal, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnFilter.addActionListener(e -> applyFilter());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnKembali.addActionListener(e -> {
            new DashboardAdminFrame();
            dispose();
        });
    }

    private void loadData(List<Donasi> data) {
        tableModel.setRowCount(0);
        long total = 0;
        for (Donasi d : data) {
            tableModel.addRow(new Object[]{
                    d.getTanggalDonasi().format(formatter),
                    d.getNamaDonatur(),
                    d.getJenisDonasi(),
                    String.format("%, d", d.getJumlahDonasi())
            });
            total += d.getJumlahDonasi();
        }
        lblTotal.setText("Total: " + String.format("%, d", total));
    }

    private void applyFilter() {
        String keyword = txtSearch.getText().toLowerCase();
        List<Donasi> filtered = DonasiRepository.getAll().stream()
                .filter(d -> d.getNamaDonatur().toLowerCase().contains(keyword) ||
                        d.getJenisDonasi().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        Comparator<Donasi> comp = switch (cmbSort.getSelectedIndex()) {
            case 1 -> Comparator.comparing(Donasi::getTanggalDonasi);
            case 2 -> Comparator.comparing(Donasi::getJumlahDonasi).reversed();
            case 3 -> Comparator.comparing(Donasi::getJumlahDonasi);
            default -> Comparator.comparing(Donasi::getTanggalDonasi).reversed();
        };

        filtered.sort(comp);
        loadData(filtered);
    }

    private void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris pada tabel terlebih dahulu!");
            return;
        }

        String nama = JOptionPane.showInputDialog(this, "Edit Nama:", table.getValueAt(row, 1));
        if (nama == null) return;

        String jumlahStr = JOptionPane.showInputDialog(this, "Edit Jumlah:", table.getValueAt(row, 3).toString().replace(" ", "").replace(",", ""));
        if (jumlahStr == null) return;

        try {
            int jumlah = Integer.parseInt(jumlahStr);
            DonasiRepository.update(row, new Donasi(nama, table.getValueAt(row, 2).toString(), jumlah));
            loadData(DonasiRepository.getAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update: Pastikan jumlah berupa angka.");
        }
    }

    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DonasiRepository.hapus(row);
            loadData(DonasiRepository.getAll());
        }
    }
}