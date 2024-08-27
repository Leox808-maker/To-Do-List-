
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