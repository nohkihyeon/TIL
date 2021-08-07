import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * Hero.java: 추상 팩토리의 클라이언트
 */
public class Hero {
	private int strength;
	private Weapon weapon;
	private Armor armor;
	public Hero(HeroBearingFactory bearingFactory) {
		weapon = bearingFactory.createWeapon();
		armor = bearingFactory.createArmor();
		strength = determineStat();
	}
	private int determineStat() {
		int[] dices = new int[4];
		for(int i=0; i<dices.length; i++)
			dices[i] = ThreadLocalRandom.current().nextInt(6)+1;
		Arrays.sort(dices);
		int sum = 0;
		for(int i=1; i<dices.length; i++)
			sum += dices[i];
		return sum;
	}
	public int getStrength() {
		return strength;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public Armor getArmor() {
		return armor;
	}
	
}
