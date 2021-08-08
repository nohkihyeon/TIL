/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * PizzaTest.java: 피자 응용 테스트 프로그램
 * 팩토리 메소드 패턴: 테스트 프로그램
 */
public class PizzaTest {
	public static void orderPizza(PizzaStore pizzaStore) {
		pizzaStore.orderPizza("치즈");
		pizzaStore.orderPizza("포테이토");
	}
	public static void main(String[] args) {
		orderPizza(new NYPizzaStore());
		System.out.println();
		orderPizza(new ChicagoPizzaStore());
	}
}
