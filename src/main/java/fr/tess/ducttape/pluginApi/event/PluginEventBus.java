package fr.tess.ducttape.pluginApi.event;

import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.eventbus.api.IEventBus;

public class PluginEventBus {

    public static final IEventBus PluginApiBus = BusBuilder.builder().startShutdown().markerType(IPluginEvent.class).useModLauncher().build();
}
