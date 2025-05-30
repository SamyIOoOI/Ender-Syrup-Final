package com.cyao.endersyrup;

import com.cyao.endersyrup.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;

public class EnderSyrupMod implements ModInitializer {
    public static final String MOD_ID = "endersyrup";
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModItems.ENDER_ASH);
            entries.add(ModItems.ENDER_SYRUP);
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isOf(ModItems.ENDER_SYRUP)) return ActionResult.PASS;

            if (!world.isClient()) {
                Random random = player.getRandom();
                BlockPos pos = player.getBlockPos();

                double x = player.getX() + (random.nextDouble() - 0.5) * 20;
                double y = MathHelper.clamp(
                        player.getY() + random.nextInt(10) - 5,
                        world.getBottomY(),
                        world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ())
                );
                double z = player.getZ() + (random.nextDouble() - 0.5) * 20;

                if (player.teleport(x, y, z, true)) {
                    stack.decrement(1);
                }
            }
            return ActionResult.SUCCESS;
        });
    }
}