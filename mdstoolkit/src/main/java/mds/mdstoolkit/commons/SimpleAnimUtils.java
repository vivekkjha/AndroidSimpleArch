package mds.mdstoolkit.commons;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by vivekjha on 29/05/15.
 */
public class SimpleAnimUtils {


    public static void translateViewX(final View v, float value, long duration) {
        //> rotate out the view
        v.animate()
                .translationX(value)
                .setDuration(duration)
                .setListener(null)
                .setInterpolator(new DecelerateInterpolator(2));

    }


    public static void scaleUpViewAnimation(View v, long duration)
    {
        v.setVisibility(View.VISIBLE);
        v.setScaleX(0.0f);
        v.setScaleY(0.0f);

        // Start the animation
        v.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator(1))
                .setListener(null);
    }

    public static void scaleDownViewAnimation(final View v, long duration)
    {
        //> fade out the view
        v.animate()
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator(3))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(View.GONE);
                    }
                });
    }

    public static void scaleDownViewAnimation(final View v, long duration, AnimatorListenerAdapter animatorListenerAdapter)
    {
        //> fade out the view
        v.animate()
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator(3))
                .setListener(animatorListenerAdapter);
    }

    public static void fadeInVisibleAnimation(View v, long duration)
    {
        // Prepare the View for the animation
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0.0f);


        // Start the animation
        v.animate()
                .alpha(1.0f)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator(2))
                .setListener(null);
    }


    public static void fadeOutGoneAnimation(final View v, long duration)
    {
        //> fade out the view
        v.animate()
                .alpha(0.0f)
                .setDuration(duration)
                .setInterpolator(new DecelerateInterpolator(2))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(View.GONE);
                    }
                });
    }


    public static void rotateView(final View v, long duration, float rotationValue)
    {
        //> rotate the view
        v.animate()
                .rotation(rotationValue)
                .setDuration(duration)
                .setListener(null)
                .setInterpolator(new DecelerateInterpolator(2));
    }

    public static void removeRotation(final View v, long duration)
    {
        //> rotate out the view
        v.animate()
                .rotation(0f)
                .setDuration(duration)
                .setListener(null)
                .setInterpolator(new DecelerateInterpolator(2));

    }


    public static void fadeOutAnimation(final View v, long duration)
    {
        //> fade out the view
        v.animate()
                .alpha(0.0f)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator(2))
                .setListener(null);
    }

}
