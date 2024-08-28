package todolist;

import java.util.List;

public class GestoreStatistiche {

    private int totaleAttività;
    private int completate;
    private int nonCompletate;

    public GestoreStatistiche(List<Attività> listaAttività) {
        calcolaStatistiche(listaAttività);
    }

    private void calcolaStatistiche(List<Attività> listaAttività) {
        totaleAttività = listaAttività.size();
        completate = 0;
        nonCompletate = 0;

        for (Attività attività : listaAttività) {
            if (attività.èCompletata()) {
                completate++;
            } else {
                nonCompletate++;
            }
        }
    }

    public void visualizzaStatistiche() {
        System.out.println("Statistiche delle Attività:");
        System.out.println("Totale: " + totaleAttività);
        System.out.println("Completate: " + completate);
        System.out.println("Non completate: " + nonCompletate);
    }

    public int getTotaleAttività() {
        return totaleAttività;
    }

    public int getCompletate() {
        return completate;
    }

    public int getNonCompletate() {
        return nonCompletate;
    }
}