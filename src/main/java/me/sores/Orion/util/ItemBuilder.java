package me.sores.Orion.util;

import com.google.common.collect.Lists;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sores on 1/6/2022.
 */
public class ItemBuilder {

    private final ItemStack item;


    public ItemBuilder(Material material) {
        item = new ItemStack(material);
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(short data){
        item.setDurability(data);
        return this;
    }

    public ItemBuilder setData(MaterialData data){
        item.setData(data);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(Lists.newArrayList(lore));
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addBlankLore() {
        addLore(" ");
        return this;
    }

    public ItemBuilder addLineLore() {
        return addLineLore(20);
    }

    public ItemBuilder addLineLore(int length) {
        addLore("&8&m&l" + Strings.repeat('-', length));
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> original = itemMeta.getLore();
        if (original == null) original = new ArrayList<>();
        Collections.addAll(original, lore);
        itemMeta.setLore(original);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLore(List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> original = itemMeta.getLore();
        if (original == null) original = new ArrayList<>();
        original.addAll(lore);
        itemMeta.setLore(original);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder clearLores() {
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            meta.setLore(new ArrayList<>());
            item.setItemMeta(meta);
        }

        return this;
    }

    public ItemBuilder setName(String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringUtil.color(name));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder unsafeEnchant(Enchantment enchantment, int level){
        item.addUnsafeEnchantment(enchantment, level);

        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level){
        item.addEnchantment(enchantment, level);

        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment){
        ItemMeta meta = item.getItemMeta();

        if(meta.hasEnchants()){
            if(item.getEnchantments().containsKey(enchantment)){
                meta.removeEnchant(enchantment);

                item.setItemMeta(meta);
            }
        }

        return this;
    }

    public ItemBuilder clearEnchants(){
        ItemMeta meta = item.getItemMeta();

        if(meta.hasEnchants()){
            for(Enchantment enchantments : item.getEnchantments().keySet()){
                item.removeEnchantment(enchantments);
            }
        }

        return this;
    }

    public ItemBuilder setEnchantments(HashMap<Enchantment, Integer> enchantments) {
        for (Enchantment enchantment : item.getEnchantments().keySet()) {
            item.removeEnchantment(enchantment);
        }
        item.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemBuilder color(Color color){
        if(item.getType().toString().contains("LEATHER_")){
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

            meta.setColor(color);
            item.setItemMeta(meta);
        }

        return this;
    }

    public ItemStack build(){
        return item;
    }

}
