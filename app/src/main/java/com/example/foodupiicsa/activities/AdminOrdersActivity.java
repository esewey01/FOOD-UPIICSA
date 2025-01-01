package com.example.foodupiicsa.activities;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.Order;
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




    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersAdapter = new OrdersAdapter();
        orderRecyclerView.setAdapter(ordersAdapter);

        ordersDatabase = FirebaseDatabase.getInstance().getReference("orders");

        //REFERENCIA A FIREBASE
        ordersDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<UserOrder> userOrders = new ArrayList<>();
                Log.d(TAG, "DATOS CARGADOS DESDE FIREABSE" + snapshot.getKey());

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    Log.d(TAG, "Usuario: " + userSnapshot.getKey());

                    String username = userSnapshot.child("username").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);
                    String boleta = userSnapshot.child("boleta").getValue(String.class);

                    if (username == null || email == null || boleta == null) {
                        Log.e(TAG, "Valores nulos encontrados en los datos del usuario");
                        Log.w(TAG, "Datos de usuario incompletos: username=" + username + ", email=" + email + ", boleta=" + boleta);
                        continue;
                    }

                    Log.d(TAG, "Usuario encontrado: username=" + username + ", email=" + email + ", boleta=" + boleta);

                    List<Order> orders = new ArrayList<>();

                    for (DataSnapshot orderSnapshot : userSnapshot.getChildren()) {
                        Log.d(TAG, "Procesando nodo de pedido: " + orderSnapshot.getKey());
                        // Asegúrate de verificar nodos válidos
                        if (orderSnapshot.hasChild("price") && orderSnapshot.hasChild("rating")) {
                            String name = orderSnapshot.getKey(); // ID o nombre del pedido
                            String price = orderSnapshot.child("price").getValue(String.class);
                            String rating = orderSnapshot.child("rating").getValue(String.class);
                            orders.add(new Order(name, price, rating));

                            if (price != null && rating != null) {
                                orders.add(new Order(name, price, rating));
                                Log.d(TAG, "Pedido agregado: name=" + name + ", price=" + price + ", rating=" + rating);
                            } else {
                                Log.w(TAG, "Pedido con datos incompletos: name=" + name + ", price=" + price + ", rating=" + rating);
                            }
                        } else {
                            Log.w(TAG, "Nodo de pedido no válido: " + orderSnapshot.getKey());
                        }


                        // Agregar usuario con pedidos a la lista
                        userOrders.add(new UserOrder(username, email, boleta, orders));
                        Log.d(TAG, "Usuario con pedidos agregado a la lista: " + username);
                    }
                    // Actualizar el adaptador con la lista procesada
                    ordersAdapter.setUserOrders(userOrders);
                    Log.d(TAG, "Adaptador actualizado con " + userOrders.size() + " usuarios.");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Error al cargar los pedidos: " + error.getMessage(), error.toException());
                Toast.makeText(AdminOrdersActivity.this, "Error al cargar los pedidos", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
