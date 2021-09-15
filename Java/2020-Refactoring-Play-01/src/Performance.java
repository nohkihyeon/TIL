/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * Performance 클래스: 공연 정보 
 * @author 김상진 
 */
public class Performance {
	private Play play;			// 공연
	private int audienceSize;	// 관객수
	public Performance(Play play, int audienceSize) {
		this.play = play;
		this.audienceSize = audienceSize;
	}
	public Play getPlay() {
		return play;
	}
	public int getAudienceSize() {
		return audienceSize;
	}
}
