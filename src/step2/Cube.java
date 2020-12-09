package step2;

public class Cube {
    private char[][] cubes;

    public Cube() {
        this.cubes = new char[][]{
                {'R', 'R', 'W'},
                {'G', 'C', 'W'},
                {'G', 'B', 'B'},
        };
    }

    public void execute(String commands) {
        char[][] preCube = this.cubes;
        int length = commands.length();

        for(int i=0; i<length; i++) {
            System.out.println(commands.charAt(i));
            if(i<length-1 && commands.charAt(i+1)=='\'') {
                preCube = pushCube(commands.charAt(i)+"'", preCube);
                i++;
            } else {
                preCube = pushCube(commands.charAt(i)+"", preCube);
            }
            printCube();
        }


    }

    private char[][] pushCube(String command, char[][] cube) {
        char[][] result = cube;
        switch (command) {
            case "U":
                result = LeftAndRight(cube, 0, "L");
                break;
            case "U'":
                result = LeftAndRight(cube, 0, "R");
                break;
            case "R":
                result = UpAndDown(cube, 2, "U");
                break;
            case "R'":
                result = UpAndDown(cube, 2, "D");
                break;
            case "L":
                result = UpAndDown(cube, 0, "D");
                break;
            case "L'":
                result = UpAndDown(cube, 0, "U");
                break;
            case "B":
                result = LeftAndRight(cube, 2, "R");
                break;
            case "B'":
                result = LeftAndRight(cube, 2, "L");
                break;
        }
        return result;
    }

    private char[][] UpAndDown(char[][] cube, int index, String direction) {
        char temp;
        if (direction.contains("U")) {
            temp = cube[0][index];
            for(int i=0; i<2; i++) {
                cube[i][index] = cube[i+1][index];
            }
            cube[2][index] = temp;
        } else if (direction.equals("D")) {
            temp = cube[2][index];
            for(int i=2; i>0; i--) {
                cube[i][index] = cube[i-1][index];
            }
            cube[0][index] = temp;
        }
        return cube;
    }

    private char[][] LeftAndRight(char[][] cube, int index, String direction) {
        char temp;
        if (direction.equals("L")) {
            temp = cube[index][0];
            for(int i=0; i<2; i++) {
                cube[index][i] = cube[index][i+1];
            }
            cube[index][2] = temp;
        } else if (direction.equals("R")) {
            temp = cube[index][2];
            for(int i=2; i>0; i--) {
                cube[index][i] = cube[index][i-1];
            }
            cube[index][0] = temp;
        }
        return cube;
    }

    public void printCube() {
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