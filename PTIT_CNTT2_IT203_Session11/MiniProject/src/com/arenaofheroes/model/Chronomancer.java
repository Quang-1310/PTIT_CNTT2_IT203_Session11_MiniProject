package com.arenaofheroes.model;

import com.arenaofheroes.service.ISkill;

public class Chronomancer extends GameCharacter implements ISkill {

    private int timeEnergy;

    public Chronomancer(String name, int hp, int attackPower, int timeEnergy) {
        super(name, hp, attackPower);
        this.timeEnergy = timeEnergy;
    }

    @Override
    public void attack(GameCharacter target) {
        if (this.timeEnergy >= 30) {
            target.takeDamage(super.getAttackPower() + 20);
            this.timeEnergy -= 30;

            System.out.println(super.getName() + " làm chậm thời gian của mục tiêu!");
        } else {
            target.takeDamage(super.getAttackPower());
        }
    }

    @Override
    public void useUltimate(GameCharacter target) {
        if (this.timeEnergy >= 120) {

            int damage = super.getAttackPower() * 4;
            target.takeDamage(damage);

            int heal = damage * 30 / 100;
            this.setHp(super.getHp() + heal);

            this.timeEnergy -= 120;

            System.out.println("Đóng băng thời gian! Gây " + damage + " damage và hồi " + heal + " HP.");
        } else {
            System.out.println("Không đủ Time Energy để dùng chiêu cuối!");
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("|Name : %-12s | HP : %-5d | TimeEnergy : %d |\n",
                super.getName(),
                super.getHp(),
                this.timeEnergy);
    }
}
