package com.mobilekck;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BillsFragment extends Fragment {


    String ID, IIN, NAME, SURNAME, LASTNAME, ADRESS, FULLNAME, WALLET, BILL;
    Button paybillsbtn, addmoneybtn;
    TextView pocket, bills;
    Integer pocketnum, billsnum;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills, container, false);


        ID = ((MainActivity) getActivity()).getID();
        IIN = ((MainActivity) getActivity()).getIIN();
        NAME = ((MainActivity) getActivity()).getNAME();
        SURNAME = ((MainActivity) getActivity()).getSURNAME();
        LASTNAME = ((MainActivity) getActivity()).getLASTNAME();
        ADRESS = ((MainActivity) getActivity()).getADRESS();
        WALLET = ((MainActivity) getActivity()).getWALLET();
        BILL = ((MainActivity) getActivity()).getBILL();


        pocket = view.findViewById(R.id.pockettext);
        bills = view.findViewById(R.id.billstext);

        pocket.setText(WALLET + " Тенге");
        bills.setText(BILL + " Тенге");

        pocketnum = Integer.parseInt((pocket.getText().toString()).replaceAll("\\D+", ""));
        billsnum = Integer.parseInt((bills.getText().toString()).replaceAll("\\D+", ""));

        paybillsbtn = view.findViewById(R.id.paybillsbtn);
        paybillsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pocketnum >= billsnum) {
                    paybillsbtn.setEnabled(true);
                    Toast.makeText(getContext(), "Спасибо за отплату!", Toast.LENGTH_SHORT).show();
                    Integer difference = pocketnum - billsnum;
                    WALLET = difference.toString();
                    pocket.setText(WALLET + " Тенге");
                    BILL = "0";
                    bills.setText(BILL + " Тенге");

                    ((MainActivity) getActivity()).UpdateDB(ID, IIN, NAME, SURNAME, LASTNAME, ADRESS, WALLET, BILL);

                } else {
                    paybillsbtn.setEnabled(false);
                    Toast.makeText(getContext(), "Недостаточно средств. Пожалуйста пополните ваш счет!", Toast.LENGTH_SHORT).show();
                }
                Log.d("PayBillsBtn", "Clicked");
            }
        });

        addmoneybtn = view.findViewById(R.id.addmoneybtn);
        addmoneybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paybillsbtn.setEnabled(true);
                pocketnum += 5000;
                WALLET = pocketnum.toString();
                pocket.setText(WALLET + " Тенге");
                Toast.makeText(getContext(), "Вы пополнили счет на 5000 тенге!", Toast.LENGTH_SHORT).show();

                ((MainActivity) getActivity()).UpdateDB(ID, IIN, NAME, SURNAME, LASTNAME, ADRESS, WALLET, BILL);

                Log.d("AddMoneyBtn", "Clicked");
            }
        });

        return view;
    }
}