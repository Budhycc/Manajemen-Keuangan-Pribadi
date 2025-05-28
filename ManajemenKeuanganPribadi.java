import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ManajemenKeuanganPribadi extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField tfKeterangan, tfJumlah;
    private JComboBox<String> cbTipe;
    private List<Transaksi> transaksiList = new ArrayList<>();
    private final String FILE_NAME = "data_keuangan.dat";

    public ManajemenKeuanganPribadi() {
        setTitle("Manajemen Keuangan Pribadi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        loadData();

        // Form Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        tfKeterangan = new JTextField(10);
        tfJumlah = new JTextField(7);
        cbTipe = new JComboBox<>(new String[]{"Pemasukan", "Pengeluaran"});
        JButton btnTambah = new JButton("Tambah");
        JButton btnHapus = new JButton("Hapus"); // Tombol hapus

        inputPanel.add(new JLabel("Keterangan:"));
        inputPanel.add(tfKeterangan);
        inputPanel.add(new JLabel("Jumlah:"));
        inputPanel.add(tfJumlah);
        inputPanel.add(cbTipe);
        inputPanel.add(btnTambah);
        inputPanel.add(btnHapus); // Tambahkan tombol hapus ke panel

        add(inputPanel, BorderLayout.NORTH);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"Tanggal", "Keterangan", "Jumlah", "Tipe"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Muat data ke tabel
        for (Transaksi t : transaksiList) {
            tableModel.addRow(new Object[]{t.tanggal, t.keterangan, t.jumlah, t.tipe});
        }

        // Chart Panel
        JButton btnLihatGrafik = new JButton("Lihat Grafik Bulanan");
        add(btnLihatGrafik, BorderLayout.SOUTH);

        // Listener
        btnTambah.addActionListener(e -> tambahTransaksi());
        btnHapus.addActionListener(e -> hapusTransaksi()); // Tambahkan aksi hapus
        btnLihatGrafik.addActionListener(e -> tampilkanGrafik());
    }

    private void tambahTransaksi() {
        String keterangan = tfKeterangan.getText();
        String jumlahStr = tfJumlah.getText();
        String tipe = cbTipe.getSelectedItem().toString();

        try {
            double jumlah = Double.parseDouble(jumlahStr);
            LocalDate tanggal = LocalDate.now();
            Transaksi transaksi = new Transaksi(tanggal, keterangan, jumlah, tipe);
            transaksiList.add(transaksi);
            tableModel.addRow(new Object[]{tanggal, keterangan, jumlah, tipe});
            tfKeterangan.setText("");
            tfJumlah.setText("");
            saveData();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusTransaksi() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus transaksi ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                transaksiList.remove(selectedRow); // Hapus dari list
                tableModel.removeRow(selectedRow);  // Hapus dari tabel
                saveData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih transaksi yang ingin dihapus.", "Tidak Ada Yang Dipilih", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tampilkanGrafik() {
        Map<Integer, Double> pemasukanBulanan = new HashMap<>();
        Map<Integer, Double> pengeluaranBulanan = new HashMap<>();

        for (Transaksi t : transaksiList) {
            int bulan = t.tanggal.getMonthValue();
            pemasukanBulanan.putIfAbsent(bulan, 0.0);
            pengeluaranBulanan.putIfAbsent(bulan, 0.0);
            if (t.tipe.equals("Pemasukan")) {
                pemasukanBulanan.put(bulan, pemasukanBulanan.get(bulan) + t.jumlah);
            } else {
                pengeluaranBulanan.put(bulan, pengeluaranBulanan.get(bulan) + t.jumlah);
            }
        }

        JTextArea textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        textArea.append("Grafik Keuangan Bulanan (Teks):\n\n");
        for (int i = 1; i <= 12; i++) {
            String bulan = LocalDate.of(2023, i, 1).getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            double pemasukan = pemasukanBulanan.getOrDefault(i, 0.0);
            double pengeluaran = pengeluaranBulanan.getOrDefault(i, 0.0);
            textArea.append(String.format("%s:\n  Pemasukan  : Rp%.2f\n  Pengeluaran: Rp%.2f\n\n", bulan, pemasukan, pengeluaran));
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Grafik Bulanan", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transaksiList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                transaksiList = (List<Transaksi>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManajemenKeuanganPribadi().setVisible(true));
    }
}

class Transaksi implements Serializable {
    LocalDate tanggal;
    String keterangan;
    double jumlah;
    String tipe;

    public Transaksi(LocalDate tanggal, String keterangan, double jumlah, String tipe) {
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
        this.tipe = tipe;
    }
}
