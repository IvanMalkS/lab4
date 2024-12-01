package org.ivan.Functions;

import java.util.List;
import java.util.function.BinaryOperator;

public class Reducer {
    public <T> T reduce(List<T> list, BinaryOperator<T> accumulator, T identity) {
        if (list == null || list.isEmpty()) {
            return identity;
        }
        T result = identity;
        for (T item : list) {
            result = accumulator.apply(result, item);
        }
        return result;
    }
}