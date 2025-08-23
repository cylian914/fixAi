package fr.tess.ducttape;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = DuctTape.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DucTapeConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> EXCLUDE_ENTITY_STRING = BUILDER
            .comment("List of excluded entity for the activation range")
            .defineListAllowEmpty("excludeEntity", List.of(
                "minecolonies:amazon",
                "minecolonies:amazonchief",
                "minecolonies:amazonspearman",
                "minecolonies:archerbarbarian",
                "minecolonies:archermummy",
                "minecolonies:archerpirate",
                "minecolonies:barbarian",
                "minecolonies:chiefbarbarian",
                "minecolonies:chiefpirate",
                "minecolonies:citizen",
                "minecolonies:drownedarcherpirate",
                "minecolonies:drownedchiefpirate",
                "minecolonies:drownedpirate",
                "minecolonies:druidpotion",
                "minecolonies:firearrow",
                "minecolonies:fishhook",
                "minecolonies:mcminecart",
                "minecolonies:mcnormalarrow",
                "minecolonies:mercenary",
                "minecolonies:mummy",
                "minecolonies:norsemenarcher",
                "minecolonies:norsemenchief",
                "minecolonies:pharao",
                "minecolonies:pirate",
                "minecolonies:shieldmaiden",
                "minecolonies:sittingentity",
                "minecolonies:spear",
                "minecolonies:visitor"
            ), DucTapeConfig::validateEntityName);

    private static boolean validateEntityName(final Object obj)
    {
        return obj instanceof final String entityName && ForgeRegistries.ENTITY_TYPES.containsKey(new ResourceLocation(entityName));
    }

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<EntityType<?>> excludeEntity;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        excludeEntity = EXCLUDE_ENTITY_STRING.get().stream()
                .map(entityName -> ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entityName)))
                .collect(Collectors.toSet());
    }
}
