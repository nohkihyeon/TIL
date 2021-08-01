# unityNullReferenceError

- 유니티에서 Raycast 스크립트 적용 시
```C#
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObjectTouchEvent : MonoBehaviour
{
    // Update is called once per frame
    void Update()
    {
            ClickDetect();
    }

    private void ClickDetect()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Debug.Log("Click ButtonDown(0)");
            RaycastHit hit_info;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit_info, Mathf.Infinity))
            {
                Debug.Log("왜 여기는 안들어오는가?");
                Debug.Log(hit_info.collider.gameObject.name);
            }
        }
    }
}

```
- 유니티 실행 후 
<img width="710" alt="스크린샷 2021-08-01 오후 10 36 14" src="https://user-images.githubusercontent.com/65120581/127772869-5a5f0fbd-5cb9-4e0d-aca5-b550c13d124e.png"> <br>
- NullReferenceException: Object reference not set to an instance of an object

- 해결방법 
<img width="381" alt="스크린샷 2021-08-01 오후 10 36 47" src="https://user-images.githubusercontent.com/65120581/127772886-297bc056-2446-4102-8e12-041e603d2d11.png"><br>
- main Camera에 Tag를 MainCamera로 수정한다.
