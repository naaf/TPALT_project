package stl2.upmc.tpalt.evenement;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import stl2.upmc.tpalt.R;
import stl2.upmc.tpalt.core.Contact;
import stl2.upmc.tpalt.core.Evenement;
import stl2.upmc.tpalt.utils.MyR;

/**
 * Created by ashraf on 16/10/2016.
 */

public class SeanceAdapter extends ArrayAdapter<Evenement> {
    private static class ViewHolder {
        public TextView textViewTitre;
        public ImageView deleteBtn;
        public ImageView editBtn;
    }
    public SeanceAdapter(Context context, List<Evenement> objects) {
        super(context, R.layout.list_item,objects);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, null);
            ViewHolder holder = new ViewHolder();
            holder.textViewTitre = (TextView)convertView.findViewById(R.id.titre);
            holder.editBtn = (ImageView)convertView.findViewById(R.id.checkBtn);
            holder.deleteBtn = (ImageView)convertView.findViewById(R.id.deleteBtn);
            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder)convertView.getTag();
        final Evenement item = getItem(position);
        holder.textViewTitre.setText(item.getNom());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Contact c : item.getListParticipant())
                    c.checked = false;
                Intent t = new Intent(v.getContext(), CheckPresenceActivity.class);
                t.putExtra(MyR.INDEX_EVENEMENT, position);
                v.getContext().startActivity(t);
            }
        });

        return convertView;
    }

}
