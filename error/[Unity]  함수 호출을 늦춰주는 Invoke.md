# [Unity]  함수 호출을 늦춰주는 Invoke

## 문제
```C#
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using System.Collections;
 
public class CameraCapture : MonoBehaviour
{
    [SerializeField]
    private Button TakePhotoButton;
    [SerializeField]
    private GameObject Canvas;
    public int fileCounter;
    public KeyCode screenshotKey;

    private Camera Camera
    {
        get
        {
            if (!_camera)
            {
                // _camera = Camera.main;
                _camera = Camera.main;
            }
            return _camera;
        }
    }

    [SerializeField]
    private Camera _camera;
    void Awake()
    {
        TakePhotoButton.onClick.AddListener(TakePhoto);
    }

    private void TakePhoto()
    {
        Debug.Log("TakePhoto()" + fileCounter);
        // Capture();
        Canvas.SetActive(false);
        Screenshot();
    }
    
    private void Screenshot()
    {
        Texture2D texture = new Texture2D(Screen.width, Screen.height, TextureFormat.RGB24, false);
        texture.ReadPixels(new Rect(0, 0, Screen.width, Screen.height), 0, 0);
        texture.Apply();

        byte[] bytes = texture.EncodeToPNG();
        string name = "AR Camera" + System.DateTime.Now.ToString("yyyy-mm-dd_HH-mm-ss") +fileCounter + ".png";
        NativeGallery.SaveImageToGallery(bytes, "AR Camera", name);
        fileCounter++;
        Destroy(texture);
        Canvas.SetActive(true);
        Debug.Log("Canvas True");
    }
}
```
위 코드를 실행하면 
![image](https://user-images.githubusercontent.com/65120581/131450310-7e3e90d6-76e9-4a46-9ac8-85bba7604b70.png)
Button들의 상위에 해당하는 Canvas가 SetActive(false) 되지 못하고 보이게 된다.


따라서 시간지연 기능 함수 Invoke를 이용한다.
```c#
Invoke("method name", 2f)     // (함수명, sec)
```
```c#
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using System.Collections;
 
public class CameraCapture : MonoBehaviour
{
    [SerializeField]
    private Button TakePhotoButton;
    [SerializeField]
    private GameObject Canvas;
    public int fileCounter;
    public KeyCode screenshotKey;

    private Camera Camera
    {
        get
        {
            if (!_camera)
            {
                // _camera = Camera.main;
                _camera = Camera.main;
            }
            return _camera;
        }
    }

    [SerializeField]
    private Camera _camera;
    void Awake()
    {
        TakePhotoButton.onClick.AddListener(TakePhoto);
    }

    private void TakePhoto()
    {
        Debug.Log("TakePhoto()" + fileCounter);
        Canvas.SetActive(false);
        Invoke("Screenshot", 1f);
        // Screenshot();
    }

    private setActiveTrue()
    {
        Canvas.SetActive(true);
    }
    
    private void Screenshot()
    {
        Texture2D texture = new Texture2D(Screen.width, Screen.height, TextureFormat.RGB24, false);
        texture.ReadPixels(new Rect(0, 0, Screen.width, Screen.height), 0, 0);
        texture.Apply();

        byte[] bytes = texture.EncodeToPNG();
        string name = "AR Camera" + System.DateTime.Now.ToString("yyyy-mm-dd_HH-mm-ss") +fileCounter + ".png";
        NativeGallery.SaveImageToGallery(bytes, "AR Camera", name);
        fileCounter++;
        Destroy(texture);
        // Canvas.SetActive(true);
        Invoke("setActiveTrue", 2f);
        Debug.Log("Canvas True");
    }
}
```
## 순서
- Canvas.SetActive(false);       Ui를 화면에서 지운다
- Invoke("Screenshot", 1f);      Screenshot함수를 1초 후 호출
- Invoke("setActiveTrue", 2f);   setActiveTrue함수를 2초후 호출

##### 시간 지연 기능 또 다른  함수 Coroutine
