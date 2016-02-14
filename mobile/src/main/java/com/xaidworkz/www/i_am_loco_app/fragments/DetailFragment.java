package com.xaidworkz.www.i_am_loco_app.fragments;

import android.app.Activity;
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
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.helpers.JobsHolder;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {


    private static JobsHolder data;
    private OnFragmentInteractionListener mListener;
    private String jobTile;
    private String jobImageUrl;
    private long jobMapStartPoint;
    private long jobMapEndPoint;
    private String jobImage;
    private long jobMapInterPoint;
    private String profileImage;
    private String jobDate;
    private float jobBudget;
    private String jobDescription;
    private String profileName;
    private int jobImage1;
    private int profileImage1;


    public static DetailFragment newInstance(JobsHolder data) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        DetailFragment.data = data;
        args.putString("jobTile", data.getTextJobTitle());
        args.putString("jobImageUrl", data.getImageJobUrl());
        args.putLong("jobMapStartPoint", data.getDestinationPoint());
        args.putLong("jobMapEndPoint", data.getStartPoint());
        args.putLong("jobMapInterPoint", data.getOptionalIntermediatePoint());
        args.putString("jobImage", data.getImageJobUrl());
        args.putString("profileImage", data.getTextProfileImageUrl());
        args.putString("jobDate", data.getTextJobDate());
        args.putFloat("jobBudget", data.getTextJobBudget());
        args.putString("jobDescription", data.getTextJobDescription());
        args.putString("profileName", data.getTextProfileName());
        // will be commented
        args.putInt("jobImage", data.getImageJob());
        args.putInt("profileImage", data.getImageProfile());

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            jobTile = getArguments().getString("jobTile", "");
            jobImageUrl = getArguments().getString("jobImageUrl", "");
            jobMapStartPoint = getArguments().getLong("jobMapStartPoint", 0);
            jobMapEndPoint = getArguments().getLong("jobMapEndPoint", 0);
            jobMapInterPoint = getArguments().getLong("jobMapInterPoint", 0);
            jobImage = getArguments().getString("jobImage", "");
            profileImage = getArguments().getString("profileImage", "");
            jobDate = getArguments().getString("jobDate", "");
            jobBudget = getArguments().getFloat("jobBudget", 0.00f);
            jobDescription = getArguments().getString("jobDescription", "");
            profileName = getArguments().getString("profileName", "");
            //will be commented
            jobImage1 = getArguments().getInt("jobImage", 0);
            profileImage1 = getArguments().getInt("profileImage", 0);
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
        Picasso.with(getActivity()).load(data.getImageJob()).placeholder(R.drawable.logo).into(imageItem);
        Picasso.with(userProfilePic.getContext()).load(data.getImageProfile()).placeholder(R.drawable.logo_blue).into(userProfilePic);
        userProfileName.setText(data.getTextProfileName());
        jobPostedPeriod.setText(data.getTextJobDate());
        jobDescription.setText(data.getTextJobDescription());
        jobTitle.setText(data.getTextJobTitle());
        buttonLikeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "you liked " + data.getTextJobTitle(), Snackbar.LENGTH_SHORT).show();
            }
        });
        buttonViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Do you want to Bid for " + data.getTextJobTitle(), Snackbar.LENGTH_INDEFINITE).setAction("confirm", new View.OnClickListener() {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(Uri uri);
    }

}
