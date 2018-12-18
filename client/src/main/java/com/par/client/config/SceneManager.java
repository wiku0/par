package com.par.client.config;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;


@Service
public class SceneManager {

    private Stage primaryStage;
    
    public void setStage(Stage stage) {
        primaryStage = stage;
    }
    
    public <T> void showScene(View<T> view) {
        showScene(view, null);
    }
    
    public <T> void showScene(View<T> view, T param) {
        primaryStage.setScene(buildScene(view));
        primaryStage.show();
        
        view.onTransition(param);
    }
    
    private <T> Scene buildScene(View<T> view) {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(view.getRoot());


        return new Scene(borderPane);
    }
    
}
