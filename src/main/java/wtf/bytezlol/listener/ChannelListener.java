package wtf.bytezlol.listener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import wtf.bytezlol.Main;
import wtf.bytezlol.handler.DetectionHandler;

public final class ChannelListener implements PluginMessageListener {

    public ChannelListener() {
        final Main plugin = Main.getInstance();
        plugin.getBlockedChannels().forEach(channel ->
                plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, this)
        );
    }

    @Override
    public void onPluginMessageReceived(final @NotNull String channel, final @NotNull Player player, final byte @NotNull [] message) {
        if (player.hasPermission("detection.bypass")) return;
        if (!Main.getInstance().getBlockedChannels().contains(channel.toLowerCase())) return;

        DetectionHandler.handle(player);
    }
}