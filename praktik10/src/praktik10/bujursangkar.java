/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
public class bujursangkar extends bangun2d{
    private int sisi;
    public bujursangkar(int sisi)
        {
        this.sisi = sisi;
        System.out.println("Sisi Bujur Sangkar : "+sisi);
        }
    @Override
    public void cetakLuas()
    {
    int luas = sisi * sisi;
    System.out.println("Luas Bujur Sangkar : "+luas);
    }
    @Override
    public void cetakKeliling()
    {
    int keliling = 4 * sisi;
    System.out.println("Keliling Bujur Sangkar : "+keliling);
    }
}
