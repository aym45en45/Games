package src;

import java.util.Scanner;

import src.XO.MainXO;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what game do u want to play?");
        System.out.println("1) XO.");
        System.out.println("2) Sudoku.");
        int game = scanner.nextInt();
        if(game == 1)
            MainXO.mainXO();

    }
}
