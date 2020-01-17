package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class goToScene1 implements goToScene {

    public void goToScene(Scene firstScene, Stage primaryStage) //method to make the changing scene button work. This is done to make it better organized.
    {
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
}
