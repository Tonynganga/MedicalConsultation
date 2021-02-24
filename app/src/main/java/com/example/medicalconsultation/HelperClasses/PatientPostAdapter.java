package com.example.medicalconsultation.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.R;

import java.util.ArrayList;

public class PatientPostAdapter extends RecyclerView.Adapter<PatientPostAdapter.PatientsPostViewHolder> {

    ArrayList<PatientPostHelperClass> patientsPosts;

    public PatientPostAdapter(ArrayList<PatientPostHelperClass> patientsPosts) {
        this.patientsPosts = patientsPosts;
    }

    public static class PatientsPostViewHolder extends RecyclerView.ViewHolder{

        TextView title, desc;
        public PatientsPostViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            title = itemView.findViewById(R.id.postTitle);
            desc = itemView.findViewById(R.id.postDescription);
        }
    }

    @NonNull
    @Override
    public PatientsPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card_design, parent, false);

        PatientsPostViewHolder patientsPostViewHolder = new PatientsPostViewHolder(view);
        return patientsPostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientsPostViewHolder holder, int position) {
        PatientPostHelperClass patientsPostHelperClass = patientsPosts.get(position);
        holder.title.setText(patientsPostHelperClass.getTitle());
        holder.desc.setText(patientsPostHelperClass.getDescription());
    }


    @Override
    public int getItemCount() {
        return patientsPosts.size();
    }


}
