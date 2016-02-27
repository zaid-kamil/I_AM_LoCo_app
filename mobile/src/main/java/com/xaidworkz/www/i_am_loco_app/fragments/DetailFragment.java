package com.xaidworkz.www.i_am_loco_app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xaidworkz.www.i_am_loco_app.AppConfig;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.activities.JobMapActivity;
import com.xaidworkz.www.i_am_loco_app.database.JobInfo;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_DEST_LATITUDE;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_DEST_LONGITUDE;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_HAS_OPTIONAL;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_OPT_LATITUDE;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_OPT_LONGITUDE;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_START_LATITUDE;
import static com.xaidworkz.www.i_am_loco_app.activities.MapActivity.EXTRA_MAP_START_LONGITUDE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {


    public static final String JOB_TILE = "jobTile";
    public static final String JOB_IMAGE_URL = "jobImageUrl";
    public static final String JOB_MAP_END_LATITUDE = "jobMapEndLatitude";
    public static final String JOB_MAP_END_LONGITUDE = "jobMapEndLongitude";
    public static final String JOB_MAP_OPT_DESCRIPTION = "jobMapEndDescription";
    public static final String JOB_MAP_OPT_LATITUDE = "jobMapOptLatitude";
    public static final String JOB_MAP_OPT_LONGITUDE = "jobMapOptLongitude";
    public static final String JOB_IMAGE = "jobImage";
    public static final String PROFILE_IMAGE = "profileImage";
    public static final String JOB_DEADLINE = "jobDeadline";
    public static final String JOB_DEADLINE_TIME = "jobDeadlineTime";
    public static final String JOB_DATE = "jobDate";
    public static final String JOB_BUDGET = "jobBudget";
    public static final String JOB_MAP_START_LATITUDE = "jobMapStartLatitude";
    public static final String JOB_MAP_START_LONGITUDE = "jobMapStartLongitude";
    public static final String JOB_MAP_START_DESCRIPTION = "jobMapStartDescription";
    public static final String PROFILE_NAME = "profileName";
    private static JobInfo.DataEntity data;
    private OnFragmentInteractionListener mListener;
    private String jobTile;
    private String jobImageUrl;

    private String jobDate;
    private String jobBudget;

    private String profileName;
    private String jobDate1;
    private String deadlineTime;
    private String deadline;
    private String profileImage;
    private String startDescription;
    private String startLongitude;
    private String startLat;
    private String optLongitude;
    private String optLat;
    private String endDescription;
    private String endLongitude;
    private String endLat;


    public static DetailFragment newInstance(JobInfo.DataEntity data) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        DetailFragment.data = data;
        args.putString(JOB_TILE, data.getJobTitle());
        args.putString(JOB_IMAGE_URL, data.getProfilePic());
        args.putString(JOB_MAP_START_LATITUDE, data.getLatitude1());
        args.putString(JOB_MAP_START_LONGITUDE, data.getLongitude1());
        args.putString(JOB_MAP_END_LONGITUDE, data.getDestLongitude());
        args.putString(JOB_MAP_END_LATITUDE, data.getDestLatitude());
        args.putString(JOB_MAP_OPT_LATITUDE, data.getLatitude2());
        args.putString(JOB_MAP_OPT_LONGITUDE, data.getLatitude2());
        args.putString(JOB_BUDGET, data.getBudget());
        args.putString(PROFILE_IMAGE, data.getProfilePic());
        args.putString(JOB_DATE, data.getJobDate());
        args.putString(JOB_DEADLINE, data.getJobDeadlineDate());
        args.putString(JOB_DEADLINE_TIME, data.getJobDeadlineTime());
        args.putString(JOB_MAP_START_DESCRIPTION, data.getJobDescription1());
        args.putString(JOB_MAP_OPT_DESCRIPTION, data.getJobDescription2());
        args.putString(PROFILE_NAME, data.getName());
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jobTile = getArguments().getString(JOB_TILE, "");
            jobImageUrl = getArguments().getString(JOB_IMAGE_URL, "");
            endLat = getArguments().getString(JOB_MAP_END_LATITUDE, "");
            endLongitude = getArguments().getString(JOB_MAP_END_LONGITUDE, "");
            endDescription = getArguments().getString(JOB_MAP_OPT_DESCRIPTION, "");
            optLat = getArguments().getString(JOB_MAP_OPT_LATITUDE, "");
            optLongitude = getArguments().getString(JOB_MAP_OPT_LONGITUDE, "");
            startLat = getArguments().getString(JOB_MAP_START_LATITUDE, "");
            startLongitude = getArguments().getString(JOB_MAP_START_LONGITUDE, "");
            startDescription = getArguments().getString(JOB_MAP_START_DESCRIPTION, "");
            profileImage = getArguments().getString(PROFILE_IMAGE, "");
            deadline = getArguments().getString(JOB_DEADLINE, "");
            deadlineTime = getArguments().getString(JOB_DEADLINE_TIME, "");
            jobDate1 = getArguments().getString(JOB_DATE, "");
            jobBudget = getArguments().getString(JOB_BUDGET, "");
            profileName = getArguments().getString(PROFILE_NAME, "");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.descrition_view, container, false);
        RelativeLayout layoutInfo = (RelativeLayout) view.findViewById(R.id.layoutInfo);
        CircleImageView userProfilePic = (CircleImageView) view.findViewById(R.id.user_profile_pic);
        TextView userProfileName = (TextView) view.findViewById(R.id.user_profile_name);
        TextView jobPostedPeriod = (TextView) view.findViewById(R.id.job_posted_period);
        TextView jobDescription = (TextView) view.findViewById(R.id.job_description);
        final TextView jobTitle = (TextView) view.findViewById(R.id.job_title);
        ImageButton buttonLikeJob = (ImageButton) view.findViewById(R.id.button_like_job);
        ImageButton buttonViewJob = (ImageButton) view.findViewById(R.id.button_view_job);
        ImageView imageItem = (ImageView) view.findViewById(R.id.imageViewItem);
        /* setup fragment data*/
        Picasso.with(getActivity()).load(AppConfig.WEBSITE_URL + "/" + data.getJobImage1()).placeholder(R.drawable.logo).into(imageItem);
        Picasso.with(userProfilePic.getContext()).load(AppConfig.WEBSITE_URL + "/" + data.getProfilePic()).placeholder(R.drawable.logo_blue).into(userProfilePic);
        userProfileName.setText(data.getName());
        jobPostedPeriod.setText(data.getJobDate());
        jobDescription.setText(data.getJobDescription1());
        jobTitle.setText(data.getJobTitle());
        buttonLikeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "you liked " + data.getJobTitle(), Snackbar.LENGTH_SHORT).show();
            }
        });
        buttonViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Do you want to Bid for " + data.getJobTitle(), Snackbar.LENGTH_INDEFINITE).setAction("view", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextStep(v);
                    }
                }).show();
            }
        });

        return view;
    }

    private void nextStep(View v) {
        Intent intent = new Intent(v.getContext(), JobMapActivity.class);
        intent.putExtra(EXTRA_MAP_START_LATITUDE, Double.parseDouble(startLat));
        intent.putExtra(EXTRA_MAP_START_LONGITUDE, Double.parseDouble(startLongitude));
        intent.putExtra(EXTRA_MAP_DEST_LATITUDE, Double.parseDouble(endLat));
        intent.putExtra(EXTRA_MAP_DEST_LONGITUDE, Double.parseDouble(endLongitude));

        if (!optLat.isEmpty() && !optLongitude.isEmpty()) {
            intent.putExtra(EXTRA_MAP_HAS_OPTIONAL, true);
            intent.putExtra(EXTRA_MAP_OPT_LATITUDE, Double.parseDouble(optLat));
            intent.putExtra(EXTRA_MAP_OPT_LONGITUDE, Double.parseDouble(optLongitude));
        } else {
            intent.putExtra(EXTRA_MAP_HAS_OPTIONAL, false);
        }
        startActivity(intent);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
