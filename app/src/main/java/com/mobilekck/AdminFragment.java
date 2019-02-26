package com.mobilekck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdminFragment extends Fragment {

    final String Log_Tag = "myLogs";
    int[] positionId = {1, 2, 3, 4};
    String[] pozitionName = {"Директор", "Программист", "Бухгалтер", "Охранник"};
    int[] positionSalary = {80000, 60000, 40000, 20000};

    String[] people_name = {"Максим", "Сергей", "Руслан", "Наталья", "Иван", "Мария", "Светлана", "Григорий"};
    int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}