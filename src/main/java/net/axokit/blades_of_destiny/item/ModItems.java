package net.axokit.blades_of_destiny.item;

import net.axokit.blades_of_destiny.BODMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BODMod.MOD_ID);


    public static final RegistryObject<Item> CURSED_DAGGER = ITEMS.register("cursed_dagger",
            () -> new SwordItem(Tiers.WOOD, 1000, -2, new Item.Properties()));


    public static final RegistryObject<Item> CURSED_SHARD = ITEMS.register("cursed_shard",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus);
    }
}
