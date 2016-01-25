package com.armoz.roadtoalcatraz.tournamentDetail.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.GameModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ruben.arana on 24/12/15.
 */
public class TournamentDetailAdapter extends RecyclerView.Adapter<TournamentDetailAdapter.ViewHolder> {

    private List<GameModel> games;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public View game_points_layout;
        public TextView tv_player1_points;
        public TextView tv_player2_points;

        public View game_names_layout;
        public TextView tv_player1_name;
        public TextView tv_player2_name;

        public TextView tv_game_date;



        public ViewHolder(View v, View game_points_layout, TextView tv_player1_points, TextView tv_player2_points, View game_names_layout, TextView tv_player1_name, TextView tv_player2_name, TextView tv_game_date) {
            super(v);
            this.view = v;
            this.game_points_layout = game_points_layout;
            this.tv_player1_points = tv_player1_points;
            this.tv_player2_points = tv_player2_points;
            this.game_names_layout = game_names_layout;
            this.tv_player1_name = tv_player1_name;
            this.tv_player2_name = tv_player2_name;
            this.tv_game_date = tv_game_date;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TournamentDetailAdapter(List<GameModel> games) {
        this.games = games;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TournamentDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);

        View game_points_layout = v.findViewById(R.id.game_points_layout);
        TextView tv_player1_points = (TextView) v.findViewById(R.id.tv_player1_points);
        TextView tv_player2_points = (TextView) v.findViewById(R.id.tv_player2_points);

        View game_names_layout = v.findViewById(R.id.game_names_layout);
        TextView tv_player1_name = (TextView) v.findViewById(R.id.tv_player1_name);
        TextView tv_player2_name = (TextView) v.findViewById(R.id.tv_player2_name);

        TextView tv_game_date = (TextView) v.findViewById(R.id.tv_game_date);

        ViewHolder vh = new ViewHolder(v, game_points_layout, tv_player1_points, tv_player2_points, game_names_layout, tv_player1_name, tv_player2_name, tv_game_date);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.v_title.setText(games.get(position).getTitle());
        //holder.v_body.setText(games.get(position).getBody());

        final ViewHolder holderCopy = holder; // make a copy

        //Player 1 name
        if (games.get(position).getPlayer1() != null){
            holder.tv_player1_name.setText(games.get(position).getPlayer1().getName());
        }
        else {
            holder.tv_player1_name.setText("-");
        }

        //Player 2 name
        if (games.get(position).getPlayer2() != null){
            holder.tv_player2_name.setText(games.get(position).getPlayer2().getName());
        }
        else {
            holder.tv_player2_name.setText("-");
        }

        //Player points
        if (games.get(position).getWinnerId() != 0){
            holder.tv_player1_points.setText(games.get(position).getPlayer1Stats().getPoints());
            holder.tv_player2_points.setText(games.get(position).getPlayer2Stats().getPoints());
        }
        else {
            holder.tv_player1_points.setText("-");
            holder.tv_player2_points.setText("-");
        }

        //Date
        DateFormat df = new SimpleDateFormat("HH:mm");
        holder.tv_game_date.setText(df.format(games.get(position).getDate()));

        holder.game_points_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("TournamentDetailAdapter", "SHOW BOXSCORE");
            }
        });

        holder.tv_player1_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("TournamentDetailAdapter", "SHOW PLAYER 1 DATA");
            }
        });

        holder.tv_player2_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("TournamentDetailAdapter", "SHOW PLAYER 2 DATA");
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return games.size();
    }


}
