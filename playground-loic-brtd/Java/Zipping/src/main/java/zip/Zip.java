package zip;

import java.util.List;
import java.util.function.BiFunction;

public class Zip {

    public static <L, R, O> Iterable<O> zip(Iterable<L> left,
                                            Iterable<R> right,
                                            BiFunction<L, R, O> combiner) {
        return () -> new ZipIterator<>(left.iterator(), right.iterator(), combiner);
    }

    public static <L, R> Iterable<Pair<L, R>> zip(Iterable<L> left, Iterable<R> right) {
        return () -> new ZipIterator<>(left.iterator(), right.iterator(), Pair::new);
    }

    public static <T> Iterable<Pair<Integer, T>> enumerate(Iterable<T> iterable) {
        return () -> new ZipIterator<>(new InfiniteIntIterator(), iterable.iterator(), Pair::new);
    }

    public static void main(String[] args) {
        var integers = List.of(6, 4, 9);
        var strings = List.of("one", "two", "three");

        for (Pair<Integer, String> pair : zip(integers, strings)) {
            System.out.println(pair);
        }
        // (6, one)
        // (4, two)
        // (9, three)

        for (String str : zip(integers, strings, (i, s) -> i + " / " + s)) {
            System.out.println(str);
        }
        // 6 / one
        // 4 / two
        // 9 / three

        var fruits = List.of("apple", "banana", "peach");

        for (Pair<Integer, String> pair : enumerate(fruits)) {
            System.out.println(pair);
        }
        // (0, apple)
        // (1, banana)
        // (2, peach)
    }
}
