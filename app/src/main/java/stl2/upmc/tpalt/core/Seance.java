package stl2.upmc.tpalt.core;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by ashraf on 16/10/2016.
 */

public class Seance {
    private String nom;
    private TypeSeance type;
    private HashMap<ContactInterneIF, Boolean> listParticipant;

    public Seance(String nom,TypeSeance tyoe) {
        this.nom = nom;
        this.type = tyoe;
        this.listParticipant = new HashMap<>();
    }

    public Seance(String nom, TypeSeance type, List<ContactInterneIF> listParticipant) {
        this.nom = nom;
        this.type = type;
        setListParticipant(listParticipant,false);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeSeance getType() {
        return type;
    }

    public void setType(TypeSeance type) {
        this.type = type;
    }

    public Set<ContactInterneIF> getListParticipant() {
        return listParticipant.keySet();
    }

    public void setListParticipant(List<ContactInterneIF> listParticipant, boolean pre) {
        this.listParticipant = new HashMap<>();
        for ( ContactInterneIF key : listParticipant)
            this.listParticipant.put(key,pre);
    }

    public void addParticipant(ContactInterneIF con, boolean pre){
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
