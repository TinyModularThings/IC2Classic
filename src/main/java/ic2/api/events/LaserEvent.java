package ic2.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LaserEvent extends LevelEvent
{
	public final Entity laser;
	public final LivingEntity source;
	public float range = 0F;
	public float power = 0F;
	public int blockBreaks = 0;
	public boolean explosive = false;
	public boolean smelt = false;
	
	public LaserEvent(LevelAccessor world, Entity laser, LivingEntity source, float range, float power, int blockBreaks, boolean explosive, boolean smelt)
	{
		super(world);
		this.laser = laser;
		this.source = source;
		this.range = range;
		this.power = power;
		this.blockBreaks = blockBreaks;
		this.explosive = explosive;
		this.smelt = smelt;
	}
	
	public static class LaserShootEvent extends LaserEvent
	{
		public final ItemStack item;
		
		public LaserShootEvent(LevelAccessor world, Entity laser, LivingEntity source, float range, float power, int blockBreaks, boolean explosive, boolean smelt, ItemStack item)
		{
			super(world, laser, source, range, power, blockBreaks, explosive, smelt);
			this.item = item;
		}
	}
	
	public static class LaserExplodeEvent extends LaserEvent
	{
		public float explosionPower;
		public float explosionDropRate;
		
		public LaserExplodeEvent(LevelAccessor world, Entity laser, LivingEntity source, float range, float power, int blockBreaks, boolean explosive, boolean smelt, float explosionPower, float explosionDropRate)
		{
			super(world, laser, source, range, power, blockBreaks, explosive, smelt);
			this.explosionPower = explosionPower;
			this.explosionDropRate = explosionDropRate;
		}
	}
	
	public static class LaserEntityHitEvent extends LaserEvent
	{
		public Entity hitEntity;
		public boolean passThrough = false;
		
		public LaserEntityHitEvent(LevelAccessor world, Entity laser, LivingEntity source, float range, float power, int blockBreaks, boolean explosive, boolean smelt, Entity hitEntity)
		{
			super(world, laser, source, range, power, blockBreaks, explosive, smelt);
			this.hitEntity = hitEntity;
		}
	}
	
	public static class LaserBlockHitEvent extends LaserEvent
	{
		public final BlockPos pos;
		public final Direction side;
		public boolean removeBlock;
		public boolean dropBlock;
		public float dropChance;
		
		public LaserBlockHitEvent(LevelAccessor world, Entity laser, LivingEntity source, float range, float power, int blockBreaks, boolean explosive, boolean smelt, BlockHitResult result, boolean removeBlock, boolean dropBlock, float dropChance)
		{
			super(world, laser, source, range, power, blockBreaks, explosive, smelt);
			pos = result.getBlockPos();
			side = result.getDirection();
			this.removeBlock = removeBlock;
			this.dropBlock = dropBlock;
			this.dropChance = dropChance;
		}
	}
}
