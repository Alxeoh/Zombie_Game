package Zomebie_Game2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	ArrayList<String> map = new ArrayList<>();
	private final int MAP_SIZE = 20;
	private final String ROAD = "road";
	private final String ZOMBIEBAT = "좀비박쥐";
	private final String ZOMBIEQUEEN = "좀비여왕";
	private final String DEATH = "죽음";
	private String heroName;
	private boolean exit;
	private boolean quit;
	private int heroPos;
	private Random ran = new Random();

	Scanner sc = new Scanner(System.in);
	Random r = new Random();
//	int pos, int att, int maxHp, int hp
	Hero h = new Hero(playerName(), 0, 30, 200, 200);
	Zombie z = new Zombie(ZOMBIEBAT, 4, 10, 200, 200);
	Boss b = new Boss(ZOMBIEQUEEN, 9, 20, 300, 300);

	private String playerName() {
		System.out.print("히어로의 이름을 입력하세요 : ");
		String name = sc.next();
		this.heroName = name;
		return name;
	}

	private void init() {
		this.exit = true;
		this.quit = true;
		for (int i = 0; i < MAP_SIZE; i++) {
			map.add(ROAD);
		}
		map.set(0, heroName);
		map.set(4, ZOMBIEBAT);
		map.set(14, ZOMBIEBAT);
		map.set(9, ZOMBIEQUEEN);
		map.set(19, ZOMBIEQUEEN);
	}

	private void printMap() {
		this.heroPos = h.getPos();
		map.set(this.heroPos, this.heroName);
		if (this.heroPos < 5) {
			for (int i = 0; i < 5; i++) {
				if (map.get(i).equals(ROAD)) {
					System.out.print("🕳️");
				} else if (map.get(i).equals(ZOMBIEBAT)) {
					System.out.print("🦇️");
				} else if (map.get(i).equals(ZOMBIEQUEEN)) {
					System.out.print("🧟‍♀️️");
				} else if (map.get(i).equals(DEATH)) {
					System.out.print("💀️");
				} else if (map.get(i).equals(heroName)) {
					System.out.print("🤺️");
				}
			}
		} else if (this.heroPos >= 5 && this.heroPos < 10) {
			for (int i = 5; i < 10; i++) {
				if (map.get(i).equals(ROAD)) {
					System.out.print("🕳️");
				} else if (map.get(i).equals(ZOMBIEBAT)) {
					System.out.print("🦇️");
				} else if (map.get(i).equals(ZOMBIEQUEEN)) {
					System.out.print("🧟‍♀️️");
				} else if (map.get(i).equals(DEATH)) {
					System.out.print("💀️");
				} else if (map.get(i).equals(heroName)) {
					System.out.print("🤺️");
				}
			}
		} else if (this.heroPos >= 10 && this.heroPos < 15) {
			for (int i = 10; i < 15; i++) {
				if (map.get(i).equals(ROAD)) {
					System.out.print("🕳️");
				} else if (map.get(i).equals(ZOMBIEBAT)) {
					System.out.print("🦇️");
				} else if (map.get(i).equals(ZOMBIEQUEEN)) {
					System.out.print("🧟‍♀️️");
				} else if (map.get(i).equals(DEATH)) {
					System.out.print("💀️");
				} else if (map.get(i).equals(heroName)) {
					System.out.print("🤺️");
				}
			}
		} else if (this.heroPos >= 15 && this.heroPos < 20) {
			for (int i = 15; i < 20; i++) {
				if (map.get(i).equals(ROAD)) {
					System.out.print("🕳️");
				} else if (map.get(i).equals(ZOMBIEBAT)) {
					System.out.print("🦇️");
				} else if (map.get(i).equals(ZOMBIEQUEEN)) {
					System.out.print("🧟‍♀️️");
				} else if (map.get(i).equals(DEATH)) {
					System.out.print("🏆️");
				} else if (map.get(i).equals(heroName)) {
					System.out.print("🤺️");
				}
			}
		}
	}

	private void compensation() {
		int r = this.ran.nextInt(2);
		if (r == 1) {
			h.setPotion(h.getPotion() + 1);
			System.out.printf("[포션획득]\n현재 포션 : %d개\n", h.getPotion());
		}
		h.setPos(h.getPos() + 1);
		map.set(h.getPos() - 1, ROAD);
	}

	private boolean result(Unit zombie) {
		boolean check = true;
		if (h.getHp() > 0) {
			if (zombie.getHp() == 0) {
				System.out.printf("[%s 처치성공!]\n",zombie.getName());
				if(zombie.getName().equals(ZOMBIEBAT)) {
					z.setHp(z.getMaxHp());
				} else {
					b.setHp(b.getMaxHp());
				}
				map.set(this.heroPos + 1, DEATH);
				check = false;
				if (this.heroPos == 18) {
					printMap();
					System.out.println("\nALL CLEAR-🏆");
					this.exit = false;
					check = false;
				}
			}
		} else {
			System.out.printf("[히어로 '%s' 사망..]\n", h.getName());
			this.exit = false;
			check = false;
		}
		return check;
	}

	private void playHero() {
		System.out.println("방향키(A / D)\n (A는 왼쪽 D는 오른쪽)");
		String dir = sc.next();
		if ((dir.equals("a") || dir.equals("A")) && heroPos > 0) {
			h.setPos(h.getPos() - 1);
		} else if (dir.equals("d") || dir.equals("D") && heroPos < 19) {
			if (map.get(h.getPos() + 1).equals(this.ZOMBIEBAT)) {
				System.out.println("좀비박쥐가 나타났다!");
				while(true) {
					System.out.println("[1.공격하기][2.hp포션사용]");
				String sel = sc.next();
				if (sel.equals("1")) {
						h.fight(z);
						boolean check = result(z);
						if(check) {
							z.fight(h);
						}else {
							break;
						}
				} else if (sel.equals("2")) {
					h.potion();
				} else {
					System.out.println("없는 메뉴입니다.");
				}
				}
			} else if (map.get(h.getPos() + 1).equals(this.ZOMBIEQUEEN)) {
				System.out.println("좀비여왕이 나타났다!");
				while(true) {
				System.out.println("[1.공격하기][2.hp포션사용]");
				String sel = sc.next();
				if (sel.equals("1")) {
						h.fight(b);
						boolean check = result(b);
						if(check) {
							b.fight(h);
						}else {
							break;
						}
				} else if (sel.equals("2")) {
					h.potion();
				} else {
					System.out.println("없는 메뉴입니다.");
				}
				}
			} else if (map.get(h.getPos() + 1).equals(DEATH)) {
				compensation();
			} else {
				h.setPos(h.getPos() + 1);
				map.set(h.getPos() - 1, ROAD);
			}
		} else {
			System.out.println("방향키를 다시 확인하세요.");
		}
	}

	void run() {
		init();
		while (this.exit) {
			printMap();
			playHero();
		}

	}
}
