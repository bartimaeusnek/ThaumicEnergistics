package thaumicenergistics.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumicenergistics.common.ThaumicEnergistics;
import thaumicenergistics.common.registries.ThEStrings;

/**
 * {@link ItemEssentiaCell} housing.
 *
 * @author Nividica
 *
 */
public class ItemStorageCasing
	extends Item
{
	public ItemStorageCasing()
	{
		this.setMaxDurability( 0 );

		this.setHasSubtypes( false );
	}

	@Override
	public String getUnlocalizedName()
	{
		return ThEStrings.Item_EssentiaCellHousing.getUnlocalized();
	}

	@Override
	public String getUnlocalizedName( final ItemStack itemStack )
	{
		return ThEStrings.Item_EssentiaCellHousing.getUnlocalized();
	}

	@Override
	public void registerIcons( final IIconRegister iconRegister )
	{
		this.itemIcon = iconRegister.registerIcon( ThaumicEnergistics.MOD_ID + ":essentia.cell.casing" );
	}

}
