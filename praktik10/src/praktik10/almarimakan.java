/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
public class almarimakan extends almari implements discountable {
    protected int jmlroda;
    
    @Override
    public void discount(int harga){
        this.harga = this.harga - harga;
    }
}
