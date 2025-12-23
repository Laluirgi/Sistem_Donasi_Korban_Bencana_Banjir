package com.donasi.banjir.view.frame;

import com.donasi.banjir.data.DonasiRepository;
import com.donasi.banjir.model.Donasi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DonasiFormFrame extends JFrame {
    private final JTextField txtNama;
    private final JTextField txtJumlah;
    private final JComboBox<String> cbJenis;

    public DonasiFormFrame() {
        setTitle("Form Donasi");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try { setIconImage(new ImageIcon("Resource/Icons/app_icon.png").getImage()); } catch (Exception ignored) {}

        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Form Layout
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 15));
        txtNama = new JTextField();
        txtJumlah = new JTextField();
        cbJenis = new JComboBox<>(new String[]{"Uang", "Pakaian", "Sembako"});

        form.add(new JLabel("Nama Donatur"));
        form.add(txtNama);
        form.add(new JLabel("Jumlah"));
        form.add(txtJumlah);
        form.add(new JLabel("Jenis Donasi"));
        form.add(cbJenis);

        // Buttons
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnSimpan = new JButton("Simpan");
        JButton btnKembali = new JButton("Kembali");

        // Styling
        styleButton(btnSimpan, new Color(76, 175, 80)); // Hijau
        styleButton(btnKembali, new Color(158, 158, 158)); // Abu-abu

        btnSimpan.addActionListener(e -> simpanData());
        btnKembali.addActionListener(e -> { new TampilanAwalFrame(); dispose(); });

        panelButton.add(btnKembali);
        panelButton.add(btnSimpan);

        content.add(new JLabel("Isi Data Donasi", SwingConstants.CENTER), BorderLayout.NORTH);
        content.add(form, BorderLayout.CENTER);
        content.add(panelButton, BorderLayout.SOUTH);

        add(content);
        setVisible(true);
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void simpanData() {
        try {
            String nama = txtNama.getText().trim();
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (nama.isEmpty()) throw new Exception("Nama kosong");
            DonasiRepository.tambah(new Donasi(nama, cbJenis.getSelectedItem().toString(), jumlah));
            JOptionPane.showMessageDialog(this, "Berhasil!");
            txtNama.setText(""); txtJumlah.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}