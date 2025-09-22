import java.util.Scanner;

public class TabularCipher {

    // Encryption
    public static String encrypt(String plaintext, int key) {
        plaintext = plaintext.replaceAll(" ", "").toUpperCase();

        // build table for visualization
        int rows = (int) Math.ceil((double) plaintext.length() / key);
        char[][] table = new char[rows][key];

        int idx = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < key; c++) {
                if (idx < plaintext.length()) {
                    table[r][c] = plaintext.charAt(idx++);
                } else {
                    table[r][c] = '-';
                }
            }
        }

        // print encryption table
        System.out.println("\nEncryption Table:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < key; c++) {
                System.out.print(table[r][c] + " ");
            }
            System.out.println();
        }

        // read column by column
        StringBuilder ciphertext = new StringBuilder();
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < rows; row++) {
                if (table[row][col] != '-') {
                    ciphertext.append(table[row][col]);
                }
            }
        }

        return ciphertext.toString();
    }

    // Decryption
    public static String decrypt(String ciphertext, int key) {
        int numRows = ciphertext.length() / key;
        int extra = ciphertext.length() % key;

        int[] colLengths = new int[key];
        for (int i = 0; i < key; i++) {
            if (i < extra) {
                colLengths[i] = numRows + 1;
            } else {
                colLengths[i] = numRows;
            }
        }

        // Split ciphertext into columns
        String[] cols = new String[key];
        int start = 0;
        for (int i = 0; i < key; i++) {
            cols[i] = ciphertext.substring(start, start + colLengths[i]);
            start += colLengths[i];
        }

        // Reconstruct row by row
        StringBuilder plaintext = new StringBuilder();
        int rows = numRows + (extra > 0 ? 1 : 0);

        // print decryption table
        System.out.println("\nDecryption Table:");
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < key; c++) {
                if (r < cols[c].length()) {
                    System.out.print(cols[c].charAt(r) + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < key; c++) {
                if (r < cols[c].length()) {
                    plaintext.append(cols[c].charAt(r));
                }
            }
        }
        return plaintext.toString();
    }

    // Main Program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key (number of columns): ");
        int key = sc.nextInt();

        String encrypted = encrypt(plaintext, key);
        System.out.println("\nEncrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);

        sc.close();
    }
}
