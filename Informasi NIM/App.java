import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nim = input.nextLine();

        if (!isValidNim(nim)) {
            System.out.println("NIM harus 8 karakter");
            input.close();
            return;
        }

        String prefix = nim.substring(0, 3);
        String prodi = getProgramStudi(prefix);

        if (prodi == null) {
            System.out.println("Kode tidak tersedia");
            input.close();
            return;
        }

        int angkatan = getAngkatan(nim);
        int urutan = getUrutan(nim);

        cetakInformasi(nim, prodi, angkatan, urutan);
        input.close();
    }

    private static boolean isValidNim(String nim) {
        return nim != null && nim.length() == 8;
    }

    private static String getProgramStudi(String prefix) {
        switch (prefix) {
            case "11S": return "Sarjana Informatika";
            case "12S": return "Sarjana Sistem Informasi";
            case "13S": return "Sarjana Teknik Elektro";
            case "21S": return "Sarjana Manajemen Rekayasa";
            case "22S": return "Sarjana Teknik Metalurgi";
            case "31S": return "Sarjana Teknik Bioproses";
            case "32S": return "Sarjana Bioteknologi";
            case "114": return "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
            case "113": return "Diploma 3 Teknologi Informasi";
            case "133": return "Diploma 3 Teknologi Komputer";
            default: return null;
        }
    }

    private static int getAngkatan(String nim) {
        return Integer.parseInt("20" + nim.substring(3, 5));
    }

    private static int getUrutan(String nim) {
        return Integer.parseInt(nim.substring(5, 8));
    }

    private static void cetakInformasi(String nim, String prodi, int angkatan, int urutan) {
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + prodi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }
}