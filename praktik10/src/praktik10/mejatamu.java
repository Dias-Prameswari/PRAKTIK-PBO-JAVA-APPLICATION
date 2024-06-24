/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
public class mejatamu extends meja implements discountable{
    protected String bentuk_kaca;
    
    @Override
    public void discount(int harga){
        this.harga = this.harga - harga;
    }
}
