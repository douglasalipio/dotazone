package br.com.dotazone.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.listeners.OnFragmentInteractionListener;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.activity.YoutubePlayVideo;
import br.com.dotazone.view.adapter.ChannelItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link br.com.dotazone.model.listeners.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelYoutubeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelYoutubeFragment extends BaseFragment implements Response.Listener<JSONObject>,
        Response.ErrorListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ViewPager mPager;
    private ListView mListViewVideo;
    private Example mVideosList;
    private String mJsonList;
    private ProgressBar mProgressBar;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelYoutubeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelYoutubeFragment newInstance(String param1, String param2) {
        ChannelYoutubeFragment fragment = new ChannelYoutubeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChannelYoutubeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.feed_video_on, container, false);
        initComponents();

        return mView;
    }


    @Override
    public void initComponents() {


        mPager = (ViewPager) mView.findViewById(R.id.tab_channel_pager);
        mListViewVideo = (ListView) mView.findViewById(R.id.feed_video_on_list);
        mListViewVideo.setOnItemClickListener(this);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progress_video);
        //mProgressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(213, 50, 43), PorterDuff.Mode.SRC_IN);
        String url = UrlUtils.getUrlNewVideoYoutube();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsObjRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onErrorResponse(VolleyError volleyError) {

        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        mJsonList = jsonObject.toString();
        mVideosList = new Gson().fromJson(mJsonList, Example.class);
        ChannelItemAdapter adapter = new ChannelItemAdapter(mVideosList);
        mListViewVideo.setAdapter(adapter);
        mProgressBar.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString(YoutubePlayVideo.VIDEO_ID, mVideosList.items.get(position).id.videoId);
        bundle.putString(YoutubePlayVideo.VIDEO_JSON, mJsonList);
        Intent intent = new Intent(ChannelYoutubeFragment.this.getActivity(), YoutubePlayVideo.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
