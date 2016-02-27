package com.xaidworkz.www.i_am_loco_app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xaidworkz.www.i_am_loco_app.AppConfig;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.database.JobInfo;
import com.xaidworkz.www.i_am_loco_app.helpers.Util;

import java.io.IOException;
import java.util.List;

public class UserJobsActivity extends AppCompatActivity {


    private ListView listJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listJobs = (ListView) findViewById(R.id.listJobs);
        getJobData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getJobData() {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(AppConfig.GET_USERS_JOBS_LIST)
                    .build();

            Call call = client.newCall(request);
            if (Util.Operations.isOnline(getApplicationContext())) {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Toast.makeText(UserJobsActivity.this, "Error fetching job details", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String jsonData = response.body().string();
                        Log.d("ERROR",jsonData);
                       /* Gson gson = new Gson();
                        JobInfo jobInfo = gson.fromJson(jsonData, JobInfo.class);
                        if (response.isSuccessful()) {

                            handleJobList(jobInfo);

                        }*/
                    }
                });

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void handleError(String s) {
        Log.d("ERROR", s);
    }

    private void handleJobList(JobInfo list) {

        List<JobInfo.DataEntity> data = list.getData();
        MyAdapter adapter = new MyAdapter(this, R.layout.simple_job_item_view, data);
        listJobs.setAdapter(adapter);
    }

    private void handleError(IOException e) {
        handleError(e.getMessage());
    }

    public class MyAdapter extends BaseAdapter {

        private final Context context;
        private final int layout;
        private final List<JobInfo.DataEntity> data;
        private final LayoutInflater inflater;

        public MyAdapter(Activity activity, int layout, List<?> data) {
            context = activity.getApplicationContext();
            this.layout = layout;
            this.data = (List<JobInfo.DataEntity>) data;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View cv, ViewGroup parent) {
            ViewHolder holder;
            if (cv == null) {
                cv = inflater.inflate(layout, parent, false);
                holder = new ViewHolder(cv);
                cv.setTag(holder);
            }
            holder = (ViewHolder) cv.getTag();
            holder.textJobName.setText(data.get(position).getJobTitle());
            holder.textJobStatus.setText(data.get(position).getJobStatus());
            holder.textJobDate.setText(data.get(position).getJobDeadlineDate());
            return cv;
        }

        class ViewHolder {
            TextView textJobName;
            TextView textJobDate;
            TextView textJobStatus;

            public ViewHolder(View v) {
                textJobDate = (TextView) v.findViewById(R.id.textJobDate);
                textJobName = (TextView) v.findViewById(R.id.textJobName);
                textJobStatus = (TextView) v.findViewById(R.id.textJobStatus);
            }
        }
    }
}
