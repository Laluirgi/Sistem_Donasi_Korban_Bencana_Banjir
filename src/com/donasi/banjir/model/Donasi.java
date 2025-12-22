package com.donasi.banjir.model;

import java.time.LocalDateTime;

public class Donasi {

    private LocalDateTime tanggalDonasi;
    private String namaDonatur;
    private String jenisDonasi;
    private int jumlahDonasi;

    // ===============================
    // FINAL: semua donasi pasti ada jam
    // ===============================
    public Donasi(String namaDonatur, String jenisDonasi, int jumlahDonasi) {
        this.tanggalDonasi = LocalDateTime.now(); // JAM NYATA
        this.namaDonatur = namaDonatur;
        this.jenisDonasi = jenisDonasi;
        this.jumlahDonasi = jumlahDonasi;
    }

    public LocalDateTime getTanggalDonasi() {
        return tanggalDonasi;
    }

    public String getNamaDonatur() {
        return namaDonatur;
    }

    public String getJenisDonasi() {
        return jenisDonasi;
    }

    public int getJumlahDonasi() {
        return jumlahDonasi;
    }
}
