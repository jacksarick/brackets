package com.brackets.blocks;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import jscheme.InputPort;
import jscheme.Scheme;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockSchemeBlock extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public Minecraft mc = Minecraft.getMinecraft();

	public BlockSchemeBlock(final String blockName) {
		super(Material.IRON);
		setCreativeTab(CreativeTabs.REDSTONE);
		setBlockName(this, blockName);

		IBlockState baseState = this.blockState.getBaseState();
		this.setDefaultState(baseState.withProperty(FACING, EnumFacing.NORTH));
	}

	/**
	 * Set the registry name of {@code block} to {@code blockName} and the translation key to the full registry name.
	 *
	 * @param block     The block
	 * @param blockName The block's name
	 */
	public static void setBlockName(final Block block, final String blockName) {
		block.setRegistryName(blockName);
		block.setTranslationKey(blockName);
	}
	

	/**
	  * Scheme
	  */ 
	public static String schemeEval(String x) {
		try {
			InputPort input = new InputPort(new StringReader(x));
			StringWriter output = new StringWriter();
			PrintWriter outputWriter = new PrintWriter(output, true);

			Scheme js = new Scheme(new String[] {""});
			js.load(input, outputWriter);
			return output.toString();
		} catch (Exception e) {
			System.out.println(e);
			return "Failed!";
		}
	}

	/**
	  * Models/Textures
	  */

	@SuppressWarnings("unused")
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()){
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()){
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()){
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()){
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

	// /**
	//  * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	//  */
	// public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	// {
	//     worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

	//     if (stack.hasDisplayName())
	//     {
	//         TileEntity tileentity = worldIn.getTileEntity(pos);

	//         if (tileentity instanceof TileEntityFurnace)
	//         {
	//             ((TileEntityFurnace)tileentity).setCustomInventoryName(stack.getDisplayName());
	//         }
	//     }
	// }

	// /**
	//  * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
	//  */
	// public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	// {
	//     if (!keepInventory)
	//     {
	//         TileEntity tileentity = worldIn.getTileEntity(pos);

	//         if (tileentity instanceof TileEntityFurnace)
	//         {
	//             InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)tileentity);
	//             worldIn.updateComparatorOutputLevel(pos, this);
	//         }
	//     }

	//     super.breakBlock(worldIn, pos, state);
	// }

	// /**
	//  * @deprecated call via {@link IBlockState#hasComparatorInputOverride()} whenever possible. Implementing/overriding
	//  * is fine.
	//  */
	// public boolean hasComparatorInputOverride(IBlockState state)
	// {
	// 	return true;
	// }

	// /**
	//  * @deprecated call via {@link IBlockState#getComparatorInputOverride(World,BlockPos)} whenever possible.
	//  * Implementing/overriding is fine.
	//  */
	// public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
	// {
	// 	return Container.calcRedstone(worldIn.getTileEntity(pos));
	// }



//    /**
//     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
//     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
//     * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
//     */
//    public EnumBlockRenderType getRenderType(IBlockState state)
//    {
//        return EnumBlockRenderType.MODEL;
//    }
//
//
//    /**
//     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
//     * blockstate.
//     * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
//     * fine.
//     */
//    public IBlockState withRotation(IBlockState state, Rotation rot)
//    {
//        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
//    }
//
//    /**
//     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
//     * blockstate.
//     * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
//     */
//    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
//    {
//        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
//    }
}