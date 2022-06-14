package com.company;


import java.util.Arrays;

//сортировки:
//0. Рассческой +
//1.Вставками без доп массива + Вставками:с доп массивом
//2. Шейкерная +
//3. Быстрая (Хоара) +
//4. Слиянием +
//5. Выбором +
//-1 12 3 34 99 2 13 67 5 -22 47 25 -108 30 99
public class Main {

    public static void main(String[] args) {
        int[] array = {-1, 12, 3, 34, 99, 2, 13, 67, 5, -22, 47, 25, -108, 30, 99};
        int[] array3 = {-1, 12, 3, 34, 99, 2, 13, 67, 5, -22, 47, 25, -108, 30, 99};
        int[] arr2 = {3, 5, 2, 10, 18, 12, 4, -1, 6};
        System.out.println(Arrays.toString(insertSort(array)));
        System.out.println(Arrays.toString(selectionSort(arr2)));
        mergeSort(array3, array3.length - 1);
        System.out.println(Arrays.toString(array3));
        int[] array4 = {-1, 12, 3, 34, 99, 2, 13, 67, 5, -22, 47, 25, -108, 30, 99};
       shakerSort(array4, array4.length);
        System.out.println(Arrays.toString(array4));

    }

    //расческой
    public static int[] combSort(int[] arr) {
        int gap = arr.length;

        boolean isSorted = false;
        while (!isSorted || gap != 1) {
            isSorted = true;
            if (gap > 1) {
                gap = (int) (gap / 1.247);
            } else {
                gap = 1;
            }
            for (int i = gap; i < arr.length; i++) {
                if (arr[i - gap] > arr[i]) {
                    int tmp = arr[i];
                    arr[i] = arr[i - gap];
                    arr[i - gap] = tmp;
                    isSorted = false;
                }
            }
        }
        return arr;
    }


    //быстрая сортировка(хоара)
    public static void quickSort(int[] arr, int first, int last) {
        if (arr.length == 0) {
            return;
        }
        if (first >= last) {
            return;
        }
        int i, j;
        i = first;
        j = last;
        int pivot = arr[(i + j) / 2];

        do {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        while (i <= j);

        if (first < j) {
            quickSort(arr, first, j);
        }
        if (i < last) {
            quickSort(arr, i, last);
        }
    }

    //сортировка выбором
    public static int[] selectionSort(int[] arr) {
        int min, tmp;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;
        }

        return arr;
    }


    //cортировка вставками
    public static int[] insertSort(int[] arr) {
        int min;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = (i - 1); j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }


    //сортировка слиянием двух отсортированных массивов
    public static void mergeSort(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[] c = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (a[i] <= b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            c[k] = a[i];
            i++;
            k++;
        }
        while (j < m) {
            c[k] = b[j];
            j++;
            k++;
        }
    }

    // рекурсивная сортировка слиянием неотсортированного массива


    public static void mergeSort(int[] a, int length) {
        if (length < 2) {
            return;
        }
        int mid = length / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[length - mid];

        for (int i = 0; i < mid; i++) {
            leftArr[i] = a[i]; //заполнение левого массива значениями исходного массива до половины
        }
        for (int i = mid; i < length; i++) {
            rightArr[i - mid] = a[i]; // заполнение правого массива значениями исходного массива начиная с середины
        }
        mergeSort(leftArr, mid);
        mergeSort(rightArr, length - mid);

        merge(a, leftArr, rightArr, mid, length - mid);
    }

    public static void merge(int[] a, int[] leftArr, int[] rightArr, int leftBound, int rightBound) {

        int i = 0, j = 0, k = 0;
        while (i < leftBound && j < rightBound) {
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
        }
        while (i < leftBound) {
            a[k++] = leftArr[i++];
        }
        while (j < rightBound) {
            a[k++] = rightArr[j++];
        }
    }

   public static void shakerSort(int[] array, int length) {
        int left = 0, right = length - 1; // левая и правая границы сортируемой области массива
        int flag = 1;  // флаг наличия перемещений // Выполнение цикла пока левая граница не сомкнётся с правой и пока в массиве имеются перемещения
        while ((left < right) && flag > 0) {
            flag = 0;
            for (int i = left; i < right; i++) {//двигаемся слева направо
                if (array[i] > array[i + 1]) { // если следующий элемент меньше текущего,
                    // меняем их местами
                    int t = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = t;
                    flag = 1;      // перемещения в этом цикле были
                }
            }
            right--; // сдвигаем правую границу на предыдущий элемент
            for (int i = right; i > left; i--)  //двигаемся справа налево
            {
                if (array[i - 1] > array[i]) // если предыдущий элемент больше текущего,
                {            // меняем их местами
                    int t = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = t;
                    flag = 1;    // перемещения в этом цикле были
                }
            }
            left++; // сдвигаем левую границу на следующий элемент
        }
    }
}
