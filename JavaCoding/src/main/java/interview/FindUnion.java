package interview;

public class FindUnion {

    public static void main(String[] args) {
        int firstArr[] = {1, 2, 3, 4, 5, 6};
        int secondArr[] = {4, 9, 13, 15, 16, 17};

        findUnion(firstArr, secondArr);
    }

    private static void findUnion(int[] firstArr, int[] secondArr) {
        int i = 0;
        int j = 0;

        while (i < firstArr.length && j < secondArr.length) {
            if (firstArr[i] < secondArr[j]) {
                System.out.print(firstArr[i] + " ");
                i++;
            } else if (secondArr[j] < firstArr[i]) {
                System.out.print(secondArr[j] + " ");
                j++;
            } else {
                System.out.print(firstArr[i] + " ");
                i++;
                j++;
            }
        }

        while (i < firstArr.length) {
            System.out.print(firstArr[i] + " ");
            i++;
        }

        while (j < secondArr.length) {
            System.out.print(secondArr[j] + " ");
            j++;
        }
    }

}