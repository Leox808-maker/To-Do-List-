
package todolist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestore {
    private List<Attività> elencoAttività = new ArrayList<Attività>();
    private List<Attività> completate = new ArrayList<Attività>();
    private List<Attività> nonCompletate = new ArrayList<Attività>();
    @FXML
    public TextField campoInserimentoAttività;
    @FXML
    public ListView<BorderPane> visualizzazioneAttività;
    @FXML
    public ToggleGroup gruppiFiltri;

    
    @FXML
    private void  inizializza() {

        
        try (ObjectInputStream fileAttività = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/sample/Assets/Data/fileAttività.dat")))) {
            boolean fineFile = false; 
            while (!fineFile) {
                try {
                    Attività attività = (Attività) fileAttività.readObject();
                    System.out.println("Attività trovata: " + attività.getDescrizione());
                    elencoAttività.add(attività);
                } catch (EOFException e) {
                    fineFile = true; 
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(Object chiave : gruppiFiltri.getProperties().keySet()){
            System.out.println("Chiave è " + chiave);
        }
        popolaElenco(elencoAttività);
        for(Attività attività : elencoAttività){
            if(attività.èCompletata()){
                completate.add(attività);
            } else{
                nonCompletate.add(attività);
            }
        }
        for(Toggle selettore : gruppiFiltri.getToggles()){
            RadioButton pulsanteFiltro = (RadioButton) selettore;
            String nomeFiltro = pulsanteFiltro.getText();
            if(nomeFiltro.equalsIgnoreCase("Tutte")){
                selettore.setUserData(elencoAttività);
            } else if(nomeFiltro.equalsIgnoreCase("Completate")){
                selettore.setUserData(completate);
            } else {
                selettore.setUserData(nonCompletate);
            }
        }

        gruppiFiltri.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> valoreOsservabile, Toggle selettoreVecchio, Toggle selettoreNuovo) {
                if(gruppiFiltri.getSelectedToggle() != null) { 
                    popolaElenco((List<Attività>)gruppiFiltri.getSelectedToggle().getUserData());
                }
            }
        });

    }
    
public void aggiungiAttività() {

        String descrizioneAttività = campoInserimentoAttività.getText(); 
        Attività nuovaAttività = new Attività(descrizioneAttività);
        elencoAttività.add(nuovaAttività); 
        nonCompletate.add(nuovaAttività); 
        campoInserimentoAttività.setText(""); 
        System.out.println("Hai aggiunto un'attività: " + nuovaAttività.getDescrizione()); 

        creaAttività(nuovaAttività);


    }


    private void eliminaAttività(Attività attività, BorderPane contenitoreAttività) {
        elencoAttività.remove(attività); 
        if(attività.èCompletata()) { 
            completate.remove(attività);
        } else {
            nonCompletate.remove(attività);
        }

        visualizzazioneAttività.getItems().remove(contenitoreAttività); 
        System.out.println("Hai rimosso un'attività: " + attività.getDescrizione());
    }


    private void creaAttività(Attività nuovaAttività) {
        Label etichettaAttività = new Label(nuovaAttività.getDescrizione());
        etichettaAttività.setAlignment(Pos.BASELINE_LEFT);

        Button pulsanteElimina = new Button();
        pulsanteElimina.getStyleClass().add("pulsanteElimina"); 

        Button pulsanteCompletata = new Button();
        pulsanteCompletata.getStyleClass().add("pulsanteCompletata");
        gestisciAttività(etichettaAttività, pulsanteCompletata, !nuovaAttività.èCompletata()); 

        HBox contenitorePulsanti = new HBox(); 
        contenitorePulsanti.getChildren().addAll(pulsanteCompletata, pulsanteElimina); 

        BorderPane contenitoreAttività = new BorderPane();
        contenitoreAttività.setLeft(etichettaAttività); 
        contenitoreAttività.setRight(contenitorePulsanti); 

        visualizzazioneAttività.getItems().add(contenitoreAttività);


        pulsanteElimina.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent eventoAzione) {
                eliminaAttività(nuovaAttività, contenitoreAttività);
            }
        });
        pulsanteCompletata.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventoAzione) {
                if(nuovaAttività.èCompletata()) { 
                    gestisciAttività(etichettaAttività, pulsanteCompletata, true); 
                    completate.remove(nuovaAttività);
                    nonCompletate.add(nuovaAttività); 
                } else { 
                    gestisciAttività(etichettaAttività, pulsanteCompletata, false); 
                    completate.add(nuovaAttività); 
                    nonCompletate.remove(nuovaAttività); 
                }
                nuovaAttività.setCompletata(!(nuovaAttività.èCompletata())); 
            }
        });

    }


    public void salvaAttività() {
        try(ObjectOutputStream fileAttività = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/sample/Assets/Data/fileAttività.dat")))){
            for (Attività attività : elencoAttività) {
                fileAttività.writeObject(attività);
                System.out.println("Attività salvata: " + attività.getDescrizione());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void popolaElenco(List<Attività> elenco) { 
        visualizzazioneAttività.getItems().clear();
        visualizzazioneAttività.refresh(); 
        for (Attività attività : elenco) {
            creaAttività(attività); 
        }
    }
    
 public void gestisciAttività(Label descrizioneAttività, Button pulsanteStato, boolean èAttiva) {

        if(!èAttiva) {
            descrizioneAttività.getStyleClass().add("barrato");
            pulsanteStato.getStyleClass().clear();
            pulsanteStato.getStyleClass().add("nonCompletata");
        } else {
            descrizioneAttività.getStyleClass().remove("barrato"); 
            pulsanteStato.getStyleClass().clear();
            pulsanteStato.getStyleClass().add("pulsanteCompletata");
        }

    }

}
