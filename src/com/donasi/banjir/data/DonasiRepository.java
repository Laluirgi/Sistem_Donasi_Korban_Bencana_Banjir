package com.donasi.banjir.data;

import com.donasi.banjir.model.Donasi;
import com.donasi.banjir.util.FileUtil;

import java.util.ArrayList;

public class DonasiRepository {

    private static ArrayList<Donasi> data = FileUtil.bacaData();

    public static ArrayList<Donasi> getAll() {
        return data;
    }

    public static void tambah(Donasi d) {
        data.add(d);
        FileUtil.simpanSemua(data);
    }

    public static void update(int index, Donasi d) {
        data.set(index, d);
        FileUtil.simpanSemua(data);
    }

    public static void hapus(int index) {
        data.remove(index);
        FileUtil.simpanSemua(data);
    }
}
