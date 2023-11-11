package hunkarada.nen.common.abstractions;

public interface Registry<T> {
    void addToRegistry(String key, T value);
    <T> T getFromRegistry(String key);
}
