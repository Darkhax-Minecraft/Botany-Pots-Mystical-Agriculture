package net.darkhax.botanypotsmystical.common.impl.config;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private static final Map<ResourceLocation, TierConfig> TIER_CONFIG = new HashMap<>();

    public static TierConfig getTierConfig(CropTier tier) {
        if (TIER_CONFIG.containsKey(tier.getId())) {
            return TIER_CONFIG.get(tier.getId());
        }
        else {
            final TierConfig config = ConfigManager.load("botanypots-mysticalagriculture/tiers/" + tier.getId().getNamespace() + "/" + tier.getId().getPath(), new TierConfig(tier));
            TIER_CONFIG.put(tier.getId(), config);
            return config;
        }
    }
}
