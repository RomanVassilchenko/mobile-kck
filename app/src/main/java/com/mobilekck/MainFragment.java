package com.mobilekck;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainFragment extends Fragment {

    String IIN, NAME, SURNAME, LASTNAME, ADRESS;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        IIN = ((MainActivity) getActivity()).getIIN();
        NAME = ((MainActivity) getActivity()).getNAME();
        SURNAME = ((MainActivity) getActivity()).getSURNAME();
        LASTNAME = ((MainActivity) getActivity()).getLASTNAME();
        ADRESS = ((MainActivity) getActivity()).getADRESS();


        TextView adresstext = view.findViewById(R.id.adresstext);
        adresstext.setText(ADRESS);

        ImageButton billsbutton = view.findViewById(R.id.billsbutton);
        ImageButton adboardbutton = view.findViewById(R.id.adboardbutton);
        ImageButton premiumbutton = view.findViewById(R.id.premiumbutton);
        ImageButton kckbutton = view.findViewById(R.id.kckbutton);

        billsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new BillsFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        adboardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new AdBoardFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        premiumbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new PremiumFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        kckbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new KCKFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });
        return view;
    }
}