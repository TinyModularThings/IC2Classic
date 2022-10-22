package ic2.api.crops;

import java.util.List;
import java.util.Set;

import ic2.api.util.ILocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ICropTile extends ILocation
{
	/**
	 * Get's the current Farmland underneath the CropTile
	 * @return the farmland
	 */
	IFarmland getFarmland();
	
	/** @return true if the crop tile is waterlogged */
	boolean isWaterLogged();
	
	/**
	 * @param water if the crop should be waterlogged
	 */
	void setWaterlogged(boolean water);
	
	/**
	 * Get's the planted crop
	 * @return the planted crop
	 */
	ICrop getCrop();
	
	/**
	 * Set's the planted Crop
	 * @param crop the crop to be planted
	 */
	void setCrop(ICrop crop);
	
	/**
	 * Get's the current Crop Growth Stage
	 * @return the current Crop Growth Stage
	 */
	int getGrowthStage();
	
	/**
	 * Set's the current Crop Growth Stage
	 * @param stage that should be set
	 */
	void setGrowthStage(int stage);
	
	/**
	 * Get's the current amount of points the Crop has grown.
	 * @return growth Points of the crop.
	 */
	int getGrowthPoints();
	
	/**
	 * Set's the current amount of points the Crop has grown.
	 * @param points of the crop.
	 */
	void setGrowthPoints(int points);
	
	/**
	 * Get's the Scan Level of the Crop.
	 * @return Crop Scan Level
	 */
	int getScanLevel();
	
	/**
	 * Sets the Crop Scan Level
	 * @param level of the crop
	 */
	void setScanLevel(int level);
	
	/**
	 * @return true if the Crop is Breeding.
	 */
	boolean isCrossBreeding();
	
	/**
	 * Set's the crops breeding state
	 * @param breed if the crop should be in a breeding state
	 */
	void setCrossBreeding(boolean breed);
	
	/**
	 * Get's Gain Stat of the current crop
	 * @return gain Stat
	 */
	int getGainStat();
	
	/**
	 * Set's Gain Stat of the current crop
	 * @param level it should be
	 */
	void setGainStat(int level);
	
	/**
	 * Get's Gain Stat of the current crop
	 * @return gain Stat
	 */
	int getGrowthStat();
	
	/**
	 * Set's Gain Stat of the current crop
	 * @param level it should be
	 */
	void setGrowthStat(int level);
	
	/**
	 * Get's Gain Stat of the current crop
	 * @return gain Stat
	 */
	int getResistanceStat();
	
	/**
	 * Set's Gain Stat of the current crop
	 * @param level it should be
	 */
	void setResistanceStat(int level);
	
	/**
	 * If the crop is ready to crossbreed.
	 * @return true if it can crossbreed.
	 */
	boolean canBreed();
	
	/**
	 * Resets the Environment Qualities of the crop to be recalculate on the next crop tick.
	 */
	void requestStateUpdate();
	
	/**
	 * Requests a Synchronization of the custom Crop Specific NBT Data.
	 */
	void onCustomDataChanged();
	
	/**
	 * Calculates the crop growth for the next crop tick, once calculated this is cached.
	 * @return the next growth ticks points that will be added
	 */
	int calculateGrowthSpeed();
	
	/**
	 * Performs a Manual Harvest of the Crops
	 * @return true if the harvest was successful (drops items on to the ground)
	 */
	boolean performManualHarvest();
	
	/**
	 * Performs the harvest and returns the drops of the Crops
	 * @param manual if the harvest is manual.
	 * @return the drops of the Crop.
	 */
	List<ItemStack> performHarvest(boolean manual);
	
	/**
	 * Removes the crop from the Tile and spawns a crop seed.
	 * @return if anything happend.
	 */
	boolean pickCrop();
	
	/**
	 * Destroyes the crop with 0 drops.
	 */
	void removeCrop();
	
	/**
	 * Checks if the desired BlockState is below. Checks the SubSoils and the block underneath the SubSoil. If air blocks are between causes to stop checks at that point.
	 * @param state that you search for.
	 * @return true if it is underneath.
	 */
	boolean isBlockBelow(BlockState state);
	
	/**
	 * Checks if the desired Block is below. Checks the SubSoils and the block underneath the SubSoil. If air blocks are between causes to stop checks at that point.
	 * @param block that you search for.
	 * @return true if it is underneath.
	 */
	boolean isBlockBelow(Block block);
	
	/**
	 * Checks if the desired BlockTag is below. Checks the SubSoils and the block underneath the SubSoil. If air blocks are between causes to stop checks at that point.
	 * @param tag that you search for.
	 * @return true if it is underneath.
	 */
	boolean isBlockBelow(TagKey<Block> tag);
	
	/**
	 * Helper function that gets all blocks underneath the crop 
	 * Stops when it encounters air.
	 * @return the list of blocks below the Crop
	 */
	Set<Block> getBlocksBelow();
	
	/**
	 * Crop Specific NBT Data that can be synchronized.
	 * @return the Crop Specific Data
	 */
	CompoundTag getCustomData();
	
	/**
	 * Gets the Light Level at the Crops position.
	 * @return the current light level.
	 */
	int getLightLevel();
	
	/**
	 * Get's or calculates the Crop Environment Quality.
	 * @return 0-10 for the current quality.
	 */
	int getEnvironmentQuality();
	
	/**
	 * Get's or calculates the Crop Nutrient Level.
	 * @return 0-20 for the current Nutrient Level.
	 */
	int getNutrients();
	
	/**
	 * Get's or calculates the Crop Humidity Level.
	 * @return 0-20 for the current Humidity Level.
	 */
	int getHumidity();
	
	/**
	 * Get's the current Water stored in the crop.
	 * @return 0-200 based on how much water there is stored.
	 */
	int getWaterStorage();
	
	/**
	 * Set's the current water storage.
	 * @param water between 0-200
	 */
	void setWaterStorage(int water);
	
	/**
	 * Get's the current Fertilizer stored in the crop.
	 * @return 0-300 based on how much Fertilizer there is stored.
	 */
	int getFertilizerStorage();
	
	/**
	 * Set's the current Fertilizer storage.
	 * @param fertilizer between 0-300
	 */
	void setFertilizerStorage(int fertilizer);
	
	/**
	 * Get's the current Weedex Stored
	 * @return 0-150 based on how much WeedEx is stored.
	 */
	int getWeedExStorage();

	/**
	 * Set's the current WeedEx storage.
	 * @param weedEx between 0-150
	 */
	void setWeedExStorage(int weedEx);
	
	/**
	 * Creates A Crop Seed Bag with the desired Crop and stats and scan Level
	 * @param crop that the seed should have.
	 * @param growthStat that the seed should have
	 * @param gainStat that the seed should have
	 * @param resistanceStat that the seed should have
	 * @param scanLevel that the seed should have
	 * @return a Seed Bag
	 */
	ItemStack createSeeds(ICrop crop, int growthStat, int gainStat, int resistanceStat, int scanLevel);
}
