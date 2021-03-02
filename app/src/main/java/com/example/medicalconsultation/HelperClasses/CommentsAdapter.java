package com.example.medicalconsultation.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicalconsultation.DoctorsProfile;
import com.example.medicalconsultation.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.medicalconsultation.FirebaseUtils.mFireStore;
import static com.example.medicalconsultation.LogInPage.DOCTOR_DETAILS;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    public static final String SEND_DOCTOR_ID = "Doctor Id";
    public ArrayList<Comment> mCommentsPosted;
    public Context mContext;

    public CommentsAdapter(Context context, String problemId) {
        mContext=context;
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
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comments.getDocId()!=null)
                {
                    Intent intent = new Intent(mContext, DoctorsProfile.class);
                    intent.putExtra(SEND_DOCTOR_ID,comments.getDocId());
                    mContext.startActivity(intent);
                }

            }
        });
        showImage(comments.getImgUri(),holder.image);
//        holder.image.setImageResource(comments.getImgUrl());

    }
    public void showImage(String url,ImageView profileimage){
        if(url!=null&&url.isEmpty()==false){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Glide.with(mContext).load(url).override(width*1/2, width*2/3).
                    centerCrop().into(profileimage);
        }
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
