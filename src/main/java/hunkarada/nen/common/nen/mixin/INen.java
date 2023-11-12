package hunkarada.nen.common.nen.mixin;


public interface INen {
     void nen$writeToNenMemory(String abilityId, String data);

     String nen$readFromNenMemory(String abilityId);

     boolean nen$collectNen(long sum);

     void nen$giveNen(long value);
}
