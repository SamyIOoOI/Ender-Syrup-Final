package com.cyao.endersyrup.item;

import com.cyao.endersyrup.EnderSyrupMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;
import net.minecraft.item.ItemStack;

public class ModItems {

    public static final Item ENDER_ASH = registerItem("ender_ash", new Item.Settings().maxCount(64), "Ender Ash");
    public static final Item ENDER_SYRUP = registerItem("ender_syrup", new Item.Settings().maxCount(16), "Ender Syrup");

    private static Item registerItem(String name, Item.Settings settings, String displayName) {
        Identifier id = Identifier.of(EnderSyrupMod.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings finalizedSettings = settings.registryKey(key);
        return Registry.register(Registries.ITEM, key, new Item(finalizedSettings) {
            @Override
            public Text getName(ItemStack stack) {
                return Text.literal(displayName);
            }
        });
    }

    public static void registerModItems() {
        EnderSyrupMod.LOGGER.info("Registering Mod Items for " + EnderSyrupMod.MOD_ID);
    }
}