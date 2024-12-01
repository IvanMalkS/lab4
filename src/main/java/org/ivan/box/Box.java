package org.ivan.box;

public class Box<T> {
    private T item;

    public void put(T item) {
        if (this.item != null) {
            throw new IllegalStateException("Box is not empty");
        }

        this.item = item;
    }

    public T get() {
        if (this.item == null) {
            throw new IllegalStateException("Box is empty");
        }
        T temp = this.item;
        this.item = null;
        return temp;
    }

    public boolean isEmpty() {
        return this.item == null;
    }
}
