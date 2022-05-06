## 쿠버네티스
- 컨테이너를 사용하면 서버의 자원을 효율적으로 사용할 수 있다. 컨테이너가 엄청나게 많아진다면 관리와 운영이 어려워져 효율성이 떨어진다.
- 이 문제를 해결하기 위해 개발된 도구가 쿠버네티스이다. <br>
1. `kubectl run nginx --image=nginx`
2. Deployment가 만들어지고 ReplicaSet가 만들어지고, ReplicaSet에 의해 Prod가 만들어 진다.
3. <img width="181" alt="스크린샷 2022-05-06 오후 4 00 16" src="https://user-images.githubusercontent.com/65120581/167082804-2e43d159-e0ad-44aa-88b7-545dd0df3ea1.png">
4. <img width="582" alt="스크린샷 2022-05-06 오후 4 05 18" src="https://user-images.githubusercontent.com/65120581/167083503-3d83c4d4-e15f-42f2-b221-c97ecb43a5bc.png">



