package com.group.findyourlawyer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.group.findyourlawyer.PostAdapter;
import com.group.findyourlawyer.R;
import com.group.findyourlawyer.models.FeedbackModel;

public class FeedbackAdepter extends FirebaseRecyclerAdapter<FeedbackModel, FeedbackAdepter.FeedbackViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeedbackAdepter(@NonNull FirebaseRecyclerOptions<FeedbackModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FeedbackViewHolder holder, int position, @NonNull FeedbackModel model) {
        holder.feedback.setText(model.getFeedback());
        holder.feedbackTime.setText(model.getTime());
        holder.feedbackReply.setText(model.getReply());
        holder.ratingBar.setRating(model.getRating());
        holder.username.setText(model.getUsername());
        holder.downarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.VISIBLE);
                holder.downarrow.setVisibility(View.GONE);
            }
        });
        holder.downarrow.setVisibility(View.VISIBLE);
        holder.uparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linearLayout.setVisibility(View.GONE);
                holder.downarrow.setVisibility(View.VISIBLE);
            }
        });
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_feedbackshow, parent, false);
        return new FeedbackViewHolder(view);
    }

    class FeedbackViewHolder extends RecyclerView.ViewHolder{

        private TextView feedback,feedbackTime,feedbackReply,username;
        private RatingBar ratingBar;
        private ImageView uparrow,downarrow;
        LinearLayout linearLayout;
        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.ral31);
            uparrow=itemView.findViewById(R.id.uparrow1);
            downarrow=itemView.findViewById(R.id.downarrow1);
            feedback= itemView.findViewById(R.id.feedback);
            feedbackTime= itemView.findViewById(R.id.feedbackTime);
            feedbackReply= itemView.findViewById(R.id.feedbackReply);
            ratingBar = itemView.findViewById(R.id.ratingbarmain);
            username = itemView.findViewById(R.id.username1);
        }

    }
}
