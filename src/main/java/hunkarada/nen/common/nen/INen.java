package hunkarada.nen.common.nen;

public interface INen {
    boolean getIsAwakened();

   void setIsAwakened(boolean isAwakened);



    public int getAbilityPoints();

    public void setAbilityPoints(int abilityPoints);

    public AuraType getAuraType();

    public void setAuraType(AuraType auraType);


    public int getNenLvl();

    public void setNenLvl(int nenLvl);


    public int getPowerCap();

    public void setPowerCap(int powerCap);


    public int getPower();

    public void setPower(int power);

    public int getNenExp();

    public void setNenExp(int nenExp);
}
