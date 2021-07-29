package fr.minemobs.minemobstemplate.listener;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import fr.minemobs.minemobstemplate.event.FallingBlockFallEvent;
import fr.minemobs.minemobstemplate.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = MinemobsTemplate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityListener {

    @SubscribeEvent
    public static void onEntityRemoved(EntityLeaveWorldEvent event) {
        Logger log = LogManager.getLogger();
        if(event.getEntity().getType() != EntityType.FALLING_BLOCK) return;
        MinecraftForge.EVENT_BUS.post(new FallingBlockFallEvent((FallingBlockEntity) event.getEntity()));
    }

    @SubscribeEvent
    public static void onAnvilFall(FallingBlockFallEvent event) {
        if(!BlockTags.ANVIL.contains(event.getEntity().getBlockState().getBlock())) return;
        BlockPos pos = new BlockPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ()).below();
        boolean foundBlock = false;
        if(event.level.getBlockState(pos).getBlock() != Blocks.IRON_BLOCK) return;
        event.level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        ItemEntity item = EntityType.ITEM.create(event.getEntity().level);
        item.setItem(new ItemStack(ItemInit.EXAMPLE_ITEM.get()));
        item.setPos(pos.getX(), pos.getY() + .25, pos.getZ());
        item.setGlowing(true);
        event.level.addFreshEntity(item);
    }

}