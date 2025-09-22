package org.example.sort;

import org.example.node.*;

public class selection_sort {
    public void sort (linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }

        node current = list.first;

        while (current != null) {
            node smnode = current;
            node scanner = current.getnext();

            while (scanner != null) {
                if (scanner.getdata() < smnode.getdata()) {
                    smnode = scanner;
                }
                scanner = scanner.getnext();
            }
            int temp = current.getdata();
            current.setdata(smnode.getdata());
            smnode.setdata(temp);
            current = current.getnext();
        }
    }

    public void sort_tampilkan(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }

        System.out.println("Kondisi awal :");
        list.display();
        System.out.println("--------------------------");
        System.out.println("Memulai proses sorting ... ");

        int swap = 1;
        node current = list.first;
        while (current != null) {
            node smnode = current;
            node scanner = current.getnext();

            while (scanner != null) {
                if (scanner.getdata() < smnode.getdata()) {
                    smnode = scanner;
                }
                scanner = scanner.getnext();
            }
            if (smnode != current) {
                System.out.println("\n kondisi sorting ke : " + swap + ": dengan elemen terkecil : " + smnode.getdata() + " menukar dengan nilai" + current.getdata() + ".");
                int temp = current.getdata();
                current.setdata(smnode.getdata());
                smnode.setdata(temp);

                list.display();

            } else {
                System.out.println("\nPass ke-" + swap + ": Elemen " + current.getdata() + " sudah di posisi yang benar. Tidak ada pertukaran.");
                list.display();
            }

            current = current.getnext();
            swap++;
        }
        System.out.println("----------------------");
        System.out.println("Proses sorting selesai !! ... ");
    }

    public long sort_timer(linkedlist list) {
        long startTime = System.nanoTime();
        this.sort(list);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
