package unixado.circularrevealanimation;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView ( R.layout.activity_first );

        // get button from UI
        Button button = findViewById ( R.id.playButton1 );

        // click listener for the button to launch next activity
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                openSecondActivity ();
            }
        } );
    }

    private void openSecondActivity(){
        Intent intent = new Intent (FirstActivity.this, SecondActivity.class);
        startActivity ( intent );

        // Override the default animation to only show the custom anim
        overridePendingTransition(0, 0);
    }
}