package ic2.core.inventory.gui.custom;

import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlasmafierGui extends GuiComponentContainer {

    @SuppressWarnings("rawtypes")
    public PlasmafierGui(ContainerComponent inventorySlotsIn) {
        super(inventorySlotsIn);
    }

}