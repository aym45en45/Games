import java.util.Scanner;

import XO.MainXO;
import sudoku.MainSudoku;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what game do u want to play?");
        System.out.println("1) XO.");
        System.out.println("2) Sudoku.");
        int game = scanner.nextInt();
        scanner.close();
        if (game == 1)
            MainXO.play();
        else if (game == 2)
            MainSudoku.play();
        else
            System.out.println("yawdi golna 1 wala 2");

    }
}
