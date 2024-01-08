package chess;

import java.util.Scanner;
import java.util.regex.*;

import game.Game;

public class MainChess extends Game {
  private static boolean x = false;
  private BoardChess board = new BoardChess();
  static boolean showMsg = true;
  boolean showMove = true;

  @Override
  public void play() {
    Scanner scanner = new Scanner(System.in);
    while (!gameOver()) {
      changePlayer();
      board.draw();
      String move;
      do {
        showMove = false;
        System.out.println("Enter your move \"" + getPlayer() + "\" player : ");
        if (showMsg) {
          System.out.println("to move enter squares coordinates.[a-h][1-8][a-h][1-8] e.g. [e2e4]");
          System.out.println("to show Valid Move enter piece coordinates.[a-h][1-8] e.g. [e1]");
          showMsg = false;
        }
        move = scanner.next();
        switch (move.length()) {
          case 2:
            if (isValidForCheckMove(move)) {
              board.showValidMove(move);
              board.draw();
              showMove = true;
            } else
              showMsg = true;
            break;
          case 4:
            if (isValidMove(move))
              board.move(move);
            else
              showMsg = true;
            break;
          default:
            showMsg = true;
            break;
        }
      } while (showMsg || showMove);
    }

    board.draw();
    System.out.println("Game over!");
    scanner.close();
  }

  private boolean isValidMove(String move) {
    String regex = "^[a-h][1-8][a-h][1-8]$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(move);
    return matcher.matches();
  }

  private boolean isValidForCheckMove(String move) {
    String regex = "^[a-h][1-8]$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(move);
    return matcher.matches();
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
