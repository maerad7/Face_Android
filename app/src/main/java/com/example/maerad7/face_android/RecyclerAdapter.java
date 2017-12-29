package com.example.maerad7.face_android;

/**
 * Created by maerad7 on 17. 12. 5.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.support.v4.content.ContextCompat.startActivity;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<HashMap<String,Object>> arrayList;
    public RecyclerAdapter(ArrayList<HashMap<String,Object>> arrayList){
        this.arrayList=new ArrayList<HashMap<String,Object>>();
        this.arrayList=arrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.missing_person_cardview,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        HashMap<String,Object> hashMap= arrayList.get(position);
        holder.MP_Name.setText((String)hashMap.get("MP_Name"));
        holder.Disappearance_Address.setText((String)hashMap.get("Disappearance_Address"));
        holder.hiddenID.setText((String)hashMap.get("MissingPerson_ID"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent= new Intent(v.getContext(),Missing_Person_Information.class);
                Bundle bundle=new Bundle();
                bundle.putString("ID", holder.hiddenID.getText().toString());

                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
        //holder.Image.setImageResource((Integer)hashMap.get("Image"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }public  void addItem(HashMap<String,Object> hashMap){
        this.arrayList.add(hashMap);
    }
    public void addItem(int position, HashMap<String,Object> hashMap){
        this.arrayList.add(hashMap);
        notifyItemInserted(position);
    }public void removeItem(int position){
        this.arrayList.remove(position);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView Image;
        public TextView MP_Name;
        public TextView Disappearance_Address;
        public TextView hiddenID;

        private final Context context;


        public ViewHolder(final View itemView) {
            super(itemView);
            context=itemView.getContext();
            Image = (ImageView)itemView.findViewById(R.id.Image);
            MP_Name = (TextView)itemView.findViewById(R.id.MP_Name);
            Disappearance_Address = (TextView)itemView.findViewById(R.id.Disappearance_Address);
            hiddenID = (TextView)itemView.findViewById(R.id.hiddenID);

        }
    }
}

