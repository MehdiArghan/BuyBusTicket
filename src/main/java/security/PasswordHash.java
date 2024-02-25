package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    public String createHashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte input : bytes) {
            stringBuilder.append(String.format("%02x", input));
        }
        return stringBuilder.toString();
    }

    public boolean checkEqualityOfHashedPassword(String enteredPassword, String dataBasePassword) throws NoSuchAlgorithmException {
        return dataBasePassword.equals(createHashedPassword(enteredPassword));
    }
}
