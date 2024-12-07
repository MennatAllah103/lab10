/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserManagementBackend;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author LENOVO
 */
public class PasswordHashing {
    
    
    
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
    // Initialize the MessageDigest for SHA-256
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    // Hash the password bytes
    byte[] hashedBytes = md.digest(password.getBytes());
    // Encode the hashed bytes to a Base64 string
    return Base64.getEncoder().encodeToString(hashedBytes);
} 
    public static boolean validatePassword(String inputPassword, String storedHashedPassword) throws NoSuchAlgorithmException {
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(storedHashedPassword);
    }
    
    
}
