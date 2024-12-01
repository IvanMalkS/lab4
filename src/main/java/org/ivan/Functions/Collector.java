package org.ivan.Functions;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Collector {
    public  <T, R> R collect(List<T> list, Supplier<R> supplier, BiConsumer<R, T> accumulator) {
        R result = supplier.get();
        for (T item : list) {
            accumulator.accept(result, item);
        }
        return result;
    }
}
