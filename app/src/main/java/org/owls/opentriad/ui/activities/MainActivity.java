package org.owls.opentriad.ui.activities;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import org.owls.opentriad.R;
import org.owls.opentriad.ui.fragments.MatchFragment;
import org.owls.opentriad.ui.fragments.UserProfileFragment;
import org.owls.opentriad.ui.fragments.delegates.OnFragmentInteractionListener;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * This Activity is the entry point of the app.
 * It displays and handles the main menu and its buttons.
 */
public class MainActivity extends Activity implements OnFragmentInteractionListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        Button newGame   = (Button) findViewById(R.id.main_menu_new_game);
        Button options   = (Button) findViewById(R.id.main_menu_options);
        Button profile   = (Button) findViewById(R.id.main_menu_profile);
        Button leaveGame = (Button) findViewById(R.id.main_menu_leave_game);

        newGame.setOnClickListener(this);
        options.setOnClickListener(this);
        profile.setOnClickListener(this);
        leaveGame.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_menu_new_game:
                Fragment frag = (MatchFragment) getFragmentManager().findFragmentByTag(MatchFragment.class.getSimpleName());
                if(frag == null){
                    frag = MatchFragment.newInstance();
                }
                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainscreen_root, frag).addToBackStack(null).commit();
                break;
            case R.id.main_menu_profile:

                frag = (UserProfileFragment) getFragmentManager().findFragmentByTag(UserProfileFragment.class.getSimpleName());
                if (frag == null) {
                    frag = UserProfileFragment.newInstance();
                }

                if(!frag.isAdded()){
                    getFragmentManager()
                            .beginTransaction()
                                .replace(R.id.profile_fragment_placeholder, frag, UserProfileFragment.class.getSimpleName())
                                .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().popBackStack();
                }

                break;
            case R.id.main_menu_options:
                break;
            case R.id.main_menu_leave_game:
                System.exit(10);
                finish();
                break;
        }
    }
}
