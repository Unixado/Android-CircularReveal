package unixado.circularrevealanimation;

import Unixado.circularactivityreveal.CircularRevealAnimation;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SecondActivity extends AppCompatActivity {

    private Bundle savedInstanceState;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        this.savedInstanceState = savedInstanceState;
        setContentView ( R.layout.activity_second );

        final ConstraintLayout rootLayout = findViewById ( R.id.rootLayout );

        final CircularRevealAnimation circularRevealAnimation = new CircularRevealAnimation ( this, rootLayout, getResources () );


        if (savedInstanceState == null) {
            rootLayout.setVisibility( View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        // Define starting coordinates of the animation
                        int x = rootLayout.getWidth () / 2;
                        int y = rootLayout.getHeight () - circularRevealAnimation.getDips ( (float) 130 );

                        circularRevealAnimation.AnimateOpening ( 2000, x, y  );
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver ().addOnGlobalLayoutListener ( this );
                        } else {
                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }

        Button backButton = findViewById ( R.id.playButton2);
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                onBackPressed ();
            }
        } );
    }

    @Override
    public void onBackPressed() {

        final ConstraintLayout rootLayout = findViewById ( R.id.rootLayout );
        CircularRevealAnimation circularRevealAnimation = new CircularRevealAnimation ( this, rootLayout, getResources () );

        int x = rootLayout.getWidth () / 2;
        int y = rootLayout.getHeight () - circularRevealAnimation.getDips ( (float) 130 );

        circularRevealAnimation.AnimateClosing (1000, x, y);
    }
}