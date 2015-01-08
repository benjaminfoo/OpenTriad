package org.owls.opentriad.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.owls.opentriad.R;
import org.owls.opentriad.controller.CardController;
import org.owls.opentriad.modell.Card;
import org.owls.opentriad.ui.fragments.delegates.OnFragmentInteractionListener;

/**
 * @author Benjamin Wulfert (wulfert.benjamin@googlemail.com)
 * @version 0.2a
 *
 */
public class CardManagementFragment extends Fragment {

    private ListView cardListView;
    private ArrayAdapter<Card> adapter;

    private OnFragmentInteractionListener mListener;

    public static CardManagementFragment newInstance() {
        return new CardManagementFragment();
    }

    public CardManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_cardmanager_list, container, false);

        cardListView = (ListView) inflatedView.findViewById(R.id.cardListView);
        adapter = new ArrayAdapter<Card>(getActivity(), R.layout.view_cardlist_entry);
        CardController cardController = new CardController(getActivity());

        for (int i = 0; i < 5; i++) {
            adapter.add(cardController.generateCard(null));
        }

        cardListView.setAdapter(adapter);

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
