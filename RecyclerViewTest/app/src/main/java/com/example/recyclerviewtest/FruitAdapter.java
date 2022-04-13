package com.example.recyclerviewtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    static  class ViewHolder extends  RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitView=view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter(List<Fruit> fruitList){
        mFruitList= fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item,viewGroup,false);
//        ViewHolder holder= new ViewHolder(view);
//        return holder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item,viewGroup,false);
        final ViewHolder holder= new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position =holder.getAdapterPosition();
                Fruit fruit =mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked view"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked image"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = mFruitList.get(i);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
