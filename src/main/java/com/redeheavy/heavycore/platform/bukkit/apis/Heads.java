package com.redeheavy.heavycore.platform.bukkit.apis;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class Heads {

    public static ItemStack VERDE;
    public static ItemStack MAGENTA;
    public static ItemStack BRANCO;
    public static ItemStack AMARELO;
    public static ItemStack ROXO;
    public static ItemStack LARANJA;
    public static ItemStack CINZA;
    public static ItemStack BLUE;

    static {
        Heads.BLUE = getSkull("http://textures.minecraft.net/texture/bef7b6829fc48758cb25ab93f28bf794d92ace0161f809a2aadd3bb12b23014");
        Heads.VERDE = getSkull("http://textures.minecraft.net/texture/361e5b333c2a3868bb6a58b6674a2639323815738e77e053977419af3f77");
        Heads.MAGENTA = new ItemBuilderCustom(Material.SKULL_ITEM, 1, 3).setSkullOwner("diablo3pk").toItemStack();
        Heads.BRANCO = new ItemBuilderCustom(Material.SKULL_ITEM, 1, 3).setSkullOwner("cy1337").toItemStack();
        Heads.LARANJA = new ItemBuilderCustom(Material.SKULL_ITEM, 1, 3).setSkullOwner("wulfric17").toItemStack();
        Heads.AMARELO = getSkull("http://textures.minecraft.net/texture/14c4141c1edf3f7e41236bd658c5bc7b5aa7abf7e2a852b647258818acd70d8");
        Heads.ROXO = getSkull("http://textures.minecraft.net/texture/e9352bcabfc27edb44ceb51b04786542f26a299a0529475346186ee94738f");
        Heads.CINZA = getSkull("http://textures.minecraft.net/texture/f2f085c6b3cb228e5ba81df562c4786762f3c257127e9725c77b7fd301d37");
    }

    public static ItemStack getSkull(final String url) {
        final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty()) {
            return skull;
        }
        final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), (String) null);
        final byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException ex15) {
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException ex16) {
        }
        skull.setItemMeta((ItemMeta) skullMeta);
        return skull;
    }
}
