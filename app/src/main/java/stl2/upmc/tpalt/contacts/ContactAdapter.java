package stl2.upmc.tpalt.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import stl2.upmc.tpalt.MyApplication;
import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.utils.MyR;

/**
 * Created by ashraf on 16/10/2016.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private int source;
    List<Contact> data;
    private MyApplication app;
    private int id;
    private int positionEvenement;

    public void setIdexEvent(int positionEvenement) {
        this.positionEvenement = positionEvenement;
    }

    private static class ViewHolder {
        public TextView textViewTitre;
        public ImageView imageView;
    }

    public ContactAdapter(Context context, List<Contact> objects) {
        super(context, R.layout.list_item, objects);
        data = objects;
        app = (MyApplication) context.getApplicationContext();
        this.id = -1;
        positionEvenement = -1;
        source = -1;
    }

    public ContactAdapter(Context context, List<Contact> objects, int source, int id) {
        super(context, source, objects);
        data = objects;
        app = (MyApplication) context.getApplicationContext();
        this.id = id;
        positionEvenement = -1;
        this.source = source;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(source == -1){
                convertView = inflater.inflate(R.layout.list_item, null);
            }else{
                convertView = inflater.inflate(source, null);
            }
            ViewHolder holder = new ViewHolder();
            holder.textViewTitre = (TextView) convertView.findViewById(R.id.titre);
            holder.imageView = (ImageView) convertView.findViewById(R.id.deleteBtn);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        final Contact item = getItem(position);
        holder.textViewTitre.setText(item.getNom() + " " + item.getPrenom());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
                if (id == MyR.CONTACT_GLOBAL) {
                    app.remove(item);
                } else if (id == MyR.CONTACT_SELECTION) {
                    if (positionEvenement > -1) {
                        app.getEvents().get(positionEvenement).removeParticipant(item);
                    }
                }
                Log.i("Adapter", "Items = " + data.size());
            }
        });

        return convertView;
    }
}
