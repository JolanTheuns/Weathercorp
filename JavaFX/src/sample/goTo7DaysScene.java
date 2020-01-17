package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class goTo7DaysScene implements goToScene {


    @Override
    public void goToScene(Scene SevenDaysScene, Stage primaryStage)
    {
        primaryStage.setScene(SevenDaysScene);
        primaryStage.show();
    }
}
