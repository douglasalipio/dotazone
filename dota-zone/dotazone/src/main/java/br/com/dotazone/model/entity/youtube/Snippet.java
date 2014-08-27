package br.com.dotazone.model.entity.youtube;

import com.google.gson.annotations.Expose;

/**
 * Created by Douglas on 27/08/2014.
 */
public class Snippet {

    @Expose
    public String publishedAt;
    @Expose
    public String channelId;
    @Expose
    public String title;
    @Expose
    public String description;
    @Expose
    public Thumbnails thumbnails;
    @Expose
    public String channelTitle;
    @Expose
    public String liveBroadcastContent;

}
