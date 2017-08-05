package com.assignment9.manojkulkarni.firebasefinal;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorDetailFragment extends android.support.v4.app.Fragment{

    Button maps1;

    static HashMap<String, ?> m;
    TextView firstNameDetail,lastNameDetail,contactDetail,addressDetail,bloodGroupDetail,firstNameDetailTxt,lastNameDetailTxt,contactDetailTxt,addressDetailTxt,bloodGroupDetailTxt;
    ImageView imageViewDetail;
    String locationDetail;
    ShareActionProvider shareActionProvider;
    public DonorDetailFragment() {
        // Required empty public constructor
        m=new HashMap();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_donor_detail, container, false);
        setHasOptionsMenu(true);
        if(getArguments()!=null)
        {

            m=(HashMap) getArguments().getSerializable("donor");

        }


        locationDetail=m.get("address").toString();

        maps1=(Button)rootView.findViewById(R.id.mapview1);
        firstNameDetail=(TextView) rootView.findViewById(R.id.dfname);
        lastNameDetail=(TextView) rootView.findViewById(R.id.dlname);
        contactDetail=(TextView)rootView.findViewById(R.id.dcnt);
        addressDetail=(TextView)rootView.findViewById(R.id.daddr);
        bloodGroupDetail=(TextView)rootView.findViewById(R.id.dbgrp);


        firstNameDetailTxt=(TextView) rootView.findViewById(R.id.dfnametxt);
        lastNameDetailTxt=(TextView) rootView.findViewById(R.id.dlnametxt);
        contactDetailTxt=(TextView)rootView.findViewById(R.id.dcnttxt);
        addressDetailTxt=(TextView)rootView.findViewById(R.id.daddrtxt);
        bloodGroupDetailTxt=(TextView)rootView.findViewById(R.id.dbgrptxt);


        maps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMaps();
            }
        });


        firstNameDetail.setText((String) m.get("firstname"));
        lastNameDetail.setText((String) m.get("lastname"));
        contactDetail.setText((String) m.get("contact"));
        addressDetail.setText((String) m.get("address"));
        bloodGroupDetail.setText((String) m.get("bloodgroup"));

        return rootView;




    }

    public void getMaps()
    {
        Log.d("something", "getMaps: ");
        Intent maps=new Intent(getActivity(),MapsActivity.class);
        maps.putExtra("address",locationDetail);
        startActivity(maps);

    }


    public static DonorDetailFragment newInstance(HashMap<String, ?> donorMap)
    {
        DonorDetailFragment f7=new DonorDetailFragment();

        m=donorMap;
        Bundle args=new Bundle();
        args.putSerializable("donor",donorMap);
        f7.setArguments(args);
        return f7;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        if(menu.findItem(R.id.share)==null)
        {
            inflater.inflate(R.menu.fragment_menu,menu);}

        MenuItem shareItem=menu.findItem(R.id.share);

        shareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        Intent intentShare=new Intent(Intent.ACTION_SEND);

        m=(HashMap) getArguments().getSerializable("donor");
        intentShare.setType("text/plain");

        intentShare.putExtra(Intent.EXTRA_TEXT, (String) m.get("firstname"));
        shareActionProvider.setShareIntent(intentShare);

        super.onCreateOptionsMenu(menu,inflater);




    }



}
