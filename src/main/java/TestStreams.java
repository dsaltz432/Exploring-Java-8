import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreams {

    /*
        Java Streams are meant to be stateless - they shouldn't alter the underlying data structure
     */

    public static void main(String[] args){
        TestStreams instance = new TestStreams();
        instance.run();
    }

    private void run() {
        streamFromObjects();
        streamFromPrimitives();
        chainsAreProcessedVertically();
        addingFilters();
        addingSorting();
        testFindAny();
        convertToArray();
        collectToList();
        collectToMap();
        concatTwoStreams();
        parallelProcessing();
    }

    private void parallelProcessing() {
        System.out.println("\nProcessing stream in parallel:");
        List<String> list = Arrays.asList("ABC", "ABCDB", "AAAC", "AEBFD", "EABC", "FABD", "GGGGG");
        list.parallelStream()
                .filter(s -> s.contains("ABC"))
                .map(s -> s.length())
                .sorted((s1,s2) -> s1.compareTo(s2))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private void concatTwoStreams() {
        System.out.println("\nConcatenating two streams together:");
        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("D", "E", "F");
        Stream<String> combined = Stream.concat(list1.stream(), list2.stream());
        combined.forEach(System.out::println);
    }

    private void collectToMap() {
        System.out.println("\nCreating a map of each string to its length:");
        Map<String,Integer> map = Arrays.asList("apple", "ball", "air", "car")
                .stream()
                .collect(Collectors.toMap(s -> s, s -> s.length()));

        Arrays.asList(map).forEach(System.out::println);
    }

    private void collectToList() {
        System.out.println("\nFinding the length of each string:");
        List<Integer> list = Arrays.asList("apple", "ball", "air", "car")
                .stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    private void convertToArray() {
        String[] array = Stream.of("apple", "ball", "air", "car").toArray(String[]::new);
    }

    private void testFindAny() {
        System.out.println("\nFinding specific elements from a list:");
        String match = Arrays.asList("apple", "ball", "air", "car")
                        .stream()
                        .filter(s -> s.equals("air"))
                        .findAny()
                        .orElse(null);
        System.out.println("Found: " + match);
    }

    private void addingSorting() {
        System.out.println("\nSorting and filtering to start with 'a'");
        Stream.of("apple", "ball", "air", "car")
                .filter(s -> s.startsWith("a"))
                .sorted((s1,s2) -> s1.compareTo(s2))
                .forEach(i -> System.out.println(i));
    }

    private void addingFilters() {
        System.out.println("\nFiltering for values > 10");
        IntStream.of(1,20,3,2,1,4,12,3,44)
                .filter( n -> n > 10)
                .forEach(i -> System.out.println(i));
    }

    private void chainsAreProcessedVertically() {
        System.out.println("\nStreams Processed Vertically:");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    private void streamFromPrimitives() {
        IntStream.range(1, 10)
                .forEach((s) -> {
                    s = 5 + s;
                });

        DoubleStream.of(1.0, 2.0, 3.0)
                .mapToObj(i -> "a " + i)
                .forEach((n) -> {
                    n = "String: " + n;
                });
    }

    private void streamFromObjects() {
        Stream.of("a1", "a2", "a3")
                .forEach((s) -> {
                    s = "String: " + s;
                });

        Arrays.asList("a1", "a2", "a3")
                .stream()
                .forEach((s) -> {
                    s = "String: " + s;
                });
    }
}
