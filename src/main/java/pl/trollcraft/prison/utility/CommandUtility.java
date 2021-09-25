package pl.trollcraft.prison.utility;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandUtility {

    public static boolean isPlayer (CommandSender sender) {
        return (sender instanceof Player);
    }

}
