package ic2.api.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public final class IC2DamageSource extends DamageSource
{
	public static final IC2DamageSource ELECTRICITY = new IC2DamageSource("electricity");
	public static final IC2DamageSource NUKE = new IC2DamageSource("nuke");
	public static final IC2DamageSource RADIATION = new IC2DamageSource("radiation");
	
	Entity source;
	
	private IC2DamageSource(String damageTypeIn)
	{
		super(damageTypeIn);
	}
	
	public static IC2DamageSource newShockDamage(Entity entity)
	{
		IC2DamageSource source = new IC2DamageSource(ELECTRICITY.getMsgId());
		source.source = entity;
		return source;
	}
	
	@Override
	public Entity getEntity()
	{
		return source;
	}
}
