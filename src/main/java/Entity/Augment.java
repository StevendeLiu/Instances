package Entity;

public class Augment {
    int featureIndex;
    int cost;
    boolean usedBefore;
    int defects;

    public int getFeatureIndex() {
        return featureIndex;
    }

    public void setFeatureIndex(int featureIndex) {
        this.featureIndex = featureIndex;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isUsedBefore() {
        return usedBefore;
    }

    public void setUsedBefore(boolean usedBefore) {
        this.usedBefore = usedBefore;
    }

    public int getDefects() {
        return defects;
    }

    public void setDefects(int defects) {
        this.defects = defects;
    }
}
