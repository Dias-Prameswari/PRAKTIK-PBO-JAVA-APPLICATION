/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktik10;

/**
 *
 * @author HP
 */
abstract class binatang {
    abstract void makan();
    abstract void tidur();
    void mati(){
        System.out.println("mati.....");
    }
}

class harimau extends binatang{
    @Override
    void makan(){
        System.out.println("harimau makan.....");
    }
    @Override
    void tidur(){
        System.out.println("harimau tidur....");
    }
}

class bebek extends binatang{
    @Override
    void makan(){
        System.out.println("bebek makan.....");
    }
    @Override
    void tidur(){
        System.out.println("bebek tidur....");
    }
}


