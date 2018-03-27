package pipl.ynov.com.messagemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Entree;

public class ListActivity extends AppCompatActivity {
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        new recoveryDataElt().execute();
    }

    class recoveryDataElt extends AsyncTask<String, String, String> {
        List<Entree> listEntree = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection urlConnection = null;

            String content = "";

            try {
                //récupère les datas
                url = new URL("http://thibault01.com:8081/getEntree");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                StringBuilder sb = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((content = reader.readLine()) != null) {
                    sb.append(content);
                }

                content = sb.toString();
                Log.i("LIST", "Content " + content);
            }
            catch (Exception ex) {
                Log.e("MON ERREUR", "Erreur lors de l'accès au WS", ex);
            }
            Log.i("LIST", "List of all entree" + listEntree);

            publishProgress(content);
            return content;
        }

        protected void onProgressUpdate(String ... values) {
            super.onProgressUpdate(values);

            try {
                //json les datas
                JSONArray ja = new JSONArray(values[0]);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    Entree entree = new Entree();
                    entree.setId(jo.getString("id"));
                    entree.setNom(jo.getString("nom"));
                    entree.setEspece(jo.getString("espece"));
                    entree.setSexe(jo.getString("sexe"));
                    entree.setDescription(jo.getString("description"));
                    listEntree.add(entree);
                }
            }
            catch (Exception ex){
                Log.e("MON ERREUR", "Parse Json", ex);
            }


            Log.i("LIST", "Create list");
            //chercher la listview
            myListView = findViewById(R.id.listView);

            //creation d'un adaptateur
            ListAdaptater adapter = new ListAdaptater(ListActivity.this, listEntree);

            //rajoute l'adaptateur à la vue list
            myListView.setAdapter(adapter);

            //ecoute de l'évènement click sur la listview
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                    Entree entree = (Entree) adapter.getItemAtPosition(position);
                    Intent intent = new Intent(ListActivity.this, ViewActivity.class);
                    intent.putExtra("id", entree.getId());
                    intent.putExtra("nom", entree.getNom());
                    intent.putExtra("espece", entree.getEspece());
                    intent.putExtra("sexe", entree.getSexe());
                    intent.putExtra("description", entree.getDescription());
                    startActivity(intent);
                }
            });
        }
    }
}
