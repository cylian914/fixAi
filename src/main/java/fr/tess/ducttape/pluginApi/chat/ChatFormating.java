package fr.tess.ducttape.pluginApi.chat;

import net.minecraft.world.entity.player.Player;


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

}
