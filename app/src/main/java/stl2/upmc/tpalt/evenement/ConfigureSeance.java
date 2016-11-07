package stl2.upmc.tpalt.evenement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import stl2.upmc.tpalt.MyApplication;
import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.SelectionContactActivity;
import stl2.upmc.tpalt.contacts.ContactAdapter;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.utils.MyR;

public class ConfigureSeance extends AppCompatActivity {

    private int positionEvenement;
    Button btnValider;
    Button btnCancel;
    ImageView addParticipant;
    TextView date;
    EditText name;
    EditText desc;
    ListView listView;

    private MyApplication app;
    private Evenement event;
    private Set<Contact> participants;
    private ArrayAdapter<Contact> adapter;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_seance);
        app = (MyApplication) this.getApplication();
        btnValider = (Button) findViewById(R.id.config_event_validate);
        btnCancel = (Button) findViewById(R.id.config_event_annuler);
        addParticipant = (ImageView) findViewById(R.id.conf_event_addParticipant);
        name = (EditText) findViewById(R.id.conf_event_name);
        desc = (EditText) findViewById(R.id.conf_event_desc);
        date = (TextView) findViewById(R.id.config_event_date);
        listView = (ListView) findViewById(R.id.config_event_list);
        contacts = new ArrayList<>();
        adapter = new ContactAdapter(this,contacts, R.layout.item_participant, MyR.CONTACT_SELECTION);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra(MyR.INDEX_EVENEMENT)) {
            positionEvenement = intent.getExtras().getInt(MyR.INDEX_EVENEMENT);
            ContactAdapter adp = (ContactAdapter) adapter;
            adp.setIdexEvent(positionEvenement);
            event = app.getEvents().get(positionEvenement);
            name.setText(event.getNom());
            date.setText(event.getDateCreation().toString());
            desc.setText(event.getDescription());
            participants = event.getListParticipant();
            for( Contact c : participants){
                if(!contacts.contains(c))
                    contacts.add(c);
            }
            adapter.notifyDataSetChanged();
        }

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(event != null){
                    event.setNom(name.getText().toString());
                }
                Intent rIntent = new Intent();
                rIntent.putExtra(MyR.INDEX_EVENEMENT, positionEvenement);
                setResult(MyR.CONFIG_RESULT_OK,rIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MyR.CONFIG_RESULT_NO);
                finish();
            }
        });

        addParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for( Contact c : event.getListParticipant()){
                    c.checked = false;
                }
                Intent t = new Intent(view.getContext(), SelectionContactActivity.class);
                t.putExtra(MyR.INDEX_EVENEMENT, positionEvenement);
                startActivityForResult(t, MyR.CONTACT_SELECTION_RESULT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int index;
        Log.v("onActivityResult", " requestCode " + requestCode + ", resultCode " + resultCode);
        Log.v("onActivityResult", " event.getListParticipant() " + event.getListParticipant().size());
        //adapter.addAll(event.getListParticipant());
        participants = event.getListParticipant();
        for( Contact c : participants){
            if(!contacts.contains(c))
                contacts.add(c);
        }
        adapter.notifyDataSetChanged();
    }
}
