import java.util.Scanner;

public class TabularCipher {

    // Encryption
    public static String encrypt(String plaintext, int key) {
        plaintext = plaintext.replaceAll(" ", "").toUpperCase();

        StringBuilder ciphertext = new StringBuilder();

        // Read column by column
        for (int col = 0; col < key; col++) {
            for (int i = col; i < plaintext.length(); i += key) {
                ciphertext.append(plaintext.charAt(i));
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
        for (int r = 0; r < numRows + 1; r++) {
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
