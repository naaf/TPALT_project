package stl2.upmc.tpalt;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.core.Evenement;

/**
 * Created by userinsta on 18/10/2016.
 */

public class MyApplication extends Application {
    private List<Evenement> events;

    public MyApplication() {
        this.events = new ArrayList<>();
    }

    public List<Evenement> getEvt(){return events;}

}
