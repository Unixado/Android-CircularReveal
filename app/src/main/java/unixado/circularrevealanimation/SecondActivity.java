package unixado.circularrevealanimation;

import android.animation.Animator;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView ( R.layout.activity_second );

        final ConstraintLayout rootLayout = findViewById ( R.id.rootLayout );

        if (savedInstanceState == null) {
            rootLayout.setVisibility( View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
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

    private void circularRevealActivity() {

        ConstraintLayout rootLayout = findViewById ( R.id.rootLayout );
        int cx = rootLayout.getWidth() / 2;

        // 64.5 dps is the dps of the center of the play button from the bottom of the screen
        int cy = rootLayout.getHeight() - getDips( (float) 130 );

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
        circularReveal.setDuration(1600);

        // make the view visible and start the animation
        rootLayout.setVisibility( View.VISIBLE);
        circularReveal.start();
    }

    @Override
    public void onBackPressed() {

        final ConstraintLayout rootLayout = findViewById ( R.id.rootLayout );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = rootLayout.getWidth () / 2;
            int cy = rootLayout.getHeight() - getDips( (float) 130 );
            float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(1000);
            circularReveal.start();
        }else{
            super.onBackPressed();
        }
    }

    private int getDips (float dps) {
        Resources resources = getResources ();
        return (int) TypedValue.applyDimension (
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics ()
        );
    }
}