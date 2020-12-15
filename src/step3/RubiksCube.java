package step3;

import java.util.Random;

public class RubiksCube {

    private final Side[] sides;
    private final static String commands = "ULFRBD'";
    private int count = 0;

    public RubiksCube() {
        char[] colors = {'B', 'W', 'O', 'G', 'Y', 'R'};
        Side[] sides = new Side[6];
        for(int i=0; i<sides.length; i++) {
            sides[i] = new Side(colors[i]);
        }
        this.sides = sides;
        print();
    }

    public int getCount() {
        return count;
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

    public boolean execute(String input) {
        boolean result = false;

        if(isNumber(input)) {
            input = randomCommand(input);
            excuteCommand(input, false);
            print();
        } else {
            if(!validCommands(input)) {
                System.out.println("잘못된 동작명령 입니다.\n");
                return false;
            }
            result = excuteCommand(input, true);
        }

        return result;
    }

    // 명령어 실행
    private boolean excuteCommand(String input, boolean isNotRandom) {
        Side[] preSides = this.sides;
        int length = input.length();

        for(int i=0; i<length; i++) {
            char command = input.charAt(i);

            if(i<length-1 && input.charAt(i+1)=='\'') {
                if(isNotRandom) System.out.println("동작 명령: " + command + "'");
                turnCube(command+"'", preSides);
                i++;
            } else {
                if(isNotRandom) System.out.println("동작 명령: " + command);
                turnCube(command+"", preSides);
            }

            if(isNotRandom){
                print();
                count++;
                if(isAnswer(preSides)) return true;
            }

        }
        return false;
    }

    // 정답 체크
    private boolean isAnswer(Side[] preSides) {
        for (Side side : preSides) {
            if(!side.isAllRight()) {
                return false;
            }
        }
        System.out.println("Rubik's Cube Clear!! \t Congraturation~~~♡♡♡♡♡\n");
        return true;
    }

    // 명령어 유효성 확인
    private boolean validCommands(String input) {
        int length = input.length();
        String commands = RubiksCube.commands;

        if(input.charAt(0) == '\'') {
            return false;
        }

        for(int i=0; i<length; i++) {
            if (!commands.contains(input.charAt(i)+"")) {
                return false;
            }
        }
        return true;
    }

    // Number 체크
    private boolean isNumber(String input) {
        try {
            if (Integer.parseInt(input) <= 0) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 랜덤한 명령어를 생성
    private String randomCommand(String input) {
        StringBuilder result = new StringBuilder();
        int randomCount = Integer.parseInt(input);
        Random random = new Random();
        String commands = RubiksCube.commands;

        for(int i=0; i<randomCount; i++) {
            int randomInt = random.nextInt(commands.length()-2); // '를 제외한 나머지 명령어
            result.append(commands.charAt(randomInt));
            if(random.nextBoolean()) {
                result.append(commands.charAt(commands.length()-1)); // commands.length()-1 => '
            }
        }

        return result.toString();
    }

    // 명령어 분기 처리
    private void turnCube(String command, Side[] sides) {
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
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[0].getSquare(0, k);
            sides[0].setSquare(0, k, sides[3].getSquare(k, 2));
            sides[3].setSquare(k, 2, sides[5].getSquare(2, k));
            sides[5].setSquare(2, k, sides[1].getSquare(k, 0));
            sides[1].setSquare(k, 0, temp);
        }
    }

    private void turnCounterR(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnR(sides, i);
        }
    }

    private void turnR(Side[] sides, int i) {
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[0].getSquare(k, 2);
            sides[0].setSquare(k, 2, sides[2].getSquare(k, 2));
            sides[2].setSquare(k, 2, sides[5].getSquare(k, 2));
            sides[5].setSquare(k, 2, sides[4].getSquare(k, 0));
            sides[4].setSquare(k, 0, temp);
        }
    }

    private void turnCounterL(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnL(sides, i);
        }
    }

    private void turnL(Side[] sides, int i) {
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[0].getSquare(k, 0);
            sides[0].setSquare(k, 0, sides[4].getSquare(k, 2));
            sides[4].setSquare(k, 2, sides[5].getSquare(k, 0));
            sides[5].setSquare(k, 0, sides[2].getSquare(k, 0));
            sides[2].setSquare(k, 0, temp);
        }
    }

    private void turnCounterD(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnD(sides, i);
        }
    }

    private void turnD(Side[] sides, int i) {
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[1].getSquare(2, k);
            sides[1].setSquare(2, k, sides[4].getSquare(2, k));
            sides[4].setSquare(2, k, sides[3].getSquare(2, k));
            sides[3].setSquare(2, k, sides[2].getSquare(2, k));
            sides[2].setSquare(2, k, temp);

        }
    }

    private void turnCounterU(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnU(sides, i);
        }
    }

    private void turnU(Side[] sides, int i) {
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[1].getSquare(0, k);
            sides[1].setSquare(0, k, sides[2].getSquare(0, k));
            sides[2].setSquare(0, k, sides[3].getSquare(0, k));
            sides[3].setSquare(0, k, sides[4].getSquare(0, k));
            sides[4].setSquare(0, k, temp);
        }
    }

    private void turnCounterF(Side[] sides, int i) {
        for(int k=0; k<3; k++) {
            turnF(sides, i);
        }
    }

    private void turnF(Side[] sides, int i) {
        // 한 면을 시계 방향으로 돌림
        sides[i].turn();
        // 사이드 돌림
        for (int k=0; k<3; k++) {
            char temp = sides[0].getSquare(2, k);
            sides[0].setSquare(2, k, sides[1].getSquare(k, 2));
            sides[1].setSquare(k, 2, sides[5].getSquare(0, k));
            sides[5].setSquare(0, k, sides[3].getSquare(k, 0));
            sides[3].setSquare(k, 0, temp);
        }

    }
}


