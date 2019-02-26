package Entity;

import java.util.List;

public class Instance {
    private List<List<Integer>> constraints;
    private List<Augment> augments;
    private List<Integer> dead;
    private List<Integer> mandatory;
    private List<Integer> richseed;

    public List<List<Integer>> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<List<Integer>> constraints) {
        this.constraints = constraints;
    }

    public List<Augment> getAugments() {
        return augments;
    }

    public void setAugments(List<Augment> augments) {
        this.augments = augments;
    }

    public List<Integer> getDead() {
        return dead;
    }

    public void setDead(List<Integer> dead) {
        this.dead = dead;
    }

    public List<Integer> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<Integer> mandatory) {
        this.mandatory = mandatory;
    }

    public List<Integer> getRichseed() {
        return richseed;
    }

    public void setRichseed(List<Integer> richseed) {
        this.richseed = richseed;
    }
}
