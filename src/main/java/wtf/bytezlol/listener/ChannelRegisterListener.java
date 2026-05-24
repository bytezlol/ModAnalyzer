package wtf.bytezlol.listener;

import wtf.bytezlol.ModAnalyzer;
import wtf.bytezlol.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.jetbrains.annotations.NotNull;

public final class ChannelRegisterListener implements Listener {

    public ChannelRegisterListener() {
        ModAnalyzer.getInstance().getServer().getPluginManager().registerEvents(this, ModAnalyzer.getInstance());
    }

    @EventHandler
    public void onChannelRegister(final @NotNull PlayerRegisterChannelEvent event) {
        if (event.getPlayer().hasPermission("detection.bypass")) return;
        if (!ModAnalyzer.getInstance().getBlockedChannels().contains(event.getChannel().toLowerCase())) return;

        ModAnalyzer.getInstance().getLogger().info(event.getPlayer().getName() + " registered blocked channel: " + event.getChannel());
        DetectionHandler.handle(event.getPlayer());
    }
}