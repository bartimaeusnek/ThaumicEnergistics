package thaumicenergistics.common.tiles.abstraction;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import thaumicenergistics.common.inventory.TheInternalInventory;

/**
 * Base for tiles with inventories.
 * Note: The inventory is not automatically saved to NBT.
 *
 * @author Nividica
 *
 */
public abstract class ThETileInventory
	extends TileEntity
	implements IInventory
{
	/**
	 * The internal inventory of the tile.
	 */
	protected final TheInternalInventory internalInventory;

	public ThETileInventory( final String invName, final int invSize, final int invStackLimit )
	{
		// Create the internal inventory
		this.internalInventory = new TheInternalInventory( invName, invSize, invStackLimit );
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public ItemStack decrStackSize( final int slotIndex, final int amount )
	{
		return this.internalInventory.decrStackSize( slotIndex, amount );
	}

	@Override
	public String getInventoryName()
	{
		return this.internalInventory.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return this.internalInventory.getInventoryStackLimit();
	}

	@Override
	public int getSizeInventory()
	{
		return this.internalInventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot( final int slotIndex )
	{
		return this.internalInventory.getStackInSlot( slotIndex );
	}

	@Override
	public ItemStack getStackInSlotOnClosing( final int slotIndex )
	{
		return this.internalInventory.getStackInSlotOnClosing( slotIndex );
	}

	@Override
	public boolean isCustomInventoryName()
	{
		return this.internalInventory.isCustomInventoryName();
	}

	@Override
	public boolean isUseableByPlayer( final EntityPlayer player )
	{
		return TheInternalInventory.isUseableByPlayer( player, this );
	}

	@Override
	public void markDirty()
	{
		this.worldObj.markTileEntityChunkModified( this.xCoord, this.yCoord, this.zCoord, this );
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void setInventorySlotContents( final int slotIndex, final ItemStack itemStack )
	{
		this.internalInventory.setInventorySlotContents( slotIndex, itemStack );
	}

}
