package diagel.secondHomework;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Homework2 {

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key:Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        /* I wrote my Task1 code here
         * 1. отфильтровываем/страхуемся от NPE
         * 2. уникализируем ) стрим
         * 2. собираем мапу в которой ключ это стринг Person::getName, а значение - количество таких стрингов
         * 3. выводим на экран каждый элемент мапы с красивостями ) */
        Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()))
                .forEach((x, y) -> System.out.println("Key: " + x + "\nValue: " + y))
        ;
        System.out.println();
        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        /*I wrote my Task2 code here
         * не знаю вариков, что бы выполнить задачу без двойного прохода по массиву
         * и соответственно фильтруем, и в каждой итерации фильтра делаем второй проход,
         * в результате отфильтруются только 3 и 7 */
        int[] array = {3, 4, 2, 7};

        System.out.print("[");
        Arrays
                .stream(array)
                .filter(e1 -> Arrays.stream(array).anyMatch(e2 -> e1 + e2 == 10))
                .forEach(e -> System.out.print(e + ", "));
        System.out.println("\b\b]");
        System.out.println();
        /*
        Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */

        /* I wrote my Task3 code here */
        fuzzySearch("car", "ca6$$#_rtwheel"); // true
        fuzzySearch("cwhl", "cartwheel"); // true
        fuzzySearch("cwhee", "cartwheel"); // true
        fuzzySearch("cartwheel", "cartwheel"); // true
        fuzzySearch("cwheeel", "cartwheel"); // false
        fuzzySearch("lw", "cartwheel"); // false
    }
    private static void fuzzySearch(String word, String string) {
        // ...за то через массивы и циклы - быстрее работает )
        char[] charsOfWord = word.toCharArray();
        char[] charsOfString = string.toCharArray();
        int count = 0;
        int iterator = 0;
        for (int i = 0; i < charsOfWord.length; i++){
            for (; iterator < charsOfString.length; ){
                if (charsOfWord[i] == charsOfString[iterator]) {
                    count++;
                    iterator++;
                    break;
                }
                iterator++;
            }
        }
        System.out.println(count == charsOfWord.length ? true : false);
    }
    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

}
