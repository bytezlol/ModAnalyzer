package dev.dunkleente.listener;

import dev.dunkleente.Main;
import dev.dunkleente.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.jetbrains.annotations.NotNull;

/**
 * PlayerJoinListener
 *
 * @author DunkleEnte
 * @since 19.03.2026
 */
public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onChannelRegister(PlayerRegisterChannelEvent event){
        if(!event.getChannel().equalsIgnoreCase("voicechat:state")) return;
        if(event.getPlayer().hasPermission("detection.bypass")) return;

        DetectionHandler.handleDetected(event.getPlayer());

        Main.getInstance().getLogger().info(event.getPlayer().getName() + " joined with channel" + event.getChannel());
    }
}
