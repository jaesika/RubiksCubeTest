package step3;

import java.util.Random;

public class RubiksCube {

    private final Side[] sides;
    private final static String commands = "ULFRBD'";

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

    public void execute(String input) {
        Side[] preSides = this.sides;
        if(isNumber(input)) {
            input = randomCommand(input);
            excuteCommand(input, preSides, false);
            print();
        } else {
            if(!validCommands(input)) {
                System.out.println("잘못된 동작명령 입니다.\n");
                return;
            }
            excuteCommand(input, preSides, true);
        }

    }

    private void excuteCommand(String input, Side[] preSides, boolean isPrint) {
        int length = input.length();

        for(int i=0; i<length; i++) {
            char command = input.charAt(i);

            if(i<length-1 && input.charAt(i+1)=='\'') {
                if(isPrint) System.out.println("동작 명령: " + command + "'");
                rotateCube(command+"'", preSides);
                i++;
            } else {
                if(isPrint) System.out.println("동작 명령: " + command);
                rotateCube(command+"", preSides);
            }
        }
        if (isPrint) print();
    }

    private boolean validCommands(String input) {
        int length = input.length();
        String commands = RubiksCube.commands;

        for(int i=0; i<length; i++) {
            if (!commands.contains(input.charAt(i)+"")) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 랜덤한 명령어를 생성
    private String randomCommand(String input) {
        Random random = new Random();
        int randomCount = Integer.parseInt(input);
        StringBuilder result = new StringBuilder();
        String commands = RubiksCube.commands;

        for(int i=0; i<randomCount; i++) {
            int randomInt = random.nextInt(commands.length()-2); // '를 제외한 나머지 명령어
            result.append(commands.charAt(randomInt));
            if(random.nextBoolean()) {
                result.append(commands.charAt(commands.length()-1)); // commands.length()-1 => '
            }
        }
        System.out.println(result.toString());

        return result.toString();
    }

    private void rotateCube(String command, Side[] sides) {
        switch (command) {
            case "U":
                turnU(sides, 0);
                break;
            case "U'":
                turnCounterU(sides, 0);
                break;
            case "L":
                turnL(sides, 1);
                break;
            case "L'":
                turnCounterL(sides, 1);
                break;
            case "F":
                turnF(sides, 2);
                break;
            case "F'":
                turnCounterF(sides, 2);
                break;
            case "R":
                turnR(sides, 3);
                break;
            case "R'":
                turnCounterR(sides, 3);
                break;
            case "B":
                turnB(sides, 4);
                break;
            case "B'":
                turnCounterB(sides, 4);
                break;
            case "D":
                turnD(sides, 5);
                break;
            case "D'":
                turnCounterD(sides, 5);
                break;
        }
    }

    private void turnCounterB(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnB(sides, i);
        }
    }

    private void turnB(Side[] sides, int i) {
        char[] temp = sides[0].getLine(0, true);
        sides[0].setLine(0, true, sides[3].getLine(2, false));
        sides[3].setLine(2, false, sides[5].getLine(2, true));
        sides[5].setLine(2, true, sides[1].getLine(0,false));
        sides[1].setLine(0, false, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
    }

    private void turnCounterR(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnR(sides, i);
        }
    }

    private void turnR(Side[] sides, int i) {
        char[] temp = sides[0].getLine(2, false);
        sides[0].setLine(2, false, sides[2].getLine(2, false));
        sides[2].setLine(2, false, sides[5].getLine(2, false));
        sides[5].setLine(2, false, sides[4].getLine(0,false));
        sides[4].setLine(0, false, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
    }

    private void turnCounterL(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnL(sides, i);
        }
    }

    private void turnL(Side[] sides, int i) {
        char[] temp = sides[0].getLine(0, false);
        sides[0].setLine(0, false, sides[4].getLine(2, false));
        sides[4].setLine(2, false, sides[5].getLine(0, false));
        sides[5].setLine(0, false, sides[2].getLine(0,false));
        sides[2].setLine(0, false, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
    }

    private void turnCounterD(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnD(sides, i);
        }
    }

    private void turnD(Side[] sides, int i) {
        char[] temp = sides[1].getLine(2, true);
        sides[1].setLine(2, true, sides[4].getLine(2, true));
        sides[4].setLine(2, true, sides[3].getLine(2, true));
        sides[3].setLine(2, true, sides[2].getLine(2,true));
        sides[2].setLine(2, true, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
    }

    private void turnCounterU(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnU(sides, i);
        }
    }

    private void turnU(Side[] sides, int i) {
        char[] temp = sides[1].getLine(0, true);
        sides[1].setLine(0, true, sides[2].getLine(0, true));
        sides[2].setLine(0, true, sides[3].getLine(0, true));
        sides[3].setLine(0, true, sides[4].getLine(0,true));
        sides[4].setLine(0, true, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
    }

    private void turnCounterF(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnF(sides, i);
        }
    }

    private void turnF(Side[] sides, int i) {
        char[] temp = sides[0].getLine(2, true);
        sides[0].setLine(2, true, sides[1].getLine(2, false));
        sides[1].setLine(2, false, sides[5].getLine(0, true));
        sides[5].setLine(0, true, sides[3].getLine(0,false));
        sides[3].setLine(0, false, temp);
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();

    }
}


