package test;
import org.jasypt.util.text.AES256TextEncryptor;

public class EncryptionDecryptionTest {
    public static void main(String[] args) {
        String encryptionKey = "REFUNDPLATFORM"; // Şifreleme anahtarı
        String originalPassword = "yeniSifre"; // Şifrelemek istediğiniz orijinal şifre

        // Şifreleme
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(encryptionKey);
        String encryptedPassword = textEncryptor.encrypt(originalPassword);

        // Deşifreleme
        String decryptedPassword = textEncryptor.decrypt(encryptedPassword);

        // Sonuçları yazdırma
        System.out.println("Original Password: " + originalPassword);
        System.out.println("Encrypted Password: " + encryptedPassword);
        System.out.println("Decrypted Password: " + decryptedPassword);

        // Doğrulama
        if (originalPassword.equals(decryptedPassword)) {
            System.out.println("Encryption and Decryption test passed!");
        } else {
            System.out.println("Encryption and Decryption test failed!");
        }
    }
}

