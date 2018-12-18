package com.par.client;


import com.par.client.Controllers.LoginControllerView;
import com.par.client.config.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

    @Autowired
    SceneManager sceneManager;

    @Autowired
    LoginControllerView loginControllerView;

    private AnnotationConfigApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        applicationContext = new AnnotationConfigApplicationContext(getClass());
        applicationContext
                .getAutowireCapableBeanFactory()
                .autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_NO, true);
        primaryStage.setTitle("Chat");
        primaryStage.setResizable(false);
        sceneManager.setStage(primaryStage);
        sceneManager.showScene(loginControllerView);
    }

    @Override
    public void stop() throws Exception {
        applicationContext.destroy();
    }


}
