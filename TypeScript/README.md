# TypeScript

## TypeScript 설치

1. [Nodejs 설치](https://github.com/nohkihyeon/TIL/blob/main/Nodejs/macOsInstall.md)
2. terminal에 `npm install -g typescript` 입력
<details>
<summary>mac에서 에러 발생 시</summary>
<div markdown="1">


<img width="711" alt="스크린샷 2021-11-29 오후 3 12 31" src="https://user-images.githubusercontent.com/65120581/143817589-91ba41d0-b338-4f19-9d47-8a208566c60d.png">
  
  - 이러한 에러가 발생한다면
  - `sudo npm install -g typescript` sudo를 붙인다. (맥의 비밀번호를 요구할 수 있다.)


</div>
</details>

## TypeScript 파일 생성

<img width="232" alt="스크린샷 2021-11-29 오후 3 17 40" src="https://user-images.githubusercontent.com/65120581/143818095-bcf03b1f-a160-4518-a4f3-838a509fab89.png">

- tsconfig.json
```typescript
{
    "compoilerOptions":{
        "target": "es5",
        "module": "commonjs",
    }
}
```

## typescript -> javascript 변환 필요
- 변환하는 과정 : 컴파일 한다.
- 터미널에 입력 : `tsc -w`
<img width="828" alt="스크린샷 2021-11-29 오후 3 21 50" src="https://user-images.githubusercontent.com/65120581/143818511-d4c747f0-aec5-44fb-90e1-7cce086b5e84.png">



## 문법
- 간단한 변수 타입 지정 가능하다.
```typescript
let 이름 : string = 'noh';

// 배열
let 이름 :string[] = ['Yoon', 'Lee']

// object
let 이름2 :{ name? : string } = { name : 'noh' }

// ? 옵션을 넣어 name이라는 속성이 불확실할 때 사용
let 이름3 :{ name? : string } = {  }

// 
let 이름4 : string | number = 'noh'

// type alias
type Name = string | number;

// function
// number를 파라미터로 가져야만하고 return도 number여야 한다.
function 함수(x :number) : number{
    return x * 2
}

// array에 쓸 수 있는 tuple type
type Member = [number, boolean];
let john:Member = [123, true]

// object 자료
type Member2 = {
    name : string
}
let john2 :Member2 = { name : 'noh' }


// 글자로 된 모든 object 속성의 타입은 : string
type Member3 = {
    [key :string] : string,
}
let john3 :Member3 = { name : 'noh' }

class User{
    name :string;
    constructor(name :string){
        this.name = name;
    }
}
```

> 출처 [타입스크립트 쓰는 이유 & 필수 문법 10분 정리](https://www.youtube.com/watch?v=xkpcNolC270)
