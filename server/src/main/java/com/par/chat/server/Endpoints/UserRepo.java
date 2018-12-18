package com.par.chat.server.Endpoints;

import com.par.shared.Classes.User;

import java.util.ArrayList;
import java.util.List;

public interface UserRepo {
    List<User> onlineUsers = new ArrayList<>();
}
