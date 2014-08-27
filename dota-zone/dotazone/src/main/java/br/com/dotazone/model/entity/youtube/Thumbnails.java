package br.com.dotazone.model.entity.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Douglas on 27/08/2014.
 */
public class Thumbnails {

    @SerializedName("default")
    @Expose
    public Default _default;
    @Expose
    public Medium medium;
    @Expose
    public High high;


}
