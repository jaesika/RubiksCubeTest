# CodesquadTest 마스터 과정

## 1단계: 단어 밀어내기 구현하기
### 요구 사항
1. 입력: 사용자로부터 단어 하나, 정수 숫자 하나( -100 <= N < 100) , L 또는 R을 입력받는다. L 또는 R은 대소문자 모두 입력 가능하다.
2. 주어진 단어를 L이면 주어진 숫자 갯수만큼 왼쪽으로, R이면 오른쪽으로 밀어낸다.
3. 밀려나간 단어는 반대쪽으로 채워진다.

### 입출력 예시
> 단어를 하나 입력 > apple <br/>
> -100<=N<100의 정수 하나를 입력 > 3 <br/>
> R(r) 또는 L(l)을 입력 > L <br/>
> result = leapp

### 동작 과정
#### 사용자로부터의 입력 <br/>
- 단어를 하나 입력 받은 후 값 검증
- 숫자를 하나 입력 받은 후 값 검증
- L 또는 R을 입력 받은 후 값 검증
- 중간의 검증에 실패하면 오류를 출력하고 다시 단어부터 입력을 받게 된다
- 검증에 모두 성공하면 단어 밀어내기를 수행한다 <br/>

```java
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
```
#### 단어 밀어내기 <br/>
- 단어를 오른쪽으로 밀어내는 경우: R(r)을 선택하고 숫자가 0이상 또는 L(l)을 선택하고 숫자가 음수
- 단어를 왼쪽으로 밀어내는 경우: L(l)을 선택하고 숫자가 0이상 또는 R(r)을 선택하고 숫자가 음수
- 단어의 길이만큼 밀어내면 다시 원상복구인 특성을 이용하여 `index = count%length`만큼 밀어내야함
- 오른쪽으로 밀 경우 <br/>
`단어의 끝에서 index만큼`을 오른쪽으로 밀어서 앞부분으로<br/> 
`단어의 처음부터 단어의크기-index-1만큼`을 오른쪽으로 밀어서 뒷 부분으로하여 반환
- 왼쪽으로 밀 경우 <br/>
`단어의 처음부터 index-1만큼` 왼쪽으로 밀어서 뒷부분으로<br/>
`index부터 단어의 끝까지`를 왼쪽으로 밀어서 앞부분으로 하여 반환

## 2단계: 평면 큐브 구현하기

## 3단계: 루빅스 큐브 구현하기