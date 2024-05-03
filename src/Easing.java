public class Easing {
    double value = 0;
    double endValue = 0;
    double duration = 0;
    long startTime = 0;
    boolean isBeingUpdated = false;

    public Easing(double value, double endValue, double duration) {
        this.value = value;
        this.endValue = endValue;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
        isBeingUpdated = true;
    }
    public void Update() {}
    public double GetValue() {
        return this.endValue * this.value;
    }
    public void ChangeValue(double endValue) {
        this.startTime = System.currentTimeMillis();
        this.endValue = endValue;
        isBeingUpdated = true;
    }
}
