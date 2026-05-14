
import processing.core.PApplet;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

    public class WaiterDashboard extends PApplet {

        Table activeTable;
        ArrayList<MenuCard> menuCards;

        public void settings() {
            size(1450, 700);
        }

        public void setup() {
            textSize(18);
            menuCards = new ArrayList<>();
        }

        public void draw() {

            drawGradientBackground();

            fill(255, 0, 0);
            textSize(15);
            text("Klik venligst på tabellerne for at se ordrerne.", 65, 80);

            fill(241, 245, 249);
            textSize(15);
            text("Når en handling udføres i terminalen,", 60, 554);
            text("overvåges den fra denne skærm.", 60, 574);


            fill(30);
            textSize(30);
            text("Waiter Dashboard", 130, 50);

            drawTables();

            if (activeTable != null) {
                drawTableDetails(activeTable);
            }


        }

        // ================= BACKGROUND =================

        public void drawGradientBackground() {

            for (int i = 0; i < height; i++) {

                float inter = map(i, 0, height, 0, 1);

                int c = lerpColor(
                        color(210, 235, 255),
                        color(40, 70, 130),
                        inter
                );

                stroke(c);
                line(0, i, width, i);
            }
        }

        // ================= TABLES =================

        public void drawTables() {

            int x = 50;
            int y = 100;

            for (Table table : Restaurant.tables) {

                fill(255);   // Hvide
                stroke(180);
                // rect(x, y, width, height, radius);
                rect(x, y, 180, 80, 20);

                if (table.getStatus() == TableStatus.AVAILABLE) {
                    fill(0, 200, 0);
                } else if (table.getStatus() ==TableStatus.BUSY) {
                    fill(220, 0, 0);
                } else {
                    fill(0, 0, 200);
                }
                ellipse(x + 150, y + 25, 20, 20);


                fill(0);
                textSize(13);
                text(table.getId()+".Table", x + 20, y + 23);

                textSize(13);
                text("Status: " + table.getStatus(), x + 20, y + 45);
                text("Faktura: " + table.getTotalAmount() + " kr", x + 20, y + 65);

                if (mousePressed &&
                        mouseX > x && mouseX < x + 180 &&
                        mouseY > y && mouseY < y + 120) {

                    activeTable = table;
                }

                if (y < 330 ) {
                    y += 110;
                } else  {
                    y = 100;
                    x += 220;
                }




            }
        }

        public void drawTableDetails(Table table) {

            fill(255);
            // rect(x, y, width, height, radius);
            rect(490, 75, 630, 500, 25);

            fill(0);
            textSize(33);
            text("Bord Detaljer", 524, 120);

            textSize(20);
            text("Table: " + activeTable.getId(), 535, 155);
            text("Status: " + activeTable.getStatus(), 700, 155);
            text("Amount: " + String.format("%.2f", activeTable.getTotalAmount()) + " kr", 915, 155);


            fill(255);
            // rect(x, y, width, height, radius);
            rect(1150, 75, 250, 500, 25);

            fill(0);
            textSize(33);
            text("Info", 1214, 120);

            textSize(20);
            text("Invoice No: " + activeTable.getOrderNo(), 1160, 155);
            // Date
            if (activeTable.getOpeningTime() != null) {
                text("Opening Time: " + activeTable.getOpeningTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 1160, 195);

            }
            // samlet varighed
            if (activeTable.getOpeningTime() != null) {
                Duration duration = Duration.between(
                        activeTable.getOpeningTime(),
                        LocalDateTime.now()
                );
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;
                long sekunder = duration.toSeconds() % 60;
                text("Total Duration: " + hours + "h " + minutes + "m " + sekunder + "s" , 1160, 235);
            }



            int t = 1;
            int p = 0;
            for (Order order : table.getOrders()) {


                fill(0);
                textSize(20);

                String line = String.format(
                        "%-10s %-15s %-10.2f %-10s %-30s",
                        t,
                        order.getCategory(),
                        order.getPrice(),
                        order.getOrderstatus(),
                        order.getMenuName()

                );

                text(line, 565, 205 + p);

                t++;
                p += 40;


            }


        }

        @Override
        public void exit() {

            dispose();   // lukkes kun skærm
        }

        public static void main(String[] args) {
            PApplet.runSketch(
                    new String[]{"WaiterDashboard"},
                    new WaiterDashboard()
            );
        }
    }


