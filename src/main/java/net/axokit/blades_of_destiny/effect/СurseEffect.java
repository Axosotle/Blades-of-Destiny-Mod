package net.axokit.blades_of_destiny.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class СurseEffect extends MobEffect {

    private static final int MAX_HEART_BLOCKS = 5;
    private static final int TICKS_PER_MINUTE = 1200;
    private static final int DURATION = 6000;

    protected СurseEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }



    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int amplifier) {

        if (pLivingEntity.level().getGameTime() % TICKS_PER_MINUTE == 0 && amplifier < MAX_HEART_BLOCKS - 1) {
            pLivingEntity.addEffect(new MobEffectInstance(this, DURATION, amplifier + 1));
        }

        int heartsToBlock = Math.min(amplifier + 1, MAX_HEART_BLOCKS);
        pLivingEntity.setHealth(Math.max(pLivingEntity.getHealth() - heartsToBlock, 1.0f));

    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, net.minecraft.world.entity.ai.attributes.AttributeMap attributeMap, int amplifier) {
        new Thread(()-> {
            try {
                for (int i = 0; i < amplifier + 1; i++) {
                    Thread.sleep(60000);
                    entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + 1.0F));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
