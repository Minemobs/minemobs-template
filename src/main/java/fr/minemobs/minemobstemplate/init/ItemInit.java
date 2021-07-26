package fr.minemobs.minemobstemplate.init;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import fr.minemobs.minemobstemplate.objects.item.ExampleItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinemobsTemplate.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", ExampleItem::new);

}
