package stl2.upmc.tpalt.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.core.Contact;

/**
 * Created by ashraf on 16/10/2016.
 */

public class ContactSelectionAdapter extends ArrayAdapter<Contact> {

    List<Contact> contactsSelected;
    private static class ViewHolder {
        public TextView nom;
        public CheckBox checkBox;
    }

    public ContactSelectionAdapter(Context context, List<Contact> objects) {
        super(context, R.layout.contact_selection, objects);
        contactsSelected = new ArrayList<>();
    }

    public ContactSelectionAdapter(Context context, int source, List<Contact> objects) {
        super(context, source, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.contact_selection, null);
            ViewHolder holder = new ViewHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.select_name);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.select_checbox);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        final Contact item = getItem(position);
        holder.nom.setText(item.getNom() + " " + item.getPrenom());
        holder.checkBox.setChecked(item.checked);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Adapter", "Modify " + position);
                item.checked = holder.checkBox.isChecked();
                if(item.checked){
                    contactsSelected.add(item);
                }else{
                    contactsSelected.remove(item);
                }
                Log.i("Adapter", "contactsSelected " + contactsSelected.size());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public List<Contact> getContactsSelected() {
        Log.i("Adapter", "getContactsSelected " + contactsSelected.size());
        return contactsSelected;
    }
}
