package hunkarada.nen.common.nen;


public interface INen {
     void nen$writeToNenMemory(String abilityId, String data);

     String nen$readFromNenMemory(String abilityId);

     boolean nen$collectNen(long sum);
}
