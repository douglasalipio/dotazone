package br.com.dotazone.model.entity;

public class Skill {

    private String dName;
    private String affectes;
    private String desc;
    private String notes;
    private String dmg;
    private String attrib;
    private String cmb;
    private String lore;
    private String hurl;
    private String idString;

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getAffectes() {
        return affectes;
    }

    public void setAffectes(String affectes) {
        this.affectes = affectes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDmg() {
        return dmg;
    }

    public void setDmg(String dmg) {
        this.dmg = dmg;
    }

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }

    public String getCmb() {
        return cmb;
    }

    public void setCmb(String cmb) {
        this.cmb = cmb;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }

    public enum SkillElementy {

        DNAME, AFFECTS, DESC, NOTES, DMG, ATTRIB, CMB, LORE, HURL
    }

}
