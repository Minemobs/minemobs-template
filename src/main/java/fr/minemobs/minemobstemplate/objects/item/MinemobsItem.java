package fr.minemobs.minemobstemplate.objects.item;

import fr.minemobs.minemobstemplate.MinemobsTemplate;
import net.minecraft.item.Item;

public abstract class MinemobsItem extends Item {

    public MinemobsItem() {
        super(new Properties().tab(MinemobsTemplate.ModItemGroup.instance));
    }
}
