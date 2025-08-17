package net.darkhax.botanypotsmystical.common.impl;

import net.darkhax.botanypots.common.api.BotanyPotsPlugin;
import net.darkhax.botanypots.common.api.command.generator.crop.CropGenerator;
import net.darkhax.botanypotsmystical.common.impl.command.generator.MysticalCropGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class MysticalBotanyPotsPlugin implements BotanyPotsPlugin {

    @Override
    public void registerCropGenerators(BiConsumer<ResourceLocation, CropGenerator> register) {
        register.accept(BotanyPotsMysticalMod.id("crop"), new MysticalCropGenerator());
    }
}