import java.util.Scanner;

public class App {
    // Pengganti "Magic Numbers" dengan konstanta bernama
    private static final int MENIT_PER_HARI = 1440;
    private static final int MENIT_PER_JAM = 60;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;

        String jamAwalStr = scanner.nextLine().trim();
        int totalMenitAwal = parseJamKeMenit(jamAwalStr);
        
        if (totalMenitAwal == -1) {
            System.out.println("Jam tidak valid");
            scanner.close();
            return;
        }

        int totalShift = hitungTotalShift(scanner);
        cetakHasilAkhir(jamAwalStr, totalMenitAwal, totalShift);
        
        scanner.close();
    }

    private static int parseJamKeMenit(String waktu) {
        String[] timeParts = waktu.split(":");
        if (timeParts.length != 2) return -1;
        
        try {
            int jam = Integer.parseInt(timeParts[0]);
            int menit = Integer.parseInt(timeParts[1]);
            
            if (jam < 0 || jam > 23 || menit < 0 || menit > 59) return -1;
            return (jam * MENIT_PER_JAM) + menit;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int hitungTotalShift(Scanner scanner) {
        int totalShift = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            
            if (line.matches("^[+-]\\d+$")) {
                try {
                    totalShift += Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Perintah tidak valid");
                }
            } else {
                System.out.println("Perintah tidak valid");
            }
        }
        return totalShift;
    }

    private static void cetakHasilAkhir(String jamAwalStr, int totalMenitAwal, int totalShift) {
        int rawFinalMins = totalMenitAwal + totalShift;
        int pergantianHari = Math.abs(Math.floorDiv(rawFinalMins, MENIT_PER_HARI));
        int finalMins = Math.floorMod(rawFinalMins, MENIT_PER_HARI);
        
        int finalJam = finalMins / MENIT_PER_JAM;
        int finalMenit = finalMins % MENIT_PER_JAM;
        
        String totalShiftStr = (totalShift > 0) ? "+" + totalShift : String.valueOf(totalShift);

        System.out.println("Jam Awal: " + jamAwalStr);
        System.out.printf("Jam Akhir: %02d:%02d\n", finalJam, finalMenit);
        System.out.println("Total Menit: " + totalShiftStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}