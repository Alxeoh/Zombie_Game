package Zomebie_Game2;

public class Hero extends Unit {
	private int potionCnt;

	public Hero(String name, int pos, int maxAtt, int maxHp, int hp) {
		super(name, pos, maxAtt, maxHp, hp);
		this.potionCnt = 3;
	}

	public int getPotion() {
		return this.potionCnt;
	}

	public void setPotion(int potion) {
		this.potionCnt = potion;
	}

	public void fight(Unit zombie) {
		int power = ran.nextInt(super.getMaxAtt()) + 1;
		if (zombie instanceof Boss) {
			Boss temp = (Boss) zombie;
			if (temp.getShield() > 0) {
				if (temp.getShield() - power > 0) {
					temp.setShield(temp.getShield() - power);
				} else {
					if (temp.getHp() - (power - temp.getShield()) > 0) {
						temp.setHp(temp.getHp() - (power - temp.getShield()));
						temp.setShield(0);
					} else {
						temp.setHp(0);
					}
				}
			} else {
				if (temp.getHp() - power <= 0) {
					temp.setHp(0);
				} else {
					temp.setHp(temp.getHp() - power);
				}
			}
			System.out.printf("[%s님의 공격 : %d] -> [좀비여왕 hp : %d/%d][쉴드 : %d]\n", super.getName(), power, temp.getHp(),
					temp.getMaxHp(), temp.getShield());
		} else {
			if (zombie.getHp() - power <= 0) {
				zombie.setHp(0);
			} else {
				zombie.setHp(zombie.getHp() - power);
			}
			System.out.printf("[%s님의 공격 : %d] -> [좀비박쥐 hp : %d/%d]\n", super.getName(), power, zombie.getHp(),
					zombie.getMaxHp());
		}
	}

	public void potion() {
		if (this.potionCnt > 0) {
			if (super.getHp() + 100 >= super.getMaxHp()) {
				super.setHp(super.getMaxHp());
			} else {
				super.setHp(super.getHp() + 100);
			}
			System.out.printf("포션을 사용했습니다.\n[%s hp : %d/%d]\n", super.getName(), super.getHp(), super.getMaxHp());
			this.potionCnt--;
		} else {
			System.out.println("포션이 없습니다.");
		}
	}
}
