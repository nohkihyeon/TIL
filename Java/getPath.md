- 11번가 API로 xml 형태 full path 경로 

```java
import lombok.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args)  {
        HashMap<String, St11Category> map = new HashMap<>();
        map.put("111", new St11Category("111", "브랜드 패션", "0", "N"));
        map.put("222", new St11Category("222", "브랜드 여성의류", "111", "N"));
        map.put("333", new St11Category("333", "원피스", "222", "Y"));
        map.put("444", new St11Category("444", "블라우스", "222", "Y"));
        map.put("555", new St11Category("555", "티셔츠", "222", "Y"));


        for(String keys : map.keySet()) {
            if(map.get(keys).leafYn.equals("Y")){
                System.out.println(getFullPath(keys, map));
            }
        }
    }

    public static String getFullPath(String keys, HashMap<String, St11Category> map) {
        String path = map.get(keys).goodsNm;
        keys = map.get(keys).parentNode;
        while(true){
            String preStr = map.get(keys).goodsNm;
            path = preStr + " > " + path;
            keys = map.get(keys).parentNode;
            if(map.get(keys).parentNode.equals("0")) {
                path = map.get(keys).goodsNm + " > " + path;
                break;
            }
        }
        return path;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class St11Category {
        String goodsNo;
        String goodsNm;
        String parentNode;
        String leafYn;
    }
}
```

```java
import lombok.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args)  {
        HashMap<String, St11Category> map = new HashMap<>();
        map.put("111", new St11Category("111", "브랜드 패션", "0", "N"));
        map.put("222", new St11Category("222", "브랜드 여성의류", "111", "N"));
        map.put("333", new St11Category("333", "원피스", "222", "Y"));
        map.put("444", new St11Category("444", "블라우스", "222", "Y"));
        map.put("555", new St11Category("555", "티셔츠", "222", "Y"));
        map.put("1", new St11Category("1", "11번가", "0", "N"));
        map.put("2", new St11Category("2", "브랜드 파워", "1", "N"));
        map.put("3", new St11Category("3", "브랜드 패", "2", "N"));
        map.put("4", new St11Category("4", "브랜드 여성의류", "3", "N"));
        map.put("5", new St11Category("5", "블라우저", "4", "Y"));


        for(String keys : map.keySet()) {
            if(map.get(keys).leafYn.equals("Y")){
                System.out.println(getFullPath(keys, map));
            }
        }
    }

    public static String getFullPath(String keys, HashMap<String, St11Category> map) {
        String path = map.get(keys).goodsNm;
        keys = map.get(keys).parentNode;
        while(true){
            String preStr = map.get(keys).goodsNm;
            path = preStr + " > " + path;
            keys = map.get(keys).parentNode;
            if(map.get(keys).parentNode.equals("0")) {
                path = map.get(keys).goodsNm + " > " + path;
                break;
            }
        }
        return path;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class St11Category {
        String goodsNo;
        String goodsNm;
        String parentNode;
        String leafYn;
    }
}
```
