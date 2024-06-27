package net.axokit.blades_of_destiny.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.axokit.blades_of_destiny.BODMod;
import net.axokit.blades_of_destiny.effect.ModEffects;

@Mod.EventBusSubscriber(modid = BODMod.MOD_ID, value = Dist.CLIENT)
public class CustomHealthBarRenderer {

    private static final ResourceLocation CUSTOM_HEALTH_BAR = new ResourceLocation(BODMod.MOD_ID, "textures/gui/custom_health_bar.png");

    @SubscribeEvent
    public static void onRenderHealthBar(RenderGuiOverlayEvent.Post event) {
        String overlayId = event.getOverlay().id().toString();
        String expectedId = "minecraft:player_health";

        if (overlayId.equals(expectedId)) {
            Minecraft mc = Minecraft.getInstance();
            LivingEntity player = mc.player;

            if (player != null && player.hasEffect(ModEffects.CURSE.get())) {
                MobEffectInstance effect = player.getEffect(ModEffects.CURSE.get());
                if (effect != null) {
                    renderCursedHealthBarOverlay(event.getGuiGraphics(), player, effect.getAmplifier());
                }
            }
        }
    }

    private static void renderCursedHealthBarOverlay(GuiGraphics guiGraphics, LivingEntity player, int amplifier) {
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        int health = (int) Math.ceil(player.getHealth());
        int maxHealth = (int) player.getMaxHealth();
        int cursedHealth = maxHealth - (amplifier + 1);

        mc.getTextureManager().bindForSetup(CUSTOM_HEALTH_BAR);

        for (int i = 0; i < maxHealth / 2; i++) {
            int x = width / 2 - 91 + (i % 10) * 8;
            int y = height - 39 + (i / 10) * 10;

            // Рисование заблокированных сердец с кастомной текстурой
            if (i >= cursedHealth / 2) {
                guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 16, 0, 9, 9);
            }

            // Рисование текущих сердец
            if (i < health / 2) {
                if (i >= cursedHealth / 2) {
                    guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 52, 0, 9, 9); // Полное сердце с кастомной текстурой
                }
            } else if (i == health / 2 && health % 2 == 1) {
                if (i >= cursedHealth / 2) {
                    guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 61, 0, 9, 9); // Половина сердца с кастомной текстурой
                }
            }
        }
    }
}