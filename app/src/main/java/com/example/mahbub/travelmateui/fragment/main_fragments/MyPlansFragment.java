package com.example.mahbub.travelmateui.fragment.main_fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.adapter.MyTourListAdapter;
import com.example.mahbub.travelmateui.model.MyTourList;

import java.util.ArrayList;


/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class MyPlansFragment extends RootFragment {
    ArrayList<MyTourList> myTourLists;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_my_plans, null);

        myTourLists = new ArrayList<>();
        myTourLists.add(new MyTourList("Shajek Valley","Khagrachori","25/12/17",R.drawable.sajek));
        myTourLists.add(new MyTourList("Jaflong","Sylhet","05/01/18",R.drawable.jaflong));


        RecyclerView myTourList = mainView.findViewById(R.id.vertical_recycler_view);
        myTourList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        String[] division = {"DHAKA","CHITTGONG","BARISHAL","KHULNA"};
        myTourList.setAdapter(new MyTourListAdapter(getContext(), myTourLists));

        return mainView;
    }
}
