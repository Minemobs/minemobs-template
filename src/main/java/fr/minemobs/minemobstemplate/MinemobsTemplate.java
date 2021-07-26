package fr.minemobs.minemobstemplate;

import fr.minemobs.minemobstemplate.init.BlockInit;
import fr.minemobs.minemobstemplate.init.FluidInit;
import fr.minemobs.minemobstemplate.init.ItemInit;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Mod("minemobstemplate")
@Mod.EventBusSubscriber(modid = "minemobstemplate", bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinemobsTemplate {

    public static final String MOD_ID = "minemobstemplate";
    public static final Logger LOGGER = LogManager.getLogger();

    public MinemobsTemplate() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream()
                .filter(blockRegistryObject -> !(blockRegistryObject.get() instanceof FlowingFluidBlock) && !(blockRegistryObject.get() instanceof TorchBlock))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().tab(ModItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties.tab(ModItemGroup.instance));
            blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
            registry.register(blockItem);
        });
    }

    public static ResourceLocation location(String _path)
    {
        return new ResourceLocation(MOD_ID, _path);
    }

    public static class ModItemGroup extends ItemGroup {

        public static final ModItemGroup instance = new ModItemGroup(ItemGroup.TABS.length, MOD_ID);

        private ItemStack item;

        public ModItemGroup(int index, String label) {
            super(index, label);
        }

        int tick = 0;

        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack getIconItem() {
            if(tick != 20) {
                tick++;
                return item != null ? item : new ItemStack(ItemInit.EXAMPLE_ITEM.get());
            }
            //Todo: Fix BlockItem not added in the list
            List<ItemStack> items = ItemInit.ITEMS.getEntries().stream()
                    .map(item -> new ItemStack(item.get())).collect(Collectors.toList());
            item = items.get(new Random().nextInt(items.size()));
            tick = 0;
            return item;
        }

        @SuppressWarnings("NullableProblems")
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
        }
    }
}
