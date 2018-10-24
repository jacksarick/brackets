package com.brackets;

import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;


import com.brackets.blocks.ModBlock;
// import com.brackets.creative.BracketsTab;

@Mod(modid = Brackets.MODID, name = Brackets.NAME, version = Brackets.VERSION)
public class Brackets
{
	public static final String MODID = "brackets";
	public static final String NAME = "Brackets";
	public static final String VERSION = "1.0";

	private static Logger logger;

	public static Block modBlock;

	// public static CreativeTabs tabBrackets = new BracketsTab("Bracket Blocks");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		modBlock = new ModBlock("modblock").setCreativeTab(CreativeTabs.REDSTONE);
		// ForgeRegistries(modBlock);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(modBlock);
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(new ItemBlock(modBlock).setRegistryName(modBlock.getRegistryName()));
	}

	// @SubscribeEvent
	// public static void registerRenders(ModelRegistryEvent event) {
	// 	registerRender(Item.getItemFromBlock(modBlock));
	// }
}
