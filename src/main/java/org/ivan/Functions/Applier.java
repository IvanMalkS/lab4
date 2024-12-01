package org.ivan.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionApplier {
    public static <T, P> List<P> applyFunction(List<T> list, Function<T, P> function) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }
}
