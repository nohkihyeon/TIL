# GSON
- GSON은 Google JSON의 약자이다. 

## GSON 설치
- [Maven Repository](https://mvnrepository.com) 접속
- GSON 검색
![스크린샷 2021-09-19 오후 5 31 13](https://user-images.githubusercontent.com/65120581/133920843-36747231-d55b-403c-a04d-a2c8db04b3ab.png)
- lib 다운로드 
![스크린샷 2021-09-19 오후 5 33 58](https://user-images.githubusercontent.com/65120581/133920934-5edd341a-ec88-409d-a589-8343f55615e2.png)
- Eclipse > Preferences > Java > Build Path > User Libraries
<img width="527" alt="스크린샷 2021-09-19 오후 5 38 58" src="https://user-images.githubusercontent.com/65120581/133921052-be7fc544-10c4-4121-8c39-006c509ee3fa.png">
- Adding User Library

<img width="576" alt="스크린샷 2021-09-19 오후 5 41 01" src="https://user-images.githubusercontent.com/65120581/133921119-9a5a41b6-0dd9-4e4d-82e1-ca6bbb095248.png">
<img width="588" alt="스크린샷 2021-09-19 오후 5 41 26" src="https://user-images.githubusercontent.com/65120581/133921124-4eddcf12-c482-4d12-9372-2d8ef40c6dd2.png">
<img width="597" alt="스크린샷 2021-09-19 오후 5 41 32" src="https://user-images.githubusercontent.com/65120581/133921126-c7237753-fd9f-48d4-a4d2-b7c6f4923f12.png">





```java
package kakaoAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class kakaoJson {
	public static void main(String[] argc) throws Exception {
		String json = getJsonDoc("맘스터치");
		System.out.println(json);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		String jsonDoc = gson.toJson(getJsonDoc("맘스터치"));
		System.out.println(jsonDoc);
		System.out.println(gson.toJson(getJsonDoc("Eluon")));
		
		File file = new File("/Users/nohgihyeon/git/Java/kakaoAPI/test.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(gson.toJson(getJsonDoc("이루온")));
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		 
	}

	private static String getJsonDoc( String keyword ) throws Exception {
		
		String encodeKeyword = "";  // 한글 주소는 encoding 해서 날려야 함
		try { encodeKeyword = URLEncoder.encode( keyword, "UTF-8" ); } 
		catch ( UnsupportedEncodingException e ) { e.printStackTrace(); }
		
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" 
	                                                                     + encodeKeyword;
		String auth = "KakaoAK " + "1191cf18c7960d3f7bf40f82d831ef30";
		
		URL url = new URL( apiUrl );
	    HttpsURLConnection conn = ( HttpsURLConnection ) url.openConnection();
		conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Authorization", auth );
	    
	    BufferedReader br;

	    int responseCode = conn.getResponseCode();
	    if( responseCode == 200 ) {  // 호출 OK
	    	br = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8") );
	    } else {  // 에러
	    	br = new BufferedReader( new InputStreamReader(conn.getErrorStream(), "UTF-8") );
	    }
	    
	    String jsonString = new String();
	    String stringLine;
	    while ( ( stringLine= br.readLine()) != null ) {
	        jsonString += stringLine;
	    }
	    return jsonString;
	}
}


```
