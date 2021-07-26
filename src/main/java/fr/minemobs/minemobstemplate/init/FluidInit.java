package fr.minemobs.minemobstemplate.init;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class FluidInit {

    public static final ResourceLocation[] FLUID_RL = {MinemobsTemplate.location("liquid/liquid_still"), MinemobsTemplate.location("liquid/liquid_flow")};

    public static final List<FluidObject> FLUIDS_LIST = new ArrayList<>();
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MinemobsTemplate.MOD_ID);

    public static final FluidObject EXAMPLE_FLUID = register("example_fluid", Color.GREEN);

    private static FluidObject register(@Nonnull String name, @Nonnull Color color) {
        ResourceLocation[] resourcesLocations = FLUID_RL;
        final ForgeFlowingFluid.Properties[] properties = {null};
        RegistryObject<FlowingFluid> FLUID = FLUIDS.register(name,
                () -> new ForgeFlowingFluid.Source(properties[0]));
        RegistryObject<FlowingFluid> FLOWING = FLUIDS.register(name + "_flowing",
                () -> new ForgeFlowingFluid.Flowing(properties[0]));
        RegistryObject<FlowingFluidBlock> blockFluid = BlockInit.BLOCKS.register(name, () ->
                new FlowingFluidBlock(FLUID, AbstractBlock.Properties.copy(Blocks.WATER).noCollission().strength(100.0f).noDrops().noOcclusion()));
        RegistryObject<BucketItem> BUCKET = ItemInit.ITEMS.register(name + "_bucket",
                () -> new BucketItem(FLUID, new Item.Properties().tab(MinemobsTemplate.ModItemGroup.instance).stacksTo(1)));
        properties[0] = new ForgeFlowingFluid.Properties(FLUID, FLOWING, FluidAttributes.builder(resourcesLocations[0], resourcesLocations[1])
                .viscosity(3)
                .density(5)
                .color(color.getRGB()))
                .bucket(BUCKET)
                .block(blockFluid);
        FluidObject fluid = new FluidObject(properties[0], FLUID, FLOWING, blockFluid, BUCKET);
        FLUIDS_LIST.add(fluid);
        return fluid;
    }

    public static class FluidObject {
        private final ForgeFlowingFluid.Properties FLUID_PROPERTIES;
        private final RegistryObject<FlowingFluid> STILL_FLUID_REGISTRY;
        private final RegistryObject<FlowingFluid> FLOWING_FLUID_REGISTRY;
        private final RegistryObject<FlowingFluidBlock> blockFluid;
        private final RegistryObject<BucketItem> bucket;

        private FluidObject(ForgeFlowingFluid.Properties fluid_properties, RegistryObject<FlowingFluid> STILL_FLUID_REGISTRY,
                            RegistryObject<FlowingFluid> flowing_fluid_registry, RegistryObject<FlowingFluidBlock> blockFluid, RegistryObject<BucketItem> bucket) {
            FLUID_PROPERTIES = fluid_properties;
            this.STILL_FLUID_REGISTRY = STILL_FLUID_REGISTRY;
            FLOWING_FLUID_REGISTRY = flowing_fluid_registry;
            this.blockFluid = blockFluid;
            this.bucket = bucket;
        }

        public ForgeFlowingFluid.Properties getFluidProperties() {
            return FLUID_PROPERTIES;
        }

        public RegistryObject<FlowingFluid> getStillFluid() {
            return STILL_FLUID_REGISTRY;
        }

        public RegistryObject<FlowingFluid> getFlowingFluid() {
            return FLOWING_FLUID_REGISTRY;
        }

        public RegistryObject<FlowingFluidBlock> getBlockFluid() {
            return blockFluid;
        }

        public RegistryObject<BucketItem> getBucket() {
            return bucket;
        }
    }

}
