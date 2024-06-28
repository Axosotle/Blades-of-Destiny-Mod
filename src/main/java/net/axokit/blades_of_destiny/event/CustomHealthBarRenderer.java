package net.axokit.blades_of_destiny.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.axokit.blades_of_destiny.BODMod;
import net.axokit.blades_of_destiny.effect.ModEffects;

@Mod.EventBusSubscriber(modid = BODMod.MOD_ID, value = Dist.CLIENT)
public class CustomHealthBarRenderer extends Gui {

    private static final ResourceLocation CUSTOM_HEALTH_BAR = new ResourceLocation(BODMod.MOD_ID, "textures/gui/custom_health_bar.png");
    private static final ResourceLocation VANILLA_HEALTH_BAR = new ResourceLocation("minecraft", "textures/gui/icons.png");

    public CustomHealthBarRenderer(Minecraft pMinecraft, ItemRenderer pItemRenderer) {
        super(pMinecraft, pItemRenderer);
    }

    @SubscribeEvent
    public static void onRenderHealthBar(RenderGuiOverlayEvent.Pre event) {
        String overlayId = event.getOverlay().id().toString();
        String expectedId = "minecraft:player_health";
        // Выводим идентификатор слоя и его побайтовое представление
        if (overlayId.equals(expectedId)) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player != null && player.hasEffect(ModEffects.CURSE.get())) {
                MobEffectInstance effect = player.getEffect(ModEffects.CURSE.get());
                if (effect != null) {
                    event.setCanceled(true);
                    renderCustomHealthBar(event.getGuiGraphics(), player, effect.getAmplifier());
                }
            }
        }
    }

    @Override
    protected void renderHearts(GuiGraphics pGuiGraphics, Player pPlayer, int pX, int pY, int pHeight, int pOffsetHeartIndex, float pMaxHealth, int pCurrentHealth, int pDisplayHealth, int pAbsorptionAmount, boolean pRenderHighlight) {
        if(pPlayer.hasEffect(ModEffects.CURSE.get())) {
            MobEffectInstance effect = pPlayer.getEffect(ModEffects.CURSE.get());
            renderCustomHealthBar(pGuiGraphics, pPlayer, effect.getAmplifier());
        }
        else
        {
            super.renderHearts(pGuiGraphics, pPlayer, pX, pY, pHeight, pOffsetHeartIndex, pMaxHealth, pCurrentHealth, pDisplayHealth, pAbsorptionAmount, pRenderHighlight);
        }
    }

    private static void renderCustomHealthBar(GuiGraphics guiGraphics, Player player, int amplifier) {
        Minecraft mc = Minecraft.getInstance();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        int health = (int) Math.ceil(player.getHealth());
        int maxHealth = (int) player.getMaxHealth();
        int cursedHealth = maxHealth - (amplifier + 1);

        for (int i = 0; i < maxHealth / 2; i++) {
            int x = width / 2 - 91 + (i % 10) * 8;
            int y = height - 39 + (i / 10) * 10;

            // Рисование заблокированных сердец с кастомной текстурой
            if (i >= cursedHealth / 2) {
                guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 16, 0, 9, 9);
            } else {
                guiGraphics.blit(VANILLA_HEALTH_BAR, x, y, 16, 0, 9, 9);
            }

            // Рисование текущих сердец
            if (i < health / 2) {
                if (i >= cursedHealth / 2) {
                    guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 52, 0, 9, 9); // Полное сердце с кастомной текстурой
                } else {
                    guiGraphics.blit(VANILLA_HEALTH_BAR, x, y, 52, 0, 9, 9); // Полное сердце со стандартной текстурой
                }
            } else if (i == health / 2 && health % 2 == 1) {
                if (i >= cursedHealth / 2) {
                    guiGraphics.blit(CUSTOM_HEALTH_BAR, x, y, 61, 0, 9, 9); // Половина сердца с кастомной текстурой
                } else {
                    guiGraphics.blit(VANILLA_HEALTH_BAR, x, y, 61, 0, 9, 9); // Половина сердца со стандартной текстурой
                }
            }
        }
    }

}
