package dev.dunkleente;

import dev.dunkleente.listener.ChannelListener;
import dev.dunkleente.listener.PlayerJoinListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    public Main() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new ChannelListener();
        new PlayerJoinListener();

    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterIncomingPluginChannel(this, "mco:channel");
    }
}
