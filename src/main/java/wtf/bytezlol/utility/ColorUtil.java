package wtf.bytezlol.utility;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

@UtilityClass
public final class ColorUtil {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static Component parse(final String input) {
        return MINI_MESSAGE.deserialize(input)
                .decoration(TextDecoration.ITALIC, false);
    }
}