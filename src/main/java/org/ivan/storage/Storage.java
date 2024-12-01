package org.ivan.storage;

public class Storage<T> {
    private final T item;
    private final T defaultValue;

    public Storage(T item, T defaultValue) {
        this.item = item;
        this.defaultValue = defaultValue;
    }

    public T get() {
        return (item != null) ? item : defaultValue;
    }
}
