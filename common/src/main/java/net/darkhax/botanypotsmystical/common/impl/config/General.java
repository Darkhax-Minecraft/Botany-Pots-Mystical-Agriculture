package net.darkhax.botanypotsmystical.common.impl.config;

import net.darkhax.pricklemc.common.api.annotations.Value;

public class General {

    @Value(comment = "When enabled mystical crops will only grow in soils of their tier or higher. Disabling this option will let them grow in any dirt-like soil.")
    public boolean require_soil_tier = true;

    @Value(comment = "When enabled botany pots will be allowed to produce fertilized essence when harvesting mystical crops. Drop rates are based on how Mystical Agriculture is configured.")
    public boolean allow_fertilized_essence_drops = true;

    @Value(comment = "When enabled botany pots will be allowed to produce excess mystical seeds. Drop rates are based on how Mystical Agriculture is configured.")
    public boolean allow_seed_drops = true;
}
