package fr.tess.ducttape.pluginApi.chat;

import fr.tess.ducttape.DuctTape;
import fr.tess.ducttape.pluginApi.event.PluginRegisterEventEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventException;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredListener;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;


public class EssentialsChatFormating extends ChatFormating {

    RegisteredListener chatLowest = null;
    Object ess = null;

    @Override
    public String FormatMessage(Player p, String message) {
        AsyncPlayerChatEvent fakeEvent = new AsyncPlayerChatEvent(false, Bukkit.getPlayer(p.getUUID()), message, Set.of());
        try {
            chatLowest.callEvent(fakeEvent);
        } catch (EventException e) {
            return super.FormatMessage(p, message);
        }
        return String.format(fakeEvent.getFormat(), getDisplayName(p), fakeEvent.getMessage());
    }

    public String getDisplayName(Player p) {
        try {
            Object user = ess.getClass().getMethod("getUser", org.bukkit.entity.Player.class).invoke(ess, Bukkit.getPlayer(p.getUUID()));
            user.getClass().getMethod("setDisplayNick").invoke(user);
            return (String) user.getClass().getMethod("getDisplayName").invoke(user);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return super.getDisplayName(p);
        }
    }

    @SubscribeEvent
    public void setHandler(PluginRegisterEventEvent event) {
        if (chatLowest != null && ess != null)
            return;
        if (event.getPlugin().getName().equals("Essentials")) {
            ess = event.getPlugin();
        }

        Set<RegisteredListener> l = event.get(AsyncPlayerChatEvent.class);
        if (l == null) return;
        l.forEach((listener) -> {
            if (listener.getListener().getClass().descriptorString().equals("Lcom/earth2me/essentials/chat/processing/ChatHandler$ChatLowest;")) {
                chatLowest = listener;
            }
        });
    }
}
