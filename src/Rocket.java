public class Rocket extends SpaceShip {
    private int cost;
    private int weight;
    private int currentWeight;
    private int maxWeight;
    double chanceOfLaunchFailure;
    double chanceOfLandingFailure;

    @Override
    public boolean launch(){
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }
    @Override
    public boolean canCarry(Item item) {
        return getCurrentWeight() + item.getWeight() <= getMaxWeight();
    }

    @Override
    public void carry(Item item) {
        currentWeight += item.getWeight();
        setCurrentWeight(currentWeight);
    }
    int getCost() {
        return cost;
    }
    void setCost(int cost) {
        this.cost = cost;
    }
    int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }
    int getCurrentWeight() {
        return currentWeight;
    }
    void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
    int getMaxWeight() {
        return maxWeight;
    }

    void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }


}
