package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class goToGraphingScene implements goToScene{     //this is the child class of the goToScene interface, designed for accessing the interface which has the graphs


    @Override
    public void goToScene(Scene graphingScene, Stage primaryStage)
    {
        primaryStage.setScene(graphingScene);
        primaryStage.show();
    }

}
