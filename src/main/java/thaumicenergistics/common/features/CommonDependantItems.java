package thaumicenergistics.common.features;

import com.google.common.base.Optional;
import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItemDefinition;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

/**
 * Every TC/AE item that any feature depends on.
 * The items can be an ItemStack, a String, or null.
 *
 * @author Nividica
 *
 */
public class CommonDependantItems
{
	// MC Items ==========================
	public String VanillaGlass = "blockGlass";
	public String IronIngot = "ingotIron";
	public ItemStack VanillaLapis;
	public ItemStack RedstoneDust;
	public ItemStack RedstoneTorch;
	public String Cobblestone = "cobblestone";
	public String NetherQuartz = "gemQuartz";

	// TC Items ==========================
	public ItemStack ZombieBrain;
	public ItemStack Thaumonomicon;
	public ItemStack AirShard;
	public ItemStack FireShard;
	public ItemStack WaterShard;
	public ItemStack EarthShard;
	public ItemStack OrderShard;
	public ItemStack EntropyShard;
	public ItemStack BallanceShard;
	public ItemStack EtheralEssence;
	public ItemStack SalisMundus;
	public Object WardedGlass;
	public ItemStack ThaumiumIngot;
	public ItemStack ArcaneWorkTable;
	public ItemStack WardedJar;
	public ItemStack FilterTube;
	public ItemStack EssentiaMirror;
	public ItemStack VisFilter;
	public ItemStack QuickSilverDrop;
	public ItemStack Thaumometer;
	public ItemStack Nitor;

	// AE Items =========================
	public Object VibrantGlass;
	public ItemStack EngineeringProcessor;
	public ItemStack CalculationProcessor;
	public ItemStack LogicProcessor;
	public String IlluminatedPanel = "itemIlluminatedPanel";
	public ItemStack MolecularAssembler;
	public ItemStack CertusQuartz;
	public ItemStack ChargedCertusQuartz;
	public ItemStack PureCertusQuartz;
	public Object QuartzGlass;
	public Object MECellWorkbench;
	public ItemStack METerminal;
	public ItemStack MEP2P;
	public ItemStack MEInterface;
	public ItemStack WirelessReceiver;
	public ItemStack DenseCell;
	public ItemStack FormationCore;
	public ItemStack AnnihilationCore;
	public ItemStack CertusWrench;
	public ItemStack VibrationChamber;
	public ItemStack MEInterfacePart;
	public ItemStack FluixCrystal;
	public ItemStack MECharger;

	/**
	 * Populates the common items
	 */
	public CommonDependantItems()
	{
		// MC items
		this.VanillaLapis = new ItemStack( (Item)Item.itemRegistry.getObject( "dye" ), 1, 4 );
		this.RedstoneDust = new ItemStack( (Item)Item.itemRegistry.getObject( "redstone" ) );
		this.RedstoneTorch = new ItemStack( net.minecraft.init.Blocks.redstone_torch );

		// AE items
		this.populateAEItems();

		// TC items
		this.populateTCItems();
	}

	/**
	 * Attempts to get the specified AE item.
	 * Returns a stack of size 1, or null.
	 *
	 * @param def
	 * @return
	 */
	private static ItemStack getAEItem( final IItemDefinition def )
	{
		// Ensure there is a definition
		if( def == null )
		{
			return null;
		}

		// Attempt to get a stack
		Optional<ItemStack> item = def.maybeStack( 1 );

		// Was a stack retrieved?
		if( item.isPresent() )
		{
			// Return the stack
			return item.get();
		}

		// Stack was not retrieved
		return null;
	}

	/**
	 * Gets an item based on if it is available and allowed by the specified
	 * config flag.
	 *
	 * @param configDependency
	 * @param preferred
	 * @param alt
	 * @return
	 */
	private static Object getItemOrAlt( final boolean configDependency, final ItemStack preferred, final Object alt )
	{
		if( ( preferred == null ) || ( !configDependency ) )
		{
			return alt;
		}

		return preferred;
	}

	/**
	 * Gets an AE item, or if unavailable sets the item to the specified
	 * alternative.
	 *
	 * @param def
	 * @param alt
	 * @return
	 */
	private static Object getItemOrAlt( final IItemDefinition def, final Object alt )
	{
		ItemStack defItem = CommonDependantItems.getAEItem( def );
		return( defItem == null ? alt : defItem );
	}

	private void populateAEItems()
	{
		// Mats
		IMaterials aeMaterials = AEApi.instance().definitions().materials();
		this.EngineeringProcessor = CommonDependantItems.getAEItem( aeMaterials.engProcessor() );
		this.CalculationProcessor = CommonDependantItems.getAEItem( aeMaterials.calcProcessor() );
		this.LogicProcessor = CommonDependantItems.getAEItem( aeMaterials.logicProcessor() );
		this.CertusQuartz = CommonDependantItems.getAEItem( aeMaterials.certusQuartzCrystal() );
		this.ChargedCertusQuartz = CommonDependantItems.getAEItem( aeMaterials.certusQuartzCrystalCharged() );
		this.PureCertusQuartz = CommonDependantItems.getAEItem( aeMaterials.purifiedCertusQuartzCrystal() );
		this.WirelessReceiver = CommonDependantItems.getAEItem( aeMaterials.wireless() );
		this.AnnihilationCore = CommonDependantItems.getAEItem( aeMaterials.annihilationCore() );
		this.FormationCore = CommonDependantItems.getAEItem( aeMaterials.formationCore() );
		this.FluixCrystal = CommonDependantItems.getAEItem( aeMaterials.fluixCrystal() );

		// Blocks
		IBlocks aeBlocks = AEApi.instance().definitions().blocks();
		this.VibrantGlass = CommonDependantItems.getItemOrAlt( aeBlocks.quartzVibrantGlass(), this.VanillaGlass );
		this.MolecularAssembler = CommonDependantItems.getAEItem( aeBlocks.molecularAssembler() );
		this.QuartzGlass = CommonDependantItems.getItemOrAlt( aeBlocks.quartzGlass(), this.VanillaGlass );
		this.MECellWorkbench = CommonDependantItems.getItemOrAlt( aeBlocks.cellWorkbench(), new ItemStack( Blocks.crafting_table ) );
		this.MEInterface = CommonDependantItems.getAEItem( aeBlocks.iface() );
		this.DenseCell = (ItemStack)CommonDependantItems.getItemOrAlt( aeBlocks.energyCellDense(), CommonDependantItems.getAEItem( aeBlocks.energyCell() ) );
		this.VibrationChamber = CommonDependantItems.getAEItem( aeBlocks.vibrationChamber() );

		// Parts
		IParts aeParts = AEApi.instance().definitions().parts();
		this.MECharger = CommonDependantItems.getAEItem( aeBlocks.charger() );
		this.MEInterfacePart = CommonDependantItems.getAEItem( aeParts.iface() );
		this.METerminal = CommonDependantItems.getAEItem( aeParts.terminal() );
		this.MEP2P = CommonDependantItems.getAEItem( aeParts.p2PTunnelME() );

		// Items
		this.CertusWrench = CommonDependantItems.getAEItem( AEApi.instance().definitions().items().certusQuartzWrench() );

	}

	private void populateTCItems()
	{
		// Shards
		this.AirShard = new ItemStack( ConfigItems.itemShard, 1, 0 );
		this.FireShard = new ItemStack( ConfigItems.itemShard, 1, 1 );
		this.WaterShard = new ItemStack( ConfigItems.itemShard, 1, 2 );
		this.EarthShard = new ItemStack( ConfigItems.itemShard, 1, 3 );
		this.OrderShard = new ItemStack( ConfigItems.itemShard, 1, 4 );
		this.EntropyShard = new ItemStack( ConfigItems.itemShard, 1, 5 );
		this.BallanceShard = new ItemStack( ConfigItems.itemShard, 1, 6 );

		// Resources
		this.Nitor = new ItemStack( ConfigItems.itemResource, 1, 1 );
		this.ThaumiumIngot = new ItemStack( ConfigItems.itemResource, 1, 2 );
		this.VisFilter = new ItemStack( ConfigItems.itemResource, 1, 8 );
		this.SalisMundus = new ItemStack( ConfigItems.itemResource, 1, 14 );

		// Blocks
		this.WardedJar = new ItemStack( ConfigBlocks.blockJar, 1, 0 );
		this.ArcaneWorkTable = new ItemStack( ConfigBlocks.blockTable, 1, 15 );
		this.FilterTube = new ItemStack( ConfigBlocks.blockTube, 1, 3 );
		this.WardedGlass = CommonDependantItems.getItemOrAlt( Config.wardedStone, new ItemStack( ConfigBlocks.blockCosmeticOpaque, 1, 2 ), this.VanillaGlass );
		this.EssentiaMirror = (ItemStack)CommonDependantItems.getItemOrAlt( Config.allowMirrors, new ItemStack( ConfigBlocks.blockMirror, 1, 6 ), this.WardedJar );

		// Items
		this.Thaumometer = new ItemStack( ConfigItems.itemThaumometer );
		this.Thaumonomicon = new ItemStack( ConfigItems.itemThaumonomicon );
		this.EtheralEssence = new ItemStack( ConfigItems.itemWispEssence );
		this.ZombieBrain = new ItemStack( ConfigItems.itemZombieBrain );
		this.QuickSilverDrop = new ItemStack( ConfigItems.itemNugget, 1, 5 );

	}
}
