package hunkarada.nen.common.abstractions.nbt;

public interface CanNbt {
    <T> String toNbt();

    <T> T fromNbt(String nbt);
}
