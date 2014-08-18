package br.com.dotazone;

import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import nl.matshofman.saxrssreader.RssItem;

public class DotaZoneBrain {

    public static final String TAG = DotaZoneBrain.class.getName();
    public static final String RATING_DOTA_ZONE = "rating_dota_zone";
    public static List<RssItem> rssItems;
    public static List<Item> items = new ArrayList<Item>();
    public static List<Hero> heroes = new ArrayList<Hero>();
    public static Hero hero;
    public static boolean isPremium;
}
