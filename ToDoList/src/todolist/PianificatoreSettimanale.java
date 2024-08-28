
package todolist;

import java.time.LocalDate;
import java.util.*;

public class PianificatoreSettimanale {

    private Map<GiornoDellaSettimana, List<Attività>> agendaSettimanale;

    public PianificatoreSettimanale() {
        agendaSettimanale = new EnumMap<>(GiornoDellaSettimana.class);
        for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
            agendaSettimanale.put(giorno, new ArrayList<>());
        }
    }

    public void aggiungiAttività(GiornoDellaSettimana giorno, Attività attività) {
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        attivitàDelGiorno.add(attività);
        System.out.println("Attività aggiunta: " + attività.getNomeAttività() + " a " + giorno);
    }

    public void rimuoviAttività(GiornoDellaSettimana giorno, Attività attività) {
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        if (attivitàDelGiorno.remove(attività)) {
            System.out.println("Attività rimossa: " + attività.getNomeAttività() + " da " + giorno);
        } else {
            System.out.println("Attività non trovata per il giorno " + giorno);
        }
    }

    public void visualizzaAttivitàPerGiorno(GiornoDellaSettimana giorno) {
        System.out.println("Attività per " + giorno + ":");
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        if (attivitàDelGiorno.isEmpty()) {
            System.out.println("  Nessuna attività.");
        } else {
            for (Attività attività : attivitàDelGiorno) {
                System.out.println("  - " + attività.getNomeAttività() + (attività.èCompletata() ? " (Completata)" : ""));
            }
        }
    }

    public void visualizzaAgendaCompleta() {
        System.out.println("Panoramica settimanale:");
        for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
            visualizzaAttivitàPerGiorno(giorno);
        }
    }

    public List<Attività> getAttivitàPerGiorno(GiornoDellaSettimana giorno) {
        return agendaSettimanale.getOrDefault(giorno, new ArrayList<>());
    }

    public void segnaAttivitàComeCompletata(GiornoDellaSettimana giorno, Attività attività) {
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        if (attivitàDelGiorno.contains(attività)) {
            attività.setCompletata(true);
            System.out.println("Attività completata: " + attività.getNomeAttività() + " in " + giorno);
        } else {
            System.out.println("Attività non trovata per il giorno " + giorno);
        }
    }

    public void pianificaSettimanaAutomaticamente() {
        for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
            if (agendaSettimanale.get(giorno).isEmpty()) {
                Attività attivitàAutomatica = new Attività("Attività automatica per " + giorno);
                aggiungiAttività(giorno, attivitàAutomatica);
            }
        }
        System.out.println("Settimana pianificata automaticamente.");
    }

    public void cancellaSettimana() {
        for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
            agendaSettimanale.get(giorno).clear();
        }
        System.out.println("Tutte le attività della settimana sono state cancellate.");
    }

    public boolean esisteAttivitàInGiorno(GiornoDellaSettimana giorno, Attività attività) {
        return agendaSettimanale.get(giorno).contains(attività);
    }

    public void aggiornaAttività(GiornoDellaSettimana giorno, Attività attivitàVecchia, Attività attivitàNuova) {
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        int index = attivitàDelGiorno.indexOf(attivitàVecchia);
        if (index != -1) {
            attivitàDelGiorno.set(index, attivitàNuova);
            System.out.println("Attività aggiornata per il giorno " + giorno);
        } else {
            System.out.println("Attività non trovata per il giorno " + giorno);
        }
    }

    public void visualizzaAttivitàNonCompletatePerGiorno(GiornoDellaSettimana giorno) {
        System.out.println("Attività non completate per " + giorno + ":");
        List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
        for (Attività attività : attivitàDelGiorno) {
            if (!attività.èCompletata()) {
                System.out.println("  - " + attività.getNomeAttività());
            }
        }
    }

    public void spostaAttivitàAGiorno(GiornoDellaSettimana giornoVecchio, GiornoDellaSettimana giornoNuovo, Attività attività) {
        if (agendaSettimanale.get(giornoVecchio).remove(attività)) {
            aggiungiAttività(giornoNuovo, attività);
            System.out.println("Attività spostata da " + giornoVecchio + " a " + giornoNuovo);
        } else {
            System.out.println("Attività non trovata nel giorno " + giornoVecchio);
        }
    }

    public void ripianificaAttività(GiornoDellaSettimana giorno, Attività attività, LocalDate nuovaData) {
        System.out.println("Attività ripianificata: " + attività.getNomeAttività() + " per il giorno " + nuovaData);
    }

    public int contaAttivitàInGiorno(GiornoDellaSettimana giorno) {
        return agendaSettimanale.get(giorno).size();
    }

    public void visualizzaStatisticheSettimana() {
        int totaleAttività = 0;
        int completate = 0;
        for (GiornoDellaSettimana giorno : GiornoDellaSettimana.values()) {
            List<Attività> attivitàDelGiorno = agendaSettimanale.get(giorno);
            totaleAttività += attivitàDelGiorno.size();
            for (Attività attività : attivitàDelGiorno) {
                if (attività.èCompletata()) {
                    completate++;
                }
            }
        }
        System.out.println("Statistiche settimanali:");
        System.out.println("  - Attività totali: " + totaleAttività);
        System.out.println("  - Attività completate: " + completate);
        System.out.println("  - Attività non completate: " + (totaleAttività - completate));
    }
}
