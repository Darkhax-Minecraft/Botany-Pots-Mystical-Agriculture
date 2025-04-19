package net.darkhax.botanypotsmystical.common.impl.config;

import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import net.darkhax.pricklemc.common.api.annotations.RangedInt;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class TierConfig {

    @Value(comment = "The amount of ticks required for a crop in this tier to grow. 20 ticks is one second.")
    @RangedInt(min = 0)
    public int tier_tick_rate;

    public TierConfig(CropTier tier) {
        this.tier_tick_rate = (int) (1200 + Math.floor(((Math.max(0, tier.getValue() - 1) * 0.25f) * 1200)));
    }
}