package com.brackets.init;


import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
// import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import com.brackets.Brackets;
import com.brackets.items.*;


@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Brackets.MODID)
public class ModItems {
	public static final ItemProgram PROGRAM = new ItemProgram();

	@Mod.EventBusSubscriber(modid = Brackets.MODID)
	public static class RegistrationHandler {

		/**
		 * Register this mod's {@link Item}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {

			final Item[] items = {
					PROGRAM
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			registry.registerAll(items);

			// for(ItemB item : items){
			// 	ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			// }
		}
	}
}