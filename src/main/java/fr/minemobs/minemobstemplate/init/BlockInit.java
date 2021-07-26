package fr.minemobs.minemobstemplate.init;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MinemobsTemplate.MOD_ID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlock("example_block");

    private static RegistryObject<Block> registerBlock(String name) {
        return BLOCKS.register(name.toLowerCase().replaceAll(" ","_"), () -> new Block(AbstractBlock.Properties
                .copy(Blocks.STONE)));
    }

    private static RegistryObject<Block> registerBlock(String name, Block.Properties properties) {
        return BLOCKS.register(name.toLowerCase().replaceAll(" ","_"), () -> new Block(properties));
    }

    private static RegistryObject<Block> registerBlock(String name, Block block) {
        return BLOCKS.register(name.toLowerCase().replaceAll(" ","_"), () -> block);
    }

}
