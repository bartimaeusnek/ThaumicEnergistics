package thaumicenergistics.common.items;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import thaumicenergistics.common.ThaumicEnergistics;
import thaumicenergistics.common.registries.ThEStrings;

/**
 * Crafting material items.
 *
 * @author Nividica
 *
 */
public class ItemMaterial
	extends Item
{

	/**
	 * Enum of all materials
	 *
	 * @author Nividica
	 *
	 */
	public static enum MaterialTypes
	{
			DIFFUSION_CORE (0, "diffusion.core", ThEStrings.Item_DiffusionCore),
			COALESCENCE_CORE (1, "coalescence.core", ThEStrings.Item_CoalescenceCore),
			IRON_GEAR (2, "iron.gear", ThEStrings.Item_IronGear);

		/**
		 * Cache of the enum values
		 */
		public static final MaterialTypes[] VALUES = MaterialTypes.values();

		/**
		 * Numeric ID of the material.
		 */
		private int ID;

		/**
		 * Location of the material texture.
		 */
		private String textureLocation;

		/**
		 * Localization string.
		 */
		private ThEStrings unlocalizedName;

		private MaterialTypes( final int ID, final String textureName, final ThEStrings unlocalizedName )
		{
			this.ID = ID;

			this.textureLocation = ThaumicEnergistics.MOD_ID + ":material." + textureName;

			this.unlocalizedName = unlocalizedName;
		}

		public int getID()
		{
			return this.ID;
		}

		/**
		 * Gets an item stack of size 1 of the material item.
		 *
		 * @return
		 */
		public ItemStack getStack()
		{
			return this.getStack( 1 );
		}

		/**
		 * Gets an item stack of the specified size of the material item.
		 *
		 * @param size
		 * @return
		 */
		public ItemStack getStack( final int size )
		{
			return ItemEnum.MATERIAL.getDMGStack( this.ordinal(), size );
		}

		public String getTextureLocation()
		{
			return this.textureLocation;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName.getUnlocalized();
		}
	}

	/**
	 * List of icons based on damage/meta value
	 */
	private IIcon[] icons;

	public ItemMaterial()
	{
		this.setMaxDurability( 0 );
		this.setHasSubtypes( true );
		this.setCreativeTab( ThaumicEnergistics.ThETab );
	}

	/**
	 * Gets the icon for the specified material.
	 */
	@Override
	public IIcon getIconFromDamage( final int damage )
	{
		int index = MathHelper.clamp_int( damage, 0, MaterialTypes.VALUES.length - 1 );

		return this.icons[index];
	}

	@Override
	public void getSubItems( final Item item, final CreativeTabs creativeTab, final List itemList )
	{
		// Add each material item
		for( MaterialTypes material : MaterialTypes.VALUES )
		{
			itemList.add( new ItemStack( item, 1, material.getID() ) );
		}
	}

	@Override
	public String getUnlocalizedName()
	{
		return ThaumicEnergistics.MOD_ID + ".item.material";
	}

	@Override
	public String getUnlocalizedName( final ItemStack itemStack )
	{
		int index = MathHelper.clamp_int( itemStack.getMetadata(), 0, MaterialTypes.VALUES.length - 1 );

		return MaterialTypes.VALUES[index].getUnlocalizedName();
	}

	/**
	 * Registers each materials icon.
	 */
	@Override
	public void registerIcons( final IIconRegister iconRegister )
	{

		// Create the icon array
		this.icons = new IIcon[MaterialTypes.VALUES.length];

		// Register each icon
		for( MaterialTypes material : MaterialTypes.VALUES )
		{
			this.icons[material.getID()] = iconRegister.registerIcon( material.getTextureLocation() );
		}
	}
}
