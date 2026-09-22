import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> frekuensi = new HashMap<>();
        
        int[] batasBawahAtas = bacaInput(scanner, frekuensi);
        if (frekuensi.isEmpty()) {
            scanner.close();
            return;
        }

        hitungDanCetakStatistik(frekuensi, batasBawahAtas[0], batasBawahAtas[1]);
        scanner.close();
    }

    private static int[] bacaInput(Scanner scanner, HashMap<Integer, Integer> frekuensi) {
        Integer tertinggi = null;
        Integer terendah = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            
            try {
                int nilai = Integer.parseInt(line);
                frekuensi.put(nilai, frekuensi.getOrDefault(nilai, 0) + 1);

                if (tertinggi == null || nilai > tertinggi) tertinggi = nilai;
                if (terendah == null || nilai < terendah) terendah = nilai;
            } catch (NumberFormatException ignored) {}
        }
        
        return new int[]{tertinggi == null ? 0 : tertinggi, terendah == null ? 0 : terendah};
    }

    private static void hitungDanCetakStatistik(HashMap<Integer, Integer> frekuensi, int tertinggi, int terendah) {
        int terbanyakVal = 0, maxFreq = 0;
        int tersedikitVal = 0, minFreq = Integer.MAX_VALUE;
        int jumlahTertinggiVal = 0, maxJumlah = 0;
        int jumlahTerendahVal = 0, minJumlah = Integer.MAX_VALUE;

        // Algoritma evaluasi frekuensi dan tie-breaking statistik
        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int val = entry.getKey();
            int freq = entry.getValue();
            int jumlah = val * freq;

            // Tie-breaking: Jika frekuensi sama, pilih nilai(val) yang lebih besar
            if (freq > maxFreq || (freq == maxFreq && val > terbanyakVal)) {
                maxFreq = freq;
                terbanyakVal = val;
            }
            // Tie-breaking: Jika frekuensi sama, pilih nilai(val) yang lebih kecil
            if (freq < minFreq || (freq == minFreq && val < tersedikitVal)) {
                minFreq = freq;
                tersedikitVal = val;
            }
            // Tie-breaking untuk akumulasi perkalian
            if (jumlah > maxJumlah || (jumlah == maxJumlah && val > jumlahTertinggiVal)) {
                maxJumlah = jumlah;
                jumlahTertinggiVal = val;
            }
            if (jumlah < minJumlah || (jumlah == minJumlah && val < jumlahTerendahVal)) {
                minJumlah = jumlah;
                jumlahTerendahVal = val;
            }
        }

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakVal + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + tersedikitVal + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiVal + " * " + frekuensi.get(jumlahTertinggiVal) + " = " + maxJumlah);
        System.out.println("Jumlah Terendah: " + jumlahTerendahVal + " * " + frekuensi.get(jumlahTerendahVal) + " = " + minJumlah);
    }
}