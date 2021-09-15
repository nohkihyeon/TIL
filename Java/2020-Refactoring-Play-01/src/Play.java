/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 리펙토링
 * Play 클래스: 영화정보
 * 연극 분류: 비극, 희극  
 * @author 김상진 
 */
public class Play {
	private String title;		// 연극제목
	private PlayType type;		// 분류코드
	public Play(String title, PlayType type) {
		this.title = title;
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public PlayType getPlayType() {
		return type;
	}
}
