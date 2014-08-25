package br.com.dotazone.model.entity;

/**
 * Created by Douglas on 24/08/2014.
 */
public class Channel {

    public String videoTitle;
    public Number viewCount;
    public String channelName;
    public String videoDate;

    public Channel(String videoTitle, Number viewCount, String channelName, String videoDate) {
        this.videoTitle = videoTitle;
        this.viewCount = viewCount;
        this.channelName = channelName;
        this.videoDate = videoDate;
    }
}
