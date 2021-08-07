/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * HeroTest.java: 테스트 프로그램
 */
public class HeroTest {
	public static void fight(Hero attacker, Hero defender) {
	}
	public static void main(String[] args) {
		Hero hero1 = new Hero(new ArcherBearingFactory());
		Hero hero2 = new Hero(new PaladinBearingFactory());
		Hero hero3 = new Hero(new ThiefBearingFactory());
		fight(hero1, hero2);
		fight(hero2, hero3);
	}

}
