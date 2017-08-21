import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

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

    public static double time(String alg, Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T)
    {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++)
        {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg,a);
        }

        return total;
    }

    public static void main2(String[] args) {
        if (args.length < 1) {
            System.out.println("The number of args is 0!");
        } else {
            String file_name = args[0];
            File file = new File(file_name);


            List<String> list = new ArrayList<String>();
            try {

                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine()
                            .toLowerCase()
                            .replaceAll("[^a-zA-Z ]|\\s+", "")
                            .split("");
                    for (int i = 0; i < line.length; i++){
                        list.add(line[i]);
                        //System.out.println(line[i]);
                    }
                }
                sc.close();

//                for (String s:list){
//                    System.out.println(s);
//                }
                String[] input = new String[list.size()];
                input = list.toArray(input);

                sort(input);
                assert isSorted(input);
                //show(input);

                double t = time("Selection",input);
                StdOut.printf("The elapsedTime() is %.15f \n ",t );

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args)
    {
        long startTime = System.nanoTime();
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1,N,T);
        double t2 = timeRandomInput(alg2,N,T);
        StdOut.printf("For %d random Doubles\n   %s is", N, alg1);
        StdOut.printf(" %.2f times faster than %s \n", t2/t1, alg2);
    }

}




