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
            if(i<length-1 && commands.charAt(i+1)=='\'') {
                preCube = pushCube(commands.charAt(i)+"'", preCube);
                i++;
            }
            preCube = pushCube(commands.charAt(i)+"", preCube);
        }
    }



    public void printCube() {
        char[][] cube = this.cubes;
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube.length; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println();
        }
    }
}