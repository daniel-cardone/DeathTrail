import org.bukkit.plugin.java.JavaPlugin;

public class DeathTrail extends JavaPlugin {
    @Override
    public void onEnable() {
        // Set all command executors
        getCommand("trail").setExecutor(new TrailCommand(this));
        getCommand("trailtype").setExecutor(new TrailTypeCommand());
        getCommand("trailspeed").setExecutor(new TrailSpeedCommand());
    }

    @Override
    public void onDisable() {}
}
