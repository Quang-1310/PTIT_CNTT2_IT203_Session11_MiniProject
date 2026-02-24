package com.arenaofheroes.model;

import com.arenaofheroes.model.GameCharacter;
import com.arenaofheroes.service.ISkill;

public class Support extends GameCharacter implements ISkill {
    private int mana;

    public Support(String name, int hp, int attackPower,int mana) {
        super(name, hp, attackPower);
        this.mana = mana;
    }

    @Override
    public void attack(GameCharacter target) {
        if(!super.check(target)){
            return;
        }
        int attackPower = super.getAttackPower();
        if(this.mana >= 5){
            target.takeDamage(attackPower);
            this.mana -= 5;
        }else{
            target.takeDamage(attackPower / 2);
        }
        System.out.printf("[Ho Tro] %s tấn công %s!\n",super.getName(),target.getName());
        System.out.printf("-> %s mất %d máu, HP còn : %d\n",target.getName(),attackPower,target.getHp());
    }

    @Override
    public void useUltimate(GameCharacter target) {

        if (this.mana < 50) {
            System.out.printf("%s Không đủ mana!\n", super.getName());
            return;
        }

        this.mana -= 50;
        int oldHp = super.getHp();
        super.setHp(oldHp + 36);

        System.out.printf("[Hỗ Trợ] %s sử dụng chiêu cuối!\n", super.getName());
        System.out.printf("-> %s hồi phục 36 máu (HP: %d → %d)\n", super.getName(), oldHp, super.getHp());
    }
}