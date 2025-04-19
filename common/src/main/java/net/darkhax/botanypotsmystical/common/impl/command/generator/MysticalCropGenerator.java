package net.darkhax.botanypotsmystical.common.impl.command.generator;

import com.blakebr0.mysticalagriculture.item.MysticalSeedsItem;
import com.google.gson.JsonObject;
import net.darkhax.botanypots.common.api.command.generator.DataHelper;
import net.darkhax.botanypots.common.api.command.generator.crop.CropGenerator;
import net.darkhax.botanypotsmystical.common.impl.BotanyPotsMysticalMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MysticalCropGenerator implements CropGenerator {

    @Override
    public boolean canGenerateCrop(ServerLevel level, ItemStack stack) {
        return stack.getItem() instanceof MysticalSeedsItem;
    }

    @Override
    public JsonObject generateData(ServerLevel level, ItemStack stack) {
        final MysticalSeedsItem seedItem = ((MysticalSeedsItem) stack.getItem());
        final Block block = seedItem.getCrop().getCropBlock();
        final JsonObject output = new JsonObject();
        output.add("bookshelf:load_conditions", DataHelper.array(DataHelper.requiresBlock(block), DataHelper.requiresItem(stack.getItem())));
        output.addProperty("type", BotanyPotsMysticalMod.id("mystical_crop").toString());
        output.addProperty("crop_id", seedItem.getCrop().getId().toString());
        return output;
    }
}