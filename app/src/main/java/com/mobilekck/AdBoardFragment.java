package com.mobilekck;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AdBoardFragment extends Fragment {

    String IIN, NAME, SURNAME, LASTNAME, ADRESS, WALLET, BILL, ADBOARDNEWTEXT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adboard, container, false);
        IIN = ((MainActivity) getActivity()).getIIN();
        NAME = ((MainActivity) getActivity()).getNAME();
        SURNAME = ((MainActivity) getActivity()).getSURNAME();
        LASTNAME = ((MainActivity) getActivity()).getLASTNAME();
        ADRESS = ((MainActivity) getActivity()).getADRESS();
        WALLET = ((MainActivity) getActivity()).getWALLET();
        BILL = ((MainActivity) getActivity()).getBILL();
        ADBOARDNEWTEXT = ((MainActivity) getActivity()).getADBOARDNEWTEXT();

        try {
            Log.e("Text", ADBOARDNEWTEXT);
        } catch (Exception e) {
            Log.e("text", e.toString());
        }

        TextView adboardtext = view.findViewById(R.id.adboardtext);
        adboardtext.setText(ADBOARDNEWTEXT);

        return view;
    }
}