package br.com.dotazone.model.entity;

public class NavDrawerItem {

    private String title;
    // boolean to set visiblity of the counter
    private boolean isCounterVisible = false;

    public NavDrawerItem() {
    }

    public NavDrawerItem(String title) {
        this.title = title;
    }

    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count) {
        this.title = title;
        this.isCounterVisible = isCounterVisible;

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCounterVisibility() {
        return this.isCounterVisible;
    }

    public void setCounterVisibility(boolean isCounterVisible) {
        this.isCounterVisible = isCounterVisible;
    }
}
