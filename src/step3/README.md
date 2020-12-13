# 3단계: 루빅스 큐브 구현하기

## 큐브 초기 상태
![tempsnip](https://user-images.githubusercontent.com/50695027/102010153-98e1ae00-3d7f-11eb-8147-795dbc8a51c3.png)

## 입출력
![image2](https://user-images.githubusercontent.com/50695027/102010193-d7776880-3d7f-11eb-972a-21df19de4f7f.PNG)

## 명령어 동작
##### 큐브 돌리기
밑에 6개의 알파벳을 입력할 시`('이 없을때)` 시계방향으로 회전, 반시계 방향 회전은 `'`으로 나타낸다 `ex) F'`
* `F` : 앞,　`R` : 오른쪽,　`U` : 위,　`B` : 뒤,　`L` : 왼쪽,　`D` : 아랫쪽<br>
* 여러개 회전 명령어 입력 가능 `ex) F'ULD`
#####무작위 섞기
* 숫자를 입력하면 숫자만큼 랜덤하게 무작위로 돌려준다 `ex) CUBE> 100` => 100번 무작위로 돌려준다
#####프로그램 종료
* `Q`를 입력

## 구현 기능
* 큐브 돌리기
* 무작위 섞기
* 프로그램 종료 (경과시간, 조작갯수 출력)
* 모든 면을 맞추면 프로그램 종료

## 구조
* RubiksCubeMain 클래스<br>
    + 기본적인 입력에 대한 처리: 프로그램 종료 요청(Q),　입력이 비거나 띄어 쓰기가 포함되어 있는지 체크<br>
    + 프로그램 종료에 대한 처리

* RubiksCube 클래스
    + 입력에 대한 파싱 처리
    + 입력한 명령어에 대한 분기 처리
    + 큐브 돌리기 명령 처리
    + 사용자 입력한 숫자만큼 랜덤한 명령어 생성하여 무작위로 섞기
    + 모든 면이 맞는지 확인에 대한 처리
    

* Side 클래스
    + Cube의 한 면을 구성할 객체 클래스
    + Side 객체를 회전하는 기능
    + Side 객체가 모두 같은 컬러인지 체크하는 기능
    + 상태 출력:　한 면을 출력,　한 줄을 출력<br>

## 코드 설명
#### 사용자 입력
> RubiksCubeMain 클래스
```java
    private static boolean validInput(String actionList) {
        try {
            if(actionList.contains(" ") ||  actionList.length()==0) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            System.out.println("잘못된 동작 명령 입니다.\n");
            return false;
        }
        return true;
    }
```
* 사용자의 입력이 비었거나 띄어 쓰기가 포함되어있는지 프로그램 차원에서 먼저 검사한다.
> RubiksCube 클래스
```java
    private final static String commands = "ULFRBD'";
 
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
```
* 입력한 명령어에 대해서 첫 글자가 `'`인지 검사하고 `commands`에 포함된 글자인지 체크하고 모두 체크하면 `true`를 리턴한다.
> RubiksCube 클래스
#### 큐브 초기화
```java
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
```
* RubiksCube 클래스 객체를 생성할 시 이와 같이 생성자에서 초기화를 시켜준다.
> Side 클래스
```java
    private char[][] side;

    public Side(char color) {
        char[][] newSide = new char[3][3];
        for (char[] chars : newSide) {
            Arrays.fill(chars, color);
        }
        this.side = newSide;
    }
```
* Side 클래스는 2차원 배열로 구성되어 있다
#### 명령어 구분
> RubiksCube 클래스
```java
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
```
* 검증을 끝마친 상태의 입력에 대하여 작음 따옴표가 뒤에 붙어 있는지를 체크해서 명령어를 실행하게 된다.
 #### 명령어에 대한 큐브 돌리기 처리
 * switch 구문을 통해 분기처리를 각각 하였다. 
 ```java
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
```
* 반 시계 방향 회전은 시계 방향 회전을 3번 돌리는 것으로 구현을 하였다.
* 시계 방향 회전은 `Side` 객체를 시계방향으로 돌리고,<br>
임시 변수 `temp`를 사용해 하나의 사각형을 총 3번 시계방향으로 돌린다.

#### 무작위 섞기
> RubiksCube 클래스
```java
    private final static String commands = "ULFRBD'";

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
```
* 사용자가 입력한 숫자 만큼 무작위로 섞어 준다.
* 입력이 숫자인지 체크를 한다.
* 입력이 숫자이면 `randomCommand(String input)`을 실행하여 `Random` 객체를 사용하여 `'`를 제외한 명령을 생성하여 새로운 명령구문에
 붙이게 된다. 또한 `'`는 `Random`객체의 `nextBoolean()`으로 랜덤한 boolean값을 확인하여 명령 구문에 하나씩 랜덤한 확률로 붙여주어 임의의 랜덤한 사용자 명령구문을 만들어 준다.
 #### 정답 체크
 >Side 클래스
```java
    public boolean isAllRight() {
        char color = side[0][0];
        boolean result = true;
        for (char[] line : side) {
            for (char c : line) {
                if(c!=color) return false;
            }
        }
        return true;
    }
```
* Side 객체가 모두 같은 색으로 이루어져 있는지 체크해준다.
 > RubiksCube 클래스
```java
    private boolean isAnswer(Side[] preSides) {
         for (Side side : preSides) {
             if(!side.isAllRight()) {
                 return false;
             }
         }
         System.out.println("Rubik's Cube Clear!! \t Congraturation~~~♡♡♡♡♡\n");
         return true;
     }
```    
* 6개의 면을 `isAllRight()` 메서드를 사용하여 체크한다.
#### 프로그램 종료
> RubiksCube 클래스
```java
    private int count = 0;

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
```
* 사용자가 숫자를 입력하여 무작위 섞기를 실행하지 않는 것만 카운트를 하고 정답을 체크하게 된다.
* 정답이 맞다면 종료 `true` 를 반환하고 축하메시지를 출력 후 RubiksCubeMain 클래스에 전달한다.
> RubiksCubeMain 클래스
```java
    private static void isClose(int count, long startTime) {
           long endTime = System.currentTimeMillis();
           int elapsedTime = (int) ((endTime-startTime)/1000);
   
           System.out.printf("경과시간: %02d:%02d\n",elapsedTime/60, elapsedTime%60);
           System.out.println("조작갯수: " + count);
           System.out.println("프로그램을 종료합니다");
       }
```
* `System.cuurentTimeMillis()`를 이용하여 프로그램의 시작시간과 종료시간의 차이를 구한다.
* `RubiksCube`객체에서 넘겨 받은 조작 갯수 `count`를 출력하게 된다. 