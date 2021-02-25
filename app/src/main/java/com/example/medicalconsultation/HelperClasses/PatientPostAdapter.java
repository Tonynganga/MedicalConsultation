package com.example.medicalconsultation.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.CommentSection;
import com.example.medicalconsultation.PostComments;
import com.example.medicalconsultation.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.medicalconsultation.FirebaseUtils.mFireStore;
import static com.example.medicalconsultation.FirebaseUtils.mFirebaseAuth;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class PatientPostAdapter extends RecyclerView.Adapter<PatientPostAdapter.PatientsPostViewHolder> {

    public static final String PROBLEM_DESCRIPTION = "Problem Description";
    ArrayList<PatientPost> mPatientPosts;
    private final Context mContext;
    private final String mUser;


    public PatientPostAdapter(Context patientHomePageContext,String user) {
        mContext =patientHomePageContext;
        mUser=user;
        if(user.equals(USER_PATIENT))
            populateArrayForPatient();
            else
        populateArrayForDoctor();
    }

    private void populateArrayForPatient() {
        CollectionReference PatientPostRef = mFireStore.collection("problems");

        PatientPostRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("SnapshotListener", error.getMessage());
                    return;
                }
                if(value.getDocumentChanges().size()>0) {
                    mPatientPosts=new ArrayList<PatientPost>();
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                String doc_id = dc.getDocument().getId();
                                PatientPost post = dc.getDocument().toObject(PatientPost.class).withId(doc_id);
                                if (post.getUserId().equals(mFirebaseAuth.getUid())) {
                                    mPatientPosts.add(post);
                                    PatientPostAdapter.this.notifyDataSetChanged();
                                }
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

    public  class PatientsPostViewHolder extends RecyclerView.ViewHolder{

        TextView title, desc;
        Button btn;
        public PatientsPostViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            title = itemView.findViewById(R.id.postTitle);
            desc = itemView.findViewById(R.id.postDescription);
            btn=itemView.findViewById(R.id.btView);
        }
    }
    public void populateArrayForDoctor() {
        CollectionReference PatientPostRef = mFireStore.collection("problems");

        PatientPostRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("SnapshotListener", error.getMessage());
                    return;
                }
                if(value.getDocumentChanges().size()>0) {
                    mPatientPosts=new ArrayList<PatientPost>();
                for (DocumentChange dc: value.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            String doc_id = dc.getDocument().getId();
                            PatientPost post = dc.getDocument().toObject(PatientPost.class).withId(doc_id);
                            mPatientPosts.add(post);
                            PatientPostAdapter.this.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                    }
                }}
            }
        });
    }

    @NonNull
    @Override
    public PatientsPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card_design, parent, false);

        PatientsPostViewHolder viewHolder = new PatientsPostViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientsPostViewHolder holder, int position) {
        PatientPost post = mPatientPosts.get(position);
        holder.title.setText(post.getCategory());
        holder.desc.setText(post.getPatientProblem());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser.equals(USER_PATIENT)){
                    Intent intent = new Intent(mContext.getApplicationContext(), CommentSection.class);
                    intent.putExtra(PROBLEM_DESCRIPTION, post.getPatientProblem());
                    mContext.startActivity(intent);

                }else {
                    Intent intent = new Intent(mContext.getApplicationContext(), PostComments.class);
                    intent.putExtra("Problem Description", post.getPatientProblem());
                    mContext.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mPatientPosts!=null)
        return mPatientPosts.size();
        else
            return 0;

    }


}
