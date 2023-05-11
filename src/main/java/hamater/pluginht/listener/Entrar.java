package hamater.pluginht.listener;

import hamater.pluginht.PluginHT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Entrar implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        pickaxe.addUnsafeEnchantment(PluginHT.encantamentoManipular, 1);
        ItemMeta meta = pickaxe.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "QUEBRAR TUDO 1");
        meta.setLore(lore);
        pickaxe.setItemMeta(meta);
        player.getEquipment().setItemInMainHand(pickaxe);
    }
}
