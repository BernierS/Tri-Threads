package INF3723;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThriThreadsMain extends Thread{

    //Initiation de toutes les variables globales.
    private static Thread threads;
    private static Thread mergeThread;
    private static Integer[] tableauFinal = new Integer[200];
    private static List<Integer[]> arraysListK;
    private static List<Integer> listOr = new ArrayList<Integer> ();
    private static int NLISTS;
    public static int countDown = 0;

    //Initiation de toutes les "getters and setters" nécessaires
    public static List<Integer[]> getArraysList () {
        return arraysListK;
    }
    public static Integer[] getTableauFinal () {
        return tableauFinal;
    }
    public static List<Integer> getListOr () {
        return listOr;
    }
    public static int getNLISTS () {
        return NLISTS;
    }
    public static void setTableauFinal (Integer[] tableauFinal) {
        ThriThreadsMain.tableauFinal = tableauFinal;
    }

    //Initiation de la méthode main
    public static void main(String[] args) throws InterruptedException {

        //Initiation de l'interface principale offrant deux options
        char point1 = ' ';
        while(point1 != 'A' && point1 != 'B'){
            System.out.println("------------------------------------MENU-1------------------------------------");
            System.out.println("Veuillez sélectionner le type de mode d'entré entre l'option A et l'option B: ");
            System.out.println("(A) Veuillez entrer 'A' si vous désirez entrer une liste d'entier vous-même.");
            System.out.println("(B) Veuillez entrer 'B' si vous désirez entrer la taille d'une liste créée par le programme.");
            Scanner obj = new Scanner(System.in);
            point1 = obj.nextLine().charAt(0);
            point1 = Character.toUpperCase(point1);

            if(point1 != 'A' && point1 != 'B') {
                System.out.println ("Vous avez entré un caractère non valide. Veuillez entrer le caractère A ou B.");
            }
        }

        //Section permettant à l'utilisateur d'entrer manuellement une liste d'entier.
        if(point1 == 'A'){
            System.out.println("Veuillez sélectionner les entiers complets séparés par une virgule. Par exemple: 5,6,23,1,49,7,5,3");
            Scanner obj1 = new Scanner(System.in);
            String entiersString = obj1.nextLine();

            //Cette ligne sert à retirer le cas ou l'utilisateur entre par erreur deux virgules de suite.
            entiersString = entiersString.replaceAll(",,",",");
            String entStr[] = entiersString.split(",");
            int size = entStr.length;
            int entier[] = new int[size];
            for(int i=0;i<size;i++){
                entier[i]= Integer.parseInt(entStr[i]);
            }
            for(int i: entier){
                listOr.add(i);
            }
        }

        //Section permettant à l'ordinateur de créer une liste d'entier selon le code fourni par l'énoncé.
        else{
            int longueur = 0;
            while(longueur<=0) {
                try {
                    System.out.println ("Veuillez entrer la taille de la liste désirée: ");
                    Scanner obj2 = new Scanner (System.in);
                    longueur = obj2.nextInt ();
                    if(longueur<=0){
                        System.out.println("Le chiffre que vous avez saisi n'est pas valide. Veuillez saisir un nouveau chiffre.");
                    }
                }
                catch(Exception e){
                    System.out.println("Le chiffre que vous avez saisi n'est pas valide. Veuillez saisir un nouveau chiffre.");
                }
            }

            //Code fourni par l'énoncé
            int[] arr = new int[longueur];
            int len = arr.length;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (i%2)*i + (i+1)%2*(len-i) ; // entiers non triés
            }

            for(int i: arr){
                listOr.add(i);
            }
        }

        //Variable permettant de calculer la durée totale du temps de l'entrée de la liste originale en millisecondes
        long starttime2 = System.nanoTime()/1000000;

        //Section du code permettant d'afficher les éléments du tableau créer, 100 éléments à la fois.
        char point2 = ' ';
        int compteur = 0;
        int from = 0;

        while (point2 != 'N'){
            System.out.println("------------------------------------MENU-2------------------------------------");
            if(compteur == 0) {
                System.out.println ("Voulez-vous afficher la liste créée? (maximum de 100 éléments)");
            }
            else{
                System.out.println ("Voulez-vous afficher les 100 prochains éléments de la liste?");
            }
            System.out.println("Veuiller répondre par la lettre O pour 'oui' ou par la lettre N pour 'non'.");
            Scanner obj3 = new Scanner(System.in);
            point2 = obj3.nextLine().charAt(0);
            point2 = Character.toUpperCase(point2);

            if(point2 != 'O' && point2 != 'N'){
                System.out.println ("Vous avez entré un caractère non valide. Veuillez entrer le caractère O ou N");
            }

            else if(point2 == 'O'){

                    try {
                        int to = from+100;
                        Integer[] tempTab = listOr.toArray (new Integer[]{});
                        tempTab = Arrays.copyOfRange (tempTab, from, to);


                        System.out.print ("Voici les 100 éléments du tableau: ");
                        for (int i = 0; i < tempTab.length; i++) {
                            if (tempTab[i] != null) {
                                System.out.print (tempTab[i] + ",");
                            }
                        }
                        System.out.println ("");
                        from += 100;
                        compteur++;
                    }
                    catch (Exception e){
                        System.out.println("Tous les éléments ont déjà été imprimés.");
                        break;
                    }
            }
            else if(point2 == 'N'){
                break;
            }
        }

        //Section servant à sélectionner le nombre de sous-listes.
        while(NLISTS<=0) {
            try {
                System.out.println("------------------------------------MENU-3------------------------------------");
                System.out.println("Veiller saisir le nombre de sous-listes désiré pour la subdivision de la liste originale.");
                Scanner obj4 = new Scanner(System.in);
                NLISTS = obj4.nextInt();
                if(NLISTS<=0){
                    System.out.println("Le chiffre que vous avez saisi n'est pas valide. Veuillez saisir un nouveau chiffre.");
                }
            }
            catch(Exception e){
                System.out.println("Le chiffre que vous avez saisi n'est pas valide. Veuillez saisir un nouveau chiffre.");
            }
        }

        System.out.println("------------------------------------EXÉCUTION------------------------------------");
        //Variable servant à calculer la fin du temps de l'entrée de la liste originale en millisecondes.
        long endtime2 = System.nanoTime()/1000000;
        //Variable permettant de calculer la durée totale de l'exécution du tri en millisecondes.
        long starttime = System.nanoTime()/1000000;

        //Cette section sert à diviser le tableau en sous-tableaux égaux.
        //Puisqu'il faut créer les listes dynamiquement, chaque liste va être conservée dans une liste de tableau (List<Integer[]>) à l'aide de la méthode "splitArray".
        //La méthode "splitArray" décrite plus bas.
        int batchSize = (listOr.size ()/NLISTS);
        Integer[] originalArray = listOr.toArray(new Integer[]{});
        List<Integer[]> listK = splitArray(originalArray, batchSize);

        //Cette boucle sert à créer 1 thread pour chaque sous-liste créer à l'étape ci-dessus.
        for(Integer[] array : listK){
            threads = new Thread(new ThreadClass("thread",array));
            threads.start();
        }

        //Dans notre situation, la méthode threads.join() nous a causé plusieurs problèmes et nous n'étions pas en mesure de synchroniser les threads efficacement.
        //Nous avons donc créé la variable "countDown" qui incrémente à chaque fois qu'un thread termine son exécution.
        //Cette boucle va donc monopolisé l'exécution du thread main jusqu'à tant que chaque thread à terminer de classé sa section du tableau désigné.
        while(countDown<=NLISTS-1){
            //Ce print ici n'imprime rien. Cependant, une boucle while vide en Java peut causer plusieurs problèmes dont des boucles infinies.
            //Nous avons donc ajouté un print vide avant d'éviter ce problème que Oracle n'a pas encore résolu.
            System.out.print("");
        }

        //Cette variable sert à garder le résultat de chaque sous-liste à l'intérieur d'une variable globale (pour ensuite l'utilisé dans la classe "mergeThread".
        arraysListK = listK;

        //Cette section sert à créer le mergeThread.
        mergeThread = new Thread (new mergeThread ());
        mergeThread.start ();
        mergeThread.join ();

        //Variable permettant de calculer la durée totale de l'exécution du tri en millisecondes.
        long endtime = System.nanoTime()/1000000;

        //Affichage des résultats
        System.out.println("------------------------------------RÉSULTATS------------------------------------");
        System.out.println(String.format("%s %,d", "Durée total du temps de l'entré de la liste originale en millisecondes est de ",  endtime2-starttime2));
        System.out.println(String.format("%s %,d", "Durée total de l'exécution du tri en millisecondes est de ",  endtime-starttime));

        //Affichage du tableau complet.
        if(tableauFinal.length<100){
            System.out.println("Tableau au complet en ordre: "+ Arrays.toString(tableauFinal));
        }

        //Affichage des 50 premiers et derniers éléments
        else{
            Integer [] tempTab = Arrays.copyOfRange (tableauFinal, 0, 50);
            Integer[] tempTab2 = Arrays.copyOfRange (tableauFinal, tableauFinal.length-50, tableauFinal.length);
            System.out.println ("Voici les 50 premiers éléments du tableau: "+Arrays.toString (tempTab));
            System.out.println ("Voici les 50 dernier éléments du tableau: "+Arrays.toString (tempTab2));
        }
    }

    //Cette méthode sert à diviser le tableau original en sous-section.
    private static List<Integer[]> splitArray(Integer[] listOr, int chunkSize) {
        List<Integer[]> listK = new ArrayList<Integer[]>();
        int totalSize = listOr.length;
        if(totalSize < chunkSize ){
            chunkSize = totalSize;
        }
        int from = 0;
        int to = chunkSize;

        //Boucle servant à créer chaque sous-liste du tableau en copiant les données de "from"(point de départ) jusqu'à "to" (point d'arrivée).
        while(from < totalSize){
            Integer[] partArray = Arrays.copyOfRange(listOr, from, to);
            listK.add(partArray);

            from+= chunkSize;
            to = from + chunkSize;
            if(to>totalSize){
                to = totalSize;
            }
        }
        return listK;
    }
}
