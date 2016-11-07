package stl2.upmc.tpalt.evenement;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import stl2.upmc.tpalt.MyApplication;
import stl2.upmc.tpalt.utils.MyR;
import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.core.Evenement;


public class ListSeance extends Fragment {
    private ImageView btn;
    private EditText ed;

    private ListView listView;
    private ArrayAdapter<Evenement> adapter;
    private List<Evenement> data;
    private MyApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_seance, container, false);
        app = (MyApplication) this.getActivity().getApplication();

        data = app.getEvents();

        btn = (ImageView) v.findViewById(R.id.searchBtn);
        ed = (EditText) v.findViewById(R.id.filtre);

        listView = (ListView) v.findViewById(R.id.listSeance);
        adapter = new SeanceAdapter(this.getActivity(), data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String item = ((Evenement) listView.getItemAtPosition(position)).getNom();
                selectEvent(position);
            }
        });

        addItemBtn(v);

        // ajouter un evenement
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creerDialog();
            }
        });


        return v;
    }

    private void addItemBtn(View v) {


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventSearch = ed.getText().toString();
                Toast.makeText(view.getContext(), "todo filter" + eventSearch, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void creerDialog() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this.getActivity());
        View promptsView = li.inflate(R.layout.dialogue_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this.getActivity());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText newEvent = (EditText) promptsView
                .findViewById(R.id.name);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String eventName = newEvent.getText().toString();
                                Evenement e = new Evenement(eventName);
                                if (app.checkAddEvenement(e))
                                    adapter.add(e);
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

    void selectEvent(int position) {
        Intent t = new Intent(getActivity(), ConfigureSeance.class);
        t.putExtra(MyR.INDEX_EVENEMENT, position);
        startActivityForResult(t, MyR.CONFIG_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int index;
        Log.v("onActivityResult", " requestCode " + requestCode + ", resultCode " + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyR.CONFIG_RESULT) {
            if (resultCode == MyR.CONFIG_RESULT_OK) {
                if (data.getExtras() != null && data.hasExtra(MyR.INDEX_EVENEMENT)) {
                    index = data.getExtras().getInt(MyR.INDEX_EVENEMENT);
                    Log.v("onActivityResult", " index " + index);
                    updateView(index);
                }
            }
        }

    }

    private void updateView(int index) {
        View v = listView.getChildAt(index -
                listView.getFirstVisiblePosition());

        if (v == null)
            return;

        TextView titre = (TextView) v.findViewById(R.id.titre);
        titre.setText(data.get(index).getNom());
    }

}
