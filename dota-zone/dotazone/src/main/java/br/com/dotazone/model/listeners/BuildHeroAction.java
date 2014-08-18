package br.com.dotazone.model.listeners;

import br.com.dotazone.model.entity.Item;

public interface BuildHeroAction {

    public void addingItemSlot(Item item);

    void verifyPayment();
}
