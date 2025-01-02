package com.example.foodupiicsa.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.Order;
import com.example.foodupiicsa.activities.adapters.OrdersAdapter;
import com.example.foodupiicsa.activities.adapters.UserOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminOrdersActivity extends AppCompatActivity {

    static final String TAG = "FIREBASEDEBUG";

    private RecyclerView orderRecyclerView;
    private OrdersAdapter ordersAdapter;
    private DatabaseReference ordersDatabase;
    private DatabaseReference usersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        Log.d(TAG,"Iniciando AdminOrdersActivity");

        //RECYCLERVIEW
        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersAdapter = new OrdersAdapter();
        orderRecyclerView.setAdapter(ordersAdapter);

        //OBTENER REFERENCIAS A FIREBASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d(TAG,"Iniciando referencias de Firebase");
        ordersDatabase = database.getReference("Ordenes");
        usersDatabase = database.getReference("users");

        loadOrders();
    }

    private void loadOrders(){
        Log.d(TAG,"Iniciando carga de órdenes");
        usersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {
                List<UserOrder> userOrders = new ArrayList<>();
                Log.d(TAG, "Número total de usuarios: " + usersSnapshot.getChildrenCount());

                for(DataSnapshot userSnapshot:usersSnapshot.getChildren()){
                    String userId=userSnapshot.getKey();
                    String username=userSnapshot.child("username").getValue(String.class);
                    String email=userSnapshot.child("email").getValue(String.class);
                    String boleta=userSnapshot.child("boleta").getValue(String.class);
                    if (username != null && email != null && boleta != null) {
                        Log.d(TAG,"PEDIDO AGREGADO: "+username+" con id:"+userId);
                        loadUserOrders(userId,username,email,boleta,userOrders);
                    }else{
                        Log.d(TAG, "DATOS DE PEDIDOS INCOMPLETOS"+userId);
                    }
                }
        }
        @Override
            public  void onCancelled(DatabaseError error){
                Log.e(TAG,"Error al cargar los pedidos"+ error.getMessage());
                Toast.makeText(AdminOrdersActivity.this,"Error al cargar los pedidos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserOrders(
            String userId,
            String username,
            String email,
            String boleta,
            List<UserOrder> userOrders
    )
    {
        Log.d(TAG,"Cargando órdenes del usuario: "+username);
        ordersDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ordersSnapshot) {
                List<Order> orders = new ArrayList<>();
                Log.d(TAG,"Número total de pedidos: "+ordersSnapshot.getChildrenCount());

                for (DataSnapshot orderGroupSnapshot : ordersSnapshot.getChildren()) {
                    Log.d(TAG, "Procesando grupo de orden: " + orderGroupSnapshot.getKey());
                    DataSnapshot orderSnapshot = orderGroupSnapshot.child("0");
                    if(orderSnapshot.exists()){
                        String name = orderSnapshot.child("name").getValue(String.class);
                        String price = orderSnapshot.child("price").getValue(String.class);

                        Log.e(TAG,"ORDEN ENCONTRADA: "+name);

                        if (name != null && price  != null) {
                            orders.add(new Order(name,price));
                            Log.d(TAG,"PEDIDO AGREGADO: "+name);
                        }else{
                            Log.d(TAG,"DATOS DE PEDIDO INCOMPLETOS"+orderSnapshot.getKey());
                        }

                    }
                }
                if(!orders.isEmpty()){
                    userOrders.add(new UserOrder(username,email,boleta,orders));
                    ordersAdapter.setUserOrders(userOrders);
                    Log.d(TAG, "PEDIDOS CARGADOS para " + username + ": " + orders.size() + " pedidos");
                } else {
                    Log.d(TAG, "No se encontraron pedidos para: " + username);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG,"Error al cargar los pedidos"+ error.getMessage());
                Toast.makeText(AdminOrdersActivity.this,"Error al cargar los pedidos",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


