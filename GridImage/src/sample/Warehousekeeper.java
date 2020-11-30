package sample;

public class Warehousekeeper extends MapElement {

        private String filePath = "/resources/WarehouseKeeper.png";

        char symbol = '@';

        private int x;
        private int y;


        public int movePlayer(char[][] myArray1, char[][] map2){

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

            }

            return x;
        }
}
