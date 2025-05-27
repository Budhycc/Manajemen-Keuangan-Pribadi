---

# 📘 Manajemen Keuangan Pribadi

Aplikasi ini adalah program desktop sederhana berbasis **Java Swing** untuk mencatat dan memantau pemasukan serta pengeluaran keuangan pribadi. Aplikasi ini dapat dijalankan di **Windows** maupun **Linux** karena dibangun menggunakan Java.

---

## ✅ 1. Compile Program

Pastikan Anda sudah berada di direktori yang berisi file `ManajemenKeuanganPribadi.java` dan `Transaksi.java`, lalu jalankan:

```bash
javac ManajemenKeuanganPribadi.java
```

Perintah ini akan mengkompilasi program dan menghasilkan file `.class` (bytecode Java).

---

## ▶️ 2. Jalankan Program

Setelah dikompilasi, Anda bisa langsung menjalankan aplikasi dengan perintah:

```bash
java ManajemenKeuanganPribadi
```

Program akan menampilkan antarmuka pengguna berbasis Java Swing.

---

## 📦 3. Membuat File JAR (Opsional)

Untuk distribusi yang lebih mudah, Anda dapat menggabungkan file `.class` ke dalam satu file `.jar`.

### Langkah-langkah:

#### a. Buat file `manifest.txt` dengan isi:

```
Main-Class: ManajemenKeuanganPribadi
```

> **Catatan:** Pastikan ada satu baris kosong di akhir file `manifest.txt`.

#### b. Buat file JAR menggunakan perintah:

```bash
jar cfm ManajemenKeuanganPribadi.jar manifest.txt *.class
```

#### c. Jalankan file JAR:

```bash
java -jar ManajemenKeuanganPribadi.jar
```

---

## 🖱️ 4. Menjalankan dengan Skrip Otomatis

### 🔹 Windows: `runManajemenKeuangan.bat`

Buat file bernama `runManajemenKeuangan.bat` dengan isi berikut:

```bat
@echo off
java -jar ManajemenKeuanganPribadi.jar
pause
```

Jalankan file ini cukup dengan klik dua kali pada Windows Explorer.

---

### 🔹 Linux/macOS: `runManajemenKeuangan.sh`

Buat file bernama `runManajemenKeuangan.sh` dengan isi berikut:

```bash
#!/bin/bash
java -jar ManajemenKeuanganPribadi.jar
read -p "Tekan Enter untuk keluar..."
```

Lalu beri izin eksekusi dengan perintah:

```bash
chmod +x runManajemenKeuangan.sh
```

Dan jalankan dengan:

```bash
./runManajemenKeuangan.sh
```

---