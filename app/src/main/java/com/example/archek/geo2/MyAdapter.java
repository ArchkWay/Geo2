package com.example.archek.geo2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archek on 11.04.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<User> list = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("adapter", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("adapter", "onBindViewHolder " + position);
        User user = list.get(position);
        holder.etName.setText(user.getName());
        holder.etLatitude.setText(String.valueOf(user.getId()));
        holder.etLongitude.setText(String.valueOf(user.getId()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<User> userList) {
        list.addAll(userList);
        notifyDataSetChanged();
    }

    public void add(User user) {
        list.add(user);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText etName;
        private EditText etLatitude;
        private EditText etLongitude;

        public ViewHolder(View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.etName);
            etLatitude = itemView.findViewById(R.id.etLatitude);
            etLongitude = itemView.findViewById(R.id.etLongitude);
        }
    }

}
