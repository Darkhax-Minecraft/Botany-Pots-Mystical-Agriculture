package net.darkhax.botanypotsmystical.common.impl.data.recipe.crop;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.google.gson.JsonParseException;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.service.Services;
import net.darkhax.bookshelf.common.api.util.DataHelper;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProvider;
import net.darkhax.botanypots.common.impl.data.display.types.AgingDisplayState;
import net.darkhax.botanypots.common.impl.data.display.types.BasicOptions;
import net.darkhax.botanypots.common.impl.data.itemdrops.SimpleDropProvider;
import net.darkhax.botanypots.common.impl.data.recipe.crop.BasicCrop;
import net.darkhax.botanypotsmystical.common.impl.BotanyPotsMysticalMod;
import net.darkhax.botanypotsmystical.common.impl.config.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class MysticalCrop extends BasicCrop {

    public static final MapCodec<MysticalCrop> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("crop_id").forGetter(c -> c.mysticalCropId)
    ).apply(instance, MysticalCrop::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, MysticalCrop> STREAM = StreamCodec.of(
            (buf, val) -> {
                buf.writeResourceLocation(val.mysticalCropId);
                Properties.STREAM.encode(buf, val.getBasicProperties());
            },
            buf -> {
                final ResourceLocation id = buf.readResourceLocation();
                final Properties properties = Properties.STREAM.decode(buf);
                return new MysticalCrop(id, properties);
            }
    );
    public static final RecipeSerializer<MysticalCrop> SERIALIZER = DataHelper.recipeSerializer(CODEC, STREAM);

    public static final Supplier<Map<ResourceLocation, Ingredient>> SOIL_TIERS = CachedSupplier.cache(() -> {
        final Map<ResourceLocation, Ingredient> tiers = new HashMap<>();
        tiers.put(CropTier.ELEMENTAL.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/elemental"))));
        tiers.put(CropTier.ONE.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/inferium"))));
        tiers.put(CropTier.TWO.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/prudentium"))));
        tiers.put(CropTier.THREE.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/tertium"))));
        tiers.put(CropTier.FOUR.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/imperium"))));
        tiers.put(CropTier.FIVE.getId(), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/supremium"))));
        if (Services.PLATFORM.isModLoaded("mysticalagradditions")) {
            tiers.put(ResourceLocation.fromNamespaceAndPath("mysticalagradditions", "6"), Ingredient.of(TagKey.create(Registries.ITEM, BotanyPotsMysticalMod.id("soil/insanium"))));
        }
        return tiers;
    });

    private final ResourceLocation mysticalCropId;

    public MysticalCrop(ResourceLocation mysticalCropId) {
        this(mysticalCropId, makeProperties(mysticalCropId));
    }

    public MysticalCrop(ResourceLocation mysticalCropId, Properties properties) {
        super(properties);
        this.mysticalCropId = mysticalCropId;
    }

    private static Properties makeProperties(ResourceLocation mysticalCropId) {
        final Crop mysticalCrop = MysticalAgricultureAPI.getCropRegistry().getCropById(mysticalCropId);
        if (mysticalCrop == null) {
            throw new JsonParseException("Crop ID '" + mysticalCropId + "' not found in registry!");
        }
        Ingredient soil = Config.CONFIG.get().require_soil_tier ? SOIL_TIERS.get().get(mysticalCrop.getTier().getId()) : BasicCrop.DIRT;
        if (soil == null) {
            soil = Ingredient.of(mysticalCrop.getTier().getFarmland().asItem());
        }
        if (mysticalCrop.getCruxBlock() != null) {
            soil = BotanyPotsMysticalMod.COMBO.apply(List.of(soil, Ingredient.of(mysticalCrop.getCruxBlock().asItem())));
        }
        int growTime = Config.getTierConfig(mysticalCrop.getTier()).tier_tick_rate;
        final List<Display> display = List.of(new AgingDisplayState(mysticalCrop.getCropBlock(), BasicOptions.ofDefault()));
        final List<ItemDropProvider> drops = List.of(getDrops(mysticalCrop));
        return new Properties(Ingredient.of(mysticalCrop.getSeedsItem()), soil, growTime, display, 0, drops, Optional.empty(), Optional.empty(), 1f, 1f);
    }

    private static ItemDropProvider getDrops(Crop crop) {
        final List<SimpleDropProvider.SimpleDrop> drops = new ArrayList<>();
        final float secondaryChance = (float) crop.getSecondaryChance(BotanyPotsMysticalMod.INFERIUM_FARMLAND.get());

        // seed
        if (Config.CONFIG.get().allow_seed_drops) {
            drops.add(new SimpleDropProvider.SimpleDrop(crop.getSeedsItem().getDefaultInstance(), 1f));
            if (BotanyPotsMysticalMod.CFG_SECOND_SEED.get() && secondaryChance > 0f) {
                drops.add(new SimpleDropProvider.SimpleDrop(crop.getSeedsItem().getDefaultInstance(), secondaryChance));
            }
        }
        // crop
        drops.add(new SimpleDropProvider.SimpleDrop(crop.getEssenceItem().getDefaultInstance(), 1f));
        if (secondaryChance > 0f) {
            drops.add(new SimpleDropProvider.SimpleDrop(crop.getEssenceItem().getDefaultInstance(), secondaryChance));
        }
        // essence
        if (Config.CONFIG.get().allow_fertilized_essence_drops && BotanyPotsMysticalMod.CFG_ESSENCE_CHANCE.get() > 0f) {
            drops.add(new SimpleDropProvider.SimpleDrop(new ItemStack(BotanyPotsMysticalMod.FERTILIZED_ESSENCE.get()), BotanyPotsMysticalMod.CFG_ESSENCE_CHANCE.get().floatValue()));
        }
        return new SimpleDropProvider(drops);
    }
}