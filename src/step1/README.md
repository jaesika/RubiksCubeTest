# 1단계: 단어 밀어내기 구현하기

### 시나리오
* 입력: 사용자로부터 단어 하나, 정수 숫자 하나( -100 <= N < 100) , L 또는 R을 입력받는다. L 또는 R은 대소문자 모두 입력 가능하다. 
* 주어진 단어를 L이면 주어진 숫자 갯수만큼 왼쪽으로, R이면 오른쪽으로 밀어낸다.
* 밀려나간 단어는 반대쪽으로 채워진다.

### 입출력
>　　정수 입력 > apple <br>
>　　숫자 입력 > 3 <br>
>R　or　L 입력 > L <br>
>result　=　leapp

### 코드 설명

* 입력 : 보다 사용자로부터 안정적인 입력을 위하여 한 줄씩 입력이 가능하게 하였다. 또한 사용자가 한 줄씩 입력하더라도
사용자가 띄어쓰기를 입력할 경우를 생각해서 모두 오류 처리를 해주었다. <br>
    * boolean validWord(String word) <br>
    띄어 쓰기가 되어있는지 체크, 입력이 되었는지 체크
    * boolean validCount(int count) <br>
    -100<=N<100의 정수값인지 체크
    * boolean validDirection(String direction) <br>
    R 또는 L만 입력했는지 체크
 ```java
    private static boolean validDirection(String direction) {
        if(direction.equalsIgnoreCase("L") || direction.equalsIgnoreCase("R")) {
            return true;
        } else {
            System.out.println("R(r) 또는 L(l) 입력이 잘못되었습니다.\n");
            return false;
        }
    }
```
String 클래스의 equalsIgnoreCase를 사용하여 소문자도 포함하여 체크한다.
    

* 단어 밀어내기
 ```java
    private static String pushWord(String word, int count, String direction) {
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
```
`count`만큼 한 쪽 방향으로 단어를 단어의 길이만큼 밀어낸다면 다시 단어는 원래대로 돌아오게 된다. 그래서 `count`를
단어의 길이만큼 나눈 나머지 만큼, 즉 `index = absValue&length` 만큼 이동을 하면 된다. <br>
* 오른쪽으로 밀어내는 경우<br>
`R이면서 count가 0이상인 경우`,　`L이면서 count가 음수인 경우`
* 왼쪽으로 밀어내는 경우 <br>
`L이면서 count가 0이상인 경우`,　`R이면서 count가 음수인 경우`<br>
<br>
오른쪽으로 미는 경우는 뒤에서부터 `index`만큼 앞부분으로 그 나머지를 뒤로하여 return 한다
왼쪽으로 미는 경우는 앞에서부터 `index`만큼 뒷부분으로 그 나머지를 앞으로하여 return 한다