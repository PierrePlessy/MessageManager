package pipl.ynov.com.messagemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //affichage de l'image
        Log.i("IMAGE", "Image dowload : " + getIntent().getStringExtra("id"));
        new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute("http://thibault01.com:8081/images/"+getIntent().getStringExtra("id")+".png");

        TextView nom = findViewById(R.id.view_nom);
        TextView espece = findViewById(R.id.view_espece);
        TextView sexe = findViewById(R.id.view_sexe);
        TextView description = findViewById(R.id.view_description);

        nom.setText(getIntent().getStringExtra("nom"));
        espece.setText(getIntent().getStringExtra("espece"));
        sexe.setText(getIntent().getStringExtra("sexe"));
        description.setText(getIntent().getStringExtra("description"));
    }
}
