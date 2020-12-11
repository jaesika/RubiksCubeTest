package step3;

public class RubiksCube {

    private final Side[] sides;

    public RubiksCube() {
        char[] colors = {'B', 'W', 'O', 'G', 'Y', 'R'};
        Side[] sides = new Side[6];
        for(int i=0; i<sides.length; i++) {
            sides[i] = new Side(colors[i]);
        }
        this.sides = sides;
        print();
    }

    // 큐브 상태 출력
    public void print() {
        Side[] sides = this.sides;
        sides[0].oneSidePrint(); // 윗면
        for(int i=0; i<3; i++) {
            for(int j=1; j<5; j++) {
                sides[j].oneLinePrint(i);
            }
            System.out.println(
            );
        }
        sides[5].oneSidePrint(); // 아랫면
    }
}
