package com.company;

public class Main {

    public static void main(String[] args) {
        boolean result = true;
        Animals chicken = new Animals("Куропатка");
        Animals egg = new Animals("Яичко");
        egg.start();
        chicken.start();
        try{
            egg.join();
            if (chicken.isAlive()) {
                System.out.println("вин зис гейм куропатОчка!");
            } else {
                System.out.println("вин зис гейм  яичко!");
            }
        }
        catch (Exception ex){

        }
    }
}
class Animals extends Thread{
    String animalName;

    Animals(String name){
        animalName = name;
    }

    public void run(){
        for(int i = 0; i < 5; i++) {
            System.out.println(animalName);
        }
    }
}
