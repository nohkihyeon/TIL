# AWS CDK
- AWS Cloud Development Kit
- 프로그래밍 언어를 사용하여 클라우드 애플리케이션 리소스를 모델링 및 프로비저닝 해주는 도구
- yaml, json 형태의 선언적 접근 방식보다 개발자에게 친화적인 언어를 사용해서 작성
- level1
  - AWS 관리 콘솔에서 리소스 만들기
- level2
  - 코드 형태의 명령형 인프라(aws-cli, aws-sdk 사용하여 deploy script 작성)
- level3
  - 코드로 선언하여 인프라 관리
- level4
  - 개발자에게 친화적인 언어로 작성 가능(DX)
  - for, if와 같은 조건문을 사용하여 동적으로 인프라 구성이 가능

<img width="805" alt="스크린샷 2022-05-09 오후 10 38 37" src="https://user-images.githubusercontent.com/65120581/167422332-3e97e034-410f-42bc-be86-5ef43b788de9.png">

## AWS CDK 구성
<img width="468" alt="스크린샷 2022-05-10 오후 8 20 48" src="https://user-images.githubusercontent.com/65120581/167617284-18aca30a-762f-4c9d-9e84-bebd48dd4a77.png">

### App
- 기본 구문
- CDK CLI를 통해 AWS CloudFormation 템플릿을 렌더링하고 배포
- 배포 가능한 단위인 하나 이상의 스택으로 구성되며 리전 및 계정에 대한 정보를 포함
### Stack
- AWS 람다, AWS ECS와 같은 AWS 리소스를 표현하는 구문이 포함된다.
### Construct(구문)
- 레벨 1 : AWS CloudFormation 리소스
- 레벨 2 : AWS 구문 라이브러리
- 레벨 3 : Your awesome stuff


> 참고
> [AWS CDK 란?](https://cherrypick.co.kr/about-aws-cdk/)
