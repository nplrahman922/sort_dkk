package org.example.sort;

import org.example.node.*;

public class insertion_sort {

    /**
     * Method sort dasar menggunakan algoritma Insertion Sort.
     * Method ini mengatur ulang pointer, bukan hanya menukar data.
     * @param list Objek linkedlist yang akan diurutkan.
     */
    public void sort(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            return;
        }

        node sorted = null; // Ini akan menjadi head dari list yang sudah terurut
        node current = list.first; // Pointer untuk iterasi list asli

        while (current != null) {
            node next = current.getnext(); // Simpan node selanjutnya

            // Panggil helper method untuk menyisipkan 'current' ke list 'sorted'
            sorted = sortedInsert(sorted, current);

            // Lanjutkan ke node berikutnya dari list asli
            current = next;
        }

        // Update head dari list asli dengan list yang sudah terurut
        list.first = sorted;
    }

    /**
     * Helper method untuk menyisipkan node ke posisi yang benar di dalam list terurut.
     * @param sortedHead Head dari list yang sudah terurut.
     * @param newNode Node baru yang akan disisipkan.
     * @return Head baru dari list terurut.
     */
    private node sortedInsert(node sortedHead, node newNode) {
        // Kasus 1: list terurut masih kosong atau newNode lebih kecil dari head
        if (sortedHead == null || sortedHead.getdata() >= newNode.getdata()) {
            newNode.setnext(sortedHead);
            return newNode;
        } else {
            // Kasus 2: Cari posisi yang tepat untuk menyisipkan node
            node current = sortedHead;
            while (current.getnext() != null && current.getnext().getdata() < newNode.getdata()) {
                current = current.getnext();
            }
            // Lakukan penyisipan
            newNode.setnext(current.getnext());
            current.setnext(newNode);
        }
        return sortedHead;
    }


    /**
     * Mengurutkan dan menampilkan setiap langkah penyisipan.
     * @param list Objek linkedlist yang akan diurutkan.
     */
    public void sort_tampilkan(linkedlist list) {
        if (list == null || list.first == null || list.first.getnext() == null) {
            System.out.println("List tidak perlu diurutkan.");
            return;
        }

        System.out.println("Kondisi Awal:");
        list.display();
        System.out.println("\n-----------------------------------------");
        System.out.println("Memulai proses Insertion Sort...");

        node sorted = null; // Head untuk list yang sudah terurut
        node current = list.first; // Pointer untuk iterasi list asli
        int step = 1;

        while (current != null) {
            // Simpan node selanjutnya SEBELUM 'current' dipindahkan.
            // 'next' akan menjadi head dari sisa list yang belum diurutkan.
            node next = current.getnext();

            // Simpan data untuk ditampilkan, karena pointer 'current' akan diubah.
            int dataToInsert = current.getdata();

            System.out.println("\nLangkah ke-" + step + ": Mengambil elemen '" + dataToInsert + "'");

            // Sisipkan 'current' ke dalam list 'sorted'
            sorted = sortedInsert(sorted, current);

            // ---- BAGIAN UNTUK MENAMPILKAN PROSES ----

            // 1. Buat list sementara untuk menampilkan bagian yang sudah terurut
            linkedlist sortedListDisplay = new linkedlist();
            sortedListDisplay.first = sorted;
            System.out.print("   -> List Terurut: ");
            sortedListDisplay.display();

            // 2. Buat list sementara untuk menampilkan sisa bagian yang belum terurut
            linkedlist unsortedListDisplay = new linkedlist();
            unsortedListDisplay.first = next; // Gunakan 'next' sebagai head
            System.out.print("   -> Sisa List   : ");
            unsortedListDisplay.display();

            // Lanjutkan iterasi ke node berikutnya dari list asli
            current = next;
            step++;
        }

        // Update head dari list asli dengan list yang sudah terurut
        list.first = sorted;
        System.out.println("\n-----------------------------------------");
        System.out.println("Proses sorting selesai!");
        System.out.println("Hasil Akhir:");
        list.display();
    }

    /**
     * Mengurutkan dan mengukur waktu eksekusi.
     * @param list Objek linkedlist yang akan diurutkan.
     * @return Durasi sorting dalam nanodetik.
     */
    public long sort_timer(linkedlist list) {
        long startTime = System.nanoTime();
        this.sort(list);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}