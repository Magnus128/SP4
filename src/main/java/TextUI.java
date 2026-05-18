

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUI {


    private static Scanner sc = new Scanner(System.in);

    public static ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){
        displayMsg(msg);
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>();  //Lave en beholder til at gemme brugerens valg

        while(choices.size() < limit){             //tjekke om brugeren skal vælge igen

            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    public static void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public static void displayMsg(String msg){
        System.out.println(msg);

    }
    public static int promptNumeric(String msg) {
        displayMsg(msg);                       //Stille brugeren et spørgsmål
        try {
            String input = sc.nextLine();                  //Give brugere et sted at placere sit svar og vente på svaret
            int numInput = Integer.parseInt(input); //Konvertere svaret til et tal
            return numInput;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            return promptNumeric(msg);
        }
    }

    public static double promptDouble(String msg) {
        displayMsg(msg);                       //Stille brugeren et spørgsmål
        try {
            String input = sc.nextLine();                  //Give brugere et sted at placere sit svar og vente på svaret
            double numInput = Double.parseDouble(input); //Konvertere svaret til et tal
            return numInput;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a double value");
            return promptDouble(msg);
        }
    }

    public static String promptText(String msg){
        displayMsg(msg);         //Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }

    public static String promptCategory(String msg){
        displayMsg(msg);         //Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        boolean flag = true;
        if (input.equalsIgnoreCase("Food")|| input.equalsIgnoreCase("Drink") || input.equalsIgnoreCase("Dessert")) {
            flag = false;
        }

        if (flag) {
            System.out.println("Category skal være Food-Drink-Dessert");
            return promptCategory(msg);
        }

        return input;
    }

    public static boolean promptBinary(String msg){
        displayMsg(msg);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("Y")){
            return true;
        }
        else if(input.equalsIgnoreCase("N")){
            return false;
        }
        else{
            return promptBinary(msg);
        }
    }

    public static String promptDate(String msg) throws IllegalArgumentException {
        displayMsg(msg);
        String input = sc.nextLine();
        // Formatet skal være YYYY-MM-DD
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(input);
        if (input.equals("0")) return input;
        if (m.find()) {
            System.out.println("Input indeholder bogstaver");
            throw new IllegalArgumentException();
        }
        if (input.length() != 10) {
            System.out.println("Input er forkert længde");
            throw new IllegalArgumentException();
        }
        if (input.charAt(4) != '-' || input.charAt(7) != '-') {
            System.out.println("Bindestrejer placeret forkert");
            throw new IllegalArgumentException();
        }
        return input;
    }

    public static String promptMonth(String msg) throws IllegalArgumentException {
        displayMsg(msg);
        String input = sc.nextLine();
        // Formatet skal være YYYY-MM
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(input);
        if (input.equals("0")) return input;
        if (m.find()) {
            System.out.println("Input indeholder bogstaver");
            throw new IllegalArgumentException();
        }
        if (input.length() != 7) {
            System.out.println("Input er forkert længde");
            throw new IllegalArgumentException();
        }
        if (input.charAt(4) != '-') {
            System.out.println("Bindestrejer placeret forkert");
            throw new IllegalArgumentException();
        }
        return input;
    }
}