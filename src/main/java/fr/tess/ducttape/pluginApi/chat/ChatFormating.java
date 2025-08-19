package fr.tess.ducttape.pluginApi.chat;

import fr.tess.ducttape.pluginApi.event.PluginEventBus;
import fr.tess.ducttape.pluginApi.event.PluginLoadedEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;


public class ChatFormating {

    static public ChatFormating format = new ChatFormating();

    static void setFormat(ChatFormating newFormat) {
        format = newFormat;
    }

    public String FormatMessage(Player p, String message) {
        return message;
    }

    public String getDisplayName(Player p) {
        return p.getDisplayName().getString();
    }

    @SubscribeEvent
    public static void pluginLoaded(PluginLoadedEvent event) {
        if (Objects.equals(event.getPluginName(), "EssentialsChat")) {
            format =new EssentialsChatFormating();
            PluginEventBus.PluginApiBus.addListener(((EssentialsChatFormating)format)::setHandler);
        }
    }

}
