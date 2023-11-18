package hunkarada.nen.common.nen.mixin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.util.ArrayList;
import java.util.HashMap;


@Mixin(Entity.class)
public abstract class EntityNen
        implements IEntityNen {
    @Unique
    ArrayList<AbilityEffect> nenAbilityEffects;

    @Unique
    private HashMap<String, String> nenMemory;

    @Unique
    Gson gson = new Gson();


//    public EntityNen(EntityType<?> type, World world) {super(type, world);}

    @Inject(method = "<init>", at = @At("RETURN"))
    public void EntityNen(EntityType<?> type, World world, CallbackInfo callbackInfo) {
        this.nenAbilityEffects = new ArrayList<>();
        this.nenMemory = new HashMap<>();

    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void nen$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){

        nbt.putString("nenAbilityEffects",gson.toJson(nenAbilityEffects));
        nbt.putString("nenMemory", gson.toJson(nenMemory));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void nen$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        this.nenAbilityEffects = gson.fromJson(nbt.getString("nenAbilityEffects"), new TypeToken<ArrayList<AbilityEffect>>(){}.getType());
        this.nenMemory = gson.fromJson(nbt.getString("nenMemory"), new TypeToken<HashMap<String, String>>(){}.getType());

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




