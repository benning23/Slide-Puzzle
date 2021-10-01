package com.example.slidepuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/*
    Author: Charlie Benning
    Date: 10/01/21

    This program initializes a solvable 15 slide puzzle using a grid view and allows a player
    to hit the 'tiles' to move them around. When the tiles are placed in the correct order the
    tiles all change to a green color to indicate that the player has solved the puzzle

    External help received from: Ben Tribelhorn, Josh Henderson, and StackOverflow
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Tile[][] tiles = new Tile[4][4]; //set up 4 x 4 array to hold tiles

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the reset btn and set it to listen in this class
        View resetBtn = (View) findViewById(R.id.resetButton);
        resetBtn.setOnClickListener(this);

        //set all buttons to find view buttons
        Button one = (Button) findViewById(R.id.button1);
        Button two = (Button) findViewById(R.id.button2);
        Button three = (Button) findViewById(R.id.button3);
        Button four = (Button) findViewById(R.id.button4);
        Button five = (Button) findViewById(R.id.button5);
        Button six = (Button) findViewById(R.id.button6);
        Button seven = (Button) findViewById(R.id.button7);
        Button eight = (Button) findViewById(R.id.button8);
        Button nine = (Button) findViewById(R.id.button9);
        Button ten = (Button) findViewById(R.id.button10);
        Button eleven = (Button) findViewById(R.id.button11);
        Button twelve = (Button) findViewById(R.id.button12);
        Button thirteen = (Button) findViewById(R.id.button13);
        Button fourteen = (Button) findViewById(R.id.button14);
        Button fifteen = (Button) findViewById(R.id.button15);
        Button sixteen = (Button) findViewById(R.id.button16);

        //initializing tiles with number (string on top) and button assignment
        Tile tile1 = new Tile("1", one);
        Tile tile2 = new Tile("2", two);
        Tile tile3 = new Tile("3", three);
        Tile tile4 = new Tile("4", four);
        Tile tile5 = new Tile("5", five);
        Tile tile6 = new Tile("6", six);
        Tile tile7 = new Tile("7", seven);
        Tile tile8 = new Tile("8", eight);
        Tile tile9 = new Tile("9", nine);
        Tile tile10 = new Tile("10", ten);
        Tile tile11 = new Tile("11", eleven);
        Tile tile12 = new Tile("12", twelve);
        Tile tile13 = new Tile("13", thirteen);
        Tile tile14 = new Tile("14", fourteen);
        Tile tile15 = new Tile("15", fifteen);
        Tile tileBlank = new Tile(" ", sixteen);

        //set all tiles in a double array of tiles
        tiles[0][0] = tile1;
        tiles[0][1] = tile2;
        tiles[0][2] = tile3;
        tiles[0][3] = tile4;
        tiles[1][0] = tile5;
        tiles[1][1] = tile6;
        tiles[1][2] = tile7;
        tiles[1][3] = tile8;
        tiles[2][0] = tile9;
        tiles[2][1] = tile10;
        tiles[2][2] = tile11;
        tiles[2][3] = tile12;
        tiles[3][0] = tile13;
        tiles[3][1] = tile14;
        tiles[3][2] = tile15;
        tiles[3][3] = tileBlank;

        //set all buttons to listen in this class
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].getBtn().setOnClickListener(this);
            }
        }
        reset(); //randomize tiles for start of the game
    }

    /*
        The onClick method identifies what button was clicked and calls the
        appropriate methods to be executed for each button
     */
    public void onClick(View view) {

        if (view.getId() == R.id.resetButton) { //if reset button is clicked call reset
            reset();
        }
        else {
            Button clickedBtn = (Button) view;
            String btnText = clickedBtn.getText().toString(); //set the clicked button's text to btnText
            if (btnText.equals(" ")) {
                //clicked on the blank, do nothing
            }
            else {
                int tileNum = Integer.valueOf(btnText); //set text number to int
                for (int i = 0; i < tiles.length; i++) { //loop through tiles to find tile with matching number
                    for (int j = 0; j < tiles[i].length; j++) {
                      if (tiles[i][j].getNum().equals(""+tileNum)) {
                          //if tile at array point i j has the number equal to the button's text,call scanBlank
                          scanBlank(i, j);
                          return; //exit after finding correct tile
                      }
                    }
                }
            }
        }
    }

    /*
        Scan the surrounding tiles to see if they are the blank spot, in order to initialize a swap
     */
    public void scanBlank(int i, int j) {
        if ((i + 1 < 4) && tiles[i + 1][j].getBtn().getText().equals(" ")){ //check if above tile is blank
            swap(tiles[i + 1][j], tiles[i][j]);
        }
        else if ((i - 1 >= 0 ) && tiles[i - 1][j].getBtn().getText().equals(" ")) { //check if below tile is blank
            swap(tiles[i - 1][j], tiles[i][j]);
        }
        else if ((j + 1 < 4) && tiles[i][j + 1].getBtn().getText().equals(" ")) { //check if right tile is blank
            swap(tiles[i][j + 1], tiles[i][j]);
        }
        else if ((j - 1 >= 0) && tiles[i][j - 1].getBtn().getText().equals(" ")) { //check if left tile is blank
            swap(tiles[i][j - 1], tiles[i][j]);
        }
    }

    /*
        swap the tile's numbers, changing the color if the puzzle is solved
     */
    private void swap(Tile tileA, Tile tileB) {
        String temp = tileA.getNum();
        tileA.setNum(tileB.getNum());
        tileB.setNum(temp);
        if (isSolved()) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    tiles[i][j].getBtn().setBackgroundColor(Color.GREEN); //change tile background color to indicate win
                    tiles[i][j].getBtn().setTextColor(Color.BLACK);
                }
            }
        }
        else {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    tiles[i][j].getBtn().setBackgroundColor(Color.BLACK); //set tiles back to default when not in win position
                    tiles[i][j].getBtn().setTextColor(Color.WHITE);
                }
            }
        }
    }

    /*
        randomly shuffles tiles around to initialize puzzle,
        with blank spot starting in bottom corner
     */
    public void reset() {
        int size = 15;
        ArrayList<Integer> nums = new ArrayList<Integer>(); //create an arrayList
        Random r = new Random(); //create a random variable
        int a;

        for (int i = 0; i < size ; i++) {
            nums.add(i + 1); //put numbers 1-15 in the arrayList
        }
        for (int i = 0; i < tiles.length; i++) { //loop through tiles array
            for (int j = 0; j < tiles[i].length; j++) {
                if (i == 3 && j == 3) { //set bottom corner to blank
                    tiles[i][j].getBtn().setText(" ");
                    tiles[i][j].setNum(" ");
                }
                else {
                    a = r.nextInt(size); //get a random int
                    tiles[i][j].getBtn().setText(String.valueOf(nums.get(a))); //set btn text to random num
                    tiles[i][j].setNum(String.valueOf(nums.get(a))); //set tile number string to random num
                    nums.remove(a); //delete random num from arraylist as to not use it again
                    size--;
                }
                tiles[i][j].getBtn().setBackgroundColor(Color.BLACK); //set background color to desired color for game
                tiles[i][j].getBtn().setTextColor(Color.WHITE);
            }
        }
        if (!isSolvable()) { //if slide puzzle isn't solvable keep resetting until it is
            reset();
        }
    }

    /*
        Checks to see if array is solved (makes sure every tile is in order)
     */
    private boolean isSolved() {

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if ((i * 4) + (j + 1) == 16) {
                    return true;
                } else if (!tiles[i][j].getNum().equals(""+((i * 4) + (j + 1)))) {
                    //if the tiles don't match in order, return false
                    return false;
                }
            }
        }
        return true;
    }

    // half permutations of puzzle are solvable
    // If tile is preceded by a tile with higher value it is as an inversion.
    // The blank tile in the solved position, the number of inversions must be even for the puzzle to be solvable
    private boolean isSolvable() {
        int countInversions = 0; //set var to count inversions

        //create array list to store double array in
        ArrayList<Tile> check = new ArrayList<Tile>();
        for (int i =0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                check.add(tiles[i][j]); //put double array in order into the arraylist to more easily identify permutations
            }
        }

        for (int i = 0; i < 16; i++) { //loop through ArrayList
            for (int j = i; j < 16; j++) {
                if (check.get(i).getNum().equals(" ") || check.get(j).getNum().equals(" ")) {
                    //skip counting or comparing the blank tile as it should always start
                    // in the last position and will therefore have no permutations
                }
                else if (Integer.valueOf(check.get(i).getNum()) > Integer.valueOf(check.get(j).getNum())) {
                    //if preceding number is larger than following one = permutation
                    countInversions++;
                }

            }
        }
        System.out.println(countInversions);
        return countInversions % 2 == 0; //if the number of inversions is even return true
    }
}