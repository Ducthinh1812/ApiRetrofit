package com.example.apiretrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCat extends RecyclerView.Adapter<AdapterCat.modelHolder>{

    Context context;
    List<Cat> ArrayLists;
    public AdapterCat(Context context, List<Cat> ArrayLists) {
        this.context = context;
        this.ArrayLists = ArrayLists;
    }

    @NonNull
    @Override
    public AdapterCat.modelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item , parent, false);
        return new AdapterCat.modelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCat.modelHolder holder, @SuppressLint("RecyclerView") int position) {
        Cat timKiem = ArrayLists.get(position);
        Picasso.get().load(timKiem.getUrl())
                .into(holder.imgSP);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("id",timKiem.getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ArrayLists.size();

    }

    public class modelHolder extends RecyclerView.ViewHolder {
        ImageView imgSP;
        LinearLayout linearLayout;
        public modelHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout= itemView.findViewById(R.id.lead);
            imgSP = (ImageView) itemView.findViewById(R.id.imageView2);

        }
    }
}
