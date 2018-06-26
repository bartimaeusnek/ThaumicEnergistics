package thaumicenergistics.common.integration.tc;

import appeng.api.AEApi;
import appeng.api.storage.data.IAEFluidStack;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.common.ThaumicEnergistics;
import thaumicenergistics.common.fluids.GaseousEssentia;
import thaumicenergistics.common.storage.AspectStack;

/**
 * Aids in converting essentia to and from a fluid.
 *
 * @author Nividica
 *
 */
public final class EssentiaConversionHelper
{
	/**
	 * Singleton
	 */
	public static final EssentiaConversionHelper INSTANCE = new EssentiaConversionHelper();

	/**
	 * Private constructor
	 */
	private EssentiaConversionHelper()
	{

	}

	/**
	 * Converts an AE fluid stack into an AspectStack.
	 *
	 * @param fluidStack
	 * @return Aspect stack if converted, null otherwise.
	 */
	public static AspectStack convertAEFluidStackToAspectStack( final IAEFluidStack fluidStack )
	{
		// Is the fluid an essentia gas?
		if( fluidStack.getFluid() instanceof GaseousEssentia )
		{
			// Create an aspect stack to match the fluid
			return new AspectStack( ( (GaseousEssentia)fluidStack.getFluid() ).getAspect(), EssentiaConversionHelper.convertFluidAmountToEssentiaAmount( fluidStack
							.getStackSize() ) );
		}

		return null;
	}

	/**
	 * Converts an essentia amount into a fluid amount(mb).
	 *
	 * @param essentiaAmount
	 * @return
	 */
	public static long convertEssentiaAmountToFluidAmount( final long essentiaAmount )
	{
		return essentiaAmount * ThaumicEnergistics.config.conversionMultiplier();
	}

	/**
	 * Converts a fluid amount(mb) into an essentia amount.
	 *
	 * @param fluidAmount
	 * @return
	 */
	public static long convertFluidAmountToEssentiaAmount( final long fluidAmount )
	{
		return fluidAmount / ThaumicEnergistics.config.conversionMultiplier();
	}

	/**
	 * Creates an AE fluid stack from the specified essentia gas. This will
	 * convert the specified amount from essentia units to fluid units(mb).
	 *
	 * @param Aspect
	 * @param essentiaAmount
	 * @return
	 */
	public static IAEFluidStack createAEFluidStackInEssentiaUnits( final Aspect aspect, final long essentiaAmount )
	{
		GaseousEssentia essentiaGas = GaseousEssentia.getGasFromAspect( aspect );

		if( essentiaGas == null )
		{
			return null;
		}

		return EssentiaConversionHelper.createAEFluidStackInFluidUnits( essentiaGas, EssentiaConversionHelper.convertEssentiaAmountToFluidAmount( essentiaAmount ) );
	}

	/**
	 * Creates an AE fluid stack from the specified essentia gas. This will
	 * convert the specified amount from essentia units to fluid units(mb).
	 *
	 * @param essentiaGas
	 * @param essentiaAmount
	 * @return
	 */
	public static IAEFluidStack createAEFluidStackInEssentiaUnits( final GaseousEssentia essentiaGas, final long essentiaAmount )
	{
		return EssentiaConversionHelper.createAEFluidStackInFluidUnits( essentiaGas, EssentiaConversionHelper.convertEssentiaAmountToFluidAmount( essentiaAmount ) );
	}

	/**
	 * Creates an AE fluid stack from the specified essentia gas with the amount
	 * specified.
	 *
	 * @param essentiaGas
	 * @param fluidAmount
	 * @return
	 */
	public static IAEFluidStack createAEFluidStackInFluidUnits( final GaseousEssentia essentiaGas, final long fluidAmount )
	{
		IAEFluidStack ret = null;
		try
		{
			ret = AEApi.instance().storage().createFluidStack( new FluidStack( essentiaGas, 1 ) );

			ret.setStackSize( fluidAmount );
		}
		catch(@SuppressWarnings("unused") Exception e )
		{
		}

		return ret;
	}
}
