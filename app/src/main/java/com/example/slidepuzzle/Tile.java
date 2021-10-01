package com.example.slidepuzzle;

import android.widget.Button;

public class Tile {
    //initialize instance variables
    private String number;
    private Button btn;

    //constructor
    public Tile (String num, Button b){
        number = num;
        btn = b;

    }

    //set getter and setter methods for instance variables
    public String getNum() {
        return number;
    }

    public void setNum(String newNum) {
        number = newNum;
        btn.setText(number);
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button newBtn) {
        btn = newBtn;
    }

}
