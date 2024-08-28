
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


