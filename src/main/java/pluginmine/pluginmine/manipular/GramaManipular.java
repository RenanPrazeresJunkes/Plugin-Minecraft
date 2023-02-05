package pluginmine.pluginmine.manipular;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pluginmine.pluginmine.PluginMine;

import static org.bukkit.Sound.*;


public class GramaManipular implements Listener {

    public GramaManipular(PluginMine plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }
    int aviso = 0;

    @EventHandler
    public void onGramaDestruct(BlockBreakEvent event){
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        if(block.getType() == Material.DIRT){
            aviso ++;
            if(aviso == 1){
                player.sendMessage("Pare de quebrar a terra");
                player.playSound(loc, AMBIENT_CAVE, 100, 100);
            }
            if(aviso == 2){
                player.sendMessage(ChatColor.DARK_BLUE + "Eu MANDEI você PARAR!, este é o ÚLTIMO AVISO!!!!");
                player.playSound(player,ENTITY_VILLAGER_DEATH,100, 10);

            }

            if (aviso == 3){
                player.sendMessage("CHEGA! Você irá pagar!");
                player.getWorld().spawnEntity(loc, EntityType.WITHER);
                player.playSound(player,ITEM_GOAT_HORN_SOUND_5,100,100);
                player.sendMessage(ChatColor.GREEN + player.getName() +ChatColor.RED + " BOA SORTE!");
                aviso = 0;
            }

    }
}
}