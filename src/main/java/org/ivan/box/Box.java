package org.ivan.storage;

public class Box<T> {
    private T item;

    public void put(T item) {
        if (this.item != null) {
            throw new IllegalStateException("Box is not empty");
        }

        this.item = item;
    }

    public T get() {
        T temp = this.item;
        this.item = null;
        return temp;
    }

    public boolean isEmpty() {
        return this.item == null;
    }
}
