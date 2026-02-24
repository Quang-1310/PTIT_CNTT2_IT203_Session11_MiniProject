package com.arenaofheroes.model;

public abstract class GameCharacter {
    private String name;
    private int hp;
    private int attackPower;
    public static int count;

    public GameCharacter(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public abstract void attack(GameCharacter target);

    public void takeDamage(int amount){
        this.hp -= amount;
        if(this.hp<=0){
            this.hp=0;
        }
    }

    public void displayInfo(){
        System.out.printf("|Name : %-10s | HP : %-5d |\n",name,hp);
    }

    public boolean check(GameCharacter target){
        if(target == null){
            return false;
        }
        if(hp <= 0){
            System.out.printf("%s đã bị hạ gục, không thể tấn công!\n", name);
            return false;
        }
        return true;
    }

    public void useUltimate(GameCharacter character) {
    }
}
