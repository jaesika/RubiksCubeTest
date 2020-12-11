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

    // 색 지정
    public void setSide(int i, int j, char color) {
        this.side[i][j] = color;
    }

    // 한 면의 한 줄을 출력
    public void oneLinePrint(int i) {
        for (char[] line : side) {
            System.out.print(line[i] + " ");
        }
        System.out.print("\t");
    }

    // 한 면을 출력
    public void oneSidePrint() {
        System.out.println("");
        for (char[] line : side) {
            System.out.print("\t\t\t");
            for (char one : line) {
                System.out.print(one+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // clockWise가 true => 시계방향, false => 반시계방향
    public void turn(boolean clockWise) {
        char[][] temp = new char[3][3];
        for(int i=0; i<side.length; i++) {
            for(int j=0; j<side[i].length; j++) {
                temp[i][j] = clockWise ? side[2-j][i] : side[j][2-i];
            }
        }
        side = temp;
    }
}
