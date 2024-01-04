package chess;

import java.util.Scanner;

import XO.DetailsXO;
import game.Game;

public class MainChess extends Game {
  private static boolean x = false;
  private BoardChess board = new BoardChess();

  @Override
  public void play() {
    Scanner scanner = new Scanner(System.in);
    board.draw();
    // while (!gameOver()) {
    // changePlayer();
    // String move, col;
    // do {
    // System.out.println(
    // "Enter your move player " + getPlayer() + ":");
    // move = scanner.next();
    // if (board.isValid(move)) {
    // board.draw();
    // } else if(board.check(move)){
    // System.out.println("Invalid move. Try again.");
    // }
    // } while (!board.isValid());
    // }

    // board.draw();
    // System.out.println("Game over!");
    // scanner.close();
    // }

  }

  @Override
  public boolean gameOver() {
    return true;
  }

  static void changePlayer() {
    x = !x;
  }

  static String getPlayer() {
    return x ? "White" : "Black";
  }
}
