package com.donasi.banjir.view.frame;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AdminLoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private final Map<String, String> adminAccounts = Map.of(
            "I", "2",
            "Ariel", "202410370110434"
    );

    public AdminLoginFrame() {
        setTitle("Login Admin");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        form.add(new JLabel("Username"));
        form.add(txtUsername);
        form.add(new JLabel("Password"));
        form.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        JButton btnKembali = new JButton("Kembali");

        btnLogin.addActionListener(e -> prosesLogin());
        btnKembali.addActionListener(e -> {
            new TampilanAwalFrame();
            dispose();
        });

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButton.add(btnKembali);
        panelButton.add(btnLogin);

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Field kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username dan Password wajib diisi",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Username tidak ditemukan
        if (!adminAccounts.containsKey(username)) {
            JOptionPane.showMessageDialog(this,
                    "Username tidak terdaftar",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Password salah
        if (!adminAccounts.get(username).equals(password)) {
            JOptionPane.showMessageDialog(this,
                    "Password salah",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Login berhasil
        JOptionPane.showMessageDialog(this, "Login berhasil");
        new DashboardAdminFrame();
        dispose();
    }
}
