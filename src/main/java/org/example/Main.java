package org.example;

import org.example.node.linkedlist;
import org.example.sort.*;
import java.util.Scanner;
import java.util.Random; // Impor kelas Random

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("Pilih mode yang ingin Anda jalankan:");
            System.out.println("1: Visualisasi Sorting Langkah per Langkah (Input Manual)");
            System.out.println("2: Uji Kecepatan Sorting (1000 Angka Acak)");
            System.out.println("3: Keluar");
            System.out.print("Masukkan pilihan Anda (1/2/3): ");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    jalankanModeVisualisasi(scanner);
                    break;
                case "2":
                    jalankanUjiKecepatan();
                    break;
                case "3":
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
     * Mode 1: Menjalankan visualisasi langkah per langkah dengan input manual.
     * Fungsi ini berisi logika lama Anda.
     */
    public static void jalankanModeVisualisasi(Scanner scanner) {
        linkedlist myList = new linkedlist();

        System.out.println("\n===== Mode: Visualisasi Langkah per Langkah =====");
        System.out.println("Masukkan angka satu per satu, lalu tekan Enter.");
        System.out.println("Ketik 's' lalu tekan Enter untuk berhenti.");

        while (true) {
            System.out.print("Masukkan angka: ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("s")) break;
            try {
                myList.insert(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            }
        }

        if (myList.first == null) {
            System.out.println("Tidak ada data untuk diurutkan.");
            return;
        }

        System.out.println("\nData yang Anda masukkan:");
        myList.display();

        System.out.println("\n===== Pilih Algoritma Sorting =====");
        System.out.println("1: Bubble Sort");
        System.out.println("2: Selection Sort");
        System.out.println("3: Insertion Sort");
        System.out.print("Masukkan pilihan Anda (1/2/3): ");

        int algoChoice = scanner.nextInt();
        linkedlist listToSort = copyList(myList);

        switch (algoChoice) {
            case 1:
                new buble_sort().sort_tampilkan(listToSort);
                break;
            case 2:
                new selection_sort().sort_tampilkan(listToSort);
                break;
            case 3:
                new insertion_sort().sort_tampilkan(listToSort);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    /**
     * Mode 2: Menjalankan uji kecepatan pada 1000 angka acak.
     * Fungsi ini berisi logika baru.
     */
    public static void jalankanUjiKecepatan() {
        System.out.println("\n===== Mode: Uji Kecepatan Sorting =====");
        System.out.println("Membuat list dengan 1000 angka acak (0-9999)...");

        linkedlist randomList = new linkedlist();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            randomList.insert(rand.nextInt(10000)); // Angka acak antara 0-9999
        }

        System.out.println("Data acak berhasil dibuat. Memulai pengujian...");

        // Menjalankan dan mengukur Bubble Sort
        long bubbleSortTime = new buble_sort().sort_timer(copyList(randomList));

        // Menjalankan dan mengukur Selection Sort
        long selectionSortTime = new selection_sort().sort_timer(copyList(randomList));

        // Menjalankan dan mengukur Insertion Sort
        long insertionSortTime = new insertion_sort().sort_timer(copyList(randomList));

        System.out.println("...Proses selesai.");
        System.out.println("\n===== Hasil Perbandingan Kecepatan Sorting (1000 Data Acak) =====");
        System.out.println("-------------------------------------------------");
        System.out.printf("1. Bubble Sort   : %,15d ns (%-10.4f ms)\n", bubbleSortTime, bubbleSortTime / 1_000_000.0);
        System.out.printf("2. Selection Sort: %,15d ns (%-10.4f ms)\n", selectionSortTime, selectionSortTime / 1_000_000.0);
        System.out.printf("3. Insertion Sort: %,15d ns (%-10.4f ms)\n", insertionSortTime, insertionSortTime / 1_000_000.0);
        System.out.println("-------------------------------------------------");
    }

    /**
     * Helper method untuk membuat salinan dari sebuah linked list.
     */
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