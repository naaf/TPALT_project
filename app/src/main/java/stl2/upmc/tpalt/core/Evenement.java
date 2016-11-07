package stl2.upmc.tpalt.core;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ashraf on 16/10/2016.
 */

public class Evenement {
    private String nom;
    private HashMap<Contact, Boolean> listParticipant;
    private Date dateCreation;
    private String description;

    public Evenement(String nom) {
        this.nom = nom;
        this.description = new String();
        this.listParticipant = new HashMap<>();
        this.dateCreation = new Date();
    }

    public Evenement(String nom, List<Contact> listParticipant) {
        this.nom = nom;
        setListParticipant(listParticipant, false);
    }

    public Evenement(Evenement event) {
        this.dateCreation = event.dateCreation;
        this.description = new String(event.description);
        this.nom = new String(event.nom);
        setListParticipant(new ArrayList<Contact>(event.getListParticipant()), false);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evenement evenement = (Evenement) o;

        return nom.equals(evenement.nom);

    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Contact> getListParticipant() {
        return listParticipant.keySet();
    }

    public void setListParticipant(List<Contact> listParticipant, boolean pre) {
        this.listParticipant = new HashMap<>();
        for (Contact key : listParticipant)
            this.listParticipant.put(key, pre);
    }

    public void addParticipant(Contact con, boolean pre) {
        if (!listParticipant.containsKey(con))
            this.listParticipant.put(con, pre);
    }

    public void addParticipant(List<Contact> con, boolean pre) {
        for (Contact key : con)
            addParticipant(key, pre);
    }

    public void removeParticipant(Contact c) {
        this.listParticipant.remove(c);
    }

    public int getSize() {
        if (listParticipant.keySet().isEmpty())
            return 0;
        else return listParticipant.size();
    }

    public void present(Contact c) {
        listParticipant.put(c, true);
    }
    public boolean isPresent(Contact c) {
        if(listParticipant.containsKey(c))
            return listParticipant.get(c);
        return false;
    }
    public void init(){
        for(Map.Entry e : listParticipant.entrySet() ){
            e.setValue(false);
        }
    }

    public String getNom() {
        return nom;
    }

}
