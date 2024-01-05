package XO;

import game.Details;

public class DetailsXO extends Details {
    private int rowValid = -1;
    private int colValid = -1;

    public int getRowValid() {
        return rowValid;
    }

    public void setRowValid(int rowValid) {
        this.rowValid = rowValid;
    }

    public int getColValid() {
        return colValid;
    }

    public void setColValid(int colValid) {
        this.colValid = colValid;
    }

}
