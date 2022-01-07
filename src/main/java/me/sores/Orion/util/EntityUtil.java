package me.sores.Orion.util;

import net.minecraft.server.v1_8_R3.NBTTagByte;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;

/**
 * Created by LavaisWatery on 2017-10-23.
 */
public class EntityUtil {

    public static void freezeEntity(Entity e){
        net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) e).getHandle();

        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
    }

    public static void removePersistance(Entity e) {
        net.minecraft.server.v1_8_R3.EntityVillager villager = ((CraftVillager) e).getHandle();

        NBTTagCompound tag = new NBTTagCompound();
        villager.c(tag);
        tag.set("PersistenceRequired", new NBTTagByte((byte) 1));
        villager.a(tag);
    }

}
