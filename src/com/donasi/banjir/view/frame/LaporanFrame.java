package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class LaporanFrame extends JFrame {

    public LaporanFrame() {
        setTitle("Laporan Donasi Korban Banjir");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea areaLaporan = new JTextArea();
        areaLaporan.setEditable(false);
        areaLaporan.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(areaLaporan);
        add(scrollPane);

        tampilkanLaporan(areaLaporan);
        setVisible(true);
    }

    private void tampilkanLaporan(JTextArea area) {
        double totalUang = 0;
        int totalTransaksi = 0;

        HashMap<String, Integer> rekapJenis = new HashMap<>();

        LocalDate tanggalAwal = null;
        LocalDate tanggalAkhir = null;

        for (Donasi d : DonasiRepository.getAll()) {
            totalTransaksi++;

            if (d.getJenisDonasi().equalsIgnoreCase("Uang")) {
                totalUang += d.getJumlahDonasi();
            }

            rekapJenis.put(
                    d.getJenisDonasi(),
                    rekapJenis.getOrDefault(d.getJenisDonasi(), 0) + 1
            );

            if (tanggalAwal == null || d.getTanggalDonasi().isBefore(tanggalAwal)) {
                tanggalAwal = d.getTanggalDonasi();
            }

            if (tanggalAkhir == null || d.getTanggalDonasi().isAfter(tanggalAkhir)) {
                tanggalAkhir = d.getTanggalDonasi();
            }
        }

        area.append("===== LAPORAN DONASI KORBAN BENCANA BANJIR =====\n\n");
        area.append("Periode          : " +
                (tanggalAwal != null ? tanggalAwal : "-") +
                " s/d " +
                (tanggalAkhir != null ? tanggalAkhir : "-") + "\n");

        area.append("Total Transaksi  : " + totalTransaksi + "\n");
        area.append("Total Donasi Uang: Rp " + totalUang + "\n\n");

        area.append("Rekap Jenis Donasi:\n");
        for (String jenis : rekapJenis.keySet()) {
            area.append("- " + jenis + " : " + rekapJenis.get(jenis) + " transaksi\n");
        }
    }
}
