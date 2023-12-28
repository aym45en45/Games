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
        if (game == 1)
            MainXO.mainXO();
        else if (game == 2)
            MainSudoku.mainSudoku();
        else
            System.out.println("yawdi golna 1 wala 2");

    }
}
