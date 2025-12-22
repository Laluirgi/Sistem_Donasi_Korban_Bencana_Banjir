package com.donasi.banjir.model;

import java.time.LocalDate;

public class Donasi {
    private String namaDonatur;
    private double jumlahDonasi;
    private String jenisDonasi;
    private LocalDate tanggalDonasi;

    public Donasi(String namaDonatur, double jumlahDonasi, String jenisDonasi, LocalDate tanggalDonasi) {
        this.namaDonatur = namaDonatur;
        this.jumlahDonasi = jumlahDonasi;
        this.jenisDonasi = jenisDonasi;
        this.tanggalDonasi = tanggalDonasi;
    }

    public String getNamaDonatur() {
        return namaDonatur;
    }

    public double getJumlahDonasi() {
        return jumlahDonasi;
    }

    public String getJenisDonasi() {
        return jenisDonasi;
    }

    public LocalDate getTanggalDonasi() {
        return tanggalDonasi;
    }
}

