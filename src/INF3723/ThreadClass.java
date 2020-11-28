package INF3723;

public class ThreadClass extends Thread{

    private Integer[] array;

    //Constructeur de la classe permettant de nommer le Thread ainsi que d'importer le tableau à trier.
    public ThreadClass(String name, Integer[] array) {
        super(name);
        this.array = array;
    }


    public void run(){

        System.out.println(Thread.currentThread().getName ()+" est activé");
        long starttime= System.nanoTime()/1000000;

        //Tri par sélection fournie par l'énoncé.
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int swap = array[i];
                array[i] = array[min];
                array[min] = swap;
            }
        }

        long endtime = System.nanoTime()/1000000;
        System.out.println(String.format("%s %,d", "Durée totale de l'exécution du tri par sélection du "+Thread.currentThread().getName ()+" en millisecondes est de ", endtime-starttime));
        ThriThreadsMain.countDown ++;
    }

}
