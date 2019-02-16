package com.mobilekck;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton billsbutton = (ImageButton) view.findViewById(R.id.billsbutton);
        ImageButton adboardbutton = (ImageButton) view.findViewById(R.id.adboardbutton);
        ImageButton premiumbutton = (ImageButton) view.findViewById(R.id.premiumbutton);
        ImageButton kckbutton = (ImageButton) view.findViewById(R.id.kckbutton);

        billsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new BillsFragment(); //YourFragment заменить на нужный
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment); //fragment_container заменить на Ваш id контейнера
                transaction.commit();
            }
        });

        adboardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new AdBoardFragment(); //YourFragment заменить на нужный
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment); //fragment_container заменить на Ваш id контейнера
                transaction.commit();
            }
        });

        premiumbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new PremiumFragment(); //YourFragment заменить на нужный
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment); //fragment_container заменить на Ваш id контейнера
                transaction.commit();
            }
        });

        kckbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new KCKFragment(); //YourFragment заменить на нужный
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment); //fragment_container заменить на Ваш id контейнера
                transaction.commit();
            }
        });
        return view;
    }
}