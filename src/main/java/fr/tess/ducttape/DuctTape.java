package fr.tess.ducttape;

import com.mojang.logging.LogUtils;
import fr.tess.ducttape.pluginApi.chat.EssentialsChatFormating;
import fr.tess.ducttape.pluginApi.event.PluginEventBus;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DuctTape.MODID)
public class DuctTape {

    public static boolean bukkit = false;

    // Define mod id in a common place for everything to reference
    public static final String MODID = "ducttape";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public DuctTape() {
   //     MinecraftForge.EVENT_BUS.addListener(this::onServerStarting); // example to get forge event bus
        try {
            Class.forName("org.bukkit.Bukkit");
            bukkit = true;

            PluginEventBus.PluginApiBus.start();
            PluginEventBus.PluginApiBus.addListener(EssentialsChatFormating::pluginLoaded);
        } catch (ClassNotFoundException ignored) {} //not bukkit

        FMLJavaModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DucTapeConfig.SPEC);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}
