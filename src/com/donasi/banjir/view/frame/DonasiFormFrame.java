package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import java.awt.*;

public class DonasiFormFrame extends JFrame {

    private JTextField txtNama, txtJumlah;
    private JComboBox<String> cbJenis;

    public DonasiFormFrame() {
        setTitle("Input Donasi");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txtNama = new JTextField();
        txtJumlah = new JTextField();
        cbJenis = new JComboBox<>(new String[]{"Uang", "Pakaian", "Sembako"});

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpanData());

        setLayout(new GridLayout(5, 2, 10, 10));
        add(new JLabel("Nama Donatur"));
        add(txtNama);
        add(new JLabel("Jumlah"));
        add(txtJumlah);
        add(new JLabel("Jenis Donasi"));
        add(cbJenis);
        add(new JLabel());
        add(btnSimpan);

        setVisible(true);
    }

    private void simpanData() {
        try {
            String nama = txtNama.getText().trim();
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            String jenis = cbJenis.getSelectedItem().toString();

            if (nama.isEmpty()) {
                throw new Exception("Nama tidak boleh kosong");
            }

            if (jumlah <= 0) {
                throw new Exception("Jumlah harus lebih dari 0");
            }

            // ✅ SIMPAN DATA
            Donasi donasi = new Donasi(nama, jenis, jumlah);
            DonasiRepository.tambah(donasi);

            JOptionPane.showMessageDialog(this, "Donasi berhasil disimpan");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
