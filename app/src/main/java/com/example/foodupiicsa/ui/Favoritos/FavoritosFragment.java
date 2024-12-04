package com.example.foodupiicsa.ui.Favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.fragments.FragmentAdapter;

public class FavoritosFragment extends Fragment {

    TableLayout tableLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    private FragmentActivity myContext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root=inflater.inflate(R.layout.fragment_favorito,container,false);


        tableLayout=root.findViewById(R.id.tab);

        return root;
    }
}
