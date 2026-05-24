package wtf.bytez.listener;

import wtf.bytez.ModAnalyzer;
import wtf.bytez.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.jetbrains.annotations.NotNull;

public final class RegisterListener implements Listener {

    public RegisterListener() {
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