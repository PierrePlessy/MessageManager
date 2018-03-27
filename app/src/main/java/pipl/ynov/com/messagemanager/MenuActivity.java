package pipl.ynov.com.messagemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button goTOList = findViewById(R.id.btn_to_list);
        Button createElt = findViewById(R.id.btn_create);

        goTOList.setOnClickListener(genericOnClickListener);
        createElt.setOnClickListener(genericOnClickListener);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener(){
        public void  onClick(final View v){
            switch(v.getId()){
                case R.id.btn_to_list:
                    Intent intent_list = new Intent(MenuActivity.this, ListActivity.class);
                    startActivity(intent_list);
                    break;
                case R.id.btn_create:
                    Intent intent_create = new Intent(MenuActivity.this, AddActivity.class);
                    startActivity(intent_create);
                    break;
                default:
                    break;
            }
        }
    };
}
