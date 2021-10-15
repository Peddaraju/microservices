package com.app.rest.dao;

import com.app.rest.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAO {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 4;
    static {
        users.add(new User(1, "Raju", new Date()));
        users.add(new User(2, "Madh", new Date()));
        users.add(new User(3, "abc", new Date()));
        users.add(new User(4, "dss", new Date()));
    }


    public List<User> getAllUsers() {
        return users;
    }

    public User save(User user) {
        if(user.getUserId() == null) {
            user.setUserId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User getUser(int id) {
        for(User user:users) {
            if(user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }
}
