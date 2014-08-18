package br.com.dotazone.model.service;

import java.util.List;

import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;

public interface AdapterAction {


    public void initList();

    public void initListItem(List<Item> items);

    public void initListIHero(List<Hero> heroes);

    public void initRating();
}
