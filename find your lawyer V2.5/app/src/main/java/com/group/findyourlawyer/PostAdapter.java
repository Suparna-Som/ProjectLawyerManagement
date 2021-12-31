package com.group.findyourlawyer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.findyourlawyer.activity.answerActivity;
import com.group.findyourlawyer.models.Post;
import com.group.findyourlawyer.models.userModel;

import java.util.Date;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostAdapter.PastViewHolder>
{
    public static String user_id;
    Context context;

    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull final PastViewHolder holder, int i, @NonNull final Post post) {
        holder.title.setText(post.getTitle());
       // holder.description.setText(post.getDescription());
        holder.author.setText(post.getAuthor());
        holder.time.setText(post.getTime());
        holder.question.setText(post.getQuestion());
        holder.areaOfLaw.setText(post.getAreaOfLaw());
        //user_id=post.getUser_id();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, answerActivity.class);
                intent.putExtra("postId",post.getPostId());
                context.startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        private TextView title,description,author,time,question,areaOfLaw;
        private RelativeLayout relativeLayout;
        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
            //description = itemView.findViewById(R.id.description);
            author = itemView.findViewById(R.id.author);
            relativeLayout = itemView.findViewById(R.id.relPost);
            question = itemView.findViewById(R.id.question);
            areaOfLaw= itemView.findViewById(R.id.areaOfLaw);
        }
    }
}