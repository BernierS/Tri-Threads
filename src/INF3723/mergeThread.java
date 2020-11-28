package INF3723;

import java.util.List;

public class mergeThread extends Thread{

    //Importation des variables globales nécessaires.
    private static List<Integer[]> arraysListK = ThriThreadsMain.getArraysList();
    private static Integer[] tableauFinal = ThriThreadsMain.getTableauFinal();
    private static int listOrSize = ThriThreadsMain.getListOr().size();
    private static int NLISTS = ThriThreadsMain.getNLISTS();

    public void run(){

        //L'algorithme merge va seulement être exécuté si la nombre de sous-liste est plus grand que 1.
        //Si le nombre de sous-liste est égal à 1, il n'est pas nécessaire d'exécuter le merge.
        if(NLISTS>1) {
            //Cette section appel la méthode merge décrite plus bas pour chaque sous tableau.
            for (int i = 0; i < arraysListK.size () - 1; i++) {
                //Cette condition est utilisée lors de la première boucle.
                if (i == 0) {
                    merge (arraysListK.get (i), arraysListK.get (i + 1));
                }
                //Cette condition est utilisée lors de toutes les autres itérations de la boucle.
                //Elle utilise le tableau qui a été fusionné à l'étape d'avant afin de créer un nouveau tableau.
                //Cette méthode est répétée jusqu'à tant que chaque tableau ait été fusionné.
                else {
                    merge (tableauFinal, arraysListK.get (i + 1));
                }
            }
            //Modifie la variable globale tableauFinale afin de l'imprimer lors des résultats de la méthode main.
            ThriThreadsMain.setTableauFinal(tableauFinal);
        }
        else{
            //Modifie la variable globale tableauFinale afin de l'imprimer lors des résultats de la méthode main.
            ThriThreadsMain.setTableauFinal(arraysListK.get(0));
        }
    }

    //Méthode de merge inspiré par le pseudo-code de l'énoncé.
    //Certaines modifications ont été adoptées afin d'exclure les erreurs.
    public static void merge(Integer[] array0,Integer[] array1){

        int i=0;
        int j=0;
        Integer [] tableauMid = new Integer[listOrSize];

        //Cette boucle va sélectionner le plus petit élément entre le premier et le deuxième tableau.
        //Il va ensuite conserver cette valeur dans la "tableauMid" et recommencer la boucle pour chaque valeur des 2 tableaux.
        for(int k=0;k< listOrSize;k++){
            try {
                if (array0[i] <= array1[j]) {
                    tableauMid[k] = array0[i];
                    i++;
                }
                else {
                    tableauMid[k] = array1[j];
                    j++;
                }
            }
            catch (Exception e){
                if(j==array1.length && i==array0.length){
                    break;
                }
                else if(i==array0.length && j<array1.length) {
                    tableauMid[k] = array1[j];
                    j++;
                }

                else{
                    tableauMid[k] = array0[i];
                    i++;
                }
            }
        }
        //Prend les données tu tableau temporaire "tableauMid et remet les valeurs dans le "tableauFinal"
        tableauFinal = tableauMid;
    }
}


