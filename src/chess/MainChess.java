package chess;

import java.util.Scanner;

import game.Game;

public class MainChess extends Game {
  private static boolean x = false;
  private BoardChess board = new BoardChess();
  static boolean showMsg=true;

  @Override
  public void play() {
    Scanner scanner = new Scanner(System.in);
    board.draw();
    while (!gameOver()) {
      changePlayer();
      String move;
      do {
        System.out.println("Enter your move \"" + getPlayer() + "\" player : ");
        if(showMsg){
          System.out.println("to move enter square coordinates. e.g. [e2e4]");
          System.out.println("to show Valid Move enter piece coordinates. e.g. [Ke1]");
          showMsg=false;
        }
        move = scanner.next();
        switch (move.length()) {
            case 3:
                board.showValidMove(move);
                break;
            case 4:
                board.move(move);
                break;
            default:
                showMsg=true;
                break;
        }
      } while (showMsg);
    }

    board.draw();
    System.out.println("Game over!");
    scanner.close();
  }

  @Override
  public boolean gameOver() {
    return false;
  }

  static void changePlayer() {
    x = !x;
  }

  static String getPlayer() {
    return x ? "White" : "Black";
  }
}
