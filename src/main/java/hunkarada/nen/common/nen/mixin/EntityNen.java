package hunkarada.nen.common.nen.mixin;


import com.google.gson.Gson;
import hunkarada.nen.common.nen.ability.abstraction.ability.AbilityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Mixin(Entity.class)
public abstract class EntityNen
        implements IEntityNen {
    @Shadow protected abstract NbtList toNbtList(float... values);

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

        //nenAbilityEffects Nbt save
        int size = nenAbilityEffects.size();
        nbt.putInt("nenAbilityEffectsSize", size);
        ArrayList<Integer> collector = new ArrayList<Integer>();
        for(int i = 0; i < size; ++i) {
            String key = "10"+i;
            nbt.putString(key, nenAbilityEffects.get(i).toNbt());
            collector.add(Integer.parseInt(key));
        }
        nbt.putIntArray("nenAbilityEffects", collector);

        //nenMemory Nbt save
        collector.clear();
        int i = 0;
        for (Map.Entry<String, String> item : nenMemory.entrySet()) {
            String key = "20"+i;
            nbt.putString(item.getKey(), item.getValue());
            nbt.putString(key, item.getKey());
            collector.add(Integer.parseInt(key));
            ++i;
        }
        nbt.putIntArray("nenMemory", collector);


    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    public void nen$readNbt(NbtCompound nbt, CallbackInfo ci){
        //nenAbilityEffects Nbt load
        nenAbilityEffects.clear();
        int[] collector = nbt.getIntArray("nenAbilityEffects");
        for(int i = 0; i < collector.length; ++i) {
            nenAbilityEffects.add(???.fromNbt(nbt.getString(String.valueOf(collector[i]))));

            //AbilityEffect idCatch = new AbilityEffect();
            //nbt.getString(String.valueOf(collector[i]));
        }

        //nenMemory Nbt load
        nenMemory.clear();
        collector = nbt.getIntArray("nenMemory");
        String keyGet;

        for(int item : collector) {
            keyGet = nbt.getString(String.valueOf(item));
            nenMemory.put(keyGet, nbt.getString(keyGet));
        }


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




