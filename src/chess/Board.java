public class Board {
    static final String[] pieces = {"K","Q","R","N","B","P","k","q","r","n","b","p"};
    static String[][] board = new String[][] {
                  {"r","n","b","q","k","b","n","r"},
                  {"p","p","p","p","p","p","p","p"},
                  {" "," "," "," "," "," "," "," "},
                  {" "," "," "," "," "," "," "," "},
                  {" "," "," "," "," "," "," "," "},
                  {" "," "," "," "," "," "," "," "},
                  {"P","P","P","P","P","P","P","P"},
                  {"R","N","B","Q","K","B","N","R"},
            };
    static void drawBoard(){
        for (int in = 0; in < 8; in++) {
            System.out.println("========================================");
            for (int i = 0; i < 8; i++)
              {
               if(in%2==0){
                   if(i%2==0)
                      System.out.print("| "+board[in][i]+" |");
                   else
                     System.out.print("|+"+board[in][i]+"+|");
              }else{
                   if(i%2!=0)
                       System.out.print("| "+board[in][i]+" |");
                   else
                       System.out.print("|+"+board[in][i]+"+|");
               }
              }
            System.out.println();
        }
        System.out.println("========================================");
    }
    public static void main(String[] args) {
      drawBoard();
    }
}
