package com.hussain.progosteach.retrofitandrxtestingwithdummyapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hussain Sherwani on 15-May-17
 * hussain.ahmed@progos.org .
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static String TAG = UserAdapter.class.getName();
    private ArrayList<ModelClass> mArrayList;

    public UserAdapter(ArrayList<ModelClass> mArrayList1){
        mArrayList = mArrayList1;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userId.setText(Integer.toString(mArrayList.get(position).getUserId()));
        holder.id.setText(Integer.toString(mArrayList.get(position).getId()));
        holder.title.setText(mArrayList.get(position).getTitle());
        holder.body.setText(mArrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView userId, id, title, body;
        public ViewHolder(View itemView) {
            super(itemView);
            userId = (TextView)itemView.findViewById(R.id.userId);
            id = (TextView)itemView.findViewById(R.id.id);
            title = (TextView)itemView.findViewById(R.id.title);
            body = (TextView)itemView.findViewById(R.id.body);
        }
    }
}
