# Tistory에서 markdown적용


## 글자 깨짐 발생
![image](https://user-images.githubusercontent.com/65120581/129691960-ec59c84c-738f-4a5d-9d5f-633137173527.png)
- -부분이 깨지는 것을 볼 수 있다.

## 해결방법 
![image](https://user-images.githubusercontent.com/65120581/129692584-70ca5fde-b187-46f8-8c11-a46aed847f8e.png) <br>
- 꾸미기의 스킨 편집으로 이동 <br>
![image](https://user-images.githubusercontent.com/65120581/129692647-52ad5682-2052-486b-b151-2fd9d89d218a.png)
- html 편집

![image](https://user-images.githubusercontent.com/65120581/129692751-d24c8744-dba1-4de6-ae7b-bb5ac7fc7dd0.png)<br>
```html
<link href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/4.0.0/github-markdown.css" rel="stylesheet">
```
빈 곳에 추가
- [최근 github css 버전 확인](https://cdnjs.com/libraries/github-markdown-css)

![image](https://user-images.githubusercontent.com/65120581/129692964-5b7cb0a1-849d-4adf-8b63-5002d2edac22.png)
```css
.markdown-body { 
	box-sizing: border-box; 
	min-width: 200px; 
	max-width: 980px; 
	margin: 0 auto; 
	padding: 45px; 
	} 
@media (max-width: 767px) { 
	.markdown-body { 
		padding: 15px; 
		} 
	}
```
css 맨 밑에 추가
![image](https://user-images.githubusercontent.com/65120581/129693243-5daf89cb-5a59-46f9-9c6e-14851740438a.png)
- html 모드 클릭

- 글의 처음에 `<div class="markdown-body">`
- 글의 마지막에 `</div>` 각각 추가해준다.

## 결과화면 
![image](https://user-images.githubusercontent.com/65120581/129693573-61a371f1-78ae-49ff-86cf-a6aeae975a77.png)



