import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TheaterCompanyTest {
	@Test
	void theaterBasicTest() {
		Invoice sangjin = new Invoice("김상진");
		sangjin.addPerformance(new Performance(new Play("햄릿", PlayType.TRAGEDY), 55));
		sangjin.addPerformance(new Performance(new Play("좋으실대로 하세요", PlayType.COMEDY), 35));
		sangjin.addPerformance(new Performance(new Play("오셀로", PlayType.TRAGEDY), 40));
		String expectedResult = 
			"고객 김상진님의 영수증:\n"+
			"\t햄릿\t6,500,000원 (관중수: 55)\n" +
			"\t좋으실대로 하세요\t5,800,000원 (관중수: 35)\n" +
			"\t오셀로\t5,000,000원 (관중수: 40)\n" +
			"총금액: 17,300,000원\n" +
			"적립포인트: 47점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
}
