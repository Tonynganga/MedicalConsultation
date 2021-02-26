package com.example.medicalconsultation.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.R;

import java.util.ArrayList;

class ComentsAdapter extends RecyclerView.Adapter<ComentsAdapter.CommentsViewHolder> {
    ArrayList<Comment> mComments;


    public ComentsAdapter() {

    }

    private void populateArrayForPatient() {

    }

    public  class CommentsViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView image;
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            title = itemView.findViewById(R.id.comment);
            image = itemView.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card_design, parent, false);

        CommentsViewHolder viewHolder = new CommentsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment comments = mComments.get(position);
        holder.title.setText(comments.getComment());
        holder.image.setImageResource(comments.getImgUrl());

    }


    @Override
    public int getItemCount() {
        if(mComments!=null)
            return mComments.size();
        else
            return 0;

    }


}
