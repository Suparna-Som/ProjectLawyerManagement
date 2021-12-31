package com.group.findyourlawyer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.models.AnswerModel;

public class AnswerAdepter extends FirebaseRecyclerAdapter<AnswerModel,AnswerAdepter.Viewholder> {



    public AnswerAdepter(@NonNull FirebaseRecyclerOptions<AnswerModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Viewholder holder, int position, @NonNull AnswerModel model) {
        holder.user.setText(model.getUsername());
        holder.ans.setText(model.getUseranswer());
        holder.accountType.setText(model.getAccountType());
        holder.time.setText(model.getTime());
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answerlayout, parent, false);
        return new AnswerAdepter.Viewholder(view);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView user,ans,accountType,time;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.ansauthor);
            time = itemView.findViewById(R.id.time1);
            ans = itemView.findViewById(R.id.ansofuser);
            accountType = itemView.findViewById(R.id.ansAccountType);
        }
    }
}
