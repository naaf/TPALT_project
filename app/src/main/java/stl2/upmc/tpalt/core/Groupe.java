package stl2.upmc.tpalt.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by userinsta on 18/10/2016.
 */

public class Groupe {
    private List<Contact> contacts;
    private String nom;
    public static Groupe DEFAUL = new Groupe("default");

    public Groupe(String nom, List<Contact> contacts) {
        this.nom = nom;
        this.contacts = contacts;
    }

    public Groupe(String nom) {
        this.nom = nom;
        this.contacts = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groupe groupe = (Groupe) o;

        return nom.equals(groupe.nom);

    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    public void add(Contact c) {
        c.addDansGroupes(this.getNom());
        contacts.add(c);
    }

    public void remove(Contact c) {
        c.removeDansGroupes(this.getNom());
        contacts.remove(c);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
