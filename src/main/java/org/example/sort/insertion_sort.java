package org.example.sort;

import org.example.node.*;

public class insertion_sort {

    /**
     * VERSI CEPAT (IN-PLACE):
     * Method sort yang efisien untuk linked list.
     */
    public void sort(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }

        node lastSorted = list.first;       // Penanda node terakhir di bagian terurut
        node current = list.first.getnext(); // Node pertama di bagian yang belum terurut

        while (current != null) {
            node next = current.getnext(); // Simpan node selanjutnya

            // Kasus 1: Posisi 'current' sudah benar (lebih besar dari yg terurut terakhir)
            if (current.getdata() >= lastSorted.getdata()) {
                lastSorted = current; // Cukup majukan penanda
            } else {
                // Kasus 2: 'current' perlu disisipkan ke bagian terurut
                node prev = null;
                node scanner = list.first;

                // Cari posisi sisip
                while (scanner.getdata() < current.getdata()) {
                    prev = scanner;
                    scanner = scanner.getnext();
                }

                // Lepaskan 'current' dari posisinya saat ini
                lastSorted.setnext(next);

                // Sisipkan 'current' di posisi barunya
                if (prev == null) { // Sisipkan di depan
                    current.setnext(list.first);
                    list.first = current;
                } else { // Sisipkan di tengah
                    current.setnext(scanner);
                    prev.setnext(current);
                }
            }
            // Lanjutkan ke elemen berikutnya
            current = next;
        }
    }

    // Anda bisa mengadopsi method sort_tampilkan_inplace yang kita buat sebelumnya ke sini
    public void sort_tampilkan(linkedlist list) {
        // (Gunakan kode sort_tampilkan_inplace dari jawaban sebelumnya)
        if (list == null || list.first == null || list.first.getnext() == null) {
            System.out.println("List tidak perlu diurutkan.");
            return;
        }
        System.out.println("Kondisi Awal:");
        list.display();
        System.out.println("\n-----------------------------------------");
        System.out.println("Memulai proses Insertion Sort (In-Place)...");
        node lastSorted = list.first;
        node current = list.first.getnext();
        int step = 1;
        while (current != null) {
            node next = current.getnext();
            int dataToMove = current.getdata();
            if (dataToMove < lastSorted.getdata()) {
                System.out.println("\nLangkah ke-" + step + ": Elemen '" + dataToMove + "' tidak urut. Mencari posisi...");
                lastSorted.setnext(next);
                node prev = null;
                node scanner = list.first;
                while (scanner.getdata() < dataToMove) {
                    prev = scanner;
                    scanner = scanner.getnext();
                }
                if (prev == null) {
                    current.setnext(list.first);
                    list.first = current;
                } else {
                    current.setnext(scanner);
                    prev.setnext(current);
                }
                System.out.print("   -> Hasil setelah menyisipkan '" + dataToMove + "': ");
                list.display();
            } else {
                lastSorted = current;
            }
            current = next;
            step++;
        }
        System.out.println("\n-----------------------------------------");
        System.out.println("Proses sorting selesai!");
    }

    /**
     * Mengukur waktu eksekusi dari method sort yang CEPAT.
     */
    public long sort_timer(linkedlist list) {
        long startTime = System.nanoTime();
        this.sort(list); // Pastikan ini memanggil sort() yang baru dan cepat
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}