/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
 class handphone extends telepon implements kamera, radio {
    private String gelombang;
    private float pixel;
    
    @Override
    public void ambilGambar()
    {
    System.out.println("Gambar terambil...");
    }
    @Override
    public void setGelombang(String gel)
    {
    this.gelombang=gel;
    }
    @Override
    public void setPixel(float pixel)
    {
    this.pixel=pixel;
    }
    public void setNomor(long no)
    {
    this.nomer=no;
    }
}
