import processing.core.PApplet;

import java.util.ArrayList;

public class KitchenDashboard extends PApplet {

    /*String activeOrder;

    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        textSize(18);
    }

    public void draw() {

        fill(0);
        textSize(40);
        text("Kitchen Menu", 300, 50);


        drawTables();

        if (activeOrder != null) {
            drawTableDetails();
        }

    }

    public void drawTables() {

        int x = 40;
        int y = 70;




        fill(255);
        stroke(0);
        // rect(x, y, width, height, radius);
        rect(x, y, 950, 450, 20);


      *//*  for (String order : "" ) {
*//*

            fill(0);
            textSize(20);
            text("", x + 40, y + 40);

              *//* if (table.getStatus() == order) {
                fill(0, 200, 0);
            } else if (table.getStatus() ==TableStatus.BUSY) {
                fill(220, 0, 0);
            } else {
                fill(0, 0, 200);
            }*//*
            ellipse(x + 560, y + 30, 20, 20);

            fill(0);
            textSize(20);
            text("AKTIVE", x + 640, y + 40);


            if (mousePressed &&
                    mouseX > x && mouseX < x + 610 &&
                    mouseY > y && mouseY < y + 50) {

                activeOrder = "READY";


            //}


            y += 40;
        }


    }

    public void drawTableDetails() {

        fill(0);
        textSize(20);
        text("READY", 780, 110);

    }

    @Override
    public void exit() {

        dispose();   // lukkes kun skærm
    }

    public static void main(String[] args) {
        PApplet.runSketch(
                new String[]{"KitchenDashboard"},
                new KitchenDashboard()
        );
    }*/



}
