package br.com.dotazone;

import com.prof.rssparser.Article;

import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;

public class DotaZoneBrain {

    public static final String TAG = DotaZoneBrain.class.getName();
    public static final String RATING_DOTA_ZONE = "rating_dota_zone";
    public static List<Article> rssItems;
    public static List<Item> items = new ArrayList<Item>();
    public static List<Hero> heroes = new ArrayList<Hero>();
    public static Hero hero;
    //TODO Issue https://github.com/douglasalipio/dotazone/issues/2
    public static boolean isPremium = true;

}
