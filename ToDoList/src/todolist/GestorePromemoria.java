
package todolist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GestorePromemoria {

    private List<Promemoria> promemoriaAttivi;

    public GestorePromemoria(List<Promemoria> promemoriaList) {
        this.promemoriaAttivi = promemoriaList;
    }


 public void aggiungiPromemoria(Attività attività, LocalDateTime orarioPromemoria) {
        Promemoria nuovoPromemoria = new Promemoria(attività, orarioPromemoria);
        promemoriaAttivi.add(nuovoPromemoria);
        pianificaPromemoria(nuovoPromemoria);
        System.out.println("Promemoria aggiunto per l'attività: " + attività.getNomeAttività() + " alle " + orarioPromemoria.toString());
    }

    public void rimuoviPromemoria(Attività attività) {
        promemoriaAttivi.removeIf(promemoria -> promemoria.getAttività().equals(attività));
        System.out.println("Promemoria rimosso per l'attività: " + attività.getNomeAttività());
    }

    public void visualizzaPromemoriaAttivi() {
        System.out.println("Promemoria attivi:");
        for (Promemoria promemoria : promemoriaAttivi) {
            System.out.println("Attività: " + promemoria.getAttività().getNomeAttività() + " - Promemoria alle: " + promemoria.getOrarioPromemoria());
        }
    }

    private void pianificaPromemoria(Promemoria promemoria) {
        long delay = calcolaDelayInMillis(promemoria.getOrarioPromemoria());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                inviaNotifica(promemoria.getAttività());
            }
        }, delay);
    }

    private long calcolaDelayInMillis(LocalDateTime orarioPromemoria) {
        LocalDateTime adesso = LocalDateTime.now();
        return java.time.Duration.between(adesso, orarioPromemoria).toMillis();
    }

    private void inviaNotifica(Attività attività) {
        System.out.println("Promemoria: È ora di completare l'attività: " + attività.getNomeAttività());
    }

    public void aggiornaPromemoria(Attività attività, LocalDateTime nuovoOrarioPromemoria) {
        for (Promemoria promemoria : promemoriaAttivi) {
            if (promemoria.getAttività().equals(attività)) {
                promemoria.setOrarioPromemoria(nuovoOrarioPromemoria);
                pianificaPromemoria(promemoria);
                System.out.println("Promemoria aggiornato per l'attività: " + attività.getNomeAttività() + " al nuovo orario: " + nuovoOrarioPromemoria.toString());
                break;
            }
        }
    }

    public boolean esistePromemoriaPerAttività(Attività attività) {
        for (Promemoria promemoria : promemoriaAttivi) {
            if (promemoria.getAttività().equals(attività)) {
                return true;
            }
        }
        return false;
    }
}