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
    //stupid implementation with switch-case construction,
    // as it will be much faster, than index calculations, and much simpler to do
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
