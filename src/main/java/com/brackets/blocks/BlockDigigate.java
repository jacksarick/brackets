package com.brackets.blocks;

import com.brackets.blocks.BlockSchemeBlock;
import com.brackets.init.ModItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockDigigate extends BlockSchemeBlock {

	// public static final PropertyDirection FACING = PropertyDirection.create("facing");
	// public String instructions = "(+ 400 20)";

	public BlockDigigate(final String blockName) {
		super(blockName);
	}

	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		final ItemStack heldItem = playerIn.getHeldItem(hand);


		if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.PROGRAM) {
			// if (!playerIn.capabilities.isCreativeMode) {}

			if (heldItem.hasTagCompound()){
				NBTTagCompound program = heldItem.getTagCompound();
				System.out.println(program.toString());
				mc.player.sendMessage(new TextComponentString("> " + schemeEval(program.getString("code"))));
				return true;
			}

			mc.player.sendMessage(new TextComponentString("That's empty"));
			return false;
		} else {
	        InventoryEnderChest inventoryenderchest = playerIn.getInventoryEnderChest();
	        TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (inventoryenderchest != null && tileentity instanceof TileEntityEnderChest) {
	            if (worldIn.getBlockState(pos.up()).doesSideBlockChestOpening(worldIn, pos.up(), EnumFacing.DOWN)) {
	                return true;
	            } else if (worldIn.isRemote) {
	                return true;
	            } else {
	                inventoryenderchest.setChestTileEntity((TileEntityEnderChest)tileentity);
	                playerIn.displayGUIChest(inventoryenderchest);
	                playerIn.addStat(StatList.ENDERCHEST_OPENED);
	                return true;
	            }
	        } else {
	            return true;
	        }
		}

		//return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityEnderChest();
    }
}