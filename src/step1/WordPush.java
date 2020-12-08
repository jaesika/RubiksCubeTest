package step1;

import java.util.Scanner;

public class WordPush {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String word, direction;
        String count;
        int countInt = 0;

        //사용자 입력
        while(true) {
            System.out.print("단어를 하나 입력 > ");
            word = scan.nextLine();
            if(word.contains(" ")) {
                System.out.println("단어를 하나만 입력해 주세요.\n");
                continue;
            } else if(word.length()==0) {
                System.out.println("단어 입력이 안되었습니다.\n");
                continue;
            }
            System.out.print("-100<=N<100의 정수 하나를 입력 > ");
            count = scan.nextLine();
            try {
                countInt = Integer.parseInt(count);
            } catch (NumberFormatException e) {
                System.out.println("숫자 입력이 잘못 되었습니다.\n");
                continue;
            }
            if(countInt<-100 || 100<=countInt) {
                System.out.println("-100이상 100미만의 숫자를 입력해 주세요\n");
                continue;
            }

            System.out.print("R(r) 또는 L(l)을 입력 > ");
            direction = scan.nextLine();
            if(!(direction.equalsIgnoreCase("L") || direction.equalsIgnoreCase("R"))) {
                System.out.println("R(r) 또는 L(l) 입력이 잘못 되었습니다.\n");
                continue;
            } else {
                break;
            }
        }
        scan.close();

        String result = pushWord(word, countInt, direction);
        System.out.println("result = " + result);
    }

    static String pushWord(String word, int count, String direction) {
        StringBuilder result = new StringBuilder();
        int length = word.length();
        int absValue = Math.abs(count);
        int index = absValue%length;

        // 오른쪽으로 밀어내는 경우
        if((direction.equalsIgnoreCase("R") && count>=0) || (direction.equalsIgnoreCase("L") && count<0)) {
            result.append(word, (length-index), length);
            result.append(word, 0, (length-index));
        } else { // 왼쪽으로 밀어내는 경우
            result.append(word, index, length);
            result.append(word, 0, index);
        }

        return result.toString();
    }
}
