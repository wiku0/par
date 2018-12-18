package com.par.shared.Api;

import com.par.shared.Classes.Messages;
import com.par.shared.Classes.User;

import java.util.List;

public interface ChatApi {
    boolean sendMessage(User user,String s);
    List<Messages> getMessages(User user) throws Exception;
    User newUser(String name) throws Exception;
    List<User> getOnlineUsers();
    boolean logOut(User u);
}
