import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * 추상 팩토리 패턴: Dungeons & Dragons 
 * Weapon.java: 무기
 * 모든 무기를 아우르는 추상 타입
 */
public abstract class Weapon {
	public abstract int damage();
	public abstract int weight();
}

// 창
class Lance extends Weapon{
	@Override public int damage() {
		return ThreadLocalRandom.current().nextInt(12)+1;
	}
	@Override public int weight() {
		return 6;
	}
}

// 단검
class Dagger extends Weapon{
	@Override public int damage() {
		return ThreadLocalRandom.current().nextInt(4)+1;
	}
	@Override public int weight() {
		return 1;
	}
}

// 큰활
class Longbow extends Weapon{
	@Override public int damage() {
		return ThreadLocalRandom.current().nextInt(8)+1;
	}
	@Override public int weight() {
		return 2;
	}
}

// 손도끼
class HandAxe extends Weapon{
	@Override public int damage() {
		return ThreadLocalRandom.current().nextInt(6)+1;
	}
	@Override public int weight() {
		return 2;
	}
}