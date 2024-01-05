public class ArrayManipulation {

    public static void main(String[] args) {
        double[] numbers = {10.5, 5, 8.0, 2, 7.5, 3, 12.6, 9.2};

        System.out.println("Original array:");
        printArray(numbers);

        long startTime = System.nanoTime();
        double[] sortedArray = mergeSort(numbers);
        long endTime = System.nanoTime();

        System.out.println("Sorted array:");
        printArray(sortedArray);

        long elapsedTime = endTime - startTime;
        System.out.println("Time taken to sort the array: " + elapsedTime + " nanoseconds");
    }

    public static double[] mergeSort(double[] array) {
        if (array.length <= 1) {
            return array; // Base case: already sorted or empty array
        }

        // Split the array into two halves
        double[] leftHalf = new double[array.length / 2];
        double[] rightHalf = new double[array.length - leftHalf.length];

        // Copy data to temp arrays
        for (int i = 0; i < leftHalf.length; i++) {
            leftHalf[i] = array[i];
        }
        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = array[leftHalf.length + i];
        }

        // Recursively sort the two halves
        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);

        // Merge the sorted halves
        double[] sortedArray = merge(leftHalf, rightHalf);

        // Release the temporary arrays
        return sortedArray;
    }

        public static double[] merge(double[] leftArray, double[] rightArray) {
            int leftIndex = 0, rightIndex = 0, mergedIndex = 0;
            double[] mergedArray = new double[leftArray.length + rightArray.length];
        
            while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                    mergedArray[mergedIndex++] = leftArray[leftIndex++];
                } else {
                    mergedArray[mergedIndex++] = rightArray[rightIndex++];
                }
            }
        
            // Copy the remaining elements of leftArray (if any)
            while (leftIndex < leftArray.length) {
                mergedArray[mergedIndex++] = leftArray[leftIndex++];
            }
        
            // Copy the remaining elements of rightArray (if any)
            while (rightIndex < rightArray.length) {
                mergedArray[mergedIndex++] = rightArray[rightIndex++];
            }
        
            return mergedArray;
        }

    public static void printArray(double[] array) {
        for (double num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}