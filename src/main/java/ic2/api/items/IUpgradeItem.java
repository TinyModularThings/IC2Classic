package ic2.api.items;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nullable;

import ic2.api.recipes.ingridients.queue.IStackOutput;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.api.recipes.registries.IMachineRecipeList.RecipeEntry;
import ic2.api.tiles.IMachine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface IUpgradeItem
{
	UpgradeType getType(ItemStack stack);
	
	EnumSet<Functions> getFunctions(ItemStack stack);
	
	void onInstall(ItemStack stack, IMachine machine);
	
	double getProcessingSpeedMultiplier(ItemStack stack, IMachine machine);
	int getExtraProcessingSpeed(ItemStack stack, IMachine machine);
	
	double getProcessingTimeMultiplier(ItemStack stack, IMachine machine);
	int getExtraProcessingTime(ItemStack stack, IMachine machine);
	
	double getEnergyDemandMultiplier(ItemStack stack, IMachine machine);
	int getExtraEnergyDemand(ItemStack stack, IMachine machine);
	
	double getEnergyStorageMultiplier(ItemStack stack, IMachine machine);
	int getExtraEnergyStorage(ItemStack stack, IMachine machine);
	
	int getExtraTier(ItemStack stack, IMachine machine);
	
	float getSoundMultiplier(ItemStack stack, IMachine machine);
	
	boolean useRedstoneInvertion(ItemStack stack, IMachine machine);
	
	void onTick(ItemStack stack, IMachine machine);
	
	void onMachineFinishedRecipePre(ItemStack stack, IMachine machine, IRecipeOutput output, CompoundTag recipeFlags);
	void onMachineFinishedRecipePost(ItemStack stack, IMachine machine, @Nullable RecipeEntry entry, List<IStackOutput> drops);
	
	void onMachineProcessed(ItemStack stack, IMachine machine);
	
	enum UpgradeType
	{
		RECIPE_MOD("ProcessingTime", "EnergyDemand", "ProcessingSpeed"),
		MACHINE_MOD("EnergyStorage", "ExtraTier"),
		REDSTONE_MOD("RedstoneInvertion"),
		PROCESSING_MOD,
		TRANSPORT_MOD,
		AUDIO_MOD,
		CUSTOM_MOD;
		
		String[] names;
		UpgradeType(String... names)
		{
			this.names = names;
		}
	}
	
	enum Functions
	{
		TICK,
		RECIPE
	}
}
