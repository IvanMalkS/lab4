package org.ivan;

import org.ivan.Functions.Applier;
import org.ivan.Functions.Filter;
import org.ivan.Functions.Reducer;
import org.ivan.Functions.Collector;
import org.ivan.Point.Point3D;
import org.ivan.box.Box;
import org.ivan.storage.Storage;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testBoxWithInteger();
        main.testStorageWithNullInteger();
        main.testStorageWithInteger();
        main.testStorageWithNullString();
        main.testStorageWithString();

        main.testBox3D();

        main.testFunctionApplier();
        main.testFilterApplier();
        main.testReducer();
        main.testCollector();
    }

    private void testBoxWithInteger() {
        Box<Integer> integerBox = new Box<>();
        integerBox.put(3);
        Integer value = integerBox.get();
        System.out.println("Extracted value from Box: " + value);
    }

    private void testStorageWithNullInteger() {
        Storage<Integer> integerStorageNull = new Storage<>(null, 0);
        Integer value = integerStorageNull.get();
        System.out.println("Extracted value from Storage (null integer): " + value);
    }

    private void testStorageWithInteger() {
        Storage<Integer> integerStorage99 = new Storage<>(99, -1);
        Integer value = integerStorage99.get();
        System.out.println("Extracted value from Storage (integer 99): " + value);
    }

    private void testStorageWithNullString() {
        Storage<String> stringStorageNull = new Storage<>(null, "default");
        String value = stringStorageNull.get();
        System.out.println("Extracted value from Storage (null string): " + value);
    }

    private void testStorageWithString() {
        Storage<String> stringStorageHello = new Storage<>("hello", "hello world");
        String value = stringStorageHello.get();
        System.out.println("Extracted value from Storage (string 'hello'): " + value);
    }

    private void testBox3D() {
        Box<Point3D> point3DBox = new Box<>();
        System.out.println("Is box empty? " + point3DBox.isEmpty());

        Point3D point3D = new Point3D(1.0, 2.0, 3.0);
        point3DBox.put(point3D);

        System.out.println("Is box empty? " + point3DBox.isEmpty());

        Point3D retrievedPoint3D = point3DBox.get();
        System.out.println("Retrieved Point3D: " + retrievedPoint3D);

        System.out.println("Is box empty? " + point3DBox.isEmpty());
    }

    private void testFunctionApplier() {
        Applier applier = new Applier();

        List<String> strings = List.of("qwerty", "asdfg", "zx");
        Function<String, Integer> stringLengthFunction = String::length;
        List<Integer> lengths = applier.applyFunction(strings, stringLengthFunction);
        System.out.println("Lengths: " + lengths);

        List<Integer> numbers = List.of(1, -3, 7);
        Function<Integer, Integer> absFunction = Math::abs;
        List<Integer> absNumbers = applier.applyFunction(numbers, absFunction);
        System.out.println("Absolute values: " + absNumbers);

        List<int[]> arrays = List.of(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9});
        Function<int[], Integer> maxFunction = array -> {
            int max = Integer.MIN_VALUE;
            for (int num : array) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        };
        List<Integer> maxValues = applier.applyFunction(arrays, maxFunction);
        System.out.println("Max values: " + maxValues);
    }

    private void testFilterApplier() {
        Filter filter = new Filter();

        List<String> strings = List.of("qwerty", "asdfg", "zx");
        Predicate<String> lengthFilter = s -> s.length() >= 3;
        List<String> filteredStrings = filter.filter(strings, lengthFilter);
        System.out.println("Filtered strings: " + filteredStrings);

        List<Integer> numbers = List.of(1, -3, 7);
        Predicate<Integer> positiveFilter = n -> n < 0;
        List<Integer> filteredNumbers = filter.filter(numbers, positiveFilter);
        System.out.println("Filtered numbers: " + filteredNumbers);

        List<int[]> arrays = List.of(new int[]{1, 2, 3}, new int[]{-4, -5, -6}, new int[]{7, 8, 9});
        Predicate<int[]> noPositiveFilter = array -> {
            for (int num : array) {
                if (num > 0) {
                    return false;
                }
            }
            return true;
        };
        List<int[]> filteredArrays = filter.filter(arrays, noPositiveFilter);
        System.out.println("Filtered arrays: " + Arrays.deepToString(filteredArrays.toArray()));
    }

    private void testReducer() {
        Reducer reducer = new Reducer();

        List<String> strings = List.of("qwerty", "asdfg", "zx");
        BinaryOperator<String> concatFunction = (s1, s2) -> s1 + s2;
        String concatenatedString = reducer.reduce(strings, concatFunction, "");
        System.out.println("Concatenated string: " + concatenatedString);

        List<Integer> numbers = List.of(1, -3, 7);
        BinaryOperator<Integer> sumFunction = Integer::sum;
        Integer sum = reducer.reduce(numbers, sumFunction, 0);
        System.out.println("Sum: " + sum);


    }

    private void testCollector() {
        Collector collector = new Collector();

        List<Integer> numbers = List.of(1, -3, 7);
        Supplier<Map<Boolean, List<Integer>>> supplier = () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
        BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator = (map, num) -> {
            map.get(num > 0).add(num);
        };
        Map<Boolean, List<Integer>> partitionedNumbers = collector.collect(numbers, supplier, accumulator);
        System.out.println("Partitioned numbers: " + partitionedNumbers);

        List<String> strings = List.of("qwerty", "asdfg", "zx", "qw");
        Supplier<Map<Integer, List<String>>> stringSupplier = HashMap::new;
        BiConsumer<Map<Integer, List<String>>, String> stringAccumulator = (map, str) -> {
            map.computeIfAbsent(str.length(), k -> new ArrayList<>()).add(str);
        };
        Map<Integer, List<String>> partitionedStrings = collector.collect(strings, stringSupplier, stringAccumulator);
        System.out.println("Partitioned strings: " + partitionedStrings);

        List<String> duplicateStrings = List.of("qwerty", "asdfg", "qwerty", "qw");
        Supplier<Set<String>> setSupplier = HashSet::new;
        BiConsumer<Set<String>, String> setAccumulator = Set::add;
        Set<String> uniqueStrings = collector.collect(duplicateStrings, setSupplier, setAccumulator);
        System.out.println("Unique strings: " + uniqueStrings);
    }
}
