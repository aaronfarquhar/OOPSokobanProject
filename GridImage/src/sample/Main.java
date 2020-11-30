package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    GridPane gridPane = new GridPane();
    static char[][] myArray1;
    static char[][] map2;

    private int x;
    private int y; // the player's current coordinates in the array

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        MapReader map = new MapReader();


        myArray1 = map.getLevel();
        map2 = map.getLevel();

        drawMap(myArray1);



        Scene scene = new Scene(gridPane, 700, 350);
        primaryStage.setTitle("Flags");
        primaryStage.setScene(scene);
        primaryStage.show();

        play(scene);










    }//end start method


    public static void main(String[] args) throws IOException {
        launch(args);


    }//end main method

    public void drawMap(char[][] myArray) {

        for (int i = 0; i < myArray.length; i++)

        {

             for (int j = 0; j < myArray[i].length; j++) //For, per column

                {



                    switch (myArray[i][j])
                    {
                        case ' ':

                        gridPane.add(new ImageView(new Image("/resources/Floor.png")), j, i);

                        break;

                        case 'X':

                        gridPane.add(new ImageView(new Image("/resources/Wall.png")), j, i);

                        break;

                        case '*':

                        gridPane.add(new ImageView(new Image("/resources/Crate.png")), j, i);

                        break;

                        case '.':

                        gridPane.add(new ImageView(new Image("/resources/Diamond.png")), j, i);

                        break;

                        case '@':

                        x = i;
                        y = j;

                        gridPane.add(new ImageView(new Image("/resources/WarehouseKeeper.png")), j, i);

                        break;
                        }//end switch



                    }//end column for




                }//end row for
            }//end draw map method


            private void play(Scene scene) {


             scene.setOnKeyPressed((EventHandler<Event>) event -> {

            // Get the key code
            KeyEvent ke = (KeyEvent)event;
            //strong turn
            KeyCode code = ke.getCode();

            switch (code) {
                case UP:

                    //channel and target point
                    if ( myArray1 [x-1][y] == ' ' || myArray1[x-1][y] == '.' ) {
                        //1. Restore the player's current location
                        if (map2[x][y] == '.' ) {
                            myArray1[x][y] = '.';
                        }else {
                            myArray1[x][y] = ' ';
                        }
                        //3. Move the player over
                        myArray1[x-1][y] = '@';
                        //4. Record the player's current coordinates
                        x = x - 1;
                        drawMap(myArray1);
                        break;
                    }
                    //If box
                    if (myArray1[x-1][y] == '*')
                    {
                        //if grind ref in front of box is a floor or a diamond
                        if (myArray1[x-1-1][y] == ' ' || myArray1[x-1-1][y] == '.')
                        {

                            if (map2[x][y] == '.')
                            {
                                myArray1[x][y] = '.';
                            }
                            else
                            {
                                myArray1[x][y] = ' ';
                            }
                            //3. Move the player over
                            myArray1[x-1][y] = '@';
                            //4. Record the player's current coordinates
                            x = x - 1;
                            // Move the box
                            //1. The current position of the box does not need to be restored.
                            //3. Moving the box
                            myArray1[x-1][y] = '*';

                            drawMap(myArray1);
                            //win();
                        }
                    }
                    break;
                case DOWN:

                    //channel and target point
                    if (myArray1[x+1][y]==' ' ||myArray1[x+1][y]=='.') {
                        if (map2[x][y]=='.') {
                            myArray1[x][y] = '.';
                        }else {
                            myArray1[x][y] = ' ';
                        }
                        //3. Move the player over
                        myArray1[x+1][y] = '@';
                        //4. Record the player's current coordinates
                        x+=1;
                        drawMap(myArray1);
                        break;
                    }
                    //If it is a box
                    if (myArray1[x+1][y]== '*') {
                        // Continue to judge the top of the box
                        // If it is a channel or target point
                        if (myArray1[x+1+1][y] == ' ' || myArray1[x+1+1][y] == '.') {
                            //Mobile player
                            if (map2[x][y]=='.') {
                                myArray1[x][y] = '.';
                            }else {
                                myArray1[x][y] = ' ';
                            }
                            //3. Move the player over
                            myArray1[x+1][y] = '@';
                            //4. Record the player's current coordinates
                            x+=1;
                            // Move the box
                            //1. The current position of the box does not need to be restored.
                            //3. Moving the box
                            myArray1[x+1][y] = '*';

                            drawMap(myArray1);
                            //win();
                        }
                    }
                    break;
                case LEFT:

                    //channel and target point
                    if (myArray1[x][y-1]== ' ' ||myArray1[x][y-1] == '.') {
                        if (map2[x][y]=='.') {
                            myArray1[x][y] = '.';
                        }else {
                            myArray1[x][y] = ' ';
                        }
                        //3. Move the player over
                        myArray1[x][y-1] = '@';
                        //4. Record the player's current coordinates
                        y-=1;
                        drawMap(myArray1);
                        break;
                    }
                    //If it is a box
                    if (myArray1[x][y-1]== '*') {
                        // Continue to judge the top of the box
                        // If it is a floor or target diamond
                        if (myArray1[x][y-1-1] == ' ' || myArray1[x][y-1-1] == '.') {

                            if (map2[x][y]==4) {
                                myArray1[x][y] = '.';
                            }else {
                                myArray1[x][y] = ' ';
                            }
                            //3. Move the player over
                            myArray1[x][y-1] = '@';
                            // Move the box
                            //1. The current position of the box does not need to be restored.
                            //3. Moving the box
                            myArray1[x][y-1-1] = '*';
                            //4. Record the player's current coordinates
                            y-=1;
                            //Redraw
                            drawMap(myArray1);
                            //win();
                        }
                    }
                    break;
                case RIGHT:

                    //channel and target point
                    if (myArray1[x][y+1] == ' ' ||myArray1[x][y+1] == '.') {
                        if (map2[x][y]=='.') {
                            myArray1[x][y] = '.';
                        }else {
                            myArray1[x][y] = ' ';
                        }
                        //3. Move the player over
                        myArray1[x][y+1] = '@';
                        //4. Record the player's current coordinates
                        y+=1;
                        drawMap(myArray1);
                        break;
                    }
                    //If it is a box
                    if (myArray1[x][y+1]== '*') {
                        // Continue to judge the top of the box
                        // If it is a channel or target point
                        if (myArray1[x][y+1+1]==' ' || myArray1[x][y+1+1]== '.' ) {
                            //Mobile player
                            if (map2[x][y]== '.' ) {
                                myArray1[x][y] = '.';
                            }else {
                                myArray1[x][y] = ' ' ;
                            }
                            //3. Move the player over
                            myArray1[x][y+1] = '@';

                            // Move the box
                            //1. The current position of the box does not need to be restored.
                            //3. Moving the box
                            myArray1[x][y+1+1] = '*';
                            //4. Record the player's current coordinates
                            y+=1;
                            drawMap(myArray1);
                            //win();
                        }
                    }
                    break;
                default:
                    break;
            }
        });

    }


}

