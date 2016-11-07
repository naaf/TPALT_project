package stl2.upmc.tpalt.evenement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import stl2.upmc.tpalt.MyApplication;
import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.contacts.ContactAdapter;
import stl2.upmc.tpalt.contacts.ContactSelectionAdapter;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.utils.MyR;

public class CheckPresenceActivity extends AppCompatActivity {

    private int positionEvenement;
    Button btnValider;
    Button btnCancel;

    ListView listView;
    private MyApplication app;
    private Evenement event;
    private Set<Contact> participants;
    private ArrayAdapter<Contact> adapter;
    List<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_presence);

        app = (MyApplication) this.getApplication();
        btnValider = (Button) findViewById(R.id.listCheck_ok);
        btnCancel = (Button) findViewById(R.id.listCheck_no);
        listView = (ListView) findViewById(R.id.listCheckView);


        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra(MyR.INDEX_EVENEMENT)) {
            positionEvenement = intent.getExtras().getInt(MyR.INDEX_EVENEMENT);
            event = app.getEvents().get(positionEvenement);
            participants = event.getListParticipant();
            contacts = new ArrayList<>(participants);
            adapter = new ContactSelectionAdapter(this, contacts);
            listView.setAdapter(adapter);
        }

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactSelectionAdapter adp = (ContactSelectionAdapter) adapter;
                List<Contact> cts = adp.getContactsSelected();
                Evenement occ = new Evenement(event);
                for (Contact c : cts) {
                    occ.present(c);
                }
                app.addOccurrence(occ);
                event.init();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }


}
