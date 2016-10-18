package stl2.upmc.tpalt.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by ashraf on 16/10/2016.
 */

public class Evenement {
    private String nom;

    private HashMap<Contact, Boolean> listParticipant;

    public Evenement(String nom) {
        this.nom = nom;

        this.listParticipant = new HashMap<>();
    }

    public Evenement(String nom, List<Contact> listParticipant) {
        this.nom = nom;
        setListParticipant(listParticipant,false);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Contact> getListParticipant() {
        return listParticipant.keySet();
    }

    public void setListParticipant(List<Contact> listParticipant, boolean pre) {
        this.listParticipant = new HashMap<>();
        for ( Contact key : listParticipant)
            this.listParticipant.put(key,pre);
    }

    public void addParticipant(Contact con, boolean pre){
        this.listParticipant.put(con,pre);
    }
    public int getSize(){
        if (listParticipant.keySet().isEmpty())
            return 0;
        else return listParticipant.size();
    }
    public String getNom() {
        return nom;
    }


}
