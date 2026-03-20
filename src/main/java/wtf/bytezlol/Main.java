package wtf.bytezlol;

import wtf.bytezlol.listener.ChannelListener;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.bytezlol.listener.ChannelRegisterListener;

import java.util.List;
import lombok.Getter;

@Getter
public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private List<String> blockedChannels;
    private String kickMessage;
    private String adminMessage;

    public Main() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigValues();

        new ChannelListener();
        new ChannelRegisterListener();
    }

    @Override
    public void onDisable() {
        blockedChannels.forEach(channel ->
                getServer().getMessenger().unregisterIncomingPluginChannel(this, channel)
        );
    }

    private void loadConfigValues() {
        blockedChannels = getConfig().getStringList("blocked-channels");
        kickMessage = getConfig().getString("kick-message");
        adminMessage = getConfig().getString("admin-message");
    }
}