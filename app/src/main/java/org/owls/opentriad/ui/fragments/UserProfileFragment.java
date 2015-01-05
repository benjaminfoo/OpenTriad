package org.owls.opentriad.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.TextView;

import org.owls.opentriad.R;
import org.owls.opentriad.controller.UserProfileController;
import org.owls.opentriad.modell.UserProfile;
import org.owls.opentriad.ui.fragments.delegates.OnFragmentInteractionListener;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.2a
 *
 * The UserProfileFragment allows the player to view his stats or change his name.
 *
 */
public class UserProfileFragment extends Fragment implements TextWatcher {

    private OnFragmentInteractionListener mListener;
    private UserProfileController userProfileController;


    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();

        return fragment;
    }
    public UserProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userProfileController = new UserProfileController(getActivity());

        View profileFragmentLayout = inflater.inflate(R.layout.fragment_profile, container, false);

        EditText username = (EditText) profileFragmentLayout.findViewById(R.id.username);
        TextView wins = (TextView) profileFragmentLayout.findViewById(R.id.wins);
        TextView looses = (TextView) profileFragmentLayout.findViewById(R.id.looses);
        TextView draws = (TextView) profileFragmentLayout.findViewById(R.id.draws);

        UserProfile defaultUserprofile = userProfileController.loadUserprofile();
        username.setText(defaultUserprofile.name);
        wins.setText(String.format("%s: %d", getString(R.string.user_profile_stats_wins), defaultUserprofile.wins));
        draws.setText(String.format("%s: %d", getString(R.string.user_profile_stats_draws), defaultUserprofile.draws));
        looses.setText(String.format("%s: %d", getString(R.string.user_profile_stats_looses), defaultUserprofile.looses));

        username.addTextChangedListener(this);

        return profileFragmentLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String newName = s.toString();
        UserProfile userProfile = userProfileController.loadUserprofile();
        userProfile.name = newName;

        // jeah we accept 0-char names :)
        userProfileController.saveUserProfile(userProfile);
    }
}
