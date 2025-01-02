package com.example.foodupiicsa.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    private List<Order> orders = new ArrayList<>();
    private List<UserOrder> userOrders = new ArrayList<>();

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        UserOrder userOrder = userOrders.get(position);
        holder.usernameTextView.setText(userOrder.getUsername());
        holder.emailTextView.setText("Email: " + userOrder.getEmail());
        holder.boletaTextView.setText("Boleta: " + userOrder.getBoleta());
        // Mostrar las órdenes
        StringBuilder ordersText = new StringBuilder("Pedidos:\n");
        for (Order order : userOrder.getOrders()) {
            ordersText.append("-> ").append(order.getName())
                    .append("       $").append(order.getPrice())
                    .append("\n");
        }
        holder.ordersTextView.setText(ordersText.toString());
    }

    @Override
    public int getItemCount() {
        return userOrders.size();
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
        notifyDataSetChanged();
    }
    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, emailTextView, boletaTextView, ordersTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            boletaTextView = itemView.findViewById(R.id.boletaTextView);
            ordersTextView = itemView.findViewById(R.id.ordersTextView);
        }
    }
}
