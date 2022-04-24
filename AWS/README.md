# 아마존 웹서비스와 클라우드 컴퓨팅

# 컴퓨팅 서비스
## EC2
- 아마존 핵심 서비스
- Elastic Compute Cloud
- AMI : 서버에 필요한 운영체제와 여러 소프트웨어들이 적절히 구성된 템플릿
- 인스턴스 연결
  - 프라이빗 키르 보안된 위치이 .ssh 디렉토리에 저장
  - 프라이빗 키의 권한을 설정
  - ssh 명령어로 인스턴스에 연결

## Auto Scaling
- 유연성을 이용한 핵심 기술
- CPU, Memory, Dis, Network 등의 시스템 Metric값이나 application을 모니터링하고 size를 자동으로 조절하는 기술
## Lambda
- 아마존 웹서비스에서 제공하는 서버리스 컴퓨팅
- C#, PowerShell, Go, Java, JavaScript, Python, Ruby
 
## ECS
- Elastic Container Service
- 클러스터에서 컨테이너를 쉽게 ㅣㄹ행, 중지 및 관리할 수 있게 해주는 컨테이너 관리 서비스
- 구성요소
<img width="590" alt="스크린샷 2022-04-22 오후 9 30 37" src="https://user-images.githubusercontent.com/65120581/164714590-37115b4c-e6ec-433e-acb8-5ec83da025dc.png">

## EKS
- Amazon Elastic Kubernetes Service
- <img width="508" alt="스크린샷 2022-04-23 오전 12 36 47" src="https://user-images.githubusercontent.com/65120581/164747564-dfc82ed1-c78f-40eb-8265-5b5aefb6c4a6.png">
1. AWS Management Console 또는 AWS CLI를 사용하거나 AWS SDK를 사용하여 Amazon EKS를 생성합니다.

2. 관리형 또는 자체 관리형 Amazon EC2 노드를 실행하거나 워크로드를 AWS Fargate에 배포합니다.

3. 클러스터가 준비되면 원하는 Kubernetes 도구(예: kubectl)를 구성하여 클러스터와 통신할 수 있습니다.

4. 다른 Kubernetes 환경에서와 마찬가지로 Amazon EKS 클러스터에 워크로드를 배포 및 관리합니다. AWS Management Console을 사용하여 노드 및 워크로드에 대한 정보를 볼 수도 있습니다.
## Elastic Beanstalk
- Java, Net, PHP, Node.js, Python, Ruby, Go, Docker를 사용하여 Apache, Nginx, Passenger, IIS와 같은 친숙한 서버에서 개발된 웹 애플리케이션 및 서비스를 간편하게 배포하고 조정할 수 있는 서비스
- 코드를 업로드해서 용량 프로비저닝, 로드 밸런싱, 오토 스케일링, 애플리케이션 상태 모니터링, 배포를 자동으로 처리하도록 구성할 수 있음
# 네트워크 서비스
## VPC
## CloudFront
## Route53
## Elastic Load Balancing
## Direct Connect
## Transit Gateway 
# 스토리 서비스
## EBS
## S3
## EFS
## S3 Glacier

# 데이터베이스 서비스
## RES
## DynamoDB
