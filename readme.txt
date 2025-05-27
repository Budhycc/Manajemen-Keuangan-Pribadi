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

## 4. Jalankan di Windows & Linux

Syaratnya:

* **Java Runtime Environment (JRE)** sudah terinstall di komputer target (Windows/Linux).
* File JAR dan data `data_keuangan.dat` berada di folder yang sama (atau bisa buat konfigurasi khusus).

---

## 5. Membuat Executable Native (Opsional)

Jika Anda ingin bikin executable native `.exe` di Windows atau binary Linux yang tidak perlu install JRE terpisah, Anda bisa gunakan tools seperti:

* **jpackage** (bundled di JDK 14+)
* **Launch4j** (Windows .exe wrapper)
* **GraalVM Native Image** (native compilation, advanced)

Kalau Anda mau saya bantu buatkan langkah pakai `jpackage`, saya bisa bantu juga.

---

Kalau mau, saya bisa bantu buatkan skrip build otomatis untuk Windows/Linux juga. Apa Anda ingin?
