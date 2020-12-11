package step3;

import java.util.Scanner;

public class RubiksCubeMain {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        RubiksCube rubiksCube = new RubiksCube();

        while (true) {
            System.out.print("CUBE> ");
            input = scan.nextLine();
            System.out.println();
            if (!validInput(input)) {
                continue;
            } else if (isClose(input)) {
                break;
            }
            rubiksCube.execute(input);
        }
        scan.close();
    }

    private static boolean isClose(String input) {
        if (input.equals("Q")) {
            System.out.println("GoodBye~~");
            return true;
        }
        return false;
    }

    private static boolean validInput(String actionList) {
        try {
            if(actionList.contains(" ") ||  actionList.length()==0) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            System.out.println("동작을 다시 입력해주세요. ");
            return false;
        }
        return true;
    }
}
