# 2단계: 평면 큐브 구현하기

## 구조
#### CubeMain 클래스
* 기본적인 입력에 대한 오류 처리
* 프로그램 종료 요청에 대한 처리
* Cube 객체에 실행 요청
#### Cube 클래스
* 입력에 대해 큐브 동작 명령어인지 체크
* 동작 명령어를 분기 처리
* 큐브 상태 출력

## 시나리오
초기 평면 큐브 상태
> R　R　W <br>
> G　C　W <br>
> G　B　B <br>

큐브 동작 명령어
> U     : 가장 윗줄을 왼쪽으로 한 칸 밀기 RRW -> RWR <br>
> U'    : 가장 윗줄을 오른쪽으로 한 칸 밀기 RRW -> WRR <br>
> R     : 가장 오른쪽 줄을 위로 한 칸 밀기 WWB -> WBW <br>
> R'    : 가장 오른쪽 줄을 아래로 한 칸 밀기 WWB -> BWW <br>
> L     : 가장 왼쪽 줄을 아래로 한 칸 밀기 RGG -> GRG <br>
> L'    : 가장 왼쪽 줄을 위로 한 칸 밀기 RGG -> GGR <br>
> B     : 가장 아랫줄을 오른쪽으로 한 칸 밀기 GBB -> BGB <br>
> B'    : 가장 아랫줄을 왼쪽으로 한 칸 밀기 GBB -> BBG <br>
> Q     : GoodBye~~~를 출력하고 프로그램을 종료한다. <br>

## 입출력
![image](https://user-images.githubusercontent.com/50695027/102006149-3332f900-3d62-11eb-89ec-da5fa741d2bd.PNG)

## 코드 설명
#### 사용자 입력에 대한 처리
* CubeMain 클래스
```java
    private static boolean isClose(String actionInput) {
        if(actionInput.equals("Q")) {
            System.out.println("GoodBye~~~");
            return true;
        }
        return false;
    }

    private static boolean validInput(String actionList) {
        try {
            if (actionList.contains(" ") || actionList.length() == 0) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            System.out.println("값을 다시 입력해주세요.\n");
            return false;
        }
        return true;
    }
```
사용자의 종료에 대한 요청과 입력에 대해서 띄어 쓰기가 있는지 입력을 제대로 했는지에 대한 처리를 한다.
* Cube 클래스
```java
    private final static String commands = "URLB'";

    private boolean validation(String input) {
        int length = input.length();
        String commands = Cube.commands;

        for(int i=0; i<length; i++) {
            if(!commands.contains(input.charAt(i)+"")) {
                return false;
            }
        }
        return true;
    }
```
동장 명령어가 맞는지 체크하는 것은 큐브 동작 명령어들이 있는 commands에 명령어들을 하나씩 체크한다. 또한 맨앞에 작은따옴표가 오면 안되기에
첫번째가 작은따옴표인지 체크한다.
#### 큐브 초기화
```java
    private final char[][] cubes;

    public Cube() {
        this.cubes = new char[][]{
                {'R', 'R', 'W'},
                {'G', 'C', 'W'},
                {'G', 'B', 'B'},
        };
    }
```
Cube 클래스의 생성자로 객체가 생성될 시에 위의 값으로 초기화를 시켜준다.

#### 명령어에 대한 처리
* Cube 클래스
```java
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
```
String 형식으로 들어온 입력에 대하여 뒤에 따옴표가 있는지 체크하고 있으면 따옴표를 붙여 명령어를 실행한다 <br>
<br>

```java
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
```
`index`는 밀어내는 줄의 인덱스 값이다. 그렇기에 index는 고정으로 하고 밀어낸다<br>
밀어내는 동작은 임시 변수 `temp`에 담아서 한 쪽 방향으로 밀고 마지막 변수에 `temp`를 대입하여 처리