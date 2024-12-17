import java.util.*;

public class SistemLayananRumahSakitDigital {
    public static void main(String[] args) {
        ArrayList<String> rawatJalan = new ArrayList<>();
        LinkedList<String> rawatInap = new LinkedList<>();
        Queue<String> antrianObat = new LinkedList<>();
        Set<String> dokter = new HashSet<>();
        HashMap<String, List<String>> poliMap = new HashMap<>();

        // Data dokter
        dokter.add("dr. Abidin A. Prawirakusuma Sp.JP");
        dokter.add("Anggraini Alam, Dr., dr., Sp.A(K)");
        dokter.add("Ahmad Rizal, dr., SpS(K).,PhD");
        dokter.add("Arif Dermawan,dr.,SpTHT-KL(K).,M.Kes");
        dokter.add("Anita Rachmawati, dr., SpOG(K)");
        dokter.add("dr. Senov Permadi, SpPD");
        dokter.add("dr. Herfianto Lemena");
        dokter.add("dr. Ade Rines Satya");

        // Data poli
        poliMap.put("Poli Jantung", new ArrayList<>());
        poliMap.put("Poli Anak", new ArrayList<>());
        poliMap.put("Poli Saraf", new ArrayList<>());
        poliMap.put("Poli THT", new ArrayList<>());
        poliMap.put("Poli Kandungan", new ArrayList<>());
        poliMap.put("Poli Dalam", new ArrayList<>());
        poliMap.put("Poli Umum", new ArrayList<>());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem Layanan Rumah Sakit Digital ===");
            System.out.println("1. Pendaftaran Pasien");
            System.out.println("2. Lihat Pasien Rawat Jalan");
            System.out.println("3. Lihat Pasien Rawat Inap");
            System.out.println("4. Proses Pemeriksaan Dokter");
            System.out.println("5. Proses Antrian Obat");
            System.out.println("6. Daftar Dokter Praktek");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    pendaftaranPasien(scanner, rawatJalan, rawatInap, poliMap);
                    break;
                case 2:
                    tampilkanPasien(rawatJalan, "Rawat Jalan");
                    break;
                case 3:
                    tampilkanPasien(new ArrayList<>(rawatInap), "Rawat Inap");
                    break;
                case 4:
                    prosesPemeriksaanDokter(scanner, poliMap, antrianObat);
                    break;
                case 5:
                    prosesAntrianObat(antrianObat);
                    break;
                case 6:
                    tampilkanDokterPraktek(dokter);
                    break;
                case 7:
                    System.out.println(
                            "Keluar dari sistem. Terimakasih sudah menggunakan Sistem Layanan Rumah Sakit Digital.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    public static void pendaftaranPasien(Scanner scanner, ArrayList<String> rawatJalan, LinkedList<String> rawatInap,
            HashMap<String, List<String>> poliMap) {
        System.out.println("\n=== Pendaftaran Pasien ===");
        System.out.println("1. Rawat Jalan");
        System.out.println("2. Rawat Inap");
        System.out.print("Pilih jenis pendaftaran: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        if (jenis == 1) {
            System.out.print("Masukkan nama pasien: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan poli tujuan: ");
            String poli = scanner.nextLine();

            if (poliMap.containsKey(poli)) {
                poliMap.get(poli).add(nama);
                rawatJalan.add(nama);
                System.out.println("Pasien atas nama " + nama + " berhasil didaftarkan ke Poli " + poli);
            } else {
                System.out.println("Poli tidak ditemukan. Pendaftaran gagal.");
            }
        } else if (jenis == 2) {
            System.out.print("Masukkan nama pasien: ");
            String nama = scanner.nextLine();
            rawatInap.add(nama);
            System.out.println("Pasien atas nama " + nama + " berhasil didaftarkan untuk rawat inap.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void prosesPemeriksaanDokter(Scanner scanner, HashMap<String, List<String>> poliMap,
            Queue<String> antrianObat) {
        System.out.println("\n=== Proses Pemeriksaan Dokter ===");
        System.out.print("Masukkan nama poli yang dituju: ");
        String poli = scanner.nextLine();

        if (poliMap.containsKey(poli)) {
            List<String> pasienPoli = poliMap.get(poli);
            if (!pasienPoli.isEmpty()) {
                String pasien = pasienPoli.remove(0);
                System.out.println("Pasien atas nama " + pasien + " sedang dalam pemeriksaan dokter di poli " + poli);

                System.out.print("Apakah pasien membutuhkan obat? (Ya/Tidak): ");
                String kebutuhanObat = scanner.nextLine();
                if (kebutuhanObat.equalsIgnoreCase("Ya")) {
                    antrianObat.add(pasien);
                    System.out.println("Pasien atas nama " + pasien + " ditambahkan ke antrian obat.");
                } else {
                    System.out.println("Pasien atas nama " + pasien + " selesai diperiksa tanpa membutuhkan obat.");
                }
            } else {
                System.out.println("Tidak ada pasien di poli " + poli);
            }
        } else {
            System.out.println("Poli tidak ditemukan.");
        }
    }

    public static void prosesAntrianObat(Queue<String> antrianObat) {
        System.out.println("\n=== Proses Antrian Obat ===");
        if (antrianObat.isEmpty()) {
            System.out.println("Tidak ada antrian obat.");
        } else {
            String pasien = antrianObat.poll();
            System.out.println("Pasien atas nama " + pasien + " telah menerima obat.");
        }
    }

    public static void tampilkanPasien(List<String> pasien, String jenis) {
        System.out.println("\nDaftar Pasien " + jenis + ":");
        if (pasien.isEmpty()) {
            System.out.println("Tidak ada pasien.");
        } else {
            for (String p : pasien) {
                System.out.println("- " + p);
            }
        }
    }

    public static void tampilkanDokterPraktek(Set<String> dokter) {
        System.out.println("\nDaftar Dokter Praktek:");
        for (String d : dokter) {
            System.out.println("- " + d);
        }
    }
}
