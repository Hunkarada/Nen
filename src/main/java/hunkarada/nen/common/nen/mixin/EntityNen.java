package hunkarada.nen.common.nen.mixin;


import hunkarada.nen.common.nen.IEntityNen;
import hunkarada.nen.common.nen.NenMemory;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
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


@Mixin(Entity.class)
public abstract class EntityNen
        implements IEntityNen {

    // effects, which entity has at himself.
    @Unique
    private ArrayList<AbilityEffect> nenAbilityEffects;

    @Unique
    private NenMemory nenMemory;

    @SuppressWarnings("MethodNameSameAsClassName")
    @Inject(method = "<init>", at = @At("RETURN"))
    public void EntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.nenAbilityEffects = new ArrayList<>();
        this.nenMemory = new NenMemory();

    }

    public NbtCompound nen$saveNenEntityToNbt(){
        NbtCompound nbt = new NbtCompound();

        NbtCompound abilityEffectsNbt = new NbtCompound();
        for (AbilityEffect effect : nenAbilityEffects){
            abilityEffectsNbt.putString(AbilityEffect.toNbt(effect), AbilityEffect.toNbt(effect));
        }
        nbt.put("nenAbilityEffects", abilityEffectsNbt);
        //nenMemory Nbt save
        nbt.put("nenMemory", NenMemory.toNbt(nenMemory));

        return nbt;
    }
    public void nen$loadNenEntityFromNbt(NbtCompound nbt){
        // should be string, check is not necessary.
        // key = value anyway, we don't care coz it works like an array, not map
        String[] abilityEffectKeys = nbt.getCompound("nenAbilityEffects").getKeys().toArray(new String[0]);
        for(String key : abilityEffectKeys){
            nenAbilityEffects.add(AbilityEffect.fromNbt(key));
        }
        //nenMemory Nbt load
        nenMemory = NenMemory.fromNbt(nbt);
    }

    @Inject(method = "writeNbt", at = @At("RETURN"))
    public void nen$writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir){
        nbt.put("entityNen", nen$saveNenEntityToNbt());
    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    public void nen$readNbt(NbtCompound nbt, CallbackInfo ci){
        NbtCompound unpackedNbt = nbt.getCompound("entityNen");
        nen$loadNenEntityFromNbt(unpackedNbt);
    }
    @Inject(method = "tick", at = @At("RETURN"))
    public void nen$tick(CallbackInfo ci){
        for (AbilityEffect effect : nenAbilityEffects){
            if (effect.calcDuration()){
                effect.tickEffect((Entity) (Object) this);
            }
            else {
                nen$removeNenAbilityEffect(effect);
            }
        }
    }

//NenAbilityEffects

    // Usage of set methods is UNSAFE! IT DOESN'T CHECK ANYTHING.
    public ArrayList<AbilityEffect> nen$getNenAbilityEffects(){
        return this.nenAbilityEffects;
    }
    public void nen$setNenAbilityEffects(ArrayList<AbilityEffect> nenAbilityEffects){
        this.nenAbilityEffects = nenAbilityEffects;
    }

    @Override
    public void nen$applyNenAbilityEffect(AbilityEffect nenAbilityEffect, PlayerEntity player, double nenPower) {
        nenAbilityEffect.prepareEffect(player, nenPower);
        if (nenAbilityEffect.getIsInstant()){
            nenAbilityEffect.applyInstantEffectOnEntity((Entity) (Object) this, player, nenPower);
        }
        else {
            this.nenAbilityEffects.add(nenAbilityEffect);
        }
    }

    @Override
    public void nen$removeNenAbilityEffect(AbilityEffect nenAbilityEffect) {
        nenAbilityEffect.removeEffect((Entity) (Object) this);
        this.nenAbilityEffects.remove(nenAbilityEffect);
    }

    //NenMemory
    public NenMemory nen$getNenMemory() {
        return nenMemory;
    }

    public void nen$setNenMemory(NenMemory nenMemory) {
        this.nenMemory = nenMemory;
    }

    public void nen$writeToNenMemory(NenMemory.NenMemoryKey id, String data){
        this.nenMemory.write(id, data);
    }
    @Nullable
    public String nen$readFromNenMemory(NenMemory.NenMemoryKey id){
        return this.nenMemory.read(id);
    }


}




