package com.brackets.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



import com.brackets.Brackets;
import com.brackets.blocks.*;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(Brackets.MODID)
public class ModBlocks {

	public static final BlockModBlock MOD_BLOCK = new BlockModBlock("modblock");
	public static final BlockBaker BAKER = new BlockBaker("baker");

	public static final BlockDigigate DIGIGATE = new BlockDigigate("digigate");

	@Mod.EventBusSubscriber(modid = Brackets.MODID)
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		private static final Block[] blocks = {
			MOD_BLOCK,
			DIGIGATE,
			BAKER
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

			final IForgeRegistry<Item> registry = event.getRegistry();

			// final ItemBlock[] items = {
			// 		new ItemBlock(MOD_BLOCK),
			// 		new ItemBlock(DIGIGATE),
			// 		new ItemBlock(BAKER)
			// };

			ItemBlock[] items = new ItemBlock[blocks.length];
			Arrays.setAll(items, (b) -> new ItemBlock(blocks[b]));

			registry.registerAll(items);

			// for (final ItemBlock item : items) {
			// 	final Block block = item.getBlock();
			// 	final ResourceLocation registryName = block.getRegistryName();
			// 	registry.register(item.setRegistryName(registryName));
			// 	ModelLoader.setCustomModelResourceLocation(item, 0, new  ModelResourceLocation(Brackets.MODID + ":" + item.getRegistryName(), "inventory"));
			// 	ITEM_BLOCKS.add(item);
			// }
		}
	}
}