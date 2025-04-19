package net.darkhax.botanypotsmystical.impl;

import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.botanypotsmystical.common.impl.BotanyPotsMysticalMod;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;

@Mod(BotanyPotsMysticalMod.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod() {
        BotanyPotsMysticalMod.CFG_SECOND_SEED = CachedSupplier.cache(() -> ModConfigs.SECONDARY_SEED_DROPS.get());
        BotanyPotsMysticalMod.CFG_ESSENCE_CHANCE = CachedSupplier.cache(() -> ModConfigs.FERTILIZED_ESSENCE_DROP_CHANCE.get());
        BotanyPotsMysticalMod.FERTILIZED_ESSENCE = CachedSupplier.cache(() -> ModItems.FERTILIZED_ESSENCE.get());
        BotanyPotsMysticalMod.INFERIUM_FARMLAND = CachedSupplier.cache(() -> ModBlocks.INFERIUM_FARMLAND.get());
        BotanyPotsMysticalMod.COMBO = ingredients -> CompoundIngredient.of(ingredients.toArray(Ingredient[]::new));
    }
}