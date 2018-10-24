package com.brackets.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import com.brackets.Brackets;
import com.brackets.blocks.*;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Brackets.MODID)
public class ModBlocks {

	public static final BlockModBlock MOD_BLOCK = (BlockModBlock) new BlockModBlock("Mod Block").setCreativeTab(CreativeTabs.REDSTONE);

	@Mod.EventBusSubscriber(modid = Brackets.MODID)
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
				MOD_BLOCK
			};

			registry.registerAll(blocks);
		}

		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

			final ItemBlock[] items = {
					new ItemBlock(MOD_BLOCK),
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = block.getRegistryName();
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}
		}
	}
}