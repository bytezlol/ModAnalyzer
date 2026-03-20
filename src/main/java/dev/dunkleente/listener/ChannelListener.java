package dev.dunkleente.listener;

import dev.dunkleente.Main;
import dev.dunkleente.handler.DetectionHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

/**
 * ChannelListener
 *
 * @author DunkleEnte
 * @since 19.03.2026
 */
public class ChannelListener implements PluginMessageListener {

    public ChannelListener() {
        Main.getInstance().getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "voicechat:state", this);
    }

    @Override
    public void onPluginMessageReceived(final @NotNull String channel, final @NotNull Player player, final byte[] message) {
        if (!channel.equalsIgnoreCase("voicechat:state")) return;
        if (player.hasPermission("detection.bypass")) return;

        DetectionHandler.handleDetected(player);
    }
}
