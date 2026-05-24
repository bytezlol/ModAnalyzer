package wtf.bytez.listener;

import wtf.bytez.ModAnalyzer;
import wtf.bytez.handler.DetectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        ModAnalyzer.getInstance().getServer().getPluginManager().registerEvents(this, ModAnalyzer.getInstance());
    }

    @EventHandler
    public void onChannelRegister(PlayerRegisterChannelEvent event){
        if(!event.getChannel().equalsIgnoreCase("voicechat:state")) return;
        if(event.getPlayer().hasPermission("detection.bypass")) return;

        DetectionHandler.handle(event.getPlayer());

        ModAnalyzer.getInstance().getLogger().info(event.getPlayer().getName() + " joined with channel" + event.getChannel());
    }
}
