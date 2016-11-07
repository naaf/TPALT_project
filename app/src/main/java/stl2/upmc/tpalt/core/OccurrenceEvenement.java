package stl2.upmc.tpalt.core;

import java.util.Date;

/**
 * Created by ashraf on 06/11/2016.
 */

public class OccurrenceEvenement {
    private Date date;
    private Evenement evenement;


    public OccurrenceEvenement( Evenement evenement) {
        this.date = new Date();
        this.evenement = evenement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
