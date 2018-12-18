package com.par.chat.server.Impl;


import com.par.chat.server.Endpoints.UserRepo;
import com.par.shared.Api.ChatApi;
import com.par.shared.Classes.Messages;
import com.par.shared.Classes.User;
import com.par.shared.Exceptions.LoginAlreadyTaken;
import com.par.shared.Exceptions.UserNotLoggedIn;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ChatApiImpl implements ChatApi, UserRepo {

    private final Logger LOGGER = Logger.getLogger(ChatApiImpl.class.getName());


    User user;

    @Override
    public boolean sendMessage(User user,String message) {
        Messages messages = new Messages();
        messages.setMsg(message);
        messages.setUser(user);
        onlineUsers.stream().forEach(s -> s.getUnreadMessages().add(messages));
        return true;
    }

    @Override
    public List<Messages> getMessages(User user) throws UserNotLoggedIn {
        try {
            List<Messages> msg = onlineUsers.stream().filter(us -> us.equals(user)).findFirst().get().getUnreadMessages();
            List<Messages> returnMsg = new ArrayList<>(msg);
            msg.clear();
            return returnMsg;
        } catch (NoSuchElementException e) {
            throw new UserNotLoggedIn("User is not logged in");
        }
    }


    @Override
    public User newUser(String name) throws LoginAlreadyTaken {
        user = new User();
        user.setName(name);
        if (!onlineUsers.contains(user)) {
            onlineUsers.add(user);
            return user;
        } else {
            throw new LoginAlreadyTaken("Login already taken");
        }

    }

    @Override
    public List<User> getOnlineUsers() {
        return onlineUsers;
    }

    @Override
    public boolean logOut(User u) {
        onlineUsers.remove(u);
        LOGGER.log(Level.INFO, "\n --- WYLOGOWANO --- \n \t User: " + u.getName() + " \n  ------");
        return true;
    }

}


