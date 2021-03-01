package com.example.medicalconsultation.HelperClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.medicalconsultation.FirebaseUtils.mFireStore;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    public ArrayList<Comment> mCommentsPosted;


    public CommentsAdapter(Context context, String problemId) {
        populateComments(problemId);

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
        Comment comments = mCommentsPosted.get(position);
        holder.title.setText(comments.getComment());
//        holder.image.setImageResource(comments.getImgUrl());

    }
    private void populateComments(String problemId) {
        CollectionReference commentRef = mFireStore.collection("problems").document(problemId).collection("comments");

        commentRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("SnapshotListener", error.getMessage());
                    return;
                }
                if(value.getDocumentChanges().size()>0) {
                    mCommentsPosted = new ArrayList<Comment>();
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                Comment comnt = dc.getDocument().toObject(Comment.class);
                                mCommentsPosted.add(comnt);
                                CommentsAdapter.this.notifyDataSetChanged();
                                break;
                            case MODIFIED:
                                break;
                            case REMOVED:
                                break;
                        }
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        if(mCommentsPosted !=null)
            return mCommentsPosted.size();
        else
            return 0;

    }


}
