package net.darkhax.botanypotsmystical.common.impl;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class BotanyPotsMysticalMod {

    public static final String MOD_ID = "botanypotsmystical";
    public static final String MOD_NAME = "BotanyPots-Mystical";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static Supplier<Boolean> CFG_SECOND_SEED;
    public static Supplier<Double> CFG_ESSENCE_CHANCE;
    public static Supplier<Item> FERTILIZED_ESSENCE;
    public static Supplier<Block> INFERIUM_FARMLAND;
    public static Function<List<Ingredient>, Ingredient> COMBO;
    
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}