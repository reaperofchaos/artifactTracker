package com.jacobconner.artifacttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jacobconner.artifacttracker.domain.Artifact;
import java.util.ArrayList;
import java.util.List;

public class ArtifactAdapter extends RecyclerView.Adapter{
    private ArrayList<Artifact> artifactList;
    private View.OnClickListener mOnItemClickListener;
    private Context parentContext;
    private boolean ableToDelete;


    public boolean canDelete(){return this.ableToDelete;}

    public void setCanDelete(boolean ableToDelete) {
        this.ableToDelete = ableToDelete;
        this.notifyDataSetChanged();
    }

    public class ArtifactViewHolder extends RecyclerView.ViewHolder {

        public TextView lblArtifactType;
        public TextView lblArtifactID;
        public TextView lblX;
        public TextView lbly;
        public TextView lblZ;

        public Button deleteButton;
        public ArtifactViewHolder(@NonNull View itemView) {
            super(itemView);

            lblArtifactID = itemView.findViewById(R.id.lblArtifactId);
            lblArtifactType = itemView.findViewById(R.id.lblArtifactType);
            lblX = itemView.findViewById(R.id.lblX);
            lbly= itemView.findViewById(R.id.lblY);
            lblZ = itemView.findViewById(R.id.lblZ);
            deleteButton = itemView.findViewById(R.id.btnEdit);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getId() {
            return lblArtifactID;
        }
        public TextView getLblArtifactType() {
            return lblArtifactType;
        }
        public TextView getLblX() {
            return lblX;
        }
        public TextView getLbly(){return lbly;}
        public TextView getlblZ(){return lblZ;}
        public Button getDeleteButton() {
            return deleteButton;
        }
    }


    public ArtifactAdapter(ArrayList<Artifact> arrayList, Context context) {
        artifactList = arrayList;
        parentContext = context;

    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artifact_item, parent, false);

        return new ArtifactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ArtifactViewHolder rvh = (ArtifactViewHolder) holder;
        rvh.getId().setText(artifactList.get(position).getId());
        rvh.getLblArtifactType().setText(artifactList.get(position).getArtifactType());
        rvh.getLblX().setText(String.valueOf(artifactList.get(position).getxCoord()));
        rvh.getLbly().setText(String.valueOf(artifactList.get(position).getyCoord()));
        rvh.getlblZ().setText(String.valueOf(artifactList.get(position).getzCoord()));
        if(!canDelete()){
            rvh.getDeleteButton().setVisibility(View.GONE);
            //notifyDataSetChanged();
        }else{
            rvh.getDeleteButton().setVisibility(View.VISIBLE);
        }
        rvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                deleteItem(rvh.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(artifactList != null){
            return artifactList.size();
        }
        return 0;
    }

//    private void deleteItem(int position) {
//        Artifact artifact = artifactList.get(position);
//        ContactDataSource ds = new ContactDataSource(parentContext);
//        try {
//            ds.open();
//            boolean didDelete = ds.deleteContact(contact.getId());
//            ds.close();
//            if (didDelete) {
//                contactList.remove(position);
//                notifyDataSetChanged();
//            }
//            else {
//                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
//            }
//
//        }
//        catch (Exception e) {
//            Toast.makeText(parentContext, "Delete Failed! " + e, Toast.LENGTH_LONG).show();
//
//        }
//    }



}

