package org.example.PhoneValidator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    public static void findValidPhoneNumbers(String filePath) {
        if (!Files.exists(Paths.get(filePath))) {
            System.err.println("File not found");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern pattern = Pattern.compile("(\\(\\d{3}\\)\\s*\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "file.txt";
        findValidPhoneNumbers(filePath);
    }
}
