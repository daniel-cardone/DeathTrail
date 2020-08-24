import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trails {
    static int delay = 500;
    static List<Material> indestructibleBlocks = Arrays.asList(
            Material.OBSIDIAN, Material.NETHER_PORTAL, Material.END_PORTAL, Material.END_PORTAL_FRAME
    );

    // Asynchronously set a timeout to allow the player to move from their location
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    // Return an integer array of the player's x, y, and z position
    private static int[] getPos(Player target) {
        Location loc = target.getLocation();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        return new int[] {x, y, z};
    }

    public static void wideTrail(Plugin plugin, Player target) {
        setTimeout(() -> {
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                // Get the current player position
                int[] pos = getPos(target);

                // Create an empty arraylist to store the block positions
                ArrayList<int[]> blocks = new ArrayList<>();

                // Loop from x - 2 through x + 2
                for (int x = pos[0] - 2; x <= pos[0] + 2; x++) {
                    // Stay at y - 1
                    for (int y = pos[1] - 1; y <= pos[1] - 1; y++) {
                        // Loop from z - 2 through z + 2
                        for (int z = pos[2] - 2; z <= pos[2] + 2; z++) {
                            // Store the block
                            blocks.add(new int[] {x, y, z});
                        }
                    }
                }

                // Remove each block
                for (int[] block: blocks) {
                    Block b = target.getWorld().getBlockAt(block[0], block[1], block[2]);
                    if (indestructibleBlocks.contains(b.getType())) { // Ignore "indestructible" blocks
                        continue;
                    }
                    b.setType(Material.AIR);
                }
                return true;
            });
        }, delay);
    }

    public static void holeTrail(Plugin plugin, Player target) {
        setTimeout(() -> {
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                // Get the current player position
                int[] pos = getPos(target);

                // Create an empty arraylist to store the block positions
                ArrayList<int[]> blocks = new ArrayList<>();

                // Stay at x
                for (int x = pos[0]; x <= pos[0]; x++) {
                    // Loop from y - 5 through y
                    for (int y = pos[1] - 5; y <= pos[1]; y++) {
                        // Stay at z
                        for (int z = pos[2]; z <= pos[2]; z++) {
                            // Store the block
                            blocks.add(new int[] {x, y, z});
                        }
                    }
                }

                // Remove each block
                for (int[] block: blocks) {
                    target.getWorld().getBlockAt(block[0], block[1], block[2]).setType(Material.AIR);
                }
                return true;
            });
        }, delay);
    }

    public static void cubeTrail(Plugin plugin, Player target) {
        setTimeout(() -> {
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                // Get the current player position
                int[] pos = getPos(target);

                // Create an empty arraylist to store the block positions
                ArrayList<int[]> blocks = new ArrayList<>();

                // Loop from x - 1 through x + 1
                for (int x = pos[0] - 1; x <= pos[0] + 1; x++) {
                    // Loop from y - 3 through y - 1
                    for (int y = pos[1] - 3; y <= pos[1] - 1; y++) {
                        // Loop from z - 1 through z + 1
                        for (int z = pos[2] - 1; z <= pos[2] + 1; z++) {
                            // Store the block
                            blocks.add(new int[] {x, y, z});
                        }
                    }
                }

                // Remove each block
                for (int[] block: blocks) {
                    target.getWorld().getBlockAt(block[0], block[1], block[2]).setType(Material.AIR);
                }
                return true;
            });
        }, delay);
    }

    public static void voidTrail(Plugin plugin, Player target) {
        setTimeout(() -> {
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                // Get the current player position
                int[] pos = getPos(target);

                // Create an empty arraylist to store the block positions
                ArrayList<int[]> blocks = new ArrayList<>();

                // Stay at x
                for (int x = pos[0]; x <= pos[0]; x++) {
                    // Loop from y=0 through y
                    for (int y = 0; y <= pos[1]; y++) {
                        // Stay at z
                        for (int z = pos[2]; z <= pos[2]; z++) {
                            // Store the block
                            blocks.add(new int[] {x, y, z});
                        }
                    }
                }

                // Remove each block
                for (int[] block: blocks) {
                    target.getWorld().getBlockAt(block[0], block[1], block[2]).setType(Material.AIR);
                }
                return true;
            });
        }, delay);
    }
}
