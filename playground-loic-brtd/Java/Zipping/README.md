# Zipping in Java

Zipped iterators inspired by Python's `zip` and `enumerate` functions.

### Examples

```java
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
```