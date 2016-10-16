package stl2.upmc.tpalt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.core.Seance;
import stl2.upmc.tpalt.core.TypeSeance;


public class ListSeance extends Fragment {
    private Spinner spinner;
    private Button btn;
    private EditText ed;

    private ListView listView;
    private ArrayAdapter<Seance> adapter;
    private List<Seance> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_seance, container, false);

        btn = (Button) v.findViewById(R.id.button);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        ed = (EditText) v.findViewById(R.id.filtre);

        data = new ArrayList<>();
        listView = (ListView) v.findViewById(R.id.listSeance);
        adapter = new SeanceAdapter(this.getContext(), data);
        listView.setAdapter(adapter);

        addItemsOnSpinner(v);
        addItemBtn(v);


        return v;
    }

    private void addItemBtn(View v) {


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TypeSeance t = (TypeSeance) spinner.getSelectedItem();
                String s = ed.getText().toString();
                adapter.add(new Seance(s,t));
            }
        });

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner(View v) {

        List<TypeSeance> list = new ArrayList<>();
        list.add(TypeSeance.COURS);
        list.add(TypeSeance.EVENELENT);
        Log.i("List", list.toString());
        ArrayAdapter<TypeSeance> dataAdapter = new ArrayAdapter<>(v.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

}
