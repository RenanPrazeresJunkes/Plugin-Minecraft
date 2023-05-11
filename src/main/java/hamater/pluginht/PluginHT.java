package hamater.pluginht;

import hamater.pluginht.Manipular.EncantamentoManipular;
import hamater.pluginht.Manipular.GramaManipular;
import hamater.pluginht.listener.Entrar;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class PluginHT extends JavaPlugin {
    private static PluginHT plugin;
    public static EncantamentoManipular encantamentoManipular;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("*******************\n");
        Bukkit.getLogger().info("   Servidor ON \n");
        Bukkit.getLogger().info("*******************");

        plugin = this;
        encantamentoManipular = new EncantamentoManipular("quebrador");
        registerEnchantment(encantamentoManipular);
        new GramaManipular(this);

        this.getServer().getPluginManager().registerEvents(encantamentoManipular, this);
        this.getServer().getPluginManager().registerEvents(new Entrar(), this);

    }
    @Override
    public void onDisable() {
        desligar();
        // Plugin shutdown logic
        Bukkit.getLogger().info("*******************\n");
        Bukkit.getLogger().info("   A MIMIR\n");
        Bukkit.getLogger().info("*******************");
    }

    public static PluginHT getPlugin() {
        return plugin;
    }
    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            registered = false;
            e.printStackTrace();
        }
        if(registered){

        }
    }
    private void desligar(){
        try {
        Field keyField = Enchantment.class.getDeclaredField("byKey");

        keyField.setAccessible(true);
        @SuppressWarnings("unchecked")
        HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

        if(byKey.containsKey(encantamentoManipular.getKey())) {
            byKey.remove(encantamentoManipular.getKey());
        }
        Field nameField = Enchantment.class.getDeclaredField("byName");

        nameField.setAccessible(true);
        @SuppressWarnings("unchecked")
        HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

        if(byName.containsKey(encantamentoManipular.getName())) {
            byName.remove(encantamentoManipular.getName());
        }
    } catch (Exception ignored) { }

    }


}
