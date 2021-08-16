/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * InsufficientFundException.java: 잔액부족을 처리하기 위한 예외 
 */
public class InsufficientFundException extends RuntimeException {
	public InsufficientFundException() {
		super("잔액부족");
	}
	public InsufficientFundException(String message) {
		super(message);
	}
}
