package me.sores.Orion.util;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

/**
 * Created by sores on 1/6/2022.
 */
public class MiniBlock {

    /**
     * MiniBlock
     *
     * @version 1.3.5
     * @author _Ug
     *
     * No credit is needed. ;)
     */

    private Location location;

    private ItemStack item;

    private int size;

    private ArmorStand stand;

    private boolean isRemoved;

    /**
     * Create a MiniBlock
     *
     * @param location
     *            Location to spawn MiniBlock at
     *
     * @param item
     *            Item for the MiniBlock
     *
     * @param size
     *            Size of MiniBlock (1-3 for blocks, 1-2 for items)
     */
    public MiniBlock(Location location, ItemStack item, int size){

        this.location = location;

        this.item = item;

        if(!isSizeValid (item, size))size = 2;

        this.size = size;

        this.isRemoved = false;

        spawnMiniBlock(location, item, size);

    }

    /**
     * Teleport MiniBlock
     *
     * @param location
     *          Location to teleport to
     */
    public void teleport(Location location){

        this.location = location;

        if(!this.isRemoved){

            stand.remove();

            spawnMiniBlock(this.location, this.item, this.size);

        }

    }

    /**
     * Set MiniBlock item
     *
     * @param item
     *          Item to insert to MiniBlock
     */
    public void setItem(ItemStack item){

        this.item = item;

        stand.setItemInHand(item);

    }

    /**
     * Set MiniBlock size
     *
     * @param size
     *          Size of MiniBlock (1-3 for blocks, 1-2 for items)
     */
    public void setSize(int size){

        this.size = size;

        if(!this.isRemoved){

            stand.remove();

            spawnMiniBlock(this.location, this.item, this.size);

        }

    }

    /**
     * Remove MiniBlock
     */
    public void remove(){

        if(!this.isRemoved){

            stand.remove();

            this.isRemoved = true;

        }

    }

    /*
     * Getters
     */

    /**
     * Get MiniBlock location
     *
     * @return MiniBlock location
     *
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Get MiniBlock item
     *
     * @return MiniBlock item
     *
     */
    public ItemStack getItem(){
        return this.item;
    }

    /**
     * Get MiniBlock size
     *
     * @return MiniBlock size
     *
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Get removed status
     *
     * @return true if MiniBlock is removed
     *
     */
    public boolean isRemoved(){
        return this.isRemoved;
    }

    /*
     * Private
     */

    private void spawnMiniBlock(Location location, ItemStack item, int size){

        if(!isSizeValid(item, size))return;

        Location l = location;

        boolean isBlock = item.getType().isBlock();

        EulerAngle angle = new EulerAngle(0,0,0);

        boolean small = false;

        if(isBlock){
            if(size == 1){
                l.add(.2, -.445, 0);
                l.setPitch(45);
                angle = new EulerAngle(-.6,0,0);
                small = true;
            }else if(size == 2){
                l.add(.6, -.65, 0);
                l.setPitch(45);
                angle = new EulerAngle(-.25,0,0);
            }else if(size == 3){
                l.add(0, -.7, 0);
            }
        }else{
            if(size == 1){
                l.add(.2, -.5, -.1);
                angle = new EulerAngle(-.35,0,0);
                small = true;
            }else if(size == 2){
                l.add(.7, -.8, -.1);
                angle = new EulerAngle(0,1,0);
            }
        }

        ArmorStand stand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);

        fixup(stand);

        stand.setSmall(small);

        stand.setRightArmPose(angle);

        stand.setItemInHand(item);

        this.stand = stand;

    }

    private boolean isSizeValid(ItemStack item, int size){
        if(item.getType().isBlock()){
            if(size == 1 || size == 2 || size == 3){
                return true;
            }else{
                return false;
            }
        }else{
            if(size == 1 || size == 2){
                return true;
            }else{
                return false;
            }
        }
    }

    private void fixup(ArmorStand stand){
        stand.setVisible(false);
        stand.setArms(true);
        stand.setGravity(false);
        stand.setCanPickupItems(false);
    }

}
