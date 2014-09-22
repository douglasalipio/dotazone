package br.com.dotazone.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;

import br.com.dotazone.R;

import br.com.dotazone.model.entity.VideoOffline;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.listeners.OnFragmentInteractionListener;
import br.com.dotazone.model.service.DownloadVideo;
import br.com.dotazone.model.util.StringUtil;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.activity.OfflinePlayVideo;
import br.com.dotazone.view.adapter.ChannelItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link br.com.dotazone.model.listeners.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelOfflineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelOfflineFragment extends BaseFragment implements Response.Listener<JSONObject>,
        Response.ErrorListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Example mVideosList;
    private OnFragmentInteractionListener mListener;
    private View mView;
    private ListView mListViewVideo;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelOfflineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelOfflineFragment newInstance(String param1, String param2) {
        ChannelOfflineFragment fragment = new ChannelOfflineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChannelOfflineFragment() {
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
        mView = inflater.inflate(R.layout.feed_video_off, container, false);
        initComponents();
        return mView;
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
    public void initComponents() {

        String url = UrlUtils.URL_VIDEOS_JSON;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsObjRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        mListViewVideo = (ListView) mView.findViewById(R.id.feed_video_list_off);
        mListViewVideo.setOnItemClickListener(this);
        queue.add(jsObjRequest);

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

        System.out.println();
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        VideoOffline videosOffline = new Gson().fromJson(jsonObject.toString(), VideoOffline.class);
        ChannelItemAdapter adapter = new ChannelItemAdapter(videosOffline);
        mListViewVideo.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        VideoOffline.Video video = (VideoOffline.Video) parent.getItemAtPosition(position);
        String fileName = video.mTitleVideo.replaceAll("\\s", "_");
        if (StringUtil.verifyIfExistVideo(getActivity(), fileName, "mp4")) {
            Intent intent = new Intent(getActivity(), OfflinePlayVideo.class);
            intent.putExtra(OfflinePlayVideo.PATH_VIDEO, Environment.getExternalStorageDirectory().getAbsolutePath() + "/dotazone/video/" + fileName + "." + "mp4");
            startActivity(intent);
        } else {
            new DownloadVideo(getActivity().getApplicationContext()).init(video.mTitleVideo, fileName, "mp4", video.mUrlVideo);
        }
    }


}
