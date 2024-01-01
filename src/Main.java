import java.util.Scanner;

import XO.MainXO;
import sudoku.MainSudoku;
import chess.MainChess;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what game do u want to play?");
        System.out.println("1) XO.");
        System.out.println("2) Sudoku.");
        System.out.println("3) chess.");
        int game = scanner.nextInt();
        if (game == 1)
            MainXO.play();
        else if (game == 2)
            MainSudoku.play();
        else if(game ==3)
            MainChess.play();
        else
            System.out.println("yawdi golna 1 wala 2 wala 3");
        scanner.close();

    }
}
