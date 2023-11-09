package hunkarada.nen.common.nen;

import hunkarada.nen.common.abstractions.CanNbt;

public enum NenType implements CanNbt {
    ENHANCEMENT("Enhancement"), EMISSION("Emission"), MANIPULATION("Manipulation"), SPECIALIZATION("Specialization"), CONJURATION("Conjuration"), TRANSMUTATION("Transmutation"), UNIDENTIFIED("Unidentified");
    private final String id;
    NenType(String id){
        this.id = id;
    }
    @Override
    public String toString(){
        return id;
    }
    public String toNbt(){
        return this.toString();
    }
    public NenType fromNbt(String id){
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
