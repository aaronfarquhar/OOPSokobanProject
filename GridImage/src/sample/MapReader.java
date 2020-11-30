package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class MapReader {

    File mapFile = new File("C:\\GridImage\\src\\resources\\level1.txt");

    int totalRow = 11;
    int totalColumn = 22;
    private int x; // the player's current coordinates in the array
    private int y;

    char[][] myArray = new char[totalRow][totalColumn];


    public char[][] getLevel() throws FileNotFoundException {

        String loadLevel = "";


        Scanner scanner = new Scanner(mapFile);


        for (int row = 0; scanner.hasNextLine() && row < totalRow; row++) {
            char[] chars = scanner.nextLine().toCharArray();
            for (int i = 0; i < totalColumn && i < chars.length; i++) {
                myArray[row][i] = chars[i];
            }
        }

        System.out.println(Arrays.deepToString(myArray).replace("], ", "]\n"));

        return myArray;

    }


}




