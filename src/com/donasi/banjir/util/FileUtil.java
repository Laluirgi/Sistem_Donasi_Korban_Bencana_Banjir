package com.donasi.banjir.util;

import com.donasi.banjir.model.Donasi;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileUtil {

    private static final String FILE_PATH = "data/donasi.csv";

    public static ArrayList<Donasi> bacaData() {
        ArrayList<Donasi> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                list.add(new Donasi(
                        d[0],
                        Double.parseDouble(d[1]),
                        d[2],
                        LocalDate.parse(d[3])
                ));
            }
        } catch (Exception e) {
            System.out.println("Gagal baca file: " + e.getMessage());
        }

        return list;
    }

    public static void simpanSemua(ArrayList<Donasi> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Donasi d : list) {
                pw.println(
                        d.getNamaDonatur() + "," +
                                d.getJumlahDonasi() + "," +
                                d.getJenisDonasi() + "," +
                                d.getTanggalDonasi()
                );
            }
        } catch (IOException e) {
            System.out.println("Gagal simpan file: " + e.getMessage());
        }
    }
}
