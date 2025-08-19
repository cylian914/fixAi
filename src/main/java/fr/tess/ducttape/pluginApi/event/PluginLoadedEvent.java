package fr.tess.ducttape.pluginApi.event;

import net.minecraftforge.eventbus.api.Event;
import org.bukkit.plugin.Plugin;

public class PluginLoadedEvent extends Event implements IPluginEvent {

    private Plugin bPlugin;

    public PluginLoadedEvent(Plugin bPlugin) {
        this.bPlugin = bPlugin;
    }

    public Plugin getPlugin() {
        return bPlugin;
    }

    public String getPluginName() {
        return bPlugin.getName();
    }
}
