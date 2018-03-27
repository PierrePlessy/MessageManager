package pipl.ynov.com.messagemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import model.Entree;

public class AddActivity extends AppCompatActivity {
    TextView nom;
    TextView espece;
    TextView sexe;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nom = findViewById(R.id.editName);
        espece = findViewById(R.id.editSpecies);
        sexe = findViewById(R.id.editSex);
        description = findViewById(R.id.editDescription);

        Button validate = findViewById(R.id.btnValidate);
        Button reset = findViewById(R.id.btnReset);

        validate.setOnClickListener(genericOnClickListener);
        reset.setOnClickListener(genericOnClickListener);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener(){
        public void  onClick(final View v){
            switch(v.getId()){
                case R.id.btnValidate:
                    if(TextUtils.isEmpty(nom.getText())) {
                        nom.setError("Ce champs est requis");
                    }

                    if(TextUtils.isEmpty(espece.getText())) {
                        espece.setError("Ce champs est requis");
                    }

                    if(TextUtils.isEmpty(sexe.getText())) {
                        sexe.setError("Ce champs est requis");
                    }

                    if(!TextUtils.isEmpty(nom.getText()) && !TextUtils.isEmpty(espece.getText()) && !TextUtils.isEmpty(sexe.getText())) {
                        //passe l'entree à sauvegarder pour l'envoyer au web service
                        new SaveEntree(
                                new Entree(
                                        nom.getText().toString(),
                                        espece.getText().toString(),
                                        sexe.getText().toString(),
                                        description.getText().toString()
                                ),
                                AddActivity.this
                        ).execute();
                    }
                    break;
                case R.id.buttonEmpty:
                    nom.setText(null);
                    espece.setText(null);
                    sexe.setText(null);
                    description.setText(null);
                    break;
                default:
                    break;
            }
        }
    };
}
