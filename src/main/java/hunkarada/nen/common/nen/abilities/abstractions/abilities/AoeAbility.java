package hunkarada.nen.common.nen.abilities.abstractions.abilities;

import hunkarada.nen.common.nen.abilities.abstractions.shapes.Shapes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public abstract class AoeAbility extends Ability {

    protected Shapes shape;
    protected float radius;

    protected int lifetime;

    public void cast(LivingEntity caster){
        switch (shape){
            case SPHERE -> createSphereEntity(calcSpherePos(caster), radius);
            case CUBE -> createCubeEntity(calcCubePos(caster).get(0), calcCubePos(caster).get(1));
        }
    }

    protected abstract void createSphereEntity(Vec3d centerPos, float radius);
    protected abstract void createCubeEntity(Vec3d firstPos, Vec3d secondPos);

    protected abstract Vec3d calcSpherePos(LivingEntity caster);
    protected abstract ArrayList<Vec3d> calcCubePos(LivingEntity caster);
}
