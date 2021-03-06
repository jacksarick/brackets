package com.brackets.init;

import com.brackets.blocks.*;
import com.brackets.Brackets;

import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Brackets.MODID)
public class ModBlocks {

	// public static final BlockModBlock MOD_BLOCK = new BlockModBlock("modblock");
	public static final BlockBaker BAKER = new BlockBaker("baker");
	public static final BlockDigigate DIGIGATE = new BlockDigigate("digigate");

	@Mod.EventBusSubscriber(modid = Brackets.MODID)
	public static class RegistrationHandler {

		private static final Block[] blocks = {
			// MOD_BLOCK,
			BAKER,
			DIGIGATE
		};

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {

			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.registerAll(blocks);
		}

		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

			ItemBlock[] items = new ItemBlock[blocks.length];
			Arrays.setAll(items, (b) -> new ItemBlock(blocks[b]).setRegistryName(blocks[b].getRegistryName()));

			final IForgeRegistry<Item> registry = event.getRegistry();
			registry.registerAll(items);

			for(ItemBlock item : items){
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
	}
}