package net.axokit.blades_of_destiny;

import net.axokit.blades_of_destiny.effect.ModEffects;
import net.axokit.blades_of_destiny.event.CustomHealthBarRenderer;
import net.axokit.blades_of_destiny.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BODMod.MOD_ID)
public class BODMod {
    public static final String MOD_ID = "blades_of_destiny";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BODMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация предметов
        ModItems.register(modEventBus);

        // Регистрация эффектов
        ModEffects.register(modEventBus);

        // Регистрация общих настроек
        modEventBus.addListener(this::commonSetup);

        // Регистрация клиентских настроек
        modEventBus.addListener(this::clientSetup);

        // Регистрация событий
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Здесь можно разместить код, который должен выполняться на стороне сервера и клиента
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Регистрация пользовательского рендерера здоровья
        MinecraftForge.EVENT_BUS.register(CustomHealthBarRenderer.class);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Здесь можно разместить код, который должен выполняться при запуске сервера
    }
}
