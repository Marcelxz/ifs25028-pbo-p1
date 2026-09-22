import java.util.Scanner;
import java.util.Locale;

public class App {
    private static final int TOTAL_BOBOT_VALID = 100;
    private static final String[] SIMBOL_VALID = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA_KOMPONEN = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] bobotAwal = bacaBobotAwal(scanner);
        if (bobotAwal == null) {
            scanner.close();
            return;
        }
        
        double[] totalBobotDiinput = new double[6];
        double[] totalPerolehan = new double[6];
        
        prosesInputNilai(scanner, totalBobotDiinput, totalPerolehan);
        cetakDanHitungAkhir(bobotAwal, totalBobotDiinput, totalPerolehan);
        
        scanner.close();
    }

    private static int[] bacaBobotAwal(Scanner scanner) {
        int[] bobotAwal = new int[6];
        int totalBobotAwal = 0;
        
        for (int i = 0; i < 6; i++) {
            if (scanner.hasNextLine()) {
                try {
                    bobotAwal[i] = Integer.parseInt(scanner.nextLine().trim());
                    totalBobotAwal += bobotAwal[i];
                } catch (NumberFormatException e) {
                    System.out.println("Input bobot awal harus berupa angka.");
                    return null;
                }
            }
        }
        
        if (totalBobotAwal != TOTAL_BOBOT_VALID) {
            System.out.println("Total bobot harus 100");
            return null;
        }
        return bobotAwal;
    }

    private static void prosesInputNilai(Scanner scanner, double[] totalBobotDiinput, double[] totalPerolehan) {
        while (scanner.hasNextLine()) {
            String baris = scanner.nextLine().trim();
            if (baris.equals("---")) break;
            if (baris.isEmpty()) continue;
            
            String[] bagian = baris.split("\\|");
            if (bagian.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }
            
            try {
                String simbol = bagian[0].trim();
                double bobot = Double.parseDouble(bagian[1].trim());
                double perolehan = Double.parseDouble(bagian[2].trim());
                
                int indeks = getIndeksKomponen(simbol);
                if (indeks == -1) {
                    System.out.println("Simbol tidak dikenal");
                    continue;
                }
                
                perolehan = Math.max(0, Math.min(perolehan, bobot)); // Validasi max(bobot) dan min(0)
                
                totalBobotDiinput[indeks] += bobot;
                totalPerolehan[indeks] += perolehan;
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
            }
        }
    }

    private static int getIndeksKomponen(String simbol) {
        for (int i = 0; i < SIMBOL_VALID.length; i++) {
            if (SIMBOL_VALID[i].equals(simbol)) return i;
        }
        return -1;
    }

    private static void cetakDanHitungAkhir(int[] bobotAwal, double[] totalBobotDiinput, double[] totalPerolehan) {
        System.out.println("Perolehan Nilai:");
        double totalNilaiAkhir = 0.0;
        
        for (int i = 0; i < 6; i++) {
            int perolehan100 = 0;
            if (totalBobotDiinput[i] > 0) {
                perolehan100 = (int) ((totalPerolehan[i] * 100) / totalBobotDiinput[i]);
            }
            
            double kontribusi = (perolehan100 * bobotAwal[i]) / 100.0;
            totalNilaiAkhir += kontribusi;
            System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%d)\n", NAMA_KOMPONEN[i], perolehan100, kontribusi, bobotAwal[i]);
        }
        
        totalNilaiAkhir = Math.round(totalNilaiAkhir * 1000.0) / 1000.0;
        System.out.println();
        System.out.printf(Locale.US, ">> Nilai Akhir: %.2f\n", totalNilaiAkhir);
        System.out.println(">> Grade: " + tentukanGrade(totalNilaiAkhir));
    }

    private static String tentukanGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}