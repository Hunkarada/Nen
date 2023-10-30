package hunkarada.nen.common.nen;

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
        return nenType.toString();
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
