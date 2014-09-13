package br.com.dotazone.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.alipio on 9/12/2014.
 */
public class VideoOffline {


    @SerializedName("channels")
    public List<Channel> channels = new ArrayList<Channel>();

    public class Channel {

        @SerializedName("channel_name")
        public String mChannel;

        @SerializedName("championship")
        public List<Championship> championship = new ArrayList<Championship>();

    }

    public class Championship {

        @SerializedName("championship_name")
        public String mChampionship;

        @SerializedName("championship_image")
        public String mChampionShipImg;

        @SerializedName("videos")
        public List<Video> mVideos = new ArrayList<Video>();

    }

    public class Video {

        @SerializedName("url_video")
        public String mUrlVideo;
        @SerializedName("date_video")
        public String mDateVideo;
        @SerializedName("title_video")
        public String mTitleVideo;
    }
}
