package com.donasi.banjir.util;

import com.donasi.banjir.model.Donasi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileUtil {

    private static final String FILE_PATH = "/data/donasi.csv";

    public static ArrayList<Donasi> bacaDataDonasi() {
        ArrayList<Donasi> list = new ArrayList<>();

        try {
            InputStream is = FileUtil.class.getResourceAsStream(FILE_PATH);

            if (is == null) {
                throw new Exception("File donasi.csv tidak ditemukan");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String baris;

            while ((baris = br.readLine()) != null) {
                String[] data = baris.split(",");

                Donasi donasi = new Donasi(
                        data[0],
                        Double.parseDouble(data[1]),
                        data[2],
                        LocalDate.parse(data[3])
                );

                list.add(donasi);
            }

        } catch (NumberFormatException e) {
            System.out.println("Format angka salah: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }

        return list;
    }
}
