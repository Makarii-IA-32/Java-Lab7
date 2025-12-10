import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();

        IntStream.rangeClosed(2, n)
                .filter(Main::isPrime)
                .boxed()
                .collect(Collectors.groupingBy(
                        Main::countZerosInBinary
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .ifPresentOrElse(
                        entry -> {
                            int maxZeros = entry.getKey();
                            List<Integer> primes = entry.getValue();
                            System.out.println("Прості числа з кількістю нулів (" + maxZeros + "):");
                            primes.forEach(prime ->
                                    System.out.println(prime + " (" + Integer.toBinaryString(prime) + ")")
                            );
                        },
                        () -> System.out.println("Простих чисел не знайдено")
                );
        scanner.close();
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(num))
                .noneMatch(i -> num % i == 0);
    }

    static int countZerosInBinary(int num) {
        return (int) Integer.toBinaryString(num)
                .chars()
                .filter(ch -> ch == '0')
                .count();
    }
}