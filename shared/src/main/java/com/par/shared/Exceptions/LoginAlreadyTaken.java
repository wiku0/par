package com.par.shared.Exceptions;

public class LoginAlreadyTaken extends Exception {
    public LoginAlreadyTaken(String login_already_taken) {
        super(login_already_taken);
    }
}
