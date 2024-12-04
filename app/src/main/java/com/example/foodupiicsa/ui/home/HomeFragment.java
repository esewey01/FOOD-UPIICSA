package com.example.foodupiicsa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.HomeHorAdapter;
import com.example.foodupiicsa.activities.adapters.HomeVerAdapter;
import com.example.foodupiicsa.activities.adapters.UpdateVerticalRec;
import com.example.foodupiicsa.activities.models.HomeHorModel;
import com.example.foodupiicsa.activities.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment implements UpdateVerticalRec {
    // Variables del RecyclerView y adaptador
    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    /////////////////////////////////////VERTICAL
    ArrayList<HomeVerModel> homeVerModeList;
    HomeVerAdapter homeVerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Encuentra el
        homeVerticalRec=root.findViewById(R.id.home_vertical_rec);

        // Encuentra el RecyclerView por su ID en el diseño
        homeHorizontalRec = root.findViewById(R.id.home_horizontal_rec);

        //HORIZONTAL RECYCLERVIEW
        // Inicializa la lista de modelos
        homeHorModelList = new ArrayList<>();

        // Agrega elementos a la lista de modelos (corregidos errores de sintaxis)
        homeHorModelList.add(new HomeHorModel(R.drawable.pizza, "Pizza")); // Eliminado el uso de `name:` innecesario
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburguesa, "Hamburguesa"));
        homeHorModelList.add(new HomeHorModel(R.drawable.tazacafe, "Taza de café"));
        homeHorModelList.add(new HomeHorModel(R.drawable.zalamero, "Zalamero"));
        homeHorModelList.add(new HomeHorModel(R.drawable.helado, "Helado"));

        // Configura el adaptador para el RecyclerView
        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);

        // Configura el LayoutManager para el RecyclerView (en modo horizontal)
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //VERTICAL RECYCLERVIEW
        homeVerModeList=new ArrayList<>();
        /*homeVerModeList.add(new HomeVerModel("ProductName", "#", "##:##", R.drawable.public_ipn));
        homeVerModeList.add(new HomeVerModel("ProductName", "#", "##:##", R.drawable.public_image));
        homeVerModeList.add(new HomeVerModel("ProductName", "#", "##:##", R.drawable.public_image2));
        homeVerModeList.add(new HomeVerModel("ProductName", "#", "##:##", R.drawable.mapaupiicsa));*/

        homeVerAdapter=new HomeVerAdapter(getActivity(),homeVerModeList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        /*
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);*/
        return root;
    }


    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter=new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}
