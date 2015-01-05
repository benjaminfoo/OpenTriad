package org.owls.opentriad.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.owls.opentriad.R;
import org.owls.opentriad.controller.GameController;
import org.owls.opentriad.ui.fragments.delegates.OnFragmentInteractionListener;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.1a
 *
 * The MatchFragment contains the main game elements:
 *  - A GameController which handles user-interaction and the game-rules
 *  - The BattleField and the BattleFieldView which displays the cards in battle
 *  - The DeckViews which displays the opponents "hand" (or background if opponent)
 *  - The TextViews for displaying the opponents score
 *  - A TextView for displaying game log messages
 */
public class MatchFragment extends Fragment {

    public GameController gameController;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance() {
        return new MatchFragment();
    }
    public MatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_match_screen, container, false);
        gameController = new GameController(inflatedView);
        return inflatedView;
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



}
