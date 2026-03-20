package dev.dunkleente.handler;

import dev.dunkleente.Main;
import dev.dunkleente.utility.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DetectionHandler
 *
 * @author DunkleEnte
 * @since 19.03.2026
 */
public class DetectionHandler {

    private static final @NotNull Set<UUID> detected = new HashSet<>();

    public static void handleDetected(final @NotNull Player player){
        if(detected.contains(player.getUniqueId())) return;
        detected.add(player.getUniqueId());


        Main.getInstance().getLogger().warning(player.getName() + " is using an illegal modification!");

        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()){
            if(onlinePlayer.hasPermission("detection.admin")) {
                onlinePlayer.sendRichMessage("<#FF0000><b>DETECTION</b> <dark_gray>▶ <#FF0000>" + player.getName() + " <white>has been kicked for using illegal mods.");
                onlinePlayer.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        }

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if(player.isOnline()) player.kick(ColorUtil.parse("<#FF0000><b>DETECTION</b> <dark_gray>▶ <white>You have been kicked for using illegal mods."));
            detected.remove(player.getUniqueId());
        }, 20L);
    }
}
