# pull request 방법

## 순서
1. Fork
2. clone, remote설정
3. branch 생성
4. 수정 작어 후 add, commit, push
5. pull Request 생성
6. 코드리뷰, Merge Pull Request
7. Merge 이 branch 삭제 및 동기화

### 1. Fork
- fork는 다른 사람의 Github repository에서 내가 어떤 부분을 수정하거나 추가 기능을 넣고 싶을 때 해당 respository를 내 Github repository로 그대로 복제하는 기능이다. fork한 저장소는 원본(다른 사람의 github repository)와 연결되어 있다. 여기서 연결 되어 있다는 의미는 original repository에 어떤 변화가 생기면(새로운 commit) 이는 그대로 forked된 repository로 반영할 수 있다. 이 때 fetch나 rebase의 과정이 필요하다.
![스크린샷 2021-07-30 오후 11 42 21](https://user-images.githubusercontent.com/65120581/127669877-0423ed0c-ccf3-4763-b41b-9c2a44e4e1ff.png)

> ### clone
> - clone은 특정 repository를 내 local machine에 복사하여 새로운 저장소를 만든다. clone한 원본 repository를 remote 저장소 origin으로 가지고 있다. 권한이 없는 경우 해당 저장소로 push 하지 못한다. 또한 기존의 제일 처음 original repository와 연결되지 못한다. 즉 저장소의 commit, 등의 로그를 보지 못함 <br>
> ![스크린샷 2021-07-30 오후 11 45 34](https://user-images.githubusercontent.com/65120581/127670339-c2d43942-92c6-4753-988c-658458224dea.png)
> - 상대방이 먼저 push 후 origin/master에 변경 사항으 적용하였기 때문에 해다 변경 사항을 내 로컬에서도 적용학 위해서 친구가 수정한 커밋을 Fetch하고 Merge 해야한다.

### 2. clone, remote 설정
### 3. branch 생성
### 4. 수정 작어 후 add, commit, push
### 5. Pull Request 생성
<img width="1006" alt="스크린샷 2021-07-30 오후 11 50 58" src="https://user-images.githubusercontent.com/65120581/127671108-251cc107-f0d7-410a-b0fa-9890ffef723b.png">
- push 후 계정에 Compare & pull request 버튼이 활성화 되어 있다.
- 해당 버튼을 클릭 후 메시지를 작성한다.
<img width="1022" alt="스크린샷 2021-07-30 오후 11 53 11" src="https://user-images.githubusercontent.com/65120581/127671404-d07cf242-dc66-4d65-b5d9-fae906293e92.png">
### 6. 코드리뷰, Merge pull Request
- Review Changes
<img width="1386" alt="스크린샷 2021-07-30 오후 11 56 29" src="https://user-images.githubusercontent.com/65120581/127671878-1ad3c5aa-326f-48ca-8c5e-4e139069ca90.png">

- PR을 Merge 하느 방법으 3가지가 있다.
<img width="1349" alt="스크린샷 2021-07-31 오전 12 03 55" src="https://user-images.githubusercontent.com/65120581/127672852-e93afb27-90e0-44b1-9d5c-f298a137bc2c.png"> <br>
    #### 1. Create a merge commit
    - PR의 commit들이 메시지와 함께 master의 Head commit을 들어간다. `$ git merge` 명령어와 똑같다.
    #### 2. Rebase and merge
    - PR의 커밋로그들이 master에 재정렬돼서 병합된다. PR에서 작성하 모든 커밋들이 master에서 관리돼야 한다면 Rebase and merge로 병합하는 방법이 좋다.
    - 로컬에서 작성된 모드 커밋로그들까지 추적하 수 있는 장점이 있다.
<img width="942" alt="스크린샷 2021-07-31 오전 12 12 12" src="https://user-images.githubusercontent.com/65120581/127673950-37203e58-3b20-4961-a86c-5b5135c85260.png"> <br>

    #### 3. Squash and merge
    - PR의 commit log들을 한개로 추려 master에 병합하는 방법이다. Squash and Merge를 클릭하면 PR 제목으로 된 1개의 커밋로그가 master에 병합된다.
    


### PR 병합 후 Revert
- `$ git revert`와 동일하게 PR도 되돌릴 수 있다. <br>
<img width="979" alt="스크린샷 2021-07-31 오전 12 13 33" src="https://user-images.githubusercontent.com/65120581/127674112-cea9cf42-46e2-4725-b8f4-6bb061ba36cc.png">
- Revert 버튼을 클릭 하며 모든 commit이 되돌려진다.
- Delete branch 버튼으 클릭하면 PR 병하 후에는 필요 없는 브랜치르 지울 수 있다.
<img width="929" alt="스크린샷 2021-07-31 오전 12 16 12" src="https://user-images.githubusercontent.com/65120581/127674385-90171ee2-a77a-4fba-98ac-8885f19e1c54.png">

<br>
<br>
<br>
<br>
<br>
<br>
> ## 참조

> ### [git 초보를 위한 풀리퀘스트(pull request) 방법](https://wayhome25.github.io/git/2017/07/08/git-first-pull-request-story/)
> ### [Git fork와 clone 의 차이점](https://velog.io/@imacoolgirlyo/Git-fork와-clone-의-차이점-5sjuhwfzgp)
> ### [6. Github으로 협업하는 법](https://brunch.co.kr/@anonymdevoo/9)
