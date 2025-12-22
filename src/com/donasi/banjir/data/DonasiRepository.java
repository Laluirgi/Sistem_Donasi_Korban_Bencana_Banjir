package com.donasi.banjir.data;

import com.donasi.banjir.model.Donasi;
import com.donasi.banjir.util.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;

public class DonasiRepository {

    public static ArrayList<Donasi> getSemuaDonasi() {
        ArrayList<Donasi> list = FileUtil.bacaDataDonasi();

        // Sorting berdasarkan tanggal terbaru
        list.sort(Comparator.comparing(Donasi::getTanggalDonasi).reversed());

        return list;
    }
}
