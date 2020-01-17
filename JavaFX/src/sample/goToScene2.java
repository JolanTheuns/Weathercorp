package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class goToScene2 implements goToScene {

    public void goToScene(Scene secondScene, Stage primaryStage)
    {
        primaryStage.setScene(secondScene);
        primaryStage.show();
    }
}
