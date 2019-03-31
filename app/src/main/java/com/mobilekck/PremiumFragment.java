package com.mobilekck;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PremiumFragment extends Fragment {

    String IIN, NAME, SURNAME, LASTNAME, ADRESS, FULLNAME, WALLET, BILL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premium, container, false);
        IIN = ((MainActivity) getActivity()).getIIN();
        NAME = ((MainActivity) getActivity()).getNAME();
        SURNAME = ((MainActivity) getActivity()).getSURNAME();
        LASTNAME = ((MainActivity) getActivity()).getLASTNAME();
        ADRESS = ((MainActivity) getActivity()).getADRESS();
        WALLET = ((MainActivity) getActivity()).getWALLET();
        BILL = ((MainActivity) getActivity()).getBILL();
        return view;
    }
}