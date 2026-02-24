package com.arenaofheroes.main;

import com.arenaofheroes.model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Warrior yasuo = new Warrior("Yasuo", 400, 50, 20);
        Assassin talon = new Assassin("Talon", 300, 50, 100);
        Support threst = new Support("Threst", 500, 30, 100);
        Mage veigar = new Mage("Veigar", 300, 100, 100);
        Chronomancer huyHoan =  new Chronomancer("Hoan", 300, 100, 100);
        GameCharacter goblin = new GameCharacter("Goblin", 100, 10) {
            @Override
            public void attack(GameCharacter target) {
                System.out.println("[Quái vật] Goblin tấn công!");
                target.takeDamage(10);
                System.out.printf("-> Goblin cắn trộm %s gây 10 sát thương!\n", target.getName());
            }
        };

        GameCharacter[] characters = {yasuo, veigar, goblin, talon, threst, huyHoan};
        int currentSize = characters.length;

        System.out.println("=== ARENA OF HEROES ===");

        while (currentSize > 1) {
            int attackerIdx = (int) (Math.random() * currentSize);
            int victimIdx;

            do {
                victimIdx = (int) (Math.random() * currentSize);
            } while (victimIdx == attackerIdx);

            System.out.println("\n--- LƯỢT ĐẤU ---");

            // Tỉ lệ dùng chiêu (phải để trong vòng lặp mới random mỗi lượt được)
            int randomSkill = (int) (Math.random() * 2);
            if (randomSkill == 0) {
                characters[attackerIdx].attack(characters[victimIdx]);
            } else {
                if (characters[attackerIdx] instanceof Mage mage) {
                    characters[attackerIdx].useUltimate(characters[victimIdx]);
                } else if (characters[attackerIdx] instanceof Warrior warrior) {
                    warrior.useUltimate(characters[victimIdx]);
                } else {
                    characters[attackerIdx].attack(characters[victimIdx]);
                }

            }

            // KIỂM TRA NẾU TƯỚNG BỊ HẠ GỤC
            if (characters[victimIdx].getHp() <= 0) {
                System.out.printf("!!! [%s] ĐÃ BỊ LOẠI !!!\n", characters[victimIdx].getName());

                for (int i = victimIdx; i < currentSize - 1; i++) {
                    characters[i] = characters[i + 1];
                }

                currentSize--;
                characters[currentSize] = null;
            }

            System.out.println("============ THÔNG SỐ HIỆN TẠI ===========");
            for (int i = 0; i < currentSize; i++) {
                characters[i].displayInfo();
            }
            System.out.println("------------------------------------------");
        }

        System.out.println("\nTRẬN ĐẤU KẾT THÚC! NGƯỜI THẮNG CUỐI CÙNG: " + characters[0].getName());

        System.out.println("\n================================================");
        System.out.println("TRẬN ĐẤU KẾT THÚC!");
        announceWinner(characters);
    }

    // Hàm hiển thị thông tin tất cả các tướng
    static void matchStatistics(GameCharacter[] characters) {
        for (GameCharacter character : characters) {
            character.displayInfo();
        }
        System.out.println("------------------------------------------------");
    }

    // Hàm thông báo người thắng cuộc
    static void announceWinner(GameCharacter[] characters) {
        for (GameCharacter c : characters) {
            if (c.getHp() > 0) {
                System.out.println("NGƯỜI CHIẾN THẮNG CUỐI CÙNG: " + c.getName().toUpperCase());
                return;
            }
        }
    }
}