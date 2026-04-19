package com.korit.servletstudy.login;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users;

    public UserRepository(ServletContext context) {
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
                    objectMapper.getTypeFactory().constructCollectionType(List.class, User.class)
            );
        } catch (IOException e) {
            users = new ArrayList<>();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    public User save(User user) {
        users.add(user);
        return user;
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
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findByAll() {
        return users;
    }

}
