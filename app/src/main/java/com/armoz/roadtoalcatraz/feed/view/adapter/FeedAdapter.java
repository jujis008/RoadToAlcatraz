package com.armoz.roadtoalcatraz.feed.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.MessageModel;

import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<MessageModel> messages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public View v_title_layout;
        public TextView v_title;

        public View v_body_layout;
        public TextView v_body;
        public Button b_action;

        public ViewHolder(View v, View v_title_layout, TextView v_title, View v_body_layout, TextView v_body, Button b_action) {
            super(v);
            this.view = v;
            this.v_title_layout = v_title_layout;
            this.v_title = v_title;
            this.v_body_layout = v_body_layout;
            this.v_body = v_body;
            this.b_action = b_action;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(List<MessageModel> messages) {
        this.messages = messages;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        // set the view's size, margins, paddings and layout parameters

        View v_title_layout = v.findViewById(R.id.messages_title_layout);
        TextView tv_title = (TextView) v.findViewById(R.id.tv_message_title);

        View v_body_layout = v.findViewById(R.id.messages_body_layout);
        TextView tv_body = (TextView) v.findViewById(R.id.tv_message_body);
        Button b_action = (Button) v.findViewById(R.id.bt_message_action);

        v_body_layout.setVisibility(View.GONE);

        ViewHolder vh = new ViewHolder(v, v_title_layout, tv_title, v_body_layout, tv_body, b_action);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.v_title.setText(messages.get(position).getTitle());
        holder.v_body.setText(messages.get(position).getBody());

        final ViewHolder holderCopy = holder; // make a copy

        holder.v_title_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holderCopy.v_body_layout.getVisibility() == View.VISIBLE) {
                    holderCopy.v_body_layout.animate()
                            .alpha(0)
                            .setDuration(500).
                            setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    holderCopy.v_body_layout.setVisibility(View.GONE);
                                }
                            });
                } else {
                    holderCopy.v_body_layout.setVisibility(View.VISIBLE);
                    holderCopy.v_body_layout.animate()
                            .alpha(1)
                            .setDuration(500).
                            setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                }
                            });
                }


                /*TranslateAnimation anim = null;
                if (holderCopy.v_body_layout.getVisibility() == View.GONE) {

                    anim = new TranslateAnimation(0.0f, 0.0f, -holderCopy.v_body_layout.getHeight(), 0.0f);
                    holderCopy.v_body_layout.setVisibility(View.VISIBLE);
                } else {
                    anim = new TranslateAnimation(0.0f, 0.0f, 0.0f, -v.getHeight());
                    Animation.AnimationListener collapselistener = new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holderCopy.v_body_layout.setVisibility(View.GONE);
                        }
                    };

                    anim.setAnimationListener(collapselistener);
                }

                anim.setDuration(300);
                anim.setInterpolator(new AccelerateInterpolator(0.5f));
                holderCopy.v_body_layout.startAnimation(anim);

*/
                }
            }

            );

        }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return messages.size();
    }


}
