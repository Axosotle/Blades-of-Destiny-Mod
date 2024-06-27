package net.axokit.blades_of_destiny.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CurseEffect extends MobEffect {

    protected CurseEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.getHealth() > (pLivingEntity.getMaxHealth() - (pAmplifier + 1))) {
            pLivingEntity.setHealth(pLivingEntity.getMaxHealth() - (pAmplifier + 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int pAmplifier) {
        return duration % 20 == 0;
    }
}