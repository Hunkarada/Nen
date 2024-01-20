package hunkarada.nen.common.nen;

import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.Random;

public enum NenType {
    ENHANCEMENT("Enhancement"), EMISSION("Emission"), MANIPULATION("Manipulation"), SPECIALIZATION("Specialization"), CONJURATION("Conjuration"), TRANSMUTATION("Transmutation"), UNIDENTIFIED("Unidentified");
    private final String id;
    NenType(String id){
        this.id = id;
    }
    @Override
    public String toString(){
        return id;
    }
    public static String toNbt(NenType nenType){
        return nenType.id;
    }
    // gives a random nen type, but NOT UNIDENTIFIED.
    public static NenType randomType(){
        ArrayList<NenType> nenTypes = new ArrayList<>();
        nenTypes.add(NenType.ENHANCEMENT); nenTypes.add(NenType.EMISSION); nenTypes.add(NenType.MANIPULATION); nenTypes.add(NenType.SPECIALIZATION); nenTypes.add(NenType.CONJURATION); nenTypes.add(NenType.TRANSMUTATION);
        return nenTypes.get(new Random().nextInt(nenTypes.toArray().length));
    }
    //stupid implementation with switch-case construction.
    public double calcTypeMultiplier(NenType anotherNenType) {
        switch (this.id){
            case "Enhancement" -> {
                switch (anotherNenType.id){
                    case "Enhancement" -> {
                        return 1.0;
                    }
                    case "Emission", "Transmutation" -> {
                        return 0.8;
                    }
                    case "Manipulation", "Conjuration" -> {
                        return 0.6;
                    }
                    case "Specialization" -> {
                        return 0.01;
                    }
                }
            }
            case "Emission" -> {
                switch (anotherNenType.id){
                    case "Enhancement", "Manipulation" -> {
                        return 0.8;
                    }
                    case "Emission" -> {
                        return 1.0;
                    }
                    case "Specialization" -> {
                        return 0.01;
                    }
                    case "Conjuration" -> {
                        return 0.4;
                    }
                    case "Transmutation" -> {
                        return 0.6;
                    }
                }

            }
            case "Manipulation" -> {
                switch (anotherNenType.id){
                    case "Enhancement", "Conjuration" -> {
                        return 0.6;
                    }
                    case "Emission" -> {
                        return 0.8;
                    }
                    case "Manipulation" -> {
                        return 1.0;
                    }
                    case "Specialization" -> {
                        return 0.01;
                    }
                    case "Transmutation" -> {
                        return 0.4;
                    }
                }

            }
            case "Specialization" -> {
                switch (anotherNenType.id){
                    case "Enhancement" -> {
                        return 0.4;
                    }
                    case "Emission", "Transmutation" -> {
                        return 0.6;
                    }
                    case "Manipulation", "Conjuration" -> {
                        return 0.8;
                    }
                    case "Specialization" -> {
                        return 1.0;
                    }
                }            }
            case "Conjuration" -> {
                switch (anotherNenType.id){
                    case "Enhancement", "Manipulation" -> {
                        return 0.6;
                    }
                    case "Emission" -> {
                        return 0.4;
                    }
                    case "Specialization" -> {
                        return 0.01;
                    }
                    case "Conjuration" -> {
                        return 1.0;
                    }
                    case "Transmutation" -> {
                        return 0.8;
                    }
                }
            }
            case "Transmutation" -> {
                switch (anotherNenType.id){
                    case "Enhancement", "Conjuration" -> {
                        return 0.8;
                    }
                    case "Emission" -> {
                        return 0.6;
                    }
                    case "Manipulation" -> {
                        return 0.4;
                    }
                    case "Specialization" -> {
                        return 0.01;
                    }
                    case "Transmutation" -> {
                        return 1.0;
                    }
                }
            }
        }
        throw new RuntimeException("NenType Error");
    }

    public static NenType fromNbt(NbtCompound nbt){
        String id = nbt.getString("nenType");
        return switch (id) {
            case "Enhancement" -> NenType.ENHANCEMENT;
            case "Emission" -> NenType.EMISSION;
            case "Manipulation" -> NenType.MANIPULATION;
            case "Specialization" -> NenType.SPECIALIZATION;
            case "Conjuration" -> NenType.CONJURATION;
            case "Transmutation" -> NenType.TRANSMUTATION;
            default -> NenType.UNIDENTIFIED;
        };
    }
    public static NenType fromNbt(String id){
        return switch (id) {
            case "Enhancement" -> NenType.ENHANCEMENT;
            case "Emission" -> NenType.EMISSION;
            case "Manipulation" -> NenType.MANIPULATION;
            case "Specialization" -> NenType.SPECIALIZATION;
            case "Conjuration" -> NenType.CONJURATION;
            case "Transmutation" -> NenType.TRANSMUTATION;
            default -> NenType.UNIDENTIFIED;
        };
    }

}
