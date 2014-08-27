package br.com.dotazone.model.entity.youtube;

import com.google.gson.annotations.Expose;

/**
 * Created by Douglas on 27/08/2014.
 */
public class Item {

    @Expose
    public String kind;
    @Expose
    public String etag;
    @Expose
    public Id id;
    @Expose
    public Snippet snippet;


}
