package ic2.core.block.machine.high;

import java.math.RoundingMode;
import java.util.Arrays;

import com.google.common.math.DoubleMath;

import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.network.adv.NetworkField.BitLevel;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.comparator.ComparatorManager;
import ic2.core.block.base.util.comparator.comparators.ComparatorAmplifier;
import ic2.core.block.base.util.comparator.comparators.ComparatorProgress;
import ic2.core.block.base.util.info.AmplifierInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.machine.high.container.ContainerMassFabricator;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.custom.MassFabricatorGui;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2BlockLang;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityMassFabricator extends TileEntityElecMachine implements ITickable, IProgressMachine, IHasGui {
    @NetworkField(index = 7)
    public int scrap = 0;
    @NetworkField(index = 8, compression = BitLevel.Bit8)
    public int state = 0;
    private int prevState = 0;
    public int lastScrap = 0;
    private AudioSource audioSource;
    private AudioSource audioSourceScrap;
    private float multiplier;
    private boolean requireAmplifier;

    public TileEntityMassFabricator() {
        super(2, 512);
        this.multiplier = IC2.config.getFloat("uuEU");
        this.requireAmplifier = IC2.config.getFlag("UUScrap");
        this.maxEnergy = DoubleMath.roundToInt(7100000.0D * this.multiplier, RoundingMode.UP);
        this.addNetworkFields(new String[] { "state" });
        this.addGuiFields(new String[] { "scrap" });
        this.addInfos(new InfoComponent[] { new ProgressInfo(this), new AmplifierInfo(this) });
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Import, new int[] { 0 });
        handler.registerDefaultSlotAccess(AccessRule.Export, new int[] { 1 });
        handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, new int[] { 0 });
        handler.registerDefaultSlotsForSide(RotationList.VERTICAL, new int[] { 1 });
        handler.registerInputFilter(CommonFilters.MassFab, new int[] { 0 });
        handler.registerSlotType(SlotType.Input, new int[] { 0 });
        handler.registerSlotType(SlotType.Output, new int[] { 1 });
    }

    @Override
    protected void addComparators(ComparatorManager manager) {
        super.addComparators(manager);
        manager.removeComparator("EnergyStorage");
        manager.addComparatorMode(new ComparatorProgress(this));
        manager.addComparatorMode(new ComparatorAmplifier(this));
    }

    @Override
    public float getProgress() {
        return this.energy;
    }

    @Override
    public float getMaxProgress() {
        return 7000000.0F * this.multiplier;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2BlockLang.massFabricator;
    }

    @Override
    public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
        if (((amount > 512.0D) || (amount <= 0.0D)) || (this.requireAmplifier && this.scrap <= 0)) {
        } else {
            int bonus = (int) amount;
            if (bonus > this.scrap) {
                bonus = this.scrap;
            }

            this.scrap -= bonus;
            this.energy = (int) (this.energy + amount + 5 * bonus);
            this.getNetwork().updateTileGuiField(this, "energy");
            this.getNetwork().updateTileGuiField(this, "scrap");
            if (this.scrap < 1000 && !this.inventory.get(0).isEmpty()) {
                this.refillAmplifier();
            }
        }
        return 0.0D;
    }

    @Override
    public double getDemandedEnergy() {
        return !this.isRedstonePowered() && (!this.requireAmplifier || this.scrap > 0) ? super.getDemandedEnergy()
                : 0.0D;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.scrap = nbt.getInteger("Scrap");
        this.lastScrap = nbt.getInteger("LastScrap");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Scrap", this.scrap);
        nbt.setInteger("LastScrap", this.lastScrap);
        return nbt;
    }

    @Override
    public void onUnloaded() {
        if (this.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource = null;
            this.audioSourceScrap = null;
        }

        super.onUnloaded();
    }

    @Override
    public void onNetworkUpdate(String field) {
        if (field.equals("state") && this.prevState != this.state) {
            if (this.audioSource != null && this.audioSource.isRemoved()) {
                this.audioSource = null;
            }

            if (this.audioSourceScrap != null && this.audioSourceScrap.isRemoved()) {
                this.audioSourceScrap = null;
            }

            if (this.state == 0) {
                if (this.audioSource != null) {
                    this.audioSource.stop();
                }

                if (this.audioSourceScrap != null) {
                    this.audioSourceScrap.stop();
                }
            } else if (this.state == 1) {
                if (this.audioSource == null) {
                    this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, Ic2Sounds.massFabLoop,
                            true, false, IC2.audioManager.defaultVolume);
                }

                if (this.audioSource != null) {
                    this.audioSource.play();
                }

                if (this.audioSourceScrap != null) {
                    this.audioSourceScrap.stop();
                }
            } else if (this.state == 2) {
                if (this.audioSource == null) {
                    this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, Ic2Sounds.massFabLoop,
                            true, false, IC2.audioManager.defaultVolume);
                }

                if (this.audioSourceScrap == null) {
                    this.audioSourceScrap = IC2.audioManager.createSource(this, PositionSpec.Center,
                            Ic2Sounds.massFabScrapLoop, true, false, IC2.audioManager.defaultVolume);
                }

                if (this.audioSource != null) {
                    this.audioSource.play();
                }

                if (this.audioSourceScrap != null) {
                    this.audioSourceScrap.play();
                }
            }

            this.prevState = this.state;
        }

        super.onNetworkUpdate(field);
    }

    private void setState(int state) {
        this.state = state;
        if (this.prevState != state) {
            this.getNetwork().updateTileEntityField(this, "state");
        }

        this.prevState = state;
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public boolean needsInitialRedstoneUpdate() {
        return true;
    }

    @Override
    public void update() {
        this.handleRedstone();
        this.updateNeighbors();
        if (!this.isRedstonePowered() && this.energy > 0) {
            this.setState(this.scrap > 0 ? 2 : 1);
            this.setActive(true);
            if (this.scrap < 1000 && !this.inventory.get(0).isEmpty()) {
                this.refillAmplifier();
            }

            if (this.energy >= this.getMaxProgress()) {
                this.attemptGeneration();
                this.notifyNeighbors();
            }
        } else {
            this.setState(0);
            this.setActive(false);
            if (this.requireAmplifier && this.scrap < 1000 && !this.inventory.get(0).isEmpty()) {
                this.refillAmplifier();
            }
        }

        this.updateComparators();
    }

    public void refillAmplifier() {
        RecipeEntry entry = ClassicRecipes.massfabAmplifier.getRecipeInAndOutput(this.inventory.get(0), false);
        if (entry != null) {
            if (this.inventory.get(0).getItem().hasContainerItem(this.inventory.get(0))) {
                this.inventory.set(0, this.inventory.get(0).getItem().getContainerItem(this.inventory.get(0)));
            } else {
                this.inventory.get(0).shrink(entry.getInput().getAmount());
            }

            int value = entry.getOutput().getMetadata().getInteger("amplification");
            this.scrap += value;
            this.lastScrap = value;
            this.getNetwork().updateTileGuiField(this, "scrap");
        }

    }

    public void attemptGeneration() {
        if (this.inventory.get(1).isEmpty()) {
            this.inventory.set(1, Ic2Items.uuMatter.copy());
            this.energy = (int) (this.energy - this.getMaxProgress());
            this.getNetwork().updateTileGuiField(this, "energy");
        } else if (StackUtil.isStackEqual(this.inventory.get(1), Ic2Items.uuMatter)
                && this.inventory.get(1).getCount() < this.inventory.get(1).getMaxStackSize()) {
            this.energy = (int) (this.energy - this.getMaxProgress());
            this.getNetwork().updateTileGuiField(this, "energy");
            this.inventory.get(1).grow(1);
        }
    }

    @Override
    public double getWrenchDropRate() {
        return 0.75D;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerMassFabricator(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return MassFabricatorGui.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer player) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !this.isInvalid();
    }

    @Override
    public boolean hasGui(EntityPlayer player) {
        return true;
    }

    public static void init() {
        addAmplifier(Ic2Items.scrap.copy(), 5000, "ScrapAmplifier");
        addAmplifier(Ic2Items.scrapBox.copy(), 45000, "ScrapboxAmplifier");
        addAmplifier(Ic2Items.scrapMetal.copy(), 100000, "ScrapMetalAmplifier");
    }

    public static void addAmplifier(ItemStack input, int amplifier, String id) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("amplification", amplifier);
        ClassicRecipes.massfabAmplifier.addRecipe(new RecipeInputItemStack(input),
                new MachineOutput(nbt, Arrays.asList(Ic2Items.uuMatter)), id);
    }
}