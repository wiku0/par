package com.par.client.Controllers;

import com.par.client.config.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainControllerView extends View {

    @Autowired
    MainController mainController;

    @Override
    public void onTransition(Object param) {
        mainController.onTransition(param) ;
    }

    @Override
    protected String getViewUrl() {
        return "/main.fxml";
    }

    @Override
    protected Object getController() {
        return mainController;
    }
}
