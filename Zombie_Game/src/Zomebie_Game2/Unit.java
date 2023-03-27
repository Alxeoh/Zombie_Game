package Zomebie_Game2;

import java.util.Random;

public class Unit {

	private String name;
	private int maxAtt;
	private int maxHp;
	private int hp;
	private int pos;
	Random ran = new Random();

	public Unit(String name, int pos, int maxAtt, int maxHp, int hp) {
		this.name = name;
		this.pos = pos;
		this.maxAtt = maxAtt;
		this.maxHp = maxHp;
		this.hp = hp;
	}

	public String getName() {
		return this.name;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public int getPos() {
		return this.pos;
	}

	public int getMaxAtt() {
		return this.maxAtt;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}
