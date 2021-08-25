# unity3d 카메라 

- camera capture c#
```C#
using System.IO;
using UnityEngine;
using UnityEngine.UI;
 
public class CameraCapture : MonoBehaviour
{
    [SerializeField]
    private Button TakePhotoButton;
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
    // private void LateUpdate()
    // {
    //     if (Input.GetKeyDown(screenshotKey))
    //     {
    //         Capture();
    //     }
    // }
 
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
        File.WriteAllBytes(Application.dataPath + fileCounter + ".png", bytes);
        fileCounter++;
    }
}

```
- ar camera manager로 카메라 접근
<img width="636" alt="스크린샷 2021-08-26 오전 1 11 22" src="https://user-images.githubusercontent.com/65120581/130826490-42360c66-02ca-40eb-b8dd-0f6ceeda0f5a.png">

![IMG_3EB64F844ADD-1](https://user-images.githubusercontent.com/65120581/130826875-92ab2f6f-a18b-45bb-8a7d-98de5e260e3d.jpeg)

- take photo button 클릭 시 나타나는 에러
```
2021-08-26 01:04:38.054210+0900 yatop12[1814:382650] UnityIAP UnityEarlyTransactionObserver: Created
2021-08-26 01:04:38.054636+0900 yatop12[1814:382650] UnityIAP UnityEarlyTransactionObserver: Registered for lifecycle events
2021-08-26 01:04:38.438869+0900 yatop12[1814:382650] Built from '2019.4/staging' branch, Version '2019.4.26f1 (e0392c6b2363)', Build type 'Release', Scripting Backend 'il2cpp'
2021-08-26 01:04:38.444030+0900 yatop12[1814:382650] MemoryManager: Using 'Default' Allocator.
-> applicationDidFinishLaunching()
2021-08-26 01:04:40.096089+0900 yatop12[1814:382650] UnityIAP UnityEarlyTransactionObserver: Added to the payment queue
-> applicationDidBecomeActive()
[Subsystems] Discovering subsystems at path /private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data/UnitySubsystems
[Subsystems] No descriptors matched for  examples in UnitySubsystems/UnityARKit/UnitySubsystemsManifest.json.
[Subsystems] 1 'inputs' descriptors matched in UnitySubsystems/UnityARKit/UnitySubsystemsManifest.json
[Subsystems] No descriptors matched for  displays in UnitySubsystems/UnityARKit/UnitySubsystemsManifest.json.
[Subsystems] No descriptors matched for  meshings in UnitySubsystems/UnityARKit/UnitySubsystemsManifest.json.
GfxDevice: creating device client; threaded=1
Initializing Metal device caps: Apple A12 GPU
Initialize engine version: 2019.4.26f1 (e0392c6b2363)
2021-08-26 01:04:40.111677+0900 yatop12[1814:383042] fopen failed for data file: errno = 2 (No such file or directory)
2021-08-26 01:04:40.111724+0900 yatop12[1814:383042] Errors found! Invalidating cache...
The referenced script (Unknown) on this Behaviour is missing! 
(Filename:  Line: 199)

The referenced script on this Behaviour (Game Object '<null>') is missing! 
(Filename:  Line: 334)

A scripted object (probably UnityEngine.XR.ARCore.ARCoreLoaderSettings?) has a different serialization layout when loading. (Read 56 bytes but expected 60 bytes)
Did you #ifdef UNITY_EDITOR a section of your serialized properties in any of your scripts? 
(Filename:  Line: 2342)

2021-08-26 01:04:42.548381+0900 yatop12[1814:383042] fopen failed for data file: errno = 2 (No such file or directory)
2021-08-26 01:04:42.548629+0900 yatop12[1814:383042] Errors found! Invalidating cache...
The referenced script (TouchEvent) on this Behaviour is missing! 
(Filename:  Line: 199)

The referenced script on this Behaviour (Game Object 'Canvas') is missing! 
(Filename:  Line: 334)

2021-08-26 01:04:42.861675+0900 yatop12[1814:382650] Unbalanced calls to begin/end appearance transitions for <UnityViewControllerStoryboard: 0x102c11a40>.
UnloadTime: 5.848417 ms
[Subsystems] Loading plugin UnityARKit for subsystem ARKit-Input...
[Subsystems] UnityARKit successfully registered Provider for ARKit-Input
2021-08-26 01:04:44.018947+0900 yatop12[1814:382650] 
    // 2D joint skeleton
    enum JointIndices
    {
        Invalid = -1,
        Head = 0, // parent: Neck1 [1]
        Neck1 = 1, // parent: Root [16]
        RightShoulder1 = 2, // parent: Neck1 [1]
        RightForearm = 3, // parent: RightShoulder1 [2]
        RightHand = 4, // parent: RightForearm [3]
        LeftShoulder1 = 5, // parent: Neck1 [1]
        LeftForearm = 6, // parent: LeftShoulder1 [5]
        LeftHand = 7, // parent: LeftForearm [6]
        RightUpLeg = 8, // parent: Root [16]
        RightLeg = 9, // parent: RightUpLeg [8]
        RightFoot = 10, // parent: RightLeg [9]
        LeftUpLeg = 11, // parent: Root [16]
        LeftLeg = 12, // parent: LeftUpLeg [11]
        LeftFoot = 13, // parent: LeftLeg [12]
        RightEye = 14, // parent: Head [0]
        LeftEye = 15, // parent: Head [0]
        Root = 16, // parent: <none> [-1]
    }
2021-08-26 01:04:44.040353+0900 yatop12[1814:382650] 
    // 3D joint skeleton
    enum JointIndices
    {
        Invalid = -1,
        Root = 0, // parent: <none> [-1]
        Hips = 1, // parent: Root [0]
        LeftUpLeg = 2, // parent: Hips [1]
        LeftLeg = 3, // parent: LeftUpLeg [2]
        LeftFoot = 4, // parent: LeftLeg [3]
        LeftToes = 5, // parent: LeftFoot [4]
        LeftToesEnd = 6, // parent: LeftToes [5]
        RightUpLeg = 7, // parent: Hips [1]
        RightLeg = 8, // parent: RightUpLeg [7]
        RightFoot = 9, // parent: RightLeg [8]
        RightToes = 10, // parent: RightFoot [9]
        RightToesEnd = 11, // parent: RightToes [10]
        Spine1 = 12, // parent: Hips [1]
        Spine2 = 13, // parent: Spine1 [12]
        Spine3 = 14, // parent: Spine2 [13]
        Spine4 = 15, // parent: Spine3 [14]
        Spine5 = 16, // parent: Spine4 [15]
        Spine6 = 17, // parent: Spine5 [16]
        Spine7 = 18, // parent: Spine6 [17]
        LeftShoulder1 = 19, // parent: Spine7 [18]
        LeftArm = 20, // parent: LeftShoulder1 [19]
        LeftForearm = 21, // parent: LeftArm [20]
        LeftHand = 22, // parent: LeftForearm [21]
        LeftHandIndexStart = 23, // parent: LeftHand [22]
        LeftHandIndex1 = 24, // parent: LeftHandIndexStart [23]
        LeftHandIndex2 = 25, // parent: LeftHandIndex1 [24]
        LeftHandIndex3 = 26, // parent: LeftHandIndex2 [25]
        LeftHandIndexEnd = 27, // parent: LeftHandIndex3 [26]
        LeftHandMidStart = 28, // parent: LeftHand [22]
        LeftHandMid1 = 29, // parent: LeftHandMidStart [28]
        LeftHandMid2 = 30, // parent: LeftHandMid1 [29]
        LeftHandMid3 = 31, // parent: LeftHandMid2 [30]
        LeftHandMidEnd = 32, // parent: LeftHandMid3 [31]
        LeftHandPinkyStart = 33, // parent: LeftHand [22]
        LeftHandPinky1 = 34, // parent: LeftHandPinkyStart [33]
        LeftHandPinky2 = 35, // parent: LeftHandPinky1 [34]
        LeftHandPinky3 = 36, // parent: LeftHandPinky2 [35]
        LeftHandPinkyEnd = 37, // parent: LeftHandPinky3 [36]
        LeftHandRingStart = 38, // parent: LeftHand [22]
        LeftHandRing1 = 39, // parent: LeftHandRingStart [38]
        LeftHandRing2 = 40, // parent: LeftHandRing1 [39]
        LeftHandRing3 = 41, // parent: LeftHandRing2 [40]
        LeftHandRingEnd = 42, // parent: LeftHandRing3 [41]
        LeftHandThumbStart = 43, // parent: LeftHand [22]
        LeftHandThumb1 = 44, // parent: LeftHandThumbStart [43]
        LeftHandThumb2 = 45, // parent: LeftHandThumb1 [44]
        LeftHandThumbEnd = 46, // parent: LeftHandThumb2 [45]
        Neck1 = 47, // parent: Spine7 [18]
        Neck2 = 48, // parent: Neck1 [47]
        Neck3 = 49, // parent: Neck2 [48]
        Neck4 = 50, // parent: Neck3 [49]
        Head = 51, // parent: Neck4 [50]
        Jaw = 52, // parent: Head [51]
        Chin = 53, // parent: Jaw [52]
        LeftEye = 54, // parent: Head [51]
        LeftEyeLowerLid = 55, // parent: LeftEye [54]
        LeftEyeUpperLid = 56, // parent: LeftEye [54]
        LeftEyeball = 57, // parent: LeftEye [54]
        Nose = 58, // parent: Head [51]
        RightEye = 59, // parent: Head [51]
        RightEyeLowerLid = 60, // parent: RightEye [59]
        RightEyeUpperLid = 61, // parent: RightEye [59]
        RightEyeball = 62, // parent: RightEye [59]
        RightShoulder1 = 63, // parent: Spine7 [18]
        RightArm = 64, // parent: RightShoulder1 [63]
        RightForearm = 65, // parent: RightArm [64]
        RightHand = 66, // parent: RightForearm [65]
        RightHandIndexStart = 67, // parent: RightHand [66]
        RightHandIndex1 = 68, // parent: RightHandIndexStart [67]
        RightHandIndex2 = 69, // parent: RightHandIndex1 [68]
        RightHandIndex3 = 70, // parent: RightHandIndex2 [69]
        RightHandIndexEnd = 71, // parent: RightHandIndex3 [70]
        RightHandMidStart = 72, // parent: RightHand [66]
        RightHandMid1 = 73, // parent: RightHandMidStart [72]
        RightHandMid2 = 74, // parent: RightHandMid1 [73]
        RightHandMid3 = 75, // parent: RightHandMid2 [74]
        RightHandMidEnd = 76, // parent: RightHandMid3 [75]
        RightHandPinkyStart = 77, // parent: RightHand [66]
        RightHandPinky1 = 78, // parent: RightHandPinkyStart [77]
        RightHandPinky2 = 79, // parent: RightHandPinky1 [78]
        RightHandPinky3 = 80, // parent: RightHandPinky2 [79]
        RightHandPinkyEnd = 81, // parent: RightHandPinky3 [80]
        RightHandRingStart = 82, // parent: RightHand [66]
        RightHandRing1 = 83, // parent: RightHandRingStart [82]
        RightHandRing2 = 84, // parent: RightHandRing1 [83]
        RightHandRing3 = 85, // parent: RightHandRing2 [84]
        RightHandRingEnd = 86, // parent: RightHandRing3 [85]
        RightHandThumbStart = 87, // parent: RightHand [66]
        RightHandThumb1 = 88, // parent: RightHandThumbStart [87]
        RightHandThumb2 = 89, // parent: RightHandThumb1 [88]
        RightHandThumbEnd = 90, // parent: RightHandThumb2 [89]
    }
Setting up 1 worker threads for Enlighten.
  Thread -> id: 171563000 -> priority: 1 
-> applicationWillResignActive()
WARNING -> applicationDidReceiveMemoryWarning()
-> applicationDidBecomeActive()
Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Adding a new skeleton [2249251578E166EA-AA42872B81FED093].
HumanBodyTrackerUI:OnHumanBodiesChanged(ARHumanBodiesChangedEventArgs)
System.Action`1:Invoke(T)
UnityEngine.XR.ARFoundation.ARHumanBodyManager:OnTrackablesChanged(List`1, List`1, List`1)
UnityEngine.XR.ARFoundation.ARTrackableManager`4:Update()
UnityEngine.XR.ARFoundation.ARHumanBodyManager:Update()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Adding a new skeleton [2249251578E166EA-AA42872B81FED093].
HumanBodyTrackerUI:OnHumanBodiesChanged(ARHumanBodiesChangedEventArgs)
System.Action`1:Invoke(T)
UnityEngine.XR.ARFoundation.ARHumanBodyManager:OnTrackablesChanged(List`1, List`1, List`1)
UnityEngine.XR.ARFoundation.ARTrackableManager`4:Update()
UnityEngine.XR.ARFoundation.ARHumanBodyManager:Update()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

There is more than one HumanBodyTrackerUI in the scene.
DilmerGames.Core.Singletons.Singleton`1:get_Instance()
HumanBodyTrackerUI:CaptureFunction()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

TakePhoto()0
CameraCapture:TakePhoto()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

UnauthorizedAccessException: Access to the path "/private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data0.png" is denied.
  at System.IO.FileStream..ctor (System.String path, System.IO.FileMode mode, System.IO.FileAccess access, System.IO.FileShare share, System.Int32 bufferSize, System.Boolean anonymous, System.IO.FileOptions options) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.Create (System.String path, System.Int32 bufferSize) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.WriteAllBytes (System.String path, System.Byte[] bytes) [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.Capture () [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.TakePhoto () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityAction.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityEvent.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1].Invoke (T1 handler, UnityEngine.EventSystems.BaseEventData eventData) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents.Execute[T] (UnityEngine.GameObject target, UnityEngine.EventSystems.BaseEventData eventData, UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1] functor) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchPress (UnityEngine.EventSystems.PointerEventData pointerEvent, System.Boolean pressed, System.Boolean released) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchEvents () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.Process () [0x00000] in <00000000000000000000000000000000>:0 
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: currently not available on il2cpp Line: -1)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

TakePhoto()0
CameraCapture:TakePhoto()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

UnauthorizedAccessException: Access to the path "/private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data0.png" is denied.
  at System.IO.FileStream..ctor (System.String path, System.IO.FileMode mode, System.IO.FileAccess access, System.IO.FileShare share, System.Int32 bufferSize, System.Boolean anonymous, System.IO.FileOptions options) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.Create (System.String path, System.Int32 bufferSize) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.WriteAllBytes (System.String path, System.Byte[] bytes) [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.Capture () [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.TakePhoto () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityAction.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityEvent.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1].Invoke (T1 handler, UnityEngine.EventSystems.BaseEventData eventData) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents.Execute[T] (UnityEngine.GameObject target, UnityEngine.EventSystems.BaseEventData eventData, UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1] functor) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchPress (UnityEngine.EventSystems.PointerEventData pointerEvent, System.Boolean pressed, System.Boolean released) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchEvents () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.Process () [0x00000] in <00000000000000000000000000000000>:0 
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: currently not available on il2cpp Line: -1)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

TakePhoto()0
CameraCapture:TakePhoto()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

UnauthorizedAccessException: Access to the path "/private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data0.png" is denied.
  at System.IO.FileStream..ctor (System.String path, System.IO.FileMode mode, System.IO.FileAccess access, System.IO.FileShare share, System.Int32 bufferSize, System.Boolean anonymous, System.IO.FileOptions options) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.Create (System.String path, System.Int32 bufferSize) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.WriteAllBytes (System.String path, System.Byte[] bytes) [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.Capture () [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.TakePhoto () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityAction.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityEvent.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1].Invoke (T1 handler, UnityEngine.EventSystems.BaseEventData eventData) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents.Execute[T] (UnityEngine.GameObject target, UnityEngine.EventSystems.BaseEventData eventData, UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1] functor) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchPress (UnityEngine.EventSystems.PointerEventData pointerEvent, System.Boolean pressed, System.Boolean released) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchEvents () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.Process () [0x00000] in <00000000000000000000000000000000>:0 
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: currently not available on il2cpp Line: -1)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

TakePhoto()0
CameraCapture:TakePhoto()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

UnauthorizedAccessException: Access to the path "/private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data0.png" is denied.
  at System.IO.FileStream..ctor (System.String path, System.IO.FileMode mode, System.IO.FileAccess access, System.IO.FileShare share, System.Int32 bufferSize, System.Boolean anonymous, System.IO.FileOptions options) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.Create (System.String path, System.Int32 bufferSize) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.WriteAllBytes (System.String path, System.Byte[] bytes) [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.Capture () [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.TakePhoto () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityAction.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityEvent.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1].Invoke (T1 handler, UnityEngine.EventSystems.BaseEventData eventData) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents.Execute[T] (UnityEngine.GameObject target, UnityEngine.EventSystems.BaseEventData eventData, UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1] functor) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchPress (UnityEngine.EventSystems.PointerEventData pointerEvent, System.Boolean pressed, System.Boolean released) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchEvents () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.Process () [0x00000] in <00000000000000000000000000000000>:0 
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: currently not available on il2cpp Line: -1)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

TakePhoto()0
CameraCapture:TakePhoto()
UnityEngine.Events.UnityAction:Invoke()
UnityEngine.Events.UnityEvent:Invoke()
UnityEngine.EventSystems.EventFunction`1:Invoke(T1, BaseEventData)
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

UnauthorizedAccessException: Access to the path "/private/var/containers/Bundle/Application/30C66757-76DB-4D45-A0BF-0774F1A5EAC8/yatop12.app/Data0.png" is denied.
  at System.IO.FileStream..ctor (System.String path, System.IO.FileMode mode, System.IO.FileAccess access, System.IO.FileShare share, System.Int32 bufferSize, System.Boolean anonymous, System.IO.FileOptions options) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.Create (System.String path, System.Int32 bufferSize) [0x00000] in <00000000000000000000000000000000>:0 
  at System.IO.File.WriteAllBytes (System.String path, System.Byte[] bytes) [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.Capture () [0x00000] in <00000000000000000000000000000000>:0 
  at CameraCapture.TakePhoto () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityAction.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.Events.UnityEvent.Invoke () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1].Invoke (T1 handler, UnityEngine.EventSystems.BaseEventData eventData) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.ExecuteEvents.Execute[T] (UnityEngine.GameObject target, UnityEngine.EventSystems.BaseEventData eventData, UnityEngine.EventSystems.ExecuteEvents+EventFunction`1[T1] functor) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchPress (UnityEngine.EventSystems.PointerEventData pointerEvent, System.Boolean pressed, System.Boolean released) [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.ProcessTouchEvents () [0x00000] in <00000000000000000000000000000000>:0 
  at UnityEngine.EventSystems.StandaloneInputModule.Process () [0x00000] in <00000000000000000000000000000000>:0 
UnityEngine.EventSystems.ExecuteEvents:Execute(GameObject, BaseEventData, EventFunction`1)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchPress(PointerEventData, Boolean, Boolean)
UnityEngine.EventSystems.StandaloneInputModule:ProcessTouchEvents()
UnityEngine.EventSystems.StandaloneInputModule:Process()
 
(Filename: currently not available on il2cpp Line: -1)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

Camera UISelectCamera (UnityEngine.GameObject)
ObjectTouchEvent:ClickDetect()
 
(Filename: ./Runtime/Export/Debug/Debug.bindings.h Line: 39)

-> applicationWillResignActive()
-> applicationDidEnterBackground()

```
