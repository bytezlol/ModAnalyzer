package wtf.bytez;

import lombok.SneakyThrows;
import wtf.bytez.listener.ChannelListener;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.bytez.listener.PlayerJoinListener;
import wtf.bytez.listener.RegisterListener;

import java.util.List;
import lombok.Getter;

@Getter
public final class ModAnalyzer extends JavaPlugin {

    @Getter
    private static ModAnalyzer instance;

    private List<String> blockedChannels;
    private String kickMessage;
    private String adminMessage;

    public ModAnalyzer() {
        instance = this;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigValues();

        new ChannelListener();
        new RegisterListener();
        new PlayerJoinListener();
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