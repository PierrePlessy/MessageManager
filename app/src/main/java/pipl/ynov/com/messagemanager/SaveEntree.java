package pipl.ynov.com.messagemanager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import model.Entree;

/**
 * Created by bardock
 */

public class SaveEntree extends AsyncTask<String, Void, String> {

    private Entree entree;
    private Context context;

    public SaveEntree(Entree entree, Context context) {
        this.entree = entree;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            //Connexion au web service
            URL url = new URL("http://thibault01.com:8081/saveEntree");
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("POST");
            connexion.setRequestProperty("Content-Type", "application/json");
            connexion.setRequestProperty("Accept", "application/json");
            connexion.setDoOutput(true);
            connexion.setDoInput(true);
            connexion.connect();

            //parse des données pour l'envoie
            JSONObject params = new JSONObject();
            try {
                params.put("nom", entree.getNom());
                params.put("espece", entree.getEspece());
                params.put("sexe", entree.getSexe());
                params.put("description", entree.getDescription());
            }catch (JSONException err) {
                Log.e("Error JSON", err.getMessage());
                return "Failed";
            }

            //envoie des données
            DataOutputStream dos = new DataOutputStream(connexion.getOutputStream());
            dos.writeBytes(params.toString());

            dos.flush();
            dos.close();

            Log.i("STATU", String.valueOf(connexion.getResponseCode()));
            connexion.disconnect();
        }
        catch (MalformedURLException err) {
            Log.e("ERROR Malforme URL", err.getMessage());
            return "Failed";
        }
        catch (IOException err) {
            Log.e("ERROR IOException", err.getMessage());
        }
        return "Post resolve";
    }

    @Override
    protected void onPostExecute(String result) {
        //redirection à la page de détails du nouvel élément
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra("id", "666");
        intent.putExtra("nom", entree.getNom());
        intent.putExtra("espece", entree.getEspece());
        intent.putExtra("sexe", entree.getSexe());
        intent.putExtra("description", entree.getDescription());
        context.startActivity(intent);
    }
}
