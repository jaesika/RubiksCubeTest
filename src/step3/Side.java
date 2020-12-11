package step3;

import java.util.Arrays;

public class Side {

    private char[][] side;

    public Side(char color) {
        char[][] newSide = new char[3][3];
        for (char[] chars : newSide) {
            Arrays.fill(chars, color);
        }
        this.side = newSide;
    }

    public char getSide(int i, int j) {
        return side[i][j];
    }

    public void setSide(int i, int j, char color) {
        this.side[i][j] = color;
    }

    public char[] getLine(int index, boolean isRow) {
        char[] line = new char[3];
        if(isRow) {
            for (int i=0; i<side.length; i++) {
                line[i] = side[index][i];
            }
        } else {
            for (int i=0; i<side.length; i++) {
                line[i] = side[i][index];
            }
        }

        return line;
    }

    public void setLine(int index, boolean isRow, char[] line) {
        if(isRow) {
            for (int i=0; i<side.length; i++) {
                side[index][i] = line[i];
            }
        } else {
            for (int i=0; i<side.length; i++) {
                side[i][index] = line[i];
            }
        }
    }

    // 한 면의 한 줄을 출력
    public void oneLinePrint(int i) {
        for(int j=0; j<side[i].length; j++) {
            System.out.print(side[i][j] + " ");
        }
        System.out.print("\t");
    }

    // 한 면을 출력
    public void oneSidePrint() {
        System.out.println();
        for (char[] line : side) {
            System.out.print("\t\t");
            for (char one : line) {
                System.out.print(one + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void turn() {
        char[][] temp = new char[3][3];
        for(int i=0; i<side.length; i++) {
            for(int j=0; j<side[i].length; j++) {
                temp[i][j] = side[2-j][i];
            }
        }
        side = temp;
    }
}
