package hunkarada.nen.common.nen.mixin;


import com.google.gson.Gson;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;


@Mixin(Entity.class)
public abstract class EntityNen
        implements IEntityNen {
    // effects, which caster has at himself.
    @Unique
    ArrayList<AbilityEffect> nenAbilityEffects;

    @Unique
    private HashMap<String, String> nenMemory;


//    public EntityNen(EntityType<?> type, World world) {super(type, world);}

    @Inject(method = "<init>", at = @At("RETURN"))
    public void EntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.nenAbilityEffects = new ArrayList<>();
        this.nenMemory = new HashMap<>();

    }

    @Inject(method = "writeNbt", at = @At("RETURN"))
    public void nen$writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir){

    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    public void nen$readNbt(NbtCompound nbt, CallbackInfo ci){

    }

//NenAbilityEffects
    public ArrayList<AbilityEffect> nen$getNenAbilityEffects(){
        return this.nenAbilityEffects;
    }
    public void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects){
        this.nenAbilityEffects = nenAbilityEffects;
    }

//NenMemory
    public HashMap<String, String> nen$getNenMemory() {
        return nenMemory;
    }

    public void nen$setNenMemory(HashMap<String, String> nenMemory) {
        this.nenMemory = nenMemory;
    }

    public void nen$writeToNenMemory(String id, String data){
        this.nenMemory.put(id, data);
    }
    @Nullable
    public String nen$readFromNenMemory(String id){
        return this.nenMemory.get(id);
    }


}




