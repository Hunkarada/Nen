package hunkarada.nen.common.abstractions.nbt;

public interface CanNbt {
    <T> String toNbt(T object);
}
