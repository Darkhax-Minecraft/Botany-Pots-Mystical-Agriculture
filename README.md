<!-- name-start -->
# BotanyPots-Mystical [![CurseForge Project](https://img.shields.io/curseforge/dt/0?logo=curseforge&label=CurseForge&style=flat-square&labelColor=2D2D2D&color=555555)](https://www.curseforge.com/minecraft/mc-mods/botany-pots) [![Modrinth Project](https://img.shields.io/modrinth/dt/0?logo=modrinth&label=Modrinth&style=flat-square&labelColor=2D2D2D&color=555555)](https://modrinth.com/mod/botany-pots) [![Maven Project](https://img.shields.io/maven-metadata/v?style=flat-square&logoColor=D31A38&labelColor=2D2D2D&color=555555&label=Latest&logo=gradle&metadataUrl=https%3A%2F%2Fmaven.blamejared.com%2Fnet%2Fdarkhax%2Fbotanypotsmystical%2Fbotanypotsmystical-common-1.21.1%2Fmaven-metadata.xml)](https://maven.blamejared.com/net/darkhax/botanypotsmystical)
<!-- name-end -->
<!-- description-start -->
Mystical Agriculture support for Botany Pots. The documentation for this mod can be found [here](https://docs.darkhax.net/mods/botany-pots).
<!-- description-end -->

## Mod Info
This mod adds support for Mystical Agriculture and the Mystical Agradditions addon. We currently support 140 crops, 7 farmlands, 6 cruxes, and the fertilizer.

### Quick Info
Each crop produces its essence when grown. It can also produce 1-2 seeds, and a fertilized essence based on how you have configured the Mystical Agriculture mod. All mystical crops require a soil of their tier or higher. For example, tier 3 crops will only grow in Tertium Farmland or a higher tier soil. If the crop normally requires a crux, you can use the crux in place of a soil for a 10% speed boost. The growth time for a mystical crop scales based on their tier. Tier one crops take a minute and each additional tier takes 15 seconds longer than the previous tier.

### Configuration
By default, all mystical crops will base their botany pots stats on their normal data. This means changes made to the Mystical Agriculture mod should propagate to Botany Pots. If you want to modify how those properties are used you can override the crop file entirely with a custom one using data packs. A limited subset of properties are also available using config files.

You can modify the properties for each crop tier using files in the `.minecraft/config/botanypots-mysticalagriculture/tiers/` folder.

### Tags
To help make this mod more customizable, a few item tags have been added. These tags control which item is considered a valid soil for each tier.

- `botanypotsmystical:soil/elemental`
- `botanypotsmystical:soil/inferium`
- `botanypotsmystical:soil/prudentium/`
- `botanypotsmystical:soil/tertium`
- `botanypotsmystical:soil/imperium`
- `botanypotsmystical:soil/supremium`
- `botanypotsmystical:soil/insanium`

### Missing Crops
If a mod adds new mystical agriculture crops that are not supported, you can easily generate them using the `/botanypots debug missing seeds`. The mod will place all of the generated files in the `.minecraft/botanypots` folder.

<!-- maven-start -->
## Maven Dependency

If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to replace the version with the correct one. All versions can be viewed [here](https://maven.blamejared.com/net/darkhax/botanypotsmystical).

```gradle
repositories {
    maven { 
        url 'https://maven.blamejared.com'
    }
}

dependencies {
    // NeoForge
    implementation group: 'net.darkhax.botanypotsmystical', name: 'botanypotsmystical-neoforge-1.21.1', version: '21.1.0'

    // Forge
    implementation group: 'net.darkhax.botanypotsmystical', name: 'botanypotsmystical-forge-1.21.1', version: '21.1.0'

    // Fabric & Quilt
    modImplementation group: 'net.darkhax.botanypotsmystical', name: 'botanypotsmystical-fabric-1.21.1', version: '21.1.0'

    // Common / MultiLoader / Vanilla
    compileOnly group: 'net.darkhax.botanypotsmystical', name: 'botanypotsmystical-common-1.21.1', version: '21.1.0'
}
```
<!-- maven-end -->

<!-- sponsor-start -->
## Sponsors

[![](https://assets.blamejared.com/nodecraft/darkhax.jpg)](https://nodecraft.com/r/darkhax)    
BotanyPots-Mystical is sponsored by Nodecraft. Use code **[DARKHAX](https://nodecraft.com/r/darkhax)** for 30% of your first month of service!
<!-- sponsor-end -->