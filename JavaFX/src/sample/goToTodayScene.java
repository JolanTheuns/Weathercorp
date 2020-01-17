package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class goToTodayScene implements goToScene {

    @Override
    public void goToScene(Scene todayScene, Stage primaryStage)
    {
        primaryStage.setScene(todayScene);
        primaryStage.show();
    }
}
