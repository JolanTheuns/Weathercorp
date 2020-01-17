package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.scene.chart.*;



public class Main extends Application {

    Scene firstButtonScene; //the first scene
    Scene secondButtonScene; //the second scene
    Scene graphingScene; //the scene with the graphs
    Scene SevenDaysScene;
    Scene todayScene;
    Button newButton;

    goToScene2 goToScene2 = new goToScene2();
    goToScene1 goToScene1 = new goToScene1();
    goToGraphingScene goToGraphingScene = new goToGraphingScene();
    goTo7DaysScene goTo7DaysScene = new goTo7DaysScene();
    goToTodayScene goToTodayScene = new goToTodayScene();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("WeatherCorp Alpha 1.1.0");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        //-------------------------------SETTING THE WEATHERCORP LOGO (IMAGE) IN SCENE 1------------------------------------
        FileInputStream input = new FileInputStream("C:/Users/antek/Desktop/Saxion2ndYear/SECONDQUARTER/ProjectSoftwareEngineering/logo.PNG");
        Image weatherCorpLogo = new Image(input);
        ImageView imageView = new ImageView(weatherCorpLogo); //making the image into a node, so that we may put it into
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setTranslateX(0);
        imageView.setTranslateY(-400); //MAKE THIS INTO A SEPARATE FUNCTION


        //-------------------------------CONFIGURING BUTTONS IN SCENE 1----------------------------------
        StackPane buttonLayout1 = new StackPane();

        Button firstButtonScene1;

        //----------------------------------------------------------------------------------------
        firstButtonScene1 = new Button();
        firstButtonScene1.setText("START EXPLORING THE WEATHER");
        firstButtonScene1.setStyle("-fx-background-color: yellow; -fx-font-size: 20px; -fx-font-color: red");
        firstButtonScene1.setPrefHeight(250);
        firstButtonScene1.setPrefWidth(250);
        firstButtonScene1.setTranslateX(0);
        firstButtonScene1.setTranslateY(-50);


        Tooltip tooltipButton1 = new Tooltip("Start exploring the \n weather");
        Tooltip.install(firstButtonScene1, tooltipButton1); //making an iOS-looking tooltip
        //-----------------------------CONFIGURING LABEL IN SCENE 1--------------------------

        Label label = new Label("WEATHER VIEWER ");
        label.setTranslateX(0);
        label.setTranslateY(-300); //positioning the label using the setTranslate method
        label.setStyle("-fx-font-size: 20px; -fx-font-size: 30px; -fx-font-weight: bold; -fx-fill: #ff0000");


        buttonLayout1.getChildren().add(firstButtonScene1);
        buttonLayout1.getChildren().add(imageView);
        buttonLayout1.getChildren().add(label);

        firstButtonScene = new Scene(buttonLayout1, 900, 900); //initializing the scene with the button layout to which we mapped the buttons.
        primaryStage.setScene(firstButtonScene); //setting the scene to scene 1
        primaryStage.show(); //showing the scene in the stage


        //-------------------------------CONFIGURE LABEL IN SCENE 2-------------------------------
        Label label2 = new Label("Check the weather over here -->");
        label2.setTranslateX(0);
        label2.setTranslateY(-300); //positioning the label using the setTranslate method
        label2.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-font-color: green");



        Label companyDescriptionHeader = new Label("SOLUTION");
        companyDescriptionHeader.setTranslateX(-40);
        companyDescriptionHeader.setTranslateY(-40);
        companyDescriptionHeader.setStyle("-fx-font-size: 22px; -fx-font-weight: bold");



        Label companyDescription = new Label("The company WeatherCorp has decided to implement this solution with \n the client's need in mind. As such, we aim to provide you with \n the best weather viewing solutions, so that you no longer\n have any problem planning you day! \n \n \n \n Yours sincerely, \n The WeatherCorp Team.");
        companyDescription.setTranslateX(-100);
        companyDescription.setTranslateY(100);
        companyDescription.setStyle("-fx-font-size: 17px; -fx-font-style: italic");


        //-------------------------------CONFIGURING BUTTONS IN SCENE 2-----------------------------

        StackPane buttonLayout2 = new StackPane();

        Button firstButtonScene2;

        firstButtonScene2 = new Button();
        firstButtonScene2.setText("Back to Scene 1 HERE");
        firstButtonScene2.setStyle("-fx-background-color: blue; -fx-font-size: 30px");
        firstButtonScene2.setPrefHeight(100);
        firstButtonScene2.setPrefWidth(250);
        firstButtonScene2.setTranslateX(300);
        firstButtonScene2.setTranslateY(270);


        //------------------------------------------------------------------------------------------
        Button accessGraphingScene;

        accessGraphingScene = new Button();
        accessGraphingScene.setText("Graphs");
        accessGraphingScene.setStyle("-fx-background-color: turquoise; -fx-font-size: 16px; -font-style-bold: bold");
        accessGraphingScene.setPrefHeight(80);
        accessGraphingScene.setPrefWidth(80);
        accessGraphingScene.setTranslateX(300);
        accessGraphingScene.setTranslateY(-300);


//-------------------------------SETTING THE WEATHERCORP LOGO (IMAGE) IN SCENE 2------------------------------------
        FileInputStream input2 = new FileInputStream("C:/Users/antek/Desktop/Saxion2ndYear/SECONDQUARTER/ProjectSoftwareEngineering/logo.PNG");
        Image weatherCorpLogo2 = new Image(input2);
        ImageView imageView2 = new ImageView(weatherCorpLogo2); //making the image into a node, so that we may put it into
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        imageView2.setTranslateX(0);
        imageView2.setTranslateY(-400); //importing the image from the directory. Note: the directory must be present when the image is imported, regardless of the system
        //MAKE THIS INTO A SEPARATE FUNCTION

//------------------------------------------------------------------------------------------------------------------
        buttonLayout2.getChildren().add(firstButtonScene2);
        buttonLayout2.getChildren().add(imageView2); //adding the image to the layout
        buttonLayout2.getChildren().add(label2); //adding the label to the layout
        buttonLayout2.getChildren().add(accessGraphingScene);
        buttonLayout2.getChildren().add(companyDescriptionHeader);
        buttonLayout2.getChildren().add(companyDescription);

        Tooltip tooltipButton2Scene2 = new Tooltip("Click here to go back to scene 1");
        Tooltip.install(firstButtonScene2, tooltipButton2Scene2);


        //-------------------------------INITIALIZING THE SECOND SCENE-----------------------
        secondButtonScene = new Scene(buttonLayout2, 900, 900); //we make this scene outside the method, because we are NOT going to be making a new scene everytime we call the goToScene2 method



        //------------------------------GRAPHING SCENE BUTTONS -------------------------------
        StackPane graphLayout = new StackPane(); //setting the graphing scene. Making a new button in Scene 2 to get us there

        Button goToScene1Button;

        goToScene1Button = new Button();
        goToScene1Button.setText("Back to Scene 1 HERE");
        goToScene1Button.setStyle("-fx-background-color: blue; -fx-font-size: 30px");
        goToScene1Button.setPrefHeight(100);
        goToScene1Button.setPrefWidth(250);
        goToScene1Button.setTranslateX(-100);
        goToScene1Button.setTranslateY(-50);


        Scene graphingScene = new Scene(graphLayout,900,900);




        //----------------------------------------------------------------------------------------------------------
        Button goToScene2Button;

        goToScene2Button = new Button();
        goToScene2Button.setText("Back to Scene 2 HERE");
        goToScene2Button.setStyle("-fx-background-color: blue; -fx-font-size: 30px");
        goToScene2Button.setPrefHeight(100);
        goToScene2Button.setPrefWidth(250);
        goToScene2Button.setTranslateX(0);
        goToScene2Button.setTranslateY(-50);

        //-------------------------------SETTING THE WEATHERCORP LOGO IN GRAPHING SCENE------------------------------

        FileInputStream inputGraphing = new FileInputStream("C:/Users/antek/Desktop/Saxion2ndYear/SECONDQUARTER/ProjectSoftwareEngineering/logo.PNG");
        Image weatherCorpLogoGraphing = new Image(inputGraphing);
        ImageView imageViewGraphing = new ImageView(weatherCorpLogoGraphing); //making the image into a node, so that we may put it into
        imageViewGraphing.setFitHeight(100);
        imageViewGraphing.setFitWidth(100);
        imageViewGraphing.setTranslateX(0);
        imageViewGraphing.setTranslateY(-400);


        //------------------------------SETTING THE LABEL IN THE GRAPHING SCENE--------------------------------------
        Label graphingSceneLabel = new Label("Here, you can watch your graphs for the past 7 days in quantities such as temperature, humidity,\n luminosity, and pressure.");
        graphingSceneLabel.setTranslateX(-40);
        graphingSceneLabel.setTranslateY(-200);
        graphingSceneLabel.setStyle("-fx-font-size: 17px; -fx-font-weight: bold");
        //-----------------------------------------------------------------------------------------------------------

        graphLayout.getChildren().add(goToScene2Button);
        graphLayout.getChildren().add(imageViewGraphing);
        graphLayout.getChildren().add(graphingSceneLabel);
        //MAKE THIS INTO A SEPARATE FUNCTION



        //-------------------------------ONACTION BUTTON METHODS-----------------------------

        firstButtonScene2.setOnAction(e2 ->
        {
            goToScene1.goToScene(firstButtonScene, primaryStage);
            System.out.println("Dutch weather sucks!");
            Controller.outputText();

        });


        firstButtonScene1.setOnAction(e1 ->
        {
            goToScene2.goToScene(secondButtonScene, primaryStage);
            Controller.outputText2(); //calling the method from the interface goToScene, implemented in goToScene1, in order to go to the scene
        });


        goToScene1Button.setOnAction(e3 ->
        {
            goToScene1.goToScene(firstButtonScene, primaryStage);
        });


        accessGraphingScene.setOnAction(e4 ->
        {
            goToGraphingScene.goToScene(graphingScene,primaryStage);
        });


        goToScene2Button.setOnAction(e5 ->
        {
            goToScene2.goToScene(secondButtonScene,primaryStage);
        });


        //--------------------------------CREATING BUTTONS FOR THE GRAPHING SCENE-----------------------------
        Button goTo7Days;

        goTo7Days = new Button();
        goTo7Days.setText("View weather for the last 7 days.");
        goTo7Days.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        goTo7Days.setPrefHeight(100);
        goTo7Days.setPrefWidth(250);
        goTo7Days.setTranslateX(300);
        goTo7Days.setTranslateY(100);
        //----------------------------------------------------------------------------------------------------
        Button goToToday;

        goToToday = new Button();
        goToToday.setText("View weather today.");
        goToToday.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        goToToday.setPrefHeight(100);
        goToToday.setPrefWidth(250);
        goToToday.setTranslateX(-300);
        goToToday.setTranslateY(100); //these buttons don't work; however, when pressed, the one taking us back to scene2 no longer does either.
        //the reason it's taking us nowhere, is because the scene has not been configured yet.



        //--------------------------------SETTING THE LAMBDA EXPRESSIONS FOR THE BUTTONS IN THE GRAPHING SCENE-----------
        boolean nowOr7Days = false; //the boolean variable for which query we have to use. If it's true, we run the query for finding the weather now - if it's false, we find it for the last 7 days

        //-------------------------------------------------------------------------------------------------------------------------
        goTo7Days.setOnAction(e6 ->
        {
            set7DaysTrue(nowOr7Days); //this method was made due to the impossibility of setting a variable to something inside a lambda
            goTo7DaysScene.goToScene(SevenDaysScene, primaryStage); //the other methods are not static; why is there a problem with this one not being?
        });


        goToToday.setOnAction(e7 ->
        {
            set7DaysFalse(nowOr7Days); //same for this method, except it sets the 7days to false (so we look for the weather just today)
            goToTodayScene.goToScene(todayScene, primaryStage);
        });


        //--------------------------------ADDING THE BUTTONS TO THE GRAPHING SCENE------------------------------------------
        graphLayout.getChildren().add(goTo7Days);
        graphLayout.getChildren().add(goToToday);


        //--------------------------------CREATING BUTTONS FOR THE TODAYSCENE AND 7DAYSSCENE-----------------------------
        Button SevenDaysButton1;
        SevenDaysButton1 = new Button();
        SevenDaysButton1.setText("Temperature");
        SevenDaysButton1.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        SevenDaysButton1.setPrefHeight(100);
        SevenDaysButton1.setPrefWidth(100);
        SevenDaysButton1.setTranslateX(-300);
        SevenDaysButton1.setTranslateY(100);


        Button SevenDaysButton2;
        SevenDaysButton2 = new Button();
        SevenDaysButton2.setText("Pressure");
        SevenDaysButton2.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        SevenDaysButton2.setPrefHeight(100);
        SevenDaysButton2.setPrefWidth(100);
        SevenDaysButton2.setTranslateX(-100);
        SevenDaysButton2.setTranslateY(100);



        Button SevenDaysButton3;
        SevenDaysButton3 = new Button();
        SevenDaysButton3.setText("Humidity");
        SevenDaysButton3.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        SevenDaysButton3.setPrefHeight(100);
        SevenDaysButton3.setPrefWidth(100);
        SevenDaysButton3.setTranslateX(100);
        SevenDaysButton3.setTranslateY(100);



        Button SevenDaysButton4;
        SevenDaysButton4 = new Button();
        SevenDaysButton4.setText("Luminosity");
        SevenDaysButton4.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        SevenDaysButton4.setPrefHeight(100);
        SevenDaysButton4.setPrefWidth(100);
        SevenDaysButton4.setTranslateX(300);
        SevenDaysButton4.setTranslateY(100);



        Button backToScene2FromSeven;
        backToScene2FromSeven= new Button();
        backToScene2FromSeven.setText("Back to Scene 2 HERE");
        backToScene2FromSeven.setStyle("-fx-background-color: blue; -fx-font-size: 30px");
        backToScene2FromSeven.setPrefHeight(100);
        backToScene2FromSeven.setPrefWidth(250);
        backToScene2FromSeven.setTranslateX(0);
        backToScene2FromSeven.setTranslateY(-50);
        //-------------------------------------------------------------------------------------------------------------------
        Button TodayButton1;
        TodayButton1 = new Button();
        TodayButton1.setText("Temperature");
        TodayButton1.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        TodayButton1.setPrefHeight(100);
        TodayButton1.setPrefWidth(100);
        TodayButton1.setTranslateX(-300);
        TodayButton1.setTranslateY(100);


        Button TodayButton2;
        TodayButton2 = new Button();
        TodayButton2.setText("Pressure");
        TodayButton2.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        TodayButton2.setPrefHeight(100);
        TodayButton2.setPrefWidth(100);
        TodayButton2.setTranslateX(-100);
        TodayButton2.setTranslateY(100);



        Button TodayButton3;
        TodayButton3 = new Button();
        TodayButton3.setText("Humidity");
        TodayButton3.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        TodayButton3.setPrefHeight(100);
        TodayButton3.setPrefWidth(100);
        TodayButton3.setTranslateX(100);
        TodayButton3.setTranslateY(100);



        Button TodayButton4;
        TodayButton4 = new Button();
        TodayButton4.setText("Luminosity");
        TodayButton4.setStyle("-fx-background-color: lightblue; -fx-font-size: 15px");
        TodayButton4.setPrefHeight(100);
        TodayButton4.setPrefWidth(100);
        TodayButton4.setTranslateX(300);
        TodayButton4.setTranslateY(100);



        Button backToScene2FromToday;
        backToScene2FromToday = new Button();
        backToScene2FromToday.setText("Back to Scene 2 HERE");
        backToScene2FromToday.setStyle("-fx-background-color: blue; -fx-font-size: 30px");
        backToScene2FromToday.setPrefHeight(100);
        backToScene2FromToday.setPrefWidth(250);
        backToScene2FromToday.setTranslateX(0);
        backToScene2FromToday.setTranslateY(-50);

        //--------------------------------ADDING THE BUTTONS TO THE TODAYSCENE AND 7DAYSSCENE------------------------------
        StackPane todayLayout = new StackPane();
        todayLayout.getChildren().add(TodayButton1);
        todayLayout.getChildren().add(TodayButton2);
        todayLayout.getChildren().add(TodayButton3);
        todayLayout.getChildren().add(TodayButton4);
        todayLayout.getChildren().add(backToScene2FromToday);

        todayScene = new Scene(todayLayout, 900, 900);



        StackPane sevenDaysLayout = new StackPane();
        sevenDaysLayout.getChildren().add(SevenDaysButton1);
        sevenDaysLayout.getChildren().add(SevenDaysButton2);
        sevenDaysLayout.getChildren().add(SevenDaysButton3);
        sevenDaysLayout.getChildren().add(SevenDaysButton4);
        sevenDaysLayout.getChildren().add(backToScene2FromSeven);

        SevenDaysScene = new Scene(sevenDaysLayout, 900, 900);



        //--------------------------------SETTING LAMBDA EXPRESSIONS FOR THE BUTTONS IN THE TODAYSCENE AND 7DAYSSCENE----
        int controlVariable = 0;
        ArrayList<Integer> temporary= new ArrayList<Integer>();


        SevenDaysButton1.setOnAction(e8 ->
        {
            setControlVariable1(controlVariable);
            set7DaysTrue(nowOr7Days); //calling the appropriate methods from this class to set the controlVariable for the quantity we want, as well as whether we want it for now or for the 7 last days.
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable)); //calling the method from the weatherDAO function, in which we specify what stuff we want to fetch from the database.
            System.out.println(temporary); //printing out the arrayList which we filled in after having passed it to the setArrayList function
        });


        SevenDaysButton2.setOnAction(e9 ->
        {
            setControlVariable2(controlVariable);
            set7DaysTrue(nowOr7Days); //once again, calling the correct methods to make sure that we 'land' in the correct section of the getInformation method in the weatherDAO class, when wanting to access information
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable)); //filling in the arrayList, in the same manner as in the previous button
            System.out.println(temporary); //printing out the filled arrayList (we use the arraylist because it is a dynamic data structure, meaning it can be expanded anytime, unlike an array.)
        });


        SevenDaysButton3.setOnAction(e10 ->
        {
            setControlVariable3(controlVariable);
            set7DaysTrue(nowOr7Days);
            setArrayLists.setArrayList(temporary, weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        SevenDaysButton4.setOnAction(e11 ->
        {
            setControlVariable4(controlVariable);
            set7DaysTrue(nowOr7Days);
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        backToScene2FromSeven.setOnAction(e12 ->
        {
            goToScene2.goToScene(secondButtonScene,primaryStage);
        });

        //------------------------------------------------------------------------------------------------------------------
        TodayButton1.setOnAction(e12 ->
        {
            setControlVariable1(controlVariable);
            set7DaysFalse(nowOr7Days); //in this case, we do the same thing, but we set the nowOr7Days variable to false (because we are looking for the weather today)
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        TodayButton2.setOnAction(e13 ->
        {
            setControlVariable2(controlVariable);
            set7DaysFalse(nowOr7Days);
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        TodayButton3.setOnAction(e14 ->
        {
            setControlVariable3(controlVariable);
            set7DaysFalse(nowOr7Days);
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        TodayButton4.setOnAction(e15 ->
        {
            setControlVariable4(controlVariable);
            set7DaysFalse(nowOr7Days);
            setArrayLists.setArrayList(temporary,weatherDAO.getInformation(nowOr7Days,controlVariable));
            System.out.println(temporary);
        });


        backToScene2FromToday.setOnAction(e16 ->
        {
            goToScene2.goToScene(secondButtonScene,primaryStage);
        });
    }


    //------------------------------------METHODS FOR SETTING THE NOWOR7DAYS BOOLEAN VARIABLE TO TRUE AND FALSE AND THE CONTROLVARIABLE TO 1,2,3, OR 4 (this working around the problem of not being able to assign value to variables inside of lambda expressions. )

    public void set7DaysTrue(boolean nowOr7Days)
    {
        nowOr7Days = true;
    }



    public void set7DaysFalse(boolean nowOr7Days)
    {
        nowOr7Days = false;
    }


    public void setControlVariable1(int controlVariable1)
    {
        controlVariable1 = 1;
    }


    public void setControlVariable2(int controlVariable1)
    {
        controlVariable1 = 2;
    }


    public void setControlVariable3(int controlVariable1)
    {
        controlVariable1 = 3;
    }


    public void setControlVariable4(int controlVariable1)
    {
        controlVariable1 = 4;
    }




    //--------------------------------CALLING THE MAIN---------------------------------------
    public static void main(String[] args) {
        launch(args);
    }
}
