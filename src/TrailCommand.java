import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class TrailCommand implements CommandExecutor {
    Plugin plugin;
    HashMap<String, Integer> tasks = new HashMap<>();

    public TrailCommand(Plugin p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Make sure the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        // If no args, the command will not run
        if (args.length == 0) return false;

        // Try to get a player from the first arg
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "That player could not be found!");
            return false;
        }

        // Get the player's username
        String username = target.getName();
        // If the player has a trail, remove it; otherwise, make one
        if (tasks.containsKey(username)) {
            removeTrail(username, tasks.get(username));
            sender.sendMessage(ChatColor.GREEN + username + " no longer has a trail.");
        } else {
            createTrail(target, username, TrailSpeedCommand.speed);
            sender.sendMessage(ChatColor.GREEN + username + " now has a death trail.");
        }

        return true;
    }

    private void makeTrail(Player target) {
        // Find the current trail type and execute it
        switch (TrailTypeCommand.trailType) {
            case "wide":
                Trails.wideTrail(plugin, target);
                break;
            case "hole":
                Trails.holeTrail(plugin, target);
                break;
            case "cube":
                Trails.cubeTrail(plugin, target);
                break;
            case "void":
                Trails.voidTrail(plugin, target);
                break;
        }
    }

    private void createTrail(Player target, String playerName, int ticks) {
        // Set a repeating task to give a trail
        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                () -> makeTrail(target), 0L, ticks);
        // Store the player and the task ID
        tasks.put(playerName, taskID);
    }

    private void removeTrail(String playerName, int taskID) {
        // Remove the stored task
        tasks.remove(playerName);
        // Cancel the task from executing
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
