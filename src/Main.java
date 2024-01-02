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
        switch (game) {
            case 1:
                MainXO xo = new MainXO();
                xo.play();
                break;
            case 2:
                MainSudoku sudoku = new MainSudoku();
                sudoku.play();
                break;
            case 3:
                MainChess chess = new MainChess();
                chess.play();
                break;
            default:
                System.out.println("yawdi golna 1 wala 2 wala 3");
                break;
        }        
        scanner.close();

    }
}
