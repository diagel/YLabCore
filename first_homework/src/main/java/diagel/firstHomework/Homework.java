package diagel.firstHomework;

public class Homework {
    public static void main(String[] args) {
        /** Task 1 */
        /** Заполните массив случайным числами */
        System.out.println("Генерация псевдослучайных чисел без сторонних библиотек...");
        int[][] array = createTwoDimensionalArray(5);
        System.out.println("Массив псевдослучайных, не уникальных чисел сгенерирован.");
        /** и выведите максимальное, */
        System.out.println("Максимальное значение в массиве - " + getMax(array));
        /** минимальное */
        System.out.println("Минимальное значение в массиве - " + getMin(array));
        /** и среднее значение. */
        System.out.println("Среднее значение в массиве - " + getMiddle(array));
        System.out.println();
        /** Вывод массива */
        System.out.println("Вывод массива...");
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        /** Вторая часть задания */
        /** Отсортируйте массив [5,6,3,2,5,1,4,9] */
        int[] array2 = getSortedArray(new int[]{5, 6, 3, 2, 5, 1, 4, 9});
        System.out.println("Массив {5,6,3,2,5,1,4,9} отсортирован в ");
        for (Integer i : array2) System.out.print(i + " ");
        System.out.println();

    }

    private static int[][] createTwoDimensionalArray(int size) {
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j= 0; j < size; j++){
                array[i][j] = myRandom(1000);
            }
        }

        return array;
    }

    private static int myRandom(int max) {
        long time = System.currentTimeMillis();
        int rndInt = (int) (time % max);

        try {
            Thread.sleep(rndInt/3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return rndInt == 0 ? myRandom(max) : rndInt;
    }

    private static int getMin(int[][] array) {
        int min = array[0][0];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                min = min < array[i][j] ? min : array[i][j];
            }
        }

        return min;
    }

    private static int getMax(int[][] array) {
        int max = array[0][0];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                max = max > array[i][j] ? max : array[i][j];
            }
        }

        return max;
    }

    private static int getMiddle(int[][] array) {
        int[] arraysOfMiddles = new int[array.length];

        for (int i = 0; i < array.length; i++){
            arraysOfMiddles[i] = getSortedArray(array[i])[array.length/2];
        }

        return getSortedArray(arraysOfMiddles)[arraysOfMiddles.length/2];
    }

    private static int[] getSortedArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]){
                    array[i] = array[i] + array[j];
                    array[j] = array[i] - array[j];
                    array[i] = array[i] - array[j];
                }
            }
        }
        return array;
    }
}
