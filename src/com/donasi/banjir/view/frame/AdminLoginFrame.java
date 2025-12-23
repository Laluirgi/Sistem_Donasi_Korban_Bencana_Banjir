package com.donasi.banjir.view.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class AdminLoginFrame extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final Map<String, String> adminAccounts = Map.of("I", "2", "Ariel", "202410370110434");

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel pnl = new JPanel(new GridLayout(5, 1, 5, 5));
        pnl.setBorder(new EmptyBorder(20, 30, 20, 30));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("LOGIN");
        JButton btnBatal = new JButton("BATAL");

        styleButton(btnLogin, new Color(25, 118, 210));
        styleButton(btnBatal, new Color(211, 47, 47));

        btnLogin.addActionListener(e -> prosesLogin());
        btnBatal.addActionListener(e -> { new TampilanAwalFrame(); dispose(); });

        pnl.add(new JLabel("Username"));
        pnl.add(txtUsername);
        pnl.add(new JLabel("Password"));
        pnl.add(txtPassword);

        JPanel btnPnl = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPnl.add(btnBatal);
        btnPnl.add(btnLogin);
        pnl.add(btnPnl);

        add(pnl);
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
    }

    private void prosesLogin() {
        String u = txtUsername.getText();
        String p = new String(txtPassword.getPassword());
        if (adminAccounts.containsKey(u) && adminAccounts.get(u).equals(p)) {
            new DashboardAdminFrame(); dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal Login");
        }
    }
}