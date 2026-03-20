package wtf.bytezlol.listener;

import wtf.bytezlol.Main;
import wtf.bytezlol.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onChannelRegister(PlayerRegisterChannelEvent event){
        if(!event.getChannel().equalsIgnoreCase("voicechat:state")) return;
        if(event.getPlayer().hasPermission("detection.bypass")) return;

        DetectionHandler.handle(event.getPlayer());

        Main.getInstance().getLogger().info(event.getPlayer().getName() + " joined with channel" + event.getChannel());
    }
}
