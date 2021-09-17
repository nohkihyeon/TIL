# [LeanTween](https://assetstore.unity.com/packages/tools/animation/leantween-3595?locale=ko-KR)
- 애니메이션 도구이다.
- Unity에서 부드러운 효과를 위해서 애니메이션을 넣어 표현할 수 있다.
- 하지만 애니메이션을 넣을 경우 Idle한 프로세스가 증가하기 때문에 비효율적이다.
- LeanTween을 import 해서 사용하는 것을 추천


## LeanTween import
<img width="462" alt="스크린샷 2021-09-17 오후 11 38 21" src="https://user-images.githubusercontent.com/65120581/133801323-192e9e75-c4d4-47fe-9eb7-204e63e521c9.png">


## Script CS
```c#
using System;
using System.Collections;
using System.Collections.Generic;
using DilmerGames.Core.Singletons;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.XR.ARFoundation;
using UnityEngine.XR.ARSubsystems;

public class AlertMessageController : MonoBehaviour
{   
    [SerializeField]
    public GameObject AlertPanel;
    [SerializeField]
    public Text AlertText;

    [SerializeField]
    public GameObject StartPoint;
    
    [SerializeField]
    public GameObject EndPoint;

    public void popUpMessage(string msg)
    {
        AlertText.text = msg;
        LeanTween.move(AlertPanel, StartPoint.transform.position , 0.2f).setOnComplete(Destroy);
    }
    void Destroy()
    {

        LeanTween.move(AlertPanel, EndPoint.transform.position, 1f).setDelay(1f);
    }
}

```
- popUpMessage("msessage 내용")을 호출하면 Start point의 위치로 팝업창이 이동한다. 0.2f 이 숫자가 적을수록 속도가 빨라진다.
### 해당 object의 Inspector

<img width="414" alt="스크린샷 2021-09-17 오후 11 39 32" src="https://user-images.githubusercontent.com/65120581/133801482-44982d54-6820-4de8-ac4c-7ffa6089cacf.png">

### start point
<img width="415" alt="스크린샷 2021-09-17 오후 11 40 22" src="https://user-images.githubusercontent.com/65120581/133801610-c9245d28-9f81-430c-a3ea-58966d2d63f3.png">

## end point 
<img width="436" alt="스크린샷 2021-09-17 오후 11 40 37" src="https://user-images.githubusercontent.com/65120581/133801652-545bc84b-128c-4d0d-a1d8-8d8009bb32eb.png">


## 결과화면


https://user-images.githubusercontent.com/65120581/133803473-33bfce90-e4f8-4bfd-b35e-c112827f8876.mov



