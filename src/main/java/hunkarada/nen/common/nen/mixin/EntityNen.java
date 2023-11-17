package hunkarada.nen.common.nen.mixin;


import hunkarada.nen.common.nen.NenType;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;


@Mixin(Entity.class)
public abstract class EntityNen
        implements IEntityNen {
    @Unique
    ArrayList<AbilityEffect> nenAbilityEffects;

    @Unique
    private HashMap<String, String> nenMemory;


    public EntityNen(EntityType<?> type, World world) {
        super(type, world);
    }
    //here we are add variables for each entity.
    @Inject(method = "<init>", at = @At("RETURN"))
    public void EntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.nenAbilityEffects = new ArrayList<>();
        this.nenMemory = new HashMap<>();

    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void nen$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){

        nbt.putString("nenAbilityEffects",nenAbilityEffects.toString());
        nbt.putString("nenMemory", );
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void nen$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        this.nenExp = nbt.getInt("nenExp");
        this.nenType = nenType.fromNbt(nbt.getString("nenType"));
        this.nenAbilityPoints = nbt.getInt("nenAbilityPoints");
        this.nenRestrictions =
        this.nenAbilities =
        this.nenAbilityEffects =  nbt.getString("nenAbilityEffects")

    }

}




