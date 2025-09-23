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
     * PERUBAHAN DI SINI:
     * Mode 1: Menjalankan visualisasi langkah per langkah dengan input jumlah angka,
     * dan memungkinkan pengguna untuk mencoba beberapa algoritma pada data yang sama.
     */
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
                scanner.next(); // Membersihkan buffer scanner
                i--; // Ulangi iterasi
            }
        }

        if (myList.first == null) {
            System.out.println("Tidak ada data untuk diurutkan.");
            return;
        }

        // Loop agar pengguna bisa mencoba sorting berulang kali pada data yang sama
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
                break; // Keluar dari loop visualisasi
            }

            // PENTING: Buat salinan list DI DALAM loop
            // Ini memastikan kita selalu menggunakan data asli yang belum terurut
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
                    // Pastikan Anda sudah membuat class insertion_sort
                    // dan method sort_tampilkan di dalamnya
                    System.out.println("\n--- Visualisasi Insertion Sort ---");
                    new insertion_sort().sort_tampilkan(listToSort);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            System.out.println("\nTekan Enter untuk melanjutkan...");
            scanner.nextLine(); // Menunggu input sebelum membersihkan layar/melanjutkan
            scanner.nextLine();
        }
    }

    // Fungsi jalankanUjiKecepatan() dan copyList() tidak perlu diubah, jadi tetap sama.

    /**
     * Mode 2: Menjalankan uji kecepatan pada 100.000 angka acak.
     */
    public static void jalankanUjiKecepatan() {
        System.out.println("\n===== Mode: Uji Kecepatan Sorting =====");
        System.out.println("Membuat list dengan 100.000 angka acak (0-9999)...");

        linkedlist randomList = new linkedlist();


        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            randomList.insert(rand.nextInt(100000)); // Angka acak antara 0-9999
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