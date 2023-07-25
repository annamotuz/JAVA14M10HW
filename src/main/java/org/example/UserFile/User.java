package org.example.UserFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        String inputFilePath = "file_user.txt";
        String outputFilePath = "user.json";

        List<User> userList = readUsersFromFile(inputFilePath);

        if (userList != null && !userList.isEmpty()) {
            writeUsersToJsonFile(userList, outputFilePath);
        }
    }

    private static List<User> readUsersFromFile(String filePath) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] header = br.readLine().split(" ");

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(" ");
                if (userData.length == 2) {
                    String name = userData[0];
                    int age = Integer.parseInt(userData[1]);
                    userList.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static void writeUsersToJsonFile(List<User> userList, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(userList);
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
