# [Unity]iOS14 앨범에 사진 저장
Unity ar Camera를 이용해서 화면 스크린샷 png를 앨범 내에 저장해주는 방법이다.

## 진행된 Version
- Unity Version : 2019.4.26f1
- Xcode         : 14.5
- iOS           : 14.7


## CameraCapture script
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

## 결과화면



https://user-images.githubusercontent.com/65120581/131253451-6e043029-af3d-49a0-a447-81ea8a9027b7.mov

![IMG_25D1A8429F80-1](https://user-images.githubusercontent.com/65120581/131253483-1d6d1b46-bcd9-4bf4-a756-6a9a77508a79.jpeg)
