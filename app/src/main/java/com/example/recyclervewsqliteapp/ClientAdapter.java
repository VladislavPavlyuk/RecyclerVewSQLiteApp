package com.example.recyclervewsqliteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private final Context context;
    private final ArrayList<Client> clientList;

    public ClientAdapter(Context context, ArrayList<Client> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.client_item, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.tvSurname.setText(client.getSurname());
        holder.tvName.setText(client.getName());
        holder.tvPhone.setText(client.getPhone());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ClientActivity.class);
            intent.putExtra("client", client);
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.deleteClient(client.getId());
            clientList.remove(position);
            notifyItemRemoved(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView tvSurname, tvName, tvPhone;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
