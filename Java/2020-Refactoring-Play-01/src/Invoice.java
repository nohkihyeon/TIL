import java.util.ArrayList;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * 리펙토링
 * Invoice 클래스: 고객 영수증
 * 요청한 연극 공연에 대한 비용 계산
 * 오리지널 버전
 * @author 김상진 
 */
public class Invoice {
	private String customer;
	private ArrayList<Performance> performances = new ArrayList<>();
	public Invoice(String customer){
		this.customer = customer;
	}
	public String getCustomer() {
		return customer;
	}
	public void addPerformance(Performance performance){
		performances.add(performance);
	}
	// 총 공연 비용과 적립 포인트를 계산하여 영수증을 만듦
	// 공연비용: 비극>> 기본 400만원, 관객수>30, 초과된 관객수 x 10만원
	// 공연비용: 희극>> 기본 300만원, 관객수>20, 100만원 + (초과된 관객수) x 5만원, 관객수 x 3만원
	// 적립포인트: 관객수>30이면 그 수가 적립포인트 
	// 적립포인트: 희극이면 추가로 관객수/5만큼 적립 
	// 너무 많은 일을???
	public String statement(){
		int totalAmount = 0;
		int	creditPoints = 0;
		String result = String.format("고객 %s님의 영수증:\n", customer);
		for(Performance performance: performances){
			int thisAmount = 0;
			creditPoints = computeCredit(performance);
			result += String.format("\t%s\t%,d원 (관중수: %d)\n", 
				performance.getPlay().getTitle(), thisAmount, performance.getAudienceSize());
			totalAmount += computeCost(performance);
		}
		result += String.format("총금액: %,d원\n", totalAmount);
		result += String.format("적립포인트: %d점\n", creditPoints);
		return result;
	}
	private int computeCost(Performance performance) {
		int result = 0;
		switch(performance.getPlay().getPlayType()){
		case TRAGEDY:
			result += 4_000_000;
			if(performance.getAudienceSize()>30)
				result += 100_000*(performance.getAudienceSize()-30);
			break;
		case COMEDY:
			result += 3_000_000;
			if(performance.getAudienceSize()>20)
				result += 1_000_000+50_000*(performance.getAudienceSize()-20);
			result += 30_000*performance.getAudienceSize();
			break;
		}
		return result;
	}
	private int computeCredit(Performance performance) {
		int result = Math.max(performance.getAudienceSize()-30, 0);
		if(performance.getPlay().getPlayType()==PlayType.COMEDY) {
			result += performance.getAudienceSize()/5;
		}
		return result;
	}
}