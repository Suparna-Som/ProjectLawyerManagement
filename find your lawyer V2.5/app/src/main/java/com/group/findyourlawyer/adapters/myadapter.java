package com.group.findyourlawyer.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.group.findyourlawyer.MainActivity;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.activity.ConsultActivity;
import com.group.findyourlawyer.models.Model;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<Model,myadapter.myviewholder>
{

    Context context;

    public myadapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
        super(options);
        this.context = context;
    }

    /*public myadapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }*/

    @Override
    public void onBindViewHolder(@NonNull final myviewholder holder, int position, @NonNull final Model model)
    {
       holder.name.setText(model.getName());
       holder.lawPractices.setText(model.getLawPractices());
       holder.email.setText(model.getEmail());
       holder.address.setText(model.getAddress());
       holder.downarrow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               holder.rel3.setVisibility(View.VISIBLE);
               holder.downarrow.setVisibility(View.GONE);
           }
       });
       holder.downarrow.setVisibility(View.VISIBLE);
       holder.uparrow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                holder.rel3.setVisibility(View.GONE);
                holder.downarrow.setVisibility(View.VISIBLE);
           }
       });
       holder.consult.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent intent = new Intent(context, ConsultActivity.class);
                intent.putExtra("key",model.getId());
                context.startActivity(intent);
           }
       });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lawyerprofile,parent,false);
       return new myviewholder(view);
    }


    protected void populateViewHolder(myviewholder myviewholder, Model model, int i) {

    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,email,lawPractices,address;
        RelativeLayout rel1,rel3;
        Button downarrow,consult;
        ImageView uparrow,img1;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);
            rel1=itemView.findViewById(R.id.rel1);
            rel3=itemView.findViewById(R.id.rel3);
            uparrow=itemView.findViewById(R.id.uparrow);
            downarrow=itemView.findViewById(R.id.downarrow);
            consult = itemView.findViewById(R.id.consult);
            name=(TextView)itemView.findViewById(R.id.nametext);
            lawPractices= (TextView)itemView.findViewById(R.id.lawpractice);
            address=(TextView)itemView.findViewById(R.id.addresstxt);
            email=(TextView)itemView.findViewById(R.id.emailtext);
        }
    }
}
