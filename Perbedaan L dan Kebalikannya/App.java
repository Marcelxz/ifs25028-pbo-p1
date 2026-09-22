import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int[][] matrix = bacaMatriks(scanner, n);

        int nilaiTengah = hitungNilaiTengah(matrix, n);

        // Menghilangkan duplikasi logika n=1 dan n=2
        if (n <= 2) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
        } else {
            int nilaiL = hitungNilaiL(matrix, n);
            int nilaiKebalikanL = hitungNilaiKebalikanL(matrix, n);
            int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
            int dominan = (perbedaan == 0) ? nilaiTengah : Math.max(nilaiL, nilaiKebalikanL);

            System.out.println("Nilai L: " + nilaiL);
            System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: " + perbedaan);
            System.out.println("Dominan: " + dominan);
        }
        scanner.close();
    }

    private static int[][] bacaMatriks(Scanner scanner, int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int hitungNilaiTengah(int[][] matrix, int n) {
        if (n == 1) return matrix[0][0];
        if (n == 2) return matrix[0][0] + matrix[0][1] + matrix[1][0] + matrix[1][1];
        
        if (n % 2 == 1) {
            return matrix[n / 2][n / 2];
        } else {
            int mid1 = n / 2 - 1;
            int mid2 = n / 2;
            return matrix[mid1][mid1] + matrix[mid1][mid2] +
                   matrix[mid2][mid1] + matrix[mid2][mid2];
        }
    }

    // Perhitungan Nilai L: Kolom paling kiri (semua baris) + Baris paling bawah (kecuali ujung kiri)
    private static int hitungNilaiL(int[][] matrix, int n) {
        int nilaiL = 0;
        for (int i = 0; i < n; i++) nilaiL += matrix[i][0];
        for (int j = 1; j < n - 1; j++) nilaiL += matrix[n - 1][j];
        return nilaiL;
    }

    // Perhitungan Kebalikan L: Kolom paling kanan (semua baris) + Baris paling atas (kecuali ujung kanan)
    private static int hitungNilaiKebalikanL(int[][] matrix, int n) {
        int nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) nilaiKebalikanL += matrix[i][n - 1]; 
        for (int j = 1; j < n - 1; j++) nilaiKebalikanL += matrix[0][j];
        return nilaiKebalikanL;
    }
}