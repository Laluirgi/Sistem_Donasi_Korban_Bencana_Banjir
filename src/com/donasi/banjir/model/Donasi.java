package com.donasi.banjir.model;

import java.time.LocalDateTime;

public class Donasi {

    private LocalDateTime tanggalDonasi;
    private String namaDonatur;
    private String jenisDonasi;
    private int jumlahDonasi;

    public Donasi(String namaDonatur, String jenisDonasi, int jumlahDonasi) {
        this.namaDonatur = namaDonatur;
        this.jenisDonasi = jenisDonasi;
        this.jumlahDonasi = jumlahDonasi;
        this.tanggalDonasi = LocalDateTime.now();
    }

    public Donasi(String namaDonatur, String jenisDonasi, int jumlahDonasi,
                  LocalDateTime tanggalDonasi) {
        this.namaDonatur = namaDonatur;
        this.jenisDonasi = jenisDonasi;
        this.jumlahDonasi = jumlahDonasi;
        this.tanggalDonasi = tanggalDonasi;
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
