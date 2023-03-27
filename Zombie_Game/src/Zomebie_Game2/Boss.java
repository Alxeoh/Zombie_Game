package Zomebie_Game2;

public class Boss extends Zombie {
	private int shield;

	public Boss(String name, int pos, int maxAtt, int maxHp, int hp) {
		super(name, pos, maxAtt, maxHp, hp);
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getShield() {
		return this.shield;
	}

	public void fight(Unit hero) {
		int power = ran.nextInt(super.getMaxAtt() + 1);
		int skill = ran.nextInt(8);
		if (skill == 1) {
			this.shield += 30;
			System.out.printf("[좀비여왕 쉴드스킬 발동!] -> [좀비여왕 hp : %d/%d][쉴드 : %d]\n", super.getHp(), super.getMaxHp(),
					this.shield);
		} else if (skill == 2) {
			System.out.println("[좀비여왕 강타스킬 발동!] -> [공격력 2배]");
			power *= 2;
		}
		if (hero.getHp() - power <= 0) {
			hero.setHp(0);
		} else {
			hero.setHp(hero.getHp() - power);
		}
		System.out.printf("[좀비여왕 공격! : %d] -> [%s hp : %d/%d]\n", power, hero.getName(), hero.getHp(), hero.getMaxHp());
	}
}
