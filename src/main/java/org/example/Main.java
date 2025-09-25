package org.example;

import org.example.node.linkedlist;
import org.example.sort.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("Pilih mode yang ingin Anda jalankan:");
            System.out.println("1: Visualisasi Sorting Langkah per Langkah (Input Manual)");
            System.out.println("2: Uji Kecepatan Sorting (100.000 Angka Acak)");
            System.out.println("3: Visualisasi Otomatis ( 10 Angka Acak)");
            System.out.println("4: Keluar");
            System.out.print("Masukkan pilihan Anda (1/2/3/4): ");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    jalankanModeVisualisasi(scanner);
                    break;
                case "2":
                    jalankanUjiKecepatan();
                    break;
                case "3":
                    jalankanModeOtomatis(); // Memanggil fungsi baru
                    break;
                case "4":
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    scanner.close();
                    return; // Keluar dari program
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    /**
     * BARU: Mode 3 - Menjalankan visualisasi otomatis untuk best & worst case.
     */
    public static void jalankanModeOtomatis() {
        System.out.println("\n===== Mode: Visualisasi Otomatis Angka Acak =====");
        System.out.println("Program akan menjalankan algoritma sorting:");
        System.out.println("3. Data Acak (Kasus Umum)");
        System.out.println("-------------------------------------------------------------\n");

        // 1. Definisikan dataset

        Random rand = new Random();
        linkedlist dataAcak = new linkedlist();
        for (int i = 0; i < 10; i++) {
            dataAcak.insert(rand.nextInt(99));
        }


        // ================== BUBBLE SORT ==================
        System.out.println("\n############### ANALISIS BUBBLE SORT ###############");
        runAndDisplay("Bubble Sort: Average Case (Data Acak)", new buble_sort(), dataAcak);

        // ================== SELECTION SORT ==================
        System.out.println("\n############### ANALISIS SELECTION SORT ###############");
        runAndDisplay("Selection Sort: Average Case (Data Acak)", new selection_sort(), dataAcak);

        // ================== INSERTION SORT ==================
        System.out.println("\n############### ANALISIS INSERTION SORT ###############");
        runAndDisplay("Insertion Sort: Average Case (Data Acak)", new insertion_sort(), dataAcak);

        System.out.println("\n===== Visualisasi Otomatis Selesai =====");
        System.out.println("Tekan Enter untuk kembali ke Menu Utama...");
        try { System.in.read(); } catch(Exception e) {}
    }

    /**
     * Helper untuk membuat linked list dari array integer.
     */
    public static linkedlist createList(int[] data) {
        linkedlist list = new linkedlist();
        for (int value : data) {
            list.insert(value);
        }
        return list;
    }

    /**
     * Helper untuk menjalankan dan menampilkan hasil sorting.
     */
    public static void runAndDisplay(String title, Object sorter, linkedlist originalList) {
        System.out.println("\n======================================================");
        System.out.println(title);
        System.out.println("------------------------------------------------------");
        System.out.print("Data Awal: ");
        originalList.display();
        System.out.println("\n");

        linkedlist listToSort = copyList(originalList);

        // Menjalankan visualisasi langkah per langkah
        if (sorter instanceof buble_sort) {
            ((buble_sort) sorter).sort_tampilkan(listToSort);
        } else if (sorter instanceof selection_sort) {
            ((selection_sort) sorter).sort_tampilkan(listToSort);
        } else if (sorter instanceof insertion_sort) {
            ((insertion_sort) sorter).sort_tampilkan(listToSort);
        }

        // Menjalankan timer untuk mengukur kecepatan
        linkedlist listForTimer = copyList(originalList);
        long timeTaken = 0;
        if (sorter instanceof buble_sort) {
            timeTaken = ((buble_sort) sorter).sort_timer(listForTimer);
        } else if (sorter instanceof selection_sort) {
            timeTaken = ((selection_sort) sorter).sort_timer(listForTimer);
        } else if (sorter instanceof insertion_sort) {
            timeTaken = ((insertion_sort) sorter).sort_timer(listForTimer);
        }

        System.out.print("\nData Hasil Urut: ");
        listToSort.display();
        System.out.printf("\nWaktu Eksekusi: %,d ns\n", timeTaken);
        System.out.println("======================================================");
    }


    public static void jalankanModeVisualisasi(Scanner scanner) {
        linkedlist myList = new linkedlist();

        System.out.println("\n===== Mode: Visualisasi Langkah per Langkah =====");
        System.out.print("Berapa banyak angka yang ingin Anda masukkan? ");
        int jumlahAngka = scanner.nextInt();

        System.out.println("Masukkan " + jumlahAngka + " angka satu per satu, lalu tekan Enter.");

        for (int i = 0; i < jumlahAngka; i++) {
            System.out.print("Masukkan angka ke-" + (i + 1) + ": ");
            try {
                myList.insert(scanner.nextInt());
            } catch (Exception e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next();
                i--;
            }
        }

        if (myList.first == null) {
            System.out.println("Tidak ada data untuk diurutkan.");
            return;
        }

        while (true) {
            System.out.println("\nData asli yang Anda masukkan:");
            myList.display();

            System.out.println("\n===== Pilih Algoritma Sorting =====");
            System.out.println("1: Bubble Sort");
            System.out.println("2: Selection Sort");
            System.out.println("3: Insertion Sort");
            System.out.println("4: Kembali ke Menu Utama");
            System.out.print("Pilih algoritma untuk divisualisasikan (1/2/3/4): ");

            int algoChoice = scanner.nextInt();

            if (algoChoice == 4) {
                System.out.println("Kembali ke Menu Utama...");
                break;
            }

            linkedlist listToSort = copyList(myList);

            switch (algoChoice) {
                case 1:
                    System.out.println("\n--- Visualisasi Bubble Sort ---");
                    new buble_sort().sort_tampilkan(listToSort);
                    break;
                case 2:
                    System.out.println("\n--- Visualisasi Selection Sort ---");
                    new selection_sort().sort_tampilkan(listToSort);
                    break;
                case 3:
                    System.out.println("\n--- Visualisasi Insertion Sort ---");
                    new insertion_sort().sort_tampilkan(listToSort);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            System.out.println("\nTekan Enter untuk melanjutkan...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }


    public static void jalankanUjiKecepatan() {
        System.out.println("\n===== Mode: Uji Kecepatan Sorting =====");
        System.out.println("Membuat list dengan 100.000 angka acak (0-9999)...");

        linkedlist randomList = new linkedlist();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            randomList.insert(rand.nextInt(9999));
        }

        System.out.println("Data acak berhasil dibuat. Memulai pengujian...");

        long insertionSortTime = new insertion_sort().sort_timer(copyList(randomList));
        long bubbleSortTime = new buble_sort().sort_timer(copyList(randomList));
        long selectionSortTime = new selection_sort().sort_timer(copyList(randomList));

        System.out.println("...Proses selesai.");
        System.out.println("\n===== Hasil Perbandingan Kecepatan Sorting (100.000 Data Acak) =====");
        System.out.println("-------------------------------------------------");
        System.out.printf("1. Bubble Sort   : %,15d ns (%-10.4f ms)\n", bubbleSortTime, bubbleSortTime / 1_000_000.0);
        System.out.printf("2. Selection Sort: %,15d ns (%-10.4f ms)\n", selectionSortTime, selectionSortTime / 1_000_000.0);
        System.out.printf("3. Insertion Sort: %,15d ns (%-10.4f ms)\n", insertionSortTime, insertionSortTime / 1_000_000.0);
        System.out.println("-------------------------------------------------");
    }

    public static linkedlist copyList(linkedlist originalList) {
        if (originalList == null || originalList.first == null) return new linkedlist();
        linkedlist newList = new linkedlist();
        org.example.node.node current = originalList.first;
        while (current != null) {
            newList.insert(current.getdata());
            current = current.getnext();
        }
        return newList;
    }
}