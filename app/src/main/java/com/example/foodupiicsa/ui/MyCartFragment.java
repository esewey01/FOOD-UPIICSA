package com.example.foodupiicsa.ui;

import static java.util.Currency.getInstance;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.CartAdapter;
import com.example.foodupiicsa.activities.models.CarritoFunciones;
import com.example.foodupiicsa.activities.models.CartModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    //INICIALIZAR FIREBASE
    DatabaseReference databaseReference;

    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    TextView totalPrecioTextView;
    public MyCartFragment(){
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        Log.d("MyCartFragment", "Fragment creado. Obteniendo elementos del carrito.");
        View view= inflater.inflate(R.layout.fragment_my_cart,container,false);

        databaseReference= FirebaseDatabase.getInstance().getReference("Ordenes");

        //INICIALIZAR EL RECYCLER VIEW
        recyclerView=view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //INICIALIZAR EL TEXTVIEW DE PRECIO TOTAL
        totalPrecioTextView = view.findViewById(R.id.totalprecio);


        // OBTENER LOS ELEENTOS DEL CARRRITO
        List<CartModel> cartItems = CarritoFunciones.getInstance().getCartItems();
        Log.d("MyCartFragment", "Items en el carrito: " + cartItems.size());

        //CONFIGURAR EL ADAPTER
        cartAdapter=new CartAdapter(cartItems);
        recyclerView.setAdapter(cartAdapter);


        //CONFIGURAR DESLIZAR PARA ELIMINAR
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                cartItems.remove(position);
                cartAdapter.notifyItemRemoved(position);
                CarritoFunciones.getInstance().clearCart(); // Actualizar el carrito global
                actualizarPrecioTotal(cartItems);
                Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);


        //FUNCION BOTON ORDENAR

        Button buttonOrd = view.findViewById(R.id.buttonOrdenar);

        buttonOrd.setOnClickListener(v->{
            if (cartItems.isEmpty()){
                for (CartModel item : cartItems) {
                    Log.d("MyCartFragment", "Producto: " + item.getName() + ", Precio: $" + item.getPrice());
                }
                Toast.makeText(getContext(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
                enviarPedido(cartItems);
            }
            else{
                Toast.makeText(getContext(), "No hay productos en el carrito", Toast.LENGTH_SHORT).show();
            }
        });

        //ACTUALIZAR EL PRECIO TOTAL
        actualizarPrecioTotal(cartItems);
        return view;
    }

    private void enviarPedido(List<CartModel> cartItems) {
        String orderId=databaseReference.push().getKey();

        if (orderId!=null){
            databaseReference.child(orderId).setValue(cartItems)
                    .addOnCompleteListener(task-> {
                        if (task.isSuccessful()) {
                            Log.d("MyCartFragment", "Pedido enviado exitosamente.");
                        } else {
                            Log.e("MyCartFragment", "Error al enviar el pedido: " + task.getException());
                        }
                    });
        }
    }

    private void actualizarPrecioTotal(List<CartModel> cartItems) {
        //ACTUALIZAR EL PRECIO TOTAL
        double totalPrecio=0.0;
        //CALCULAR EL PRECIO TOTAL
        for (CartModel item:cartItems){
            try{
                totalPrecio+=Double.parseDouble(item.getPrice());
            }
            catch (NumberFormatException e){
                Log.e("MyCartFragment", "Error al convertir el precio a double: " + e);
            }
        }
        //ACTUALIZAR EL TEXTVIEW DEL PRECIO TOTAL
        totalPrecioTextView.setText(String.format("$%s", totalPrecio));
        Log.d("MyCartFragment", "Precio total actualizado: " + totalPrecio);
    }
}
