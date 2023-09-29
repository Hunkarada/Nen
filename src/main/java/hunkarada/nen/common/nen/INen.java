package hunkarada.nen.common.nen;

public interface INen {
    boolean getIsAwakened();

   void setIsAwakened(boolean isAwakened);



    public int getAbilityPoints();

    public void setAbilityPoints(int abilityPoints);

    public AuraType getAuraType();

    public void setAuraType(AuraType auraType);


    public int getLvl();

    public void setLvl(int lvl);


    public int getPowerCap();

    public void setPowerCap(int powerCap);


    public int getPower();

    public void setPower(int power);
}
