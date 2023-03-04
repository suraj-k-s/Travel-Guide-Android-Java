package com.example.travel.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.travel.Login;
import com.example.travel.R;
import com.example.travel.ui.hotel.HotelFragment;
import com.example.travel.ui.cities.CitiesFragment;

public class HomeFragment extends Fragment {

    Button b1;
    Button b2;
    Button b3,b4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        b1 = v.findViewById(R.id.button2);
        b2 = v.findViewById(R.id.button3);
        b3 = v.findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new CitiesFragment());
                fr.commit();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new HotelFragment());
                fr.commit();

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
