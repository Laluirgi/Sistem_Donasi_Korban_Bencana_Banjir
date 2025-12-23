package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;

import javax.swing.*;
import java.awt.*;

public class DonasiFormFrame extends JFrame {

    private JTextField txtNama, txtJumlah;
    private JComboBox<String> cbJenis;

    public DonasiFormFrame() {
        setTitle("Form Donasi");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        txtNama = new JTextField();
        txtJumlah = new JTextField();
        cbJenis = new JComboBox<>(new String[]{"Uang", "Pakaian", "Sembako"});

        form.add(new JLabel("Nama Donatur"));
        form.add(txtNama);
        form.add(new JLabel("Jumlah"));
        form.add(txtJumlah);
        form.add(new JLabel("Jenis Donasi"));
        form.add(cbJenis);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnKembali = new JButton("Kembali");

        btnSimpan.addActionListener(e -> simpanData());
        btnKembali.addActionListener(e -> {
            new TampilanAwalFrame();
            dispose();
        });

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButton.add(btnKembali);
        panelButton.add(btnSimpan);

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

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

            Donasi donasi = new Donasi(nama, jenis, jumlah);
            DonasiRepository.tambah(donasi);

            JOptionPane.showMessageDialog(this, "Donasi berhasil disimpan");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
