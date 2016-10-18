package stl2.upmc.tpalt.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by userinsta on 18/10/2016.
 */

public class Groupe  {
    private List<Contact> groupe;
    private String nom;


    public Groupe(String nom, List<Contact> groupe) {
        this.nom = nom;
        this.groupe = groupe;
    }

    public Groupe(String nom) {
        this.nom = nom;
        this.groupe = new ArrayList<>();
    }

    public void add(Contact c){
        groupe.add(c);
    }

    public void remove(Contact c){groupe.remove(c);}
    public List<Contact> getGroupe(){return groupe;}


}
