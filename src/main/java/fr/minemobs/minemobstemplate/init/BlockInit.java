package fr.minemobs.minemobstemplate.init;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import fr.minemobs.minemobstemplate.objects.block.ExampleBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MinemobsTemplate.MOD_ID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", ExampleBlock::new);

}
