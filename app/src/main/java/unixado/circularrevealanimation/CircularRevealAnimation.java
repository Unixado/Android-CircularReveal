package unixado.circularrevealanimation;

import android.animation.Animator;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;


public class CircularRevealAnimation {

    private final Activity activity;
    private final ViewGroup rootLayout;
    private final Resources resources;


    public CircularRevealAnimation( Activity activity, ViewGroup rootLayout, Resources resources){
        this.activity = activity;
        this.rootLayout = rootLayout;
        this.resources = resources;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void AnimateOpening ( final int duration, final int startingXCoordinate, final int startingYCoordinate){
        circularRevealActivity(duration, startingXCoordinate, startingYCoordinate);
    }

    public void AnimateClosing(final int duration, final int endingXCoordinate, final int endingYCoordinate) {

        final Activity activity = this.activity;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, endingXCoordinate, endingYCoordinate, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    rootLayout.setVisibility(View.INVISIBLE);
                    activity.finish ();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(duration);
            circularReveal.start();
        }
    }

    public int getDips (float dps) {
        return (int) TypedValue.applyDimension (
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                this.resources.getDisplayMetrics ()
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void circularRevealActivity(int duration, int startingXCoordinate, int startingYCoordinate ) {

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, startingXCoordinate, startingYCoordinate, 0, finalRadius);
        circularReveal.setDuration(duration);

        // make the view visible and start the animation
        rootLayout.setVisibility( View.VISIBLE);
        circularReveal.start();
    }

}
