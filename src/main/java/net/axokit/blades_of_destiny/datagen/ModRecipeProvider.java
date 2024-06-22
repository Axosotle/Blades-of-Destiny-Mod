package net.axokit.blades_of_destiny.datagen;


import net.axokit.blades_of_destiny.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CURSED_DAGGER.get())
                .pattern(" SS")
                .pattern(" D ")
                .pattern("FG")
                .define('S', Items.DIAMOND)
                .define('D', ModItems.CURSED_SHARD.get())
                .define('F', Items.BLAZE_ROD)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModItems.CURSED_SHARD.get()), has(ModItems.CURSED_SHARD.get()))
                .save(pWriter);
    }

}
