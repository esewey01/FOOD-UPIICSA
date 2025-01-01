package com.example.foodupiicsa.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.FeaturedAdapter;
import com.example.foodupiicsa.activities.adapters.FeaturedVerAdapter;
import com.example.foodupiicsa.activities.models.FeaturedModel;
import com.example.foodupiicsa.activities.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {


    /////////////////////////FEATURED HOR RECYCLERVIEW
    List<FeaturedModel> featuredModelList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;

    /////////////////////////FEATURED VER RECYCLERVIEW
    List<FeaturedVerModel> featuredVerModelList;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;



    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ////////////////////////////FEATURED HOR RECYCLERVIEW
        recyclerView=view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),recyclerView.HORIZONTAL,false));
        featuredModelList=new ArrayList<>();
        featuredModelList.add(new FeaturedModel(R.drawable.fav1,"Featured 1", "Descripción 1"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav2,"Featured 2", "Descripción 1"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav3,"Featured 3", "Descripción 1"));

        featuredAdapter=new FeaturedAdapter(featuredModelList);
        recyclerView.setAdapter(featuredAdapter);

        ////////////////////////////FEATURED VER RECYCLERVIEW
        recyclerView2=view.findViewById(R.id.featured_ves_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),recyclerView.VERTICAL,false));
        featuredVerModelList=new ArrayList<>();

        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1,"Featured 1","Descripcion 1", "4.8","10 a 9"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2,"Featured 2","Descripcion 1", "4.8","10 a 9"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3,"Featured 3","Descripcion 1", "4.8","10 a 9"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1,"Featured 1","Descripcion 1", "4.8","10 a 9"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2,"Featured 2","Descripcion 1", "4.8","10 a 9"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3,"Featured 3","Descripcion 1", "4.8","10 a 9"));
        featuredVerAdapter=new FeaturedVerAdapter(featuredVerModelList);
        recyclerView2.setAdapter(featuredVerAdapter);
        return view;
    }
}