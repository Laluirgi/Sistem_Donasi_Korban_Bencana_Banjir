package com.donasi.banjir.data;

import com.donasi.banjir.model.Donasi;
import com.donasi.banjir.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class DonasiRepository {

    // ===============================
    // UPDATE: pastikan data tidak null
    // ===============================
    private static ArrayList<Donasi> data;

    static {
        data = FileUtil.bacaData();
    }

    // ===============================
    // UPDATE: lindungi data internal
    // ===============================
    public static List<Donasi> getAll() {
        return new ArrayList<>(data);
    }

    // ===============================
    // CREATE
    // ===============================
    public static void tambah(Donasi d) {
        if (d == null) return;
        data.add(d);
        FileUtil.simpanSemua(data);
    }

    // ===============================
    // UPDATE
    // ===============================
    public static void update(int index, Donasi d) {
        if (index < 0 || index >= data.size() || d == null) return;
        data.set(index, d);
        FileUtil.simpanSemua(data);
    }

    // ===============================
    // DELETE
    // ===============================
    public static void hapus(int index) {
        if (index < 0 || index >= data.size()) return;
        data.remove(index);
        FileUtil.simpanSemua(data);
    }
}
