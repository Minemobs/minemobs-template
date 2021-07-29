package fr.minemobs.minemobstemplate.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;

public class FallingBlockFallEvent extends EntityEvent {

    public final World level;

    public FallingBlockFallEvent(FallingBlockEntity entity) {
        super(entity);
        this.level = entity.level;
    }

    @Override
    public FallingBlockEntity getEntity() {
        return (FallingBlockEntity) super.getEntity();
    }
}
