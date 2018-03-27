package pipl.ynov.com.messagemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import model.Entree;

public class ListAdaptater extends ArrayAdapter<Entree> {

    public ListAdaptater(Context context, List<Entree> entrees) {
        super(context, 0, entrees);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_entree,parent, false);
        }

        listViewHolder viewHolder = (listViewHolder) convertView.getTag();
//        création de la vue
        if(viewHolder == null){
            viewHolder = new listViewHolder();
            viewHolder.nom = convertView.findViewById(R.id.nom);
            viewHolder.espece = convertView.findViewById(R.id.espece);
            convertView.setTag(viewHolder);
        }

        //récupére l'index  de l'école
        Entree entree = getItem(position);

        //remplisage de la vue
        viewHolder.nom.setText(entree.getNom());
        viewHolder.espece.setText(entree.getEspece());

        return convertView;
    }

    class listViewHolder {
        public TextView nom;
        public TextView espece;
    }
}
