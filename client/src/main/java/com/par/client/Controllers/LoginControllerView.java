package com.par.client.Controllers;

import com.par.client.config.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginControllerView extends View {

    @Autowired
    LoginController loginController;

    @Override
    protected String getViewUrl() {
        return "/loginScene.fxml";
    }

    @Override
    protected Object getController() {
        return loginController;
    }
}
