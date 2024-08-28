
package todolist;

import java.util.*;

public class GestoreCategorie {

    private Map<String, List<Attività>> categorie;

    public GestoreCategorie() {
        categorie = new HashMap<>();
    }

    public void aggiungiCategoria(String nomeCategoria) {
        if (!categorie.containsKey(nomeCategoria)) {
            categorie.put(nomeCategoria, new ArrayList<>());
            System.out.println("Categoria aggiunta: " + nomeCategoria);
        } else {
            System.out.println("La categoria " + nomeCategoria + " esiste già.");
        }
    }

    public void rimuoviCategoria(String nomeCategoria) {
        if (categorie.containsKey(nomeCategoria)) {
            categorie.remove(nomeCategoria);
            System.out.println("Categoria rimossa: " + nomeCategoria);
        } else {
            System.out.println("La categoria " + nomeCategoria + " non esiste.");
        }
    }

    public void assegnaAttivitàACategoria(String nomeCategoria, Attività attività) {
        if (categorie.containsKey(nomeCategoria)) {
            categorie.get(nomeCategoria).add(attività);
            System.out.println("Attività \"" + attività.getNomeAttività() + "\" assegnata alla categoria " + nomeCategoria);
        } else {
            System.out.println("La categoria " + nomeCategoria + " non esiste.");
        }
    }

    public void rimuoviAttivitàDaCategoria(String nomeCategoria, Attività attività) {
        if (categorie.containsKey(nomeCategoria)) {
            categorie.get(nomeCategoria).remove(attività);
            System.out.println("Attività \"" + attività.getNomeAttività() + "\" rimossa dalla categoria " + nomeCategoria);
        } else {
            System.out.println("La categoria " + nomeCategoria + " non esiste.");
        }
    }

    public void visualizzaCategorie() {
        System.out.println("Categorie e Attività Associate:");
        for (String categoria : categorie.keySet()) {
            System.out.println("Categoria: " + categoria);
            for (Attività attività : categorie.get(categoria)) {
                System.out.println("  - " + attività.getNomeAttività());
            }
        }
    }

    public List<Attività> getAttivitàPerCategoria(String nomeCategoria) {
        if (categorie.containsKey(nomeCategoria)) {
            return categorie.get(nomeCategoria);
        } else {
            System.out.println("La categoria " + nomeCategoria + " non esiste.");
            return new ArrayList<>();
        }
    }

    public void visualizzaAttivitàPerCategoria(String nomeCategoria) {
        if (categorie.containsKey(nomeCategoria)) {
            System.out.println("Attività nella categoria \"" + nomeCategoria + "\":");
            for (Attività attività : categorie.get(nomeCategoria)) {
                System.out.println("  - " + attività.getNomeAttività());
            }
        } else {
            System.out.println("La categoria " + nomeCategoria + " non esiste.");
        }
    }

    public boolean esisteCategoria(String nomeCategoria) {
        return categorie.containsKey(nomeCategoria);
    }