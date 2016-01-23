package com.armoz.roadtoalcatraz.train.view.adapter;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.armoz.roadtoalcatraz.R;
import com.armoz.roadtoalcatraz.base.domain.model.PlayerModel;
import com.armoz.roadtoalcatraz.train.view.TrainJobService;
import com.armoz.roadtoalcatraz.train.view.controller.TrainController;

/**
 * Created by ruben.arana on 24/12/15.
 */
public abstract class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private static final String TAG = "TrainAdapter";
    private PlayerModel model;

    Context context;

    TrainController controller;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public TextView tv_skill_name;
        public TextView tv_skill_value;
        public TextView tv_skill_time;
        public Button bt_skill_action;

        public ViewHolder(View v, TextView tv_skill_name, TextView tv_skill_value, TextView tv_skill_time, Button bt_skill_action) {
            super(v);
            this.view = v;
            this.tv_skill_name = tv_skill_name;
            this.tv_skill_value = tv_skill_value;
            this.tv_skill_time = tv_skill_time;
            this.bt_skill_action = bt_skill_action;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrainAdapter(Context context, PlayerModel playerModel, TrainController controller) {
        this.model = playerModel;
        this.context = context;
        this.controller = controller;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_training, parent, false);

        TextView tv_skill_name = (TextView) v.findViewById(R.id.tv_skill_name);
        TextView tv_skill_value = (TextView) v.findViewById(R.id.tv_skill_value);
        TextView tv_skill_time = (TextView) v.findViewById(R.id.tv_skill_time);

        Button bt_skill_action = (Button) v.findViewById(R.id.bt_skill_action);

        ViewHolder vh = new ViewHolder(v, tv_skill_name, tv_skill_value, tv_skill_time, bt_skill_action);

        return vh;
    }

    protected PlayerModel getModel() {
        return model;
    }


    protected long calculateTime(long level) {
        return 10 + level * 2;
    }

    protected View.OnClickListener createOnClickListener(final long time, final String skillName) {

        return new View.OnClickListener() {
            public static final int JOB_ID = 1;
            public static final String TAG = "TrainOnClickListener";

            @Override
            public void onClick(View v) {

                controller.createTraining(skillName, time);

                JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                ComponentName serviceName = new ComponentName(context, TrainJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                        .setMinimumLatency(time * 1000)
                        .setOverrideDeadline((time + 1) * 1000)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                        .setRequiresCharging(false)
                        .setRequiresDeviceIdle(false)
                        .build();

                int result = scheduler.schedule(jobInfo);
                if (result == JobScheduler.RESULT_SUCCESS) {
                    Log.d(TAG, "Job scheduled successfully! It will run in " + time + " seconds");
                }
            }
        };
    }

    protected void fillHolder(ViewHolder holder, String skill, long skillLevel) {
        long time = calculateTime(skillLevel);
        holder.tv_skill_name.setText(skill);
        holder.tv_skill_value.setText(String.valueOf(skillLevel));
        holder.tv_skill_time.setText(String.valueOf(time));
        holder.bt_skill_action.setOnClickListener(createOnClickListener(time, skill));
        putTimer(holder.tv_skill_time, skill);
    }

    protected void disableActionsWhileTraining(ViewHolder holder) {
        if (getModel().getTrainingType() != null && !getModel().getTrainingType().equals("")){
            holder.bt_skill_action.setEnabled(false);
        }
        else{
            holder.bt_skill_action.setEnabled(true);
        }
    }

    protected void putTimer(final TextView tv_skill_time, String skill) {

        if (getModel().getTrainingType() != null && getModel().getTrainingType().equals(skill)) {

            long timeInMillis = getModel().getTrainingFinishingDate().getTime() - System.currentTimeMillis();

            new CountDownTimer(timeInMillis, 1000) {

                public void onTick(long millisUntilFinished) {
                    tv_skill_time.setText(String.valueOf(millisUntilFinished / 1000));
                }

                public void onFinish() {
                    tv_skill_time.setText("DONE");
                }
            }.start();
        }
    }
}
