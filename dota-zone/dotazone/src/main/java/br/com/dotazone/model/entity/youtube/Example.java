package br.com.dotazone.model.entity.youtube;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 27/08/2014.
 */
public class Example {

    @Expose
    public String kind;
    @Expose
    public String etag;
    @Expose
    public String nextPageToken;
    @Expose
    public PageInfo pageInfo;
    @Expose
    public List<Item> items = new ArrayList<Item>();


}
