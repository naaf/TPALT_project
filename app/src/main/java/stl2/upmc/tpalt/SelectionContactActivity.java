package stl2.upmc.tpalt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.contacts.ContactAdapter;
import stl2.upmc.tpalt.contacts.ContactSelectionAdapter;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.evenement.SeanceAdapter;
import stl2.upmc.tpalt.utils.MyR;

public class SelectionContactActivity extends AppCompatActivity {
    private CheckBox box;
    private TextView nom;
    private ListView listView;
    private Button valider;
    private Button annuler;

    private ArrayAdapter<Contact> adapter;
    private List<Contact> data;
    private MyApplication app;
    private int positionEvenement;
    private Evenement event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_selection);
        app = (MyApplication) this.getApplication();

        data = app.getContacts();

        nom = (TextView) findViewById(R.id.select_name);
        box = (CheckBox) findViewById(R.id.select_checbox);
        valider = (Button) findViewById(R.id.listSelection_ok) ;
        annuler= (Button) findViewById(R.id.listSelection_no) ;

        listView = (ListView) findViewById(R.id.listSelectionView);
        adapter = new ContactSelectionAdapter(this, data);
        listView.setAdapter(adapter);


        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra(MyR.INDEX_EVENEMENT)) {
            positionEvenement = intent.getExtras().getInt(MyR.INDEX_EVENEMENT);
            event = app.getEvents().get(positionEvenement);
        }

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactSelectionAdapter adp = (ContactSelectionAdapter) adapter;
                List<Contact> cts = adp.getContactsSelected();
                Log.i("Adapter", "contacts recus form adopter " + cts.size());
                event.addParticipant(cts, false);
                setResult(MyR.CONTACT_SELECTION_OK);
                finish();
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MyR.CONTACT_SELECTION_NO);
                finish();
            }
        });
    }
}
