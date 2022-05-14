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

### Construct
- 다른 Construct를 기반으로 정의될 수 있다.
- 계층적인 트리 구조를 가짐으로써 높은 수준의 추상화를 가진 패턴을 만들 수 있다.
- Scope : Construct가 정의되는 범위를 나타나게 된다. super 또는 this를 통해 Construct 자기 자신을 Scope로 넘겨줘야 한다.
- ID : Scope 내에서 유일한 식별자이며 Scope의 하위 트리 내에 캡슐화된 모든 리소스에 대한 네임 스페이스 역할을 한다. AWS CloudFormation의 논리적인 ID나 리소스 이름과 같은 고유한 식별자를 할당할 때 사용
- Props : 리소스의 구성을 정의하는 속성이나 키워드 집합이라고 볼 수 있다. s3.Bucket의 경우 "versioned=True"라는 옵션을 추가할 때 Props에 이를 적용시킨다고 생각 할 수 있다.

```python
from aws_cdk.core import App, Stack
from aws_cdk import aws_s3 as s3

class HelloCdkStack(core.Stack):
	
    def __init__(self, scope: core.Construct, id: str, **kwargs) -> None:
    	super().__init__(scope, id, **kwargs)
       
        s3.Bucket(self, "MyFirstBucket", versioned=True)
        
app = core.App()
HelloCdkStack(app, "HelloCdkStack")


raw_data = s3.Bucket(self, 'raw-data')
data_science = iam.Group(self, 'data-science')
raw_data.grand_read(data_science)
```

### Stack
```python
import * as cdk from '@aws-cdk/core';
import * as lambda from '@aws-cdk/aws-lambda';
import * as apigw from '@aws-cdk/aws-apigateway';
import { HitCounter } from './hitcounter';
import { TableViewer } from 'cdk-dynamo-table-viewer';

export class CdkWorkshopStack extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const hello = new lambda.Function(this, 'HelloHandler', {
      runtime: lambda.Runtime.NODEJS_10_X,
      code: lambda.Code.fromAsset('lambda'),
      handler: 'hello.handler'
    });

    const helloWithCounter = new HitCounter(this, 'HelloHitCounter', {
      downstream: hello
    });

    new apigw.LambdaRestApi(this, 'Endpoint', {
      handler: helloWithCounter.handler
    });

    new TableViewer(this, 'ViewHitCounter', {
      title: 'Hello Hits',
      table: helloWithCounter.table
    })
  }
}
```

`$ cdk deploy`

> 참고
> [AWS CDK 란?](https://cherrypick.co.kr/about-aws-cdk/)
> [CDK(Cloud Development Kit) 개념](https://dong-life.tistory.com/92)
