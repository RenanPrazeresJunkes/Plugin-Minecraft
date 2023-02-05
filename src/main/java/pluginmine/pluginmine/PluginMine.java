package pluginmine.pluginmine;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pluginmine.pluginmine.manipular.GramaManipular;

public final class PluginMine extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World Minecraft this is SPARTA!");

        new GramaManipular(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("A MIMIR");
    }
}
