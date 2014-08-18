package br.com.dotazone.model.entity;

public class Ability {

    /**
     * Hero's display name *
     */
    private String name;

    /**
     * Hero token *
     */
    private String u;

    /**
     * primery attribute *
     */
    private String pa;

    /**
     * attributs strange | first param to list, b and second g *
     */
    private String[] str;

    /**
     * attributs inteligent | first param to list, b and second g *
     */
    private String[] inte;

    /**
     * attributs agility | first param to list, b and second g *
     */
    private String[] agi;

    /**
     * Move speed. *
     */
    private String ms;

    /**
     * dageme | first param, mim and seconde param, max *
     */
    private String[] dmg;

    /**
     * Starting armour. *
     */
    private String armor;

    /**
     * Localized string for attack type, Melee or Ranged. *
     */
    private String dac;

    /**
     * Display string for roles. *
     */
    private String droles;

    /**
     * id principal node in ability json *
     */
    private String idString;

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public String[] getInte() {
        return inte;
    }

    public void setInte(String[] inte) {
        this.inte = inte;
    }

    public String[] getAgi() {
        return agi;
    }

    public void setAgi(String[] agi) {
        this.agi = agi;
    }

    public String[] getDmg() {
        return dmg;
    }

    public void setDmg(String[] dmg) {
        this.dmg = dmg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getDac() {
        return dac;
    }

    public void setDac(String dac) {
        this.dac = dac;
    }

    public String getDroles() {
        return droles;
    }

    public void setDroles(String droles) {
        this.droles = droles;
    }

    public enum AbilityElementy {

        DNAME("dname"), U("u"), PA("pa"), ATTRIBS("attribs"), STR("str"), B("b"), G("g"), INT("int"), AGI("agi"), MS("ms"), DMG(
                "dmg"), MIN("min"), MAX("max"), ARMOR("armor"), DAC("dac"), DROLES("droles");

        private String value;

        private AbilityElementy(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

    }

}
