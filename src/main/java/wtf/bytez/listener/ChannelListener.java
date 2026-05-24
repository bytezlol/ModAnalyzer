package wtf.bytez.listener;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import wtf.bytez.ModAnalyzer;
import wtf.bytez.handler.DetectionHandler;

public final class ChannelListener implements PluginMessageListener {

    public ChannelListener() {
        final ModAnalyzer plugin = ModAnalyzer.getInstance();
        plugin.getBlockedChannels().forEach(channel ->
                plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, channel, this)
        );
    }

    @Override
    public void onPluginMessageReceived(final @NotNull String channel, final @NotNull Player player, final byte @NotNull [] message) {
        if (player.hasPermission("detection.bypass")) return;
        if (!ModAnalyzer.getInstance().getBlockedChannels().contains(channel.toLowerCase())) return;

        DetectionHandler.handle(player);
    }
}