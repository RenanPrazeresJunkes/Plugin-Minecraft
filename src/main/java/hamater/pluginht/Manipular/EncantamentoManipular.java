package hamater.pluginht.Manipular;

import hamater.pluginht.PluginHT;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class EncantamentoManipular extends Enchantment implements Listener {

    @EventHandler

    public void onBlockBreak(BlockBreakEvent event) {
        Player player=event.getPlayer();
        if(player.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(PluginHT.encantamentoManipular.getKey()))){
        //get direction in which player broke the block
            BreakDirection direction=BreakDirection.getFacingDirection(player);
        //get list of block locations around it (here its 3x3)
        List<Location> blocks = direction.getRadius(event.getBlock(), 1, 3);
        //break them
        for(Location location:blocks)
            location.getBlock().breakNaturally();
    }
    }
    public enum BreakDirection
    {
        NORTH(0,0,-1),
        SOUTH(0,0,1),
        EAST(1,0,0),
        WEST(-1,0,0),
        UP(0,1,0),
        DOWN(0,-1,0);
        private Vector addition;
        private boolean isX;
        private boolean isY;
        private boolean isZ;
        BreakDirection(int x,int y,int z)
        {
            this.addition=new Vector(x,y,z);
            this.isX=x!=0;
            this.isY=y!=0;
            this.isZ=z!=0;
        }
        public static BreakDirection getFacingDirection(Player player) {
            Vector dir = player.getLocation().getDirection().normalize();
            double x = dir.getX();
            double y = dir.getY();
            double z = dir.getZ();
            // up or down
            if(Math.abs(y)>0.5D)
                return (y > 0.0D) ? BreakDirection.UP : BreakDirection.DOWN;
                //east or west
            else if (Math.abs(x) > 0.5D)
                return (x > 0.0D) ? BreakDirection.EAST : BreakDirection.WEST;
                //north or south
            else
                return (z > 0.0D) ? BreakDirection.SOUTH : BreakDirection.NORTH;
        }
        public List<Location> getRadius(Block start,int depth,int width)
        {
            width=Math.abs(width);
            int evenBlocks=(width%2==1)? 0:1;
            int radius=(int) ((evenBlocks!=1) ? Math.floor((width-1)/2D) : Math.floor(width/2D));
            List<Location> result=new ArrayList<Location>();
            result.add(start.getLocation().clone());
            Location location=start.getLocation();
            for(int i=0;i<depth;i++)
            {
                for(int x=-radius;x<=radius-evenBlocks;x++)
                    for(int y=-radius;y<=radius-evenBlocks;y++)
                        for(int z=-radius;z<=radius-evenBlocks;z++)
                        {
                            Location toadd = null;
                            if(isX)
                                toadd=location.clone().add(0,y,z);
                            if(isY)
                                toadd=location.clone().add(x,0,z);
                            if(isZ)
                                toadd=location.clone().add(x,y,0);
                            if(toadd!=null&&!result.contains(toadd))
                                result.add(toadd);
                        }
                location=location.add(addition);
            }
            return result;
        }
    }

    public EncantamentoManipular(String namespace){
        super(new NamespacedKey(PluginHT.getPlugin(), namespace));
    }

    @Override
    public String getName() {
        return "Quebrador de Blocos PICARETA!";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
