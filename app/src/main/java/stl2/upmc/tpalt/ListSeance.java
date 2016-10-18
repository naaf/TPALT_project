package stl2.upmc.tpalt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.core.Evenement;


public class ListSeance extends Fragment {
    private ImageView btn;
    private EditText ed;

    private ListView listView;
    private ArrayAdapter<Evenement> adapter;
    private List<Evenement> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_seance, container, false);

        btn = (ImageView) v.findViewById(R.id.searchBtn);
        ed = (EditText) v.findViewById(R.id.filtre);

        data = new ArrayList<>();
        listView = (ListView) v.findViewById(R.id.listSeance);
        adapter = new SeanceAdapter(this.getContext(), data);
        listView.setAdapter(adapter);
        addItemBtn(v);


        return v;
    }

    private void addItemBtn(View v) {


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ed.getText().toString();
                adapter.add(new Evenement(s));
            }
        });

    }

}
