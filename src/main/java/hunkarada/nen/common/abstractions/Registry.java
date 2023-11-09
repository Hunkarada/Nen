package hunkarada.nen.common.abstractions;

public interface Registry<T> {
    public abstract void addToRegistry(String key, T value);
    public abstract <T> T getFromRegistry(String key);
}
