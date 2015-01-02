package org.owls.opentriad.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.owls.opentriad.R;
import org.owls.opentriad.activities.fragments.MainScreenFragment;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * This Activity is the entry point of the app.
 * It displays and handles the main menu and its buttons.
 */
public class MainActivity extends Activity implements MainScreenFragment.OnFragmentInteractionListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        Button newGame   = (Button) findViewById(R.id.main_menu_new_game);
        Button options   = (Button) findViewById(R.id.main_menu_options);
        Button leaveGame = (Button) findViewById(R.id.main_menu_leave_game);

        newGame.setOnClickListener(this);
        options.setOnClickListener(this);
        leaveGame.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu_new_game:
                FragmentManager fragmentManager = getFragmentManager();
                MainScreenFragment frag = (MainScreenFragment) getFragmentManager().findFragmentByTag(MainScreenFragment.class.getSimpleName());
                if(frag == null){
                    frag = MainScreenFragment.newInstance();
                }
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, frag, MainScreenFragment.class.getSimpleName()).addToBackStack(null);
                fragmentTransaction.commit();
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
