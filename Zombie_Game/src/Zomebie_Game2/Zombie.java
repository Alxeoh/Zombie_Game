package Zomebie_Game2;

import java.util.Scanner;

public class Zombie extends Unit {
	Scanner scan = new Scanner(System.in);

	public Zombie(String name, int pos, int maxAtt, int maxHp, int hp) {
		super(name, pos, maxAtt, maxHp, hp);
	}

	public void fight(Unit hero) {
		int power = ran.nextInt(super.getMaxAtt() + 1);
		if (hero.getHp() - power <= 0) {
			hero.setHp(0);
		} else {
			hero.setHp(hero.getHp() - power);
		}
		System.out.printf("[좀비박쥐 공격! : %d] -> [%s hp : %d/%d]\n", power, hero.getName(), hero.getHp(), hero.getMaxHp());
		int skill = ran.nextInt(4);
		if (skill == 1) {
			System.out.printf("[좀비박쥐 흡혈스킬 발동!] -> [좀비박쥐 hp : %d + %d/ %d]\n", super.getHp(), power / 2,
					super.getMaxHp());
			if (super.getHp() + power / 2 >= super.getMaxHp()) {
				super.setHp(super.getMaxHp());
			} else {
				super.setHp(super.getHp() + power / 2);
			}
		}
	}

}
