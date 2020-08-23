import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TrailTypeCommand implements CommandExecutor {
    public static String trailType = "hole";
    List<String> allTypes = Arrays.asList("wide", "hole", "cube", "void");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // If no args, the command will not run
        if (args.length == 0) return false;

        // Get the new type
        String type = args[0].toLowerCase();

        // Make sure the type exists and set it
        if (allTypes.contains(type)) {
            trailType = type;
            sender.sendMessage(ChatColor.GREEN + "Trail type set to: " + type);
            return true;
        }

        sender.sendMessage(ChatColor.RED + "That trail type does not exist!");
        return false;
    }
}
