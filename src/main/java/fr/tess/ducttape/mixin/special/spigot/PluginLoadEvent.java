package fr.tess.ducttape.mixin.special.spigot;

import fr.tess.ducttape.DuctTape;
import fr.tess.ducttape.pluginApi.event.PluginEventBus;
import fr.tess.ducttape.pluginApi.event.PluginLoadedEvent;
import fr.tess.ducttape.pluginApi.event.PluginRegisterEventEvent;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.Map;
import java.util.Set;

@Mixin(value = SimplePluginManager.class, remap = false)
public class PluginLoadEvent {
    @Inject(method = "loadPlugin", at = @At("RETURN"))
    private void pluginLoadedForgeEvent(File file, CallbackInfoReturnable<Plugin> cir) {
        PluginEventBus.PluginApiBus.post(new PluginLoadedEvent(cir.getReturnValue()));
    }
    @Redirect(method = "registerEvents", at = @At(value = "INVOKE", target = "Lorg/bukkit/plugin/PluginLoader;createRegisteredListeners(Lorg/bukkit/event/Listener;Lorg/bukkit/plugin/Plugin;)Ljava/util/Map;"))
    private Map<Class<? extends Event>, Set<RegisteredListener>> pluginRegisterEventForgeEvent2(PluginLoader instance, Listener listener, Plugin plugin) {
        Map<Class<? extends Event>, Set<RegisteredListener>> ret = instance.createRegisteredListeners(listener, plugin);

        PluginEventBus.PluginApiBus.post(new PluginRegisterEventEvent(ret));
        return ret;
    }
    /*@Inject(method = "registerEvents", at = @At("HEAD"))
    private void pluginRegisterEventForgeEvent(Listener listener, Plugin plugin, CallbackInfo ci) {
        PluginEventBus.PluginApiBus.post(new PluginRegisterEventEvent(listener, plugin));
    }*/
}
