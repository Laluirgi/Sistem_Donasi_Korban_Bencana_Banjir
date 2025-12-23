package com.donasi.banjir.util;

import com.donasi.banjir.model.Donasi;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FileUtil {

    private static final String FILE_PATH = "data/donasi.csv";

    public static ArrayList<Donasi> bacaData() {
        ArrayList<Donasi> list = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                LocalDateTime tanggal = LocalDateTime.parse(data[0]);
                String nama = data[1];
                String jenis = data[2];
                int jumlah = Integer.parseInt(data[3]);

                Donasi d = new Donasi(nama, jenis, jumlah, tanggal);
                list.add(d);
            }

        } catch (Exception e) {
            System.err.println("Gagal baca file: " + e.getMessage());
        }

        return list;
    }

    public static void simpanSemua(ArrayList<Donasi> list) {
        File file = new File(FILE_PATH);

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Donasi d : list) {
                pw.println(
                        d.getTanggalDonasi() + "," +
                                d.getNamaDonatur() + "," +
                                d.getJenisDonasi() + "," +
                                d.getJumlahDonasi()
                );
            }
        } catch (Exception e) {
            System.err.println("Gagal simpan file: " + e.getMessage());
        }
    }
}
