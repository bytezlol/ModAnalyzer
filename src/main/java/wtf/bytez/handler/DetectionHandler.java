package wtf.bytez.handler;

import wtf.bytez.ModAnalyzer;
import wtf.bytez.utility.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class DetectionHandler {

    private static final Set<UUID> pending = new HashSet<>();

    private DetectionHandler() {}

    public static void handle(final @NotNull Player player) {
        if (!pending.add(player.getUniqueId())) return;

        final ModAnalyzer plugin = ModAnalyzer.getInstance();

        final String adminMessage = plugin.getAdminMessage()
                .replace("{player}", player.getName());

        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.hasPermission("detection.admin"))
                .forEach(admin -> {
                    admin.sendRichMessage(adminMessage);
                    admin.playSound(admin, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                });

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (player.isOnline()) {
                player.kick(ColorUtil.parse(plugin.getKickMessage()));
            }
            pending.remove(player.getUniqueId());
        }, 20L);
    }
}