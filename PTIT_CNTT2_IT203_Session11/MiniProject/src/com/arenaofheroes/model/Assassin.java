package com.arenaofheroes.model;

import com.arenaofheroes.model.GameCharacter;
import com.arenaofheroes.service.ISkill;

public class Assassin extends GameCharacter implements ISkill {
    private int energy;

    public Assassin(String name, int hp, int attackPower, int energy) {
        super(name, hp, attackPower);
        this.energy = energy;
    }

    @Override
    public void attack(GameCharacter target) {
        if (this.energy >= 50){
            target.takeDamage(super.getAttackPower());
            energy -= 50;
        } else {
            target.takeDamage(super.getAttackPower()/2);
        }
    }

    @Override
    public void useUltimate(GameCharacter target) {
        if (this.energy >= 100){
            if (target.getHp() <= 50) {
                int damage = target.getHp();
                target.takeDamage(damage);
                this.energy -= 100;
                System.out.println("One shot one kill !");
                this.setHp(this.getHp() + damage);
            } else {
                target.takeDamage(super.getAttackPower() * 3);
                this.energy -= 100;
                System.out.println("Chưa đủ điều kiện kết liễu, gây sát thương lớn!");
            }
        } else {
            System.out.println("Năng lượng không đủ để dùng chiêu cuối !.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("|Name : %-10s | HP : %-5d | Energy : %d |\n",super.getName(),super.getHp(),this.energy);
    }
}