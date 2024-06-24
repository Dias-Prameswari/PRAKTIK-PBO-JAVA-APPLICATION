/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
public class demo_latihan3 {
    public static void main(String[]args){
        almarimakan almkn = new almarimakan();
        almkn.harga = 5000;
        almkn.bahan = "kayu jati";
        almkn.roda = true;
        almkn.jmlroda = 4;
        
        almaripakaian almpki = new almaripakaian();
        almpki.harga = 7000;
        almpki.bahan = "kayu mahoni";
        almpki.roda = false;
        almpki.jmlpintu = 3;
        
        mejamakan mj = new mejamakan();
        mj.harga = 3000;
        mj.bahan = "kayu pinus";
        mj.jmlkaki = 4;
        mj.jmlkursi = 6;
    
        mejatamu mjtm = new mejatamu();
        mjtm.harga = 2000;
        mjtm.bahan = "kayu merbau";
        mjtm.jmlkaki = 4;
        mjtm.bentuk_kaca = "bulat";
        
        almkn.discount(500);//nama alias
        almpki.discount(700);//nama alias
        mjtm.discount(200);// nama alias
        
        // Display information
        System.out.println("Almari Makan:");
        System.out.println("Harga: " + almkn.harga);
        System.out.println("Bahan: " + almkn.bahan);
        System.out.println("Jumlah Roda: " + almkn.jmlroda);
        System.out.println();

        System.out.println("Almari Pakaian:");
        System.out.println("Harga: " + almpki.harga);
        System.out.println("Bahan: " + almpki.bahan);
        System.out.println("Jumlah Pintu: " + almpki.jmlpintu);
        System.out.println();

        System.out.println("Meja Makan:");
        System.out.println("Harga: " + mj .harga);
        System.out.println("Bahan: " + mj .bahan);
        System.out.println("Jumlah Kaki: " + mj .jmlkaki);
        System.out.println("Jumlah Kursi: " + mj .jmlkursi);
        System.out.println();

        System.out.println("Meja Tamu:");
        System.out.println("Harga: " + mjtm.harga);
        System.out.println("Bahan: " + mjtm.bahan);
        System.out.println("Jumlah Kaki: " + mjtm.jmlkaki);
        System.out.println("Bentuk Kaca: " + mjtm.bentuk_kaca);
        
    
    }
}
