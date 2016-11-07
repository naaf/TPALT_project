package stl2.upmc.tpalt.core;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashraf on 05/11/2016.
 */

public class Groupes {
    private Map<String, Groupe> groupes;

    public Groupes() {
        groupes = new HashMap<>();
    }

    boolean addGroup(Groupe e) {
        if (groupes.containsKey(e.getNom()))
            return false;
        groupes.put(e.getNom(),e);
        return true;
    }
}
