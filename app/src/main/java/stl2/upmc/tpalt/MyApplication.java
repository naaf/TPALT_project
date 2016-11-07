package stl2.upmc.tpalt;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.core.Groupes;
import stl2.upmc.tpalt.core.OccurrenceEvenement;

/**
 * Created by userinsta on 18/10/2016.
 */

public class MyApplication extends Application {
    List<Evenement> events;
    Groupes groupes;
    List<Contact> contacts;
    List<OccurrenceEvenement> occurrenceEvenements;
    Map<Integer, List<Object>> store;

    public MyApplication() {
        this.events = new ArrayList<>();
        this.groupes = new Groupes();
        this.contacts = new ArrayList<>();
        occurrenceEvenements = new ArrayList<>();
        store = new HashMap<>();

        init();
    }

    private void init() {
        events.add(new Evenement("AAGA"));
        events.add(new Evenement("TAS"));
        events.add(new Evenement("PPC"));
        events.add(new Evenement("DAR"));
        events.add(new Evenement("TPALT"));
        contacts.add(new Contact("ashraf", "nasser"));
        contacts.add(new Contact("franck", "nazanga"));
        contacts.add(new Contact("zak", "amri"));
        contacts.add(new Contact("wail", "wail"));
        contacts.add(new Contact("moatez", "moatez"));
    }

    public List<OccurrenceEvenement> getOccurrenceEvenements() {
        return occurrenceEvenements;
    }

    public List<Evenement> getEvents() {
        return events;
    }

    public Groupes getGroupes() {
        return groupes;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Map<Integer, List<Object>> getStore() {
        return store;
    }

    public void remove(Contact c){
        contacts.remove(c);
        for(Evenement e : events){
            e.removeParticipant(c);
        }
    }
    public void addOccurrence(Evenement evenement){
        occurrenceEvenements.add(new OccurrenceEvenement(evenement));
        Log.v("onActivityResult", " occurrenceEvenements " + occurrenceEvenements );
    }
    public boolean checkAddContact(Contact c){
        if("".equals(c.getNom()) || "".equals(c.getPrenom()))
            return false;
        if(contacts.contains(c))
            return false;
        return true;
    }

    public boolean checkAddEvenement(Evenement e){
        if("".equals(e.getNom()) )
            return false;
        if (events.contains(e))return false;
        return true;
    }

}
