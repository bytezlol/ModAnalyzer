package wtf.bytezlol.listener;

import wtf.bytezlol.Main;
import wtf.bytezlol.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.jetbrains.annotations.NotNull;

public final class ChannelRegisterListener implements Listener {

    public ChannelRegisterListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onChannelRegister(final @NotNull PlayerRegisterChannelEvent event) {
        if (event.getPlayer().hasPermission("detection.bypass")) return;
        if (!Main.getInstance().getBlockedChannels().contains(event.getChannel().toLowerCase())) return;

        Main.getInstance().getLogger().info(event.getPlayer().getName() + " registered blocked channel: " + event.getChannel());
        DetectionHandler.handle(event.getPlayer());
    }
}