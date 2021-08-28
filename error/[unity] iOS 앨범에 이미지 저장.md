# [unity] iOS 앨범에 이미지 저장하기

## 1. Asset Store
Native Gallery for Android & iOS import 하기 <br>
<img width="440" alt="스크린샷 2021-08-28 오후 5 50 36" src="https://user-images.githubusercontent.com/65120581/131216484-ab2d1f7d-afdb-4432-9592-520b3beb13c8.png">
<img width="350" alt="스크린샷 2021-08-28 오후 5 51 04" src="https://user-images.githubusercontent.com/65120581/131216499-732fc8a5-348c-42d9-8662-7adccd47eaa4.png">
<img width="433" alt="스크린샷 2021-08-28 오후 5 50 50" src="https://user-images.githubusercontent.com/65120581/131216505-3774dffe-8b69-429d-8a20-6f31ce0f30c1.png">
> # 참조 [UnityNativeGallery](https://github.com/yasirkula/UnityNativeGallery)
## 2. 스크립트 작성
```C#
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using System.Collections;
 
public class CameraCapture : MonoBehaviour
{
    [SerializeField]
    private Button TakePhotoButton;
    public int fileCounter;

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
        Capture();
    }
 
    public void Capture()
    {
        RenderTexture activeRenderTexture = RenderTexture.active;
        RenderTexture.active = Camera.targetTexture;
 
        Camera.Render();
     
        Texture2D image = new Texture2D(Camera.targetTexture.width, Camera.targetTexture.height);
        image.ReadPixels(new Rect(0, 0, Camera.targetTexture.width, Camera.targetTexture.height), 0, 0);
        image.Apply();
        RenderTexture.active = activeRenderTexture;
 
        byte[] bytes = image.EncodeToPNG();
        Destroy(image);

        // Mac path : 
        // cf) https://docs.unity3d.com/ScriptReference/Application-dataPath.html?_ga=2.85471643.307249663.1629898360-1836162968.1629898360&_gl=1*1kwusal*_ga*MTgzNjE2Mjk2OC4xNjI5ODk4MzYw*_ga_1S78EFL1W5*MTYyOTkwMDcyMi4yLjAuMTYyOTkwMDcyMi42MA..
        // File.WriteAllBytes(Application.dataPath + "/Image/" + fileCounter + ".png", bytes);

        // cf2) https://devparklibrary.tistory.com/32
        // Debug.Log(Application.persistentDataPath);
        // File.WriteAllBytes(Application.persistentDataPath +"/Image" +fileCounter + ".png", bytes);

        // iOS Mobile
        string name = "AR Camera" + System.DateTime.Now.ToString("yyyy-mm-dd_HH-mm-ss") +fileCounter + ".png";
        NativeGallery.SaveImageToGallery(bytes, "AR Camera Assistant picutres", name);
        fileCounter++;
    }
}
```
