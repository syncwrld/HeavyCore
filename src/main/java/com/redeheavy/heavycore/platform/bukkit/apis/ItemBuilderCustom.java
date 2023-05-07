package com.redeheavy.heavycore.platform.bukkit.apis;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilderCustom {
    private final ItemStack is;

    public ItemBuilderCustom(final Material m) {
        this(m, 1);
    }

    public ItemBuilderCustom(final ItemStack is) {
        this.is = is;
    }

    public ItemBuilderCustom(final Material m, final int quantia) {
        this.is = new ItemStack(m, quantia);
    }

    public ItemBuilderCustom(final Material m, final int quantia, final byte durabilidade) {
        this.is = new ItemStack(m, quantia, durabilidade);
    }

    public ItemBuilderCustom(final Material m, final int quantia, final int durabilidade) {
        this.is = new ItemStack(m, quantia, (short) durabilidade);
    }

    public ItemBuilderCustom clone() {
        return new ItemBuilderCustom(this.is);
    }

    public ItemBuilderCustom setDurability(final short durabilidade) {
        this.is.setDurability(durabilidade);
        return this;
    }

    public ItemBuilderCustom setAmount(final int amount) {
        this.is.setAmount(amount);
        final ItemMeta im = this.is.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom setDurability(final int durabilidade) {
        this.is.setDurability(Short.valueOf(String.valueOf(durabilidade)));
        return this;
    }

    public ItemBuilderCustom setName(final String nome) {
        final ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(nome);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom addUnsafeEnchantment(final Enchantment ench, final int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilderCustom removeEnchantment(final Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilderCustom setSkullOwner(final String dono) {
        try {
            final SkullMeta im = (SkullMeta) this.is.getItemMeta();
            im.setOwner(dono);
            this.is.setItemMeta(im);
        } catch (ClassCastException ex) {
        }
        return this;
    }

    public ItemBuilderCustom addEnchant(final Enchantment ench, final int level) {
        final ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom addEnchantments(final Map<Enchantment, Integer> enchantments) {
        this.is.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilderCustom setInfinityDurability() {
        this.is.setDurability((short) 32767);
        return this;
    }

    public ItemBuilderCustom addItemFlag(final ItemFlag flag) {
        final ItemMeta im = this.is.getItemMeta();
        im.addItemFlags(flag);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom setLore(final String... lore) {
        final ItemMeta im = this.is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom setLore(final List<String> lore) {
        final ItemMeta im = this.is.getItemMeta();
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom removeLoreLine(final String linha) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (!lore.contains(linha)) {
            return this;
        }
        lore.remove(linha);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom removeLoreLine(final int index) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (index < 0 || index > lore.size()) {
            return this;
        }
        lore.remove(index);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom addLoreLine(final String linha) {
        final ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        lore.add(linha);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom addLores(final List<String> linha) {
        final ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        for (final String s : linha) {
            lore.add(s);
        }
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom addLoreLine(final String linha, final int pos) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        lore.set(pos, linha);
        im.setLore(lore);
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilderCustom setDyeColor(final DyeColor cor) {
        this.is.setDurability(cor.getData());
        return this;
    }

    @Deprecated
    public ItemBuilderCustom setWoolColor(final DyeColor cor) {
        if (!this.is.getType().equals(Material.WOOL)) {
            return this;
        }
        this.is.setDurability(cor.getData());
        return this;
    }

    public ItemBuilderCustom setLeatherArmorColor(final Color cor) {
        try {
            final LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
            im.setColor(cor);
            this.is.setItemMeta(im);
        } catch (ClassCastException ex) {
        }
        return this;
    }

    public ItemStack toItemStack() {
        return this.is;
    }
}
