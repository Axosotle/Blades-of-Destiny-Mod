package net.axokit.blades_of_destiny.effect;

import net.axokit.blades_of_destiny.BODMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BODMod.MOD_ID);

    public static final RegistryObject<MobEffect> CURSE = MOB_EFFECTS.register("curse",
            () -> new СurseEffect(MobEffectCategory.HARMFUL,0xff0000));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
