/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
public class zoo3absctract {
    static void test(binatang a){
        a.makan();
        a.tidur();
        a.mati();
    
    }

    public static void main(String[]args){
        harimau macan = new harimau();
        bebek donald = new bebek();
        test(macan);
        test(donald);
        
    }
}
