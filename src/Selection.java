import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Selection {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j< N; j ++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }


    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;

        return true;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("The number of args is 0!");
        } else {
            String file_name = args[0];
            File file = new File(file_name);

            try {

                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String i = sc.next();
                    System.out.println(i);

                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //sort(a);
            //assert isSorted(a);
            //show(a);
        }
    }

}




