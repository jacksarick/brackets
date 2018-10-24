package brackets;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import brackets.blocks.modBlock;
import brackets.creative.BracketsTab;

@Mod(modid = Brackets.MODID, name = Brackets.NAME, version = Brackets.VERSION)
public class Brackets
{
	public static final String MODID = "brackets";
	public static final String NAME = "Bracket Blocks";
	public static final String VERSION = "1.0";

	private static Logger logger;

	public static Block modBlock;

	public static CreativeTabs tabBracket = new BracketsTab("Bracket Blocks");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		modBlock = new ModBlock().setBlockName("modBlock");
		modBlock.setCreativeTab(Brackets.tabBrackets);
		GameRegistry.registerBlock(modBlock, modBlock.getUnlocalizedName().substring(5));
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
