package net.darkhax.botanypotsmystical.common.impl;

import net.darkhax.bookshelf.common.api.registry.IContentProvider;
import net.darkhax.bookshelf.common.api.registry.register.Register;
import net.darkhax.botanypotsmystical.common.impl.data.recipe.crop.MysticalCrop;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class MysticalBotanyPotsContent implements IContentProvider {

    @Override
    public String contentNamespace() {
        return BotanyPotsMysticalMod.MOD_ID;
    }

    @Override
    public void registerRecipeSerializers(Register<RecipeSerializer<?>> registry) {
        registry.add("mystical_crop", MysticalCrop.SERIALIZER);
    }
}