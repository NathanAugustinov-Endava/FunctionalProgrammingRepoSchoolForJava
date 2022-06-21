import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.*;


public class CrazyLambdas {

    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    public static Supplier<String> helloSupplier() {
        return () -> "Hello";
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */
    public static Predicate<String> isEmptyPredicate() {
        return String::isEmpty;
    }

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
        return (stringInput, numberOfTimes) ->{
            String stringOutput="";
          for(int i = 0; i < numberOfTimes; i++){
              stringOutput += stringInput;
          }
          return stringOutput;
        };
    }

    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        return bigDecimalNumber -> "$" + bigDecimalNumber.toString();
    }

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        Predicate<String> firstCondition = stringInput -> stringInput.length() >= min;
        Predicate<String> secondCondition = stringInput -> stringInput.length() < max;
        return firstCondition.and(secondCondition);
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    public static IntSupplier randomIntSupplier() {
        return () -> {
            Random random = new Random();
            return random.nextInt();
        };
    }


    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        return integer -> new Random().nextInt();
    }

    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    public static IntUnaryOperator intSquareOperation() {
        return integer -> (int) Math.pow(integer, 2);
    }

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    public static LongBinaryOperator longSumOperation() {
        return Long::sum;
    }

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    public static ToIntFunction<String> stringToIntConverter() {
        return Integer::parseInt;
    }

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return ()-> integer -> integer * n;
    }

    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return function -> function.compose(String::trim);
    }

    /**
     * Receives a {@link Runnable} parameter, and returns a {@link Supplier<Thread>}. The thread will be started only
     * when you call supplier method {@link Supplier#get()}
     *
     * @param runnable the code you want to tun in new thread
     * @return a thread supplier
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {
        return () -> new Thread(runnable);
    }

    /**
     * Returns a {@link Consumer} that accepts {@link Runnable} as a parameter and runs in in a new thread.
     *
     * @return a runnable consumer
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {
        return runnable -> new Thread(runnable).start();
    }

    /**
     * Returns a {@link Function} that accepts an instance of {@link Runnable} and returns a {@link Supplier} of a
     * started {@link Thread} that is created from a given {@link Runnable}
     *
     * @return a function that transforms runnable into a thread supplier
     */
    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {
        return runnable -> () -> new Thread(runnable);
    }

    /**
     * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        return (operator, predicate) -> number -> predicate.test(number) ? operator.applyAsInt(number) : number;
    }

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        return (map, name) -> map.containsKey(name) ? map.get(name) : IntUnaryOperator.identity();
    }

    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE".
     *
     * @return a supplier instance
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        return () -> () -> () -> "WELL DONE";
    }

    public static void main(String[] args) {
        Supplier<String > supplier = helloSupplier();
        System.out.println(supplier.get());

        Predicate<String> predicate = isEmptyPredicate();
        System.out.println(predicate.test(""));

        BiFunction<String, Integer, String> biFunction =  stringMultiplier();
        System.out.println(biFunction.apply("hello",3));

        Function<BigDecimal, String> function = toDollarStringFunction();
        System.out.println(function.apply(BigDecimal.valueOf(100000000)));

        Predicate<String> stringLengthPredicate = lengthInRangePredicate(5,9);
        System.out.println(stringLengthPredicate.test("hello"));
        System.out.println(stringLengthPredicate.test("helloWorld"));

        IntSupplier randomInt =  randomIntSupplier();
        System.out.println(randomInt.getAsInt());

        IntUnaryOperator randomIntReturn =  boundedRandomIntSupplier();
        System.out.println(randomIntReturn.applyAsInt(80));

        IntUnaryOperator intSquare =  intSquareOperation();
        System.out.println(intSquare.applyAsInt(5));

        LongBinaryOperator longSum = longSumOperation();
        System.out.println(longSum.applyAsLong(150256840, 1935214622));

        ToIntFunction<String> stringToIntFunction = stringToIntConverter();
        System.out.println(stringToIntFunction.applyAsInt("36541"));

        Supplier<IntUnaryOperator> nMultiplySupplier = nMultiplyFunctionSupplier(5);
        System.out.println(nMultiplySupplier.get().applyAsInt(5));

        UnaryOperator<Function<String, String>> composedWithTrimOperator = composeWithTrimFunction();
        System.out.println(composedWithTrimOperator.apply(String::toUpperCase).apply("  hello  "));

        Runnable runnable = () -> System.out.println("Thread running");
        Supplier<Thread> threadSupplier = runningThreadSupplier(runnable);
        threadSupplier.get().start();

        Consumer<Runnable> threadConsumer = newThreadRunnableConsumer();
        threadConsumer.accept(runnable);

        Function<Runnable, Supplier<Thread>> runnableThreadSupplierFunction = runnableToThreadSupplierFunction();
        runnableThreadSupplierFunction.apply(runnable).get().start();

        BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> conditionalFunction = functionToConditionalFunction();
        IntPredicate integerPredicate = number -> number > 0;
        System.out.println(conditionalFunction.apply(intSquare,integerPredicate).applyAsInt(5));
        System.out.println(conditionalFunction.apply(intSquare,integerPredicate).applyAsInt(-1));

        BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> mapStringToFunctionNameLoader = functionLoader();
        Map<String, IntUnaryOperator> map = new HashMap<>();
        map.put("Square", intSquare);
        map.put("Random Int", boundedRandomIntSupplier());
        System.out.println(mapStringToFunctionNameLoader.apply(map,"Square").applyAsInt(20));

        Supplier<Supplier<Supplier<String>>> wellDoneSupplier = trickyWellDoneSupplier();
        System.out.println(wellDoneSupplier.get().get().get());
    }
}
