# Sistem Donasi Korban Bencana Banjir

Sistem Donasi Korban Bencana Banjir merupakan aplikasi desktop berbasis **Java Swing** yang digunakan untuk mengelola data donasi bagi korban bencana banjir. Aplikasi ini dirancang dengan menerapkan konsep **Pemrograman Berorientasi Objek (PBO)** serta penyimpanan data menggunakan **file CSV**.

---

## 📌 Fitur Utama

### 👤 Menu Awal
- Pilihan masuk sebagai **Donatur** atau **Admin**
- Mengatur alur penggunaan aplikasi

### 🤝 Donatur
- Input data donasi tanpa login
- Validasi input (tidak boleh kosong, jumlah harus angka)
- Data donasi otomatis tersimpan ke file CSV

### 🔐 Admin
- Login menggunakan username dan password
- Validasi username dan password
- Akses menu laporan donasi

### 📊 Laporan Donasi
- Menampilkan seluruh data donasi dalam bentuk tabel
- Menampilkan waktu donasi (tanggal dan jam)
- Menampilkan total keseluruhan donasi
- Data diambil dari file CSV

### 🔁 Navigasi
- Tombol kembali pada setiap menu (kecuali menu awal)
- Memudahkan perpindahan antar halaman

---

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman**: Java
- **GUI**: Java Swing
- **Penyimpanan Data**: File CSV
- **Version Control**: Git & GitHub
- **IDE**: IntelliJ IDEA

---

## 📂 Struktur Package

com.donasi.banjir
├── Main.java
├── model
│ └── Donasi.java
├── data
│ └── DonasiRepository.java
├── util
│ └── FileUtil.java
└── view
├── frame
│ ├── MenuAwalFrame.java
│ ├── DonasiFormFrame.java
│ ├── AdminLoginFrame.java
│ ├── AdminDashboardFrame.java
│ └── LaporanFrame.java

yaml
Salin kode

---

## 💾 Penyimpanan Data

Data donasi disimpan secara permanen menggunakan file:
data/donasi.csv

yaml
Salin kode
Data akan tetap tersedia meskipun aplikasi ditutup dan dibuka kembali.

---

## 🔑 Akun Admin (Default)

| Username | Password |
|--------|----------|
| admin1 | admin123 |
| admin2 | admin456 |

---

## ▶️ Cara Menjalankan Aplikasi

1. Pastikan **Java JDK** telah terpasang
2. Clone repository ini
3. Buka project menggunakan **IntelliJ IDEA**
4. Jalankan file `Main.java`

---

## 📚 Catatan

Aplikasi ini dibuat untuk memenuhi tugas praktikum mata kuliah **Pemrograman Berorientasi Objek** dengan menerapkan konsep GUI, file handling, exception handling, serta penggunaan GitHub.

---

## 👨‍💻 Pengembang

- **Nama**: Lalu Irgi Nabil Farhan (202410370110404)
- **Nama**: Ariel Ardiansyah       (202410370110434)
- **Studi Kasus**: Sistem Donasi Korban Bencana Banjir
