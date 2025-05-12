package service;

import model.User;
import java.io.*;

public class AuthService {
    private final String userFile = "data/users.txt";

    public AuthService() {
        File file = new File(userFile);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(userFile))) {
                pw.println("admin,admin,admin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public User login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
                    return new User(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String password) {
        if (isUserExist(username)) return false;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true))) {
            bw.write(username + "," + password + ",user\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUserExist(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}