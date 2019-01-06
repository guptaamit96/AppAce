package inmortal.amit.appace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splesh_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i=new Intent(Splesh_screen.this,BottomNavigation.class);
                startActivity(i);
                finish();
            }
        },5000);

    }
}
