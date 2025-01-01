package com.example.foodupiicsa.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec {
    // Variables del RecyclerView y adaptador
    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    /////////////////////////////////////VERTICAL
    ArrayList<HomeVerModel> homeVerModeList;
    HomeVerAdapter homeVerAdapter;

    //FIRABASE
    private FirebaseAuth auth;
    private DatabaseReference database;

    //TEXTVIEW  PARA MOSTRAR EL SALUDO
    private TextView textViewGreeting;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference();
        textViewGreeting=root.findViewById(R.id.holauser);

        loadUserName();

        //CONFIGURACIÓN DEL RECYCLERVIEW VERTICAL
        homeVerticalRec=root.findViewById(R.id.home_vertical_rec);
        //CONFIRGURA EL RECYCLERVIEW HORIZONTAL
        homeHorizontalRec = root.findViewById(R.id.home_horizontal_rec);

        //CONFIGURA LOS DATOS Y ADAPTADORES
        setupHorizontalRecyclerView();
        setupVerticalRecyclerView();

        //FUNCION DE BUSQUEDA
        EditText searchEditText=root.findViewById(R.id.buscador);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                homeVerAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return root;
    }

    private void loadUserName() {
        if (auth.getCurrentUser() == null){
            Toast.makeText(getContext(),"No hay usuario",Toast.LENGTH_SHORT).show();
            return;
        }
        String userId=auth.getCurrentUser().getUid();
        Log.d("HomeFragment","User ID: "+userId);

        database.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String userName = snapshot.child("username").getValue(String.class);
                    if(userName != null&&!userName.isEmpty()){
                        textViewGreeting.setText("Hola " + userName);
                        Log.d("HomeFragment","Nombre de usuario cargado: "+userName);
                    }else{
                        textViewGreeting.setText("Hola Usuario1");
                        Log.d("HomeFragment","No se encontró ningun usuario");
                    }
                }
                else{
                    textViewGreeting.setText("Hola Usuario2");
                    Log.d("HomeFragment","Snapshot no existe");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("HomeFragment", "Error: "+error.getMessage());
            }
        });
    }

    private void setupHorizontalRecyclerView(){
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
    }

    private void setupVerticalRecyclerView(){
        //INICIALIZA LA LISTA DE MODELOS
        homeVerModeList=new ArrayList<>();
        //AGREGA ELEMENTOS A LA LISTA DE MODELOS

        //CONFIGURA EL ADAPTADOR PARA EL RECYCLERVIEW
        homeVerAdapter=new HomeVerAdapter(getActivity(),homeVerModeList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        //CONFIGURA EL LAYOUTMANAGER PARA EL RECYCLERVIEW
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter=new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}
