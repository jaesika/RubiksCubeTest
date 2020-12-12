package step3;

import java.util.Scanner;

public class RubiksCubeMain {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner scan = new Scanner(System.in);
        String input;
        RubiksCube rubiksCube = new RubiksCube();

        while (true) {
            System.out.print("CUBE> ");
            input = scan.nextLine();
            System.out.println();
            if (!validInput(input)) {
                continue;
            } else if (input.equals("Q") || rubiksCube.execute(input)) {
                long end = System.currentTimeMillis();
                int elapsedTime = (int) ((end-start)/1000);
                isClose(rubiksCube.getCount(), elapsedTime);
                break;
            }
        }
        scan.close();
    }

    private static void isClose(int count, int elapsedTime) {
        System.out.printf("경과시간: %02d:%02d\n",elapsedTime/60, elapsedTime%60);
        System.out.println("조작갯수: " + count);
        System.out.println("프로그램을 종료합니다");
    }

    private static boolean validInput(String actionList) {
        try {
            if(actionList.contains(" ") ||  actionList.length()==0) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            System.out.println("잘못된 동작명령 입니다.\n");
            return false;
        }
        return true;
    }
}
