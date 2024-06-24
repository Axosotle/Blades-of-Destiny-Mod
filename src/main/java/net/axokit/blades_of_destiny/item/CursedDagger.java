package net.axokit.blades_of_destiny.item;

import net.minecraft.world.effect.MobEffect;
import net.axokit.blades_of_destiny.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CursedDagger extends SwordItem {
    public CursedDagger(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 5), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
