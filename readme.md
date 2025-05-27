Untuk membangun (build) aplikasi Java Swing Anda agar bisa dijalankan di **Windows** dan **Linux**, pada dasarnya cukup dengan memastikan Anda sudah meng-compile file `.java` menjadi `.class` (bytecode Java), karena Java bersifat cross-platform.

---

## 1. Compile Program

Jalankan perintah ini di terminal (Windows Command Prompt, PowerShell, atau Linux Terminal) pada folder yang berisi file `ManajemenKeuanganPribadi.java` dan `Transaksi.java`:

```bash
javac ManajemenKeuanganPribadi.java
```

Ini akan menghasilkan file `.class`.

---

## 2. Jalankan Program

Untuk menjalankan di kedua platform:

```bash
java ManajemenKeuanganPribadi
```

---

## 3. Membuat File JAR (Optional)

Agar mudah distribusikan, Anda bisa buat file JAR (Java Archive) yang berisi aplikasi lengkap:

1. Buat file `manifest.txt` dengan isi berikut, untuk menetapkan kelas utama (main class):

```
Main-Class: ManajemenKeuanganPribadi
```

Pastikan ada baris kosong di akhir file `manifest.txt`.

2. Jalankan perintah untuk buat JAR:

```bash
jar cfm ManajemenKeuanganPribadi.jar manifest.txt *.class
```

3. Jalankan file JAR:

```bash
java -jar ManajemenKeuanganPribadi.jar
```

---
