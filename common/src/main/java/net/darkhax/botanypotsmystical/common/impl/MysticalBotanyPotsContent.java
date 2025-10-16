package net.darkhax.botanypotsmystical.common.impl;

import net.darkhax.bookshelf.common.api.registry.ContentProvider;
import net.darkhax.bookshelf.common.api.registry.adapters.GameRegistryAdapter;
import net.darkhax.botanypotsmystical.common.impl.data.recipe.crop.MysticalCrop;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class MysticalBotanyPotsContent implements ContentProvider {

    @Override
    public String namespace() {
        return BotanyPotsMysticalMod.MOD_ID;
    }

    @Override
    public void defineRecipeSerializers(GameRegistryAdapter<RecipeSerializer<?>> registry) {
        registry.add("mystical_crop", MysticalCrop.SERIALIZER);
    }
}