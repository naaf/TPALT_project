package stl2.upmc.tpalt.contacts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.MyApplication;
import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.contacts.ContactAdapter;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.utils.MyR;


public class ListContacts extends Fragment {

    EditText edNom;
    EditText edPrenom;
    Spinner choixGroupe;

    private ListView listView;
    private ArrayAdapter<Contact> adapter;
    private List<Contact> data;
    private MyApplication app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.list_contact, container, false);
        app = (MyApplication) this.getActivity().getApplication();

        data = app.getContacts();
        listView = (ListView) v.findViewById(R.id.listViewContacts);
        adapter = new ContactAdapter(this.getActivity(), data, R.layout.item_participant, MyR.CONTACT_GLOBAL);
        listView.setAdapter(adapter);

        // ajouter un contacy
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.list_addContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creerDialog();
            }
        });
        return v;
    }


    private void creerDialog() {
        // get prompts.xml view

        LayoutInflater li = LayoutInflater.from(this.getActivity());
        View v = li.inflate(R.layout.activity_creer_contact, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this.getActivity());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(v);

        final EditText edNom = (EditText) v.findViewById(R.id.contactNom);
        final EditText edPrenom = (EditText) v.findViewById(R.id.contactPrenom);
        final Spinner choixGroupe = (Spinner) v.findViewById(R.id.choixGroupe);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String nom = edNom.getText().toString();
                                String prenom = edPrenom.getText().toString();
                                Contact c = new Contact(nom, prenom);
                                if (app.checkAddContact(c))
                                    adapter.add(c);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
