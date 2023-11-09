package hunkarada.nen.common.abstractions;

public interface CanNbt {
    <T> String toNbt();

    <T> T fromNbt(String nbt);
}
