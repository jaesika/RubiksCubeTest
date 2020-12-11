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
        for (char[] chars : side) {
            System.out.print(chars[i] + " ");
        }
        System.out.print("\t");
    }
}
