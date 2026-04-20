package com.korit.servletstudy.login;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class UserRepository {

    private List<User> users;
    private int autoincrement;
    private ServletContext context;

    public UserRepository(ServletContext context) {
        this.context = context;
        loadFile();
    }

    public void saveFile() {
        String realPath = context.getRealPath("/WEB-INF/users.json");
        try (FileWriter fileWriter = new FileWriter(realPath)){
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(users);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile() {
        String realPath = context.getRealPath("/WEB-INF/users.json");
        //System.out.println("realPath: " + realPath);
        try(FileReader fileReader = new FileReader(realPath)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            this.users = objectMapper.readValue(
                    stringBuilder.toString(),
                    new TypeReference<List<User>>() {}
//                    objectMapper.getTypeFactory().constructCollectionType(List.class, User.class)
            );
            users.stream()
                    .map(user -> user.getId())
                    .max(Comparator.comparingInt(id -> id))
                    .orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User save(User user) {
        User foundUser = findById(user.getId());
        if (foundUser == null) {
            user.setId(++autoincrement);
            users.add(user);
            saveFile();
            return user;
        }
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        foundUser.setEmail(user.getEmail());
        foundUser.setRole(user.getRole());
        saveFile();
        return foundUser;
    }

    public User findById(int id) {
        for (User user : users) {
           if (user.getId() == id) {
               return user;
           }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User user : users ) {
            if (Objects.equals(user.getUsername(), username)) return user;
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
        }
        return null;
    }

    public List<User> findByAll() {
        return users;
    }

}
