package step2;

public class Cube {
    private final char[][] cubes;
    private final static String commands = "URLB'";

    public Cube() {
        this.cubes = new char[][]{
                {'R', 'R', 'W'},
                {'G', 'C', 'W'},
                {'G', 'B', 'B'},
        };
    }

    public void execute(String input) {

        if (!validation(input)) {
            System.out.println("잘못된 명령입니다.\n");
            return;
        }

        executeCommands(input, this.cubes);
    }

    private void executeCommands(String input, char[][] preCube) {
        int length = input.length();

        for(int i=0; i<length; i++) {
            char command = input.charAt(i);

            if(i<length-1 && input.charAt(i+1)=='\'') {
                System.out.println("동작 명령: " + command + "'");
                pushCube(command+"'", preCube);
                i++;
            } else {
                System.out.println("동작 명령: " + command);
                pushCube(command+"", preCube);
            }
            print();
        }
    }

    private boolean validation(String input) {
        int length = input.length();
        String commands = Cube.commands;

        if(input.charAt(0) == '\'') {
            return false;
        }

        for(int i=0; i<length; i++) {
            if(!commands.contains(input.charAt(i)+"")) {
                return false;
            }
        }
        return true;
    }

    private void pushCube(String command, char[][] cube) {
        switch (command) {
            case "U":
                turnLeft(cube, 0);
                break;
            case "U'":
                turnRight(cube, 0);
                break;
            case "R":
                turnUp(cube, 2);
                break;
            case "R'":
                turnDown(cube, 2);
                break;
            case "L":
                turnDown(cube, 0);
                break;
            case "L'":
                turnUp(cube, 0);
                break;
            case "B":
                turnRight(cube, 2);
                break;
            case "B'":
                turnLeft(cube, 2);
                break;
        }
    }

    private void turnUp(char[][] cube, int index) {
        char temp = cube[0][index];
        for (int i=0; i<2; i++) {
            cube[i][index] = cube[i+1][index];
        }
        cube[cube.length-1][index] = temp;
    }

    private void turnDown(char[][] cube, int index) {
        char temp = cube[cube.length-1][index];
        for (int i=2; i>0; i--) {
            cube[i][index] = cube[i-1][index];
        }
        cube[0][index] = temp;
    }

    private void turnLeft(char[][] cube, int index) {
        char temp = cube[index][0];
        for (int i=0; i<2; i++) {
            cube[index][i] = cube[index][i+1];
        }
        cube[index][cube.length-1] = temp;
    }

    private void turnRight(char[][] cube, int index) {
        char temp = cube[index][cube.length-1];
        for (int i=2; i>0; i--) {
            cube[index][i] = cube[index][i-1];
        }
        cube[index][0] = temp;
    }

    public void print() {
        char[][] cube = this.cubes;
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}