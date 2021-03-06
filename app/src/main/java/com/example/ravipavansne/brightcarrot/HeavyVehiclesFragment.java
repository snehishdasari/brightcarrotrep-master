package com.example.ravipavansne.brightcarrot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeavyVehiclesFragment extends Fragment {


    private List<VehicleDetails> listheavy;
    private RecyclerView recyclerView;
    public HeavyVehiclesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_heavy_vehicles, container, false) ;

         recyclerView = (RecyclerView)  v.findViewById(R.id.recycler_heavy) ;
        recyclerView.setHasFixedSize(true );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        listheavy = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Available Vehicles").child("HeavyVehicle");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listheavy.clear();
                for(DataSnapshot d: dataSnapshot.getChildren()){

                    listheavy.add(d.getValue(VehicleDetails.class));

                }
                VehicleAdapter vehicleAdapter = new VehicleAdapter(getContext(),listheavy) ;

                recyclerView.setAdapter(vehicleAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return v ;
    }

}
