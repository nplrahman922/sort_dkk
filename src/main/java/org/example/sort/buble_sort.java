package org.example.sort;
import org.example.node.*;

public class buble_sort {

    public void sort(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }

        boolean swapped = true;

        do {
            swapped = false;
            node current = list.first;

            while (current.getnext() != null) {
                if (current.getdata() > current.getnext().getdata()) {
                    int temp = current.getdata();
                    current.setdata(current.getnext().getdata());
                    current.getnext().setdata(temp);

                    swapped = true;
                }
                current = current.getnext();
            }
        } while (swapped);
    }

    public void sort_tampilkan(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }
        boolean swapped;
        int swapCount = 1; // Counter untuk menghitung jumlah swap

        System.out.println("Kondisi Awal:");
        list.display();
        System.out.println("--------------------");
        System.out.println("Memulai proses sorting...");
        do {
            swapped = false;
            node current = list.first;

            while (current.getnext() != null) {
                if (current.getdata() > current.getnext().getdata()) {

                    int val1 = current.getdata();
                    int val2 = current.getnext().getdata();

                    current.setdata(val2);
                    current.getnext().setdata(val1);

                    swapped = true;

                    System.out.println("\nSwap ke-" + swapCount + ": Menukar " + val1 + " dengan " + val2);
                    list.display();
                    swapCount++;
                }
                current = current.getnext();

            }

        } while (swapped);
        System.out.println("--------------------");
        System.out.println("proses sorting selesai.");
    }


    public long sort_timer(linkedlist list) {

        long start = System.nanoTime();

        this.sort(list);

        long end = System.nanoTime();

        return end - start;
    }
}