package com.brackets;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Brackets.MODID, name = Brackets.NAME, version = Brackets.VERSION)
public class Brackets
{
	public static final String MODID = "brackets";
	public static final String NAME = "Brackets";
	public static final String VERSION = "1.0";

	private static Logger logger;

	// public static CreativeTabs tabBrackets = new BracketsTab("Bracket Blocks");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
