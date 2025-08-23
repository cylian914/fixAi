package fr.tess.ducttape.pluginApi.event;

import net.minecraftforge.eventbus.api.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import java.util.Map;
import java.util.Set;

public class PluginRegisterEventEvent extends Event implements IPluginEvent{

    Map<Class<? extends org.bukkit.event.Event>, Set<RegisteredListener>> listener;

    public PluginRegisterEventEvent(Map<Class<? extends org.bukkit.event.Event>, Set<RegisteredListener>> listener) {
        this.listener = listener;
    }

    public Set<RegisteredListener> get(Class<? extends org.bukkit.event.Event> event) {
        return listener.get(event);
    }

    public Plugin getPlugin() {
        return listener.values().iterator().next().iterator().next().getPlugin();
    }
}
