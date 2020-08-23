import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TrailSpeedCommand implements CommandExecutor {
    public static int speed = 20;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // If no args, the command will not run
        if (args.length == 0) return false;

        // Get the speed from the first arg
        String amtStr = args[0];
        int amt;

        // Attempt to parse it as an integer
        try {
            amt = Integer.parseInt(amtStr);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Please specify a valid number of ticks!");
            return true;
        }

        // Verify that the integer is positive
        if (amt < 1) {
            sender.sendMessage(ChatColor.RED + "Please specify a valid number of ticks!");
            return true;
        }

        // Set the new speed
        speed = amt;
        sender.sendMessage(ChatColor.GREEN + "Trail speed set to " + args[0]);
        return true;
    }
}
