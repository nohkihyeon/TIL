# txt 파일 파싱 후 중복 count

## BufferedReaer
```java
BufferedReader br = new BufferedReader(
            new FileReader("C:\\Users\\hae85\\Downloads\\yongin40_before_41.txt")
        );
```
##  Collections.frequency
```java
Set<String> set = new HashSet<String>(list);
Set<String> set = new HashSet<String>(list);
        for(String str : set){
            sb.append(str + " : " + Collections.frequency(list, str)).append("\n");
            }
```

## result.txt에 파일 쓰기
```java
File resultFile = new File("C:\\Users\\hae85\\Downloads\\result.txt");\

try{
  BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));
  for(String str : set){
      writer.write(str + " , " + Collections.frequency(list, str) + "\n");
      c += Collections.frequency(list, str);
      }
           
  writer.close();
  }
  catch(IOException e) {
  e.printStackTrace();
}

```
## 소스코드
```java
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.*;
import java.io.BufferedWriter;

public class HelloWorld{
    public static void main(String[] argc) throws IOException{
        int c=0;
        File resultFile = new File("C:\\Users\\hae85\\Downloads\\result.txt");
        BufferedReader br = new BufferedReader(
            new FileReader("C:\\Users\\hae85\\Downloads\\yongin40_before_41.txt")
        );
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<String>();
        
        String line = "";
        while((line = br.readLine())!= null){
            line = line.substring(2, 10) + ", " + line.substring(19, 22);
            list.add(line);
        }
        br.close();

        Set<String> set = new HashSet<String>(list);
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));
            for(String str : set){
                writer.write(str + " , " + Collections.frequency(list, str) + "\n");
                c += Collections.frequency(list, str);
            }
           
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("all count : "+ c);
    }
}
```
