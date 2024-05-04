/*
    Easing Package
    Made by Mason Huber
    Formula's Made by Robert Penner
*/

/**
 * Smoothly ease a value.
 * @param easeType The type of easing to be used.
 * @param easeDirection The direction of the easeType.
 * @param duration The duration of the ease.
 */
public class Easing {
    private EasingType easeType;
    private EasingDirection easeDirection;
    private double duration;

    private long startTime;
    private double startValue;
    private double endValue;

    private double percentageValue;
    private double realValue;

    private boolean isBeingUpdated;
    int normal;

    public Easing(EasingType easeType, EasingDirection easeDirection, double duration) {
        this.easeType = easeType;
        this.easeDirection = easeDirection;
        this.duration = duration;
        this.percentageValue = 0;
        isBeingUpdated = true;
    }

    /**
     * Updates the current value based on current time since value has changed.
     */
    public void Update() {
        if (isBeingUpdated) {
            double x = (System.currentTimeMillis() - this.startTime) / (duration*1000);
            if (x > 1) {
                // FINISHED
                isBeingUpdated = false;
                this.realValue = this.endValue;
                this.percentageValue = 1;
            } else {
                double lastValue = this.percentageValue;

                double value = easeType.Ease(x, easeDirection);
                //System.out.printf("Value: %s%n", value);
                //int newNormal = (normal < 0) ? 1 : -1;
                double valueChange = value - lastValue;
                //System.out.printf("%n Normal: %s %n", this.normal);

                //if (normal < 0) {
                //    // Going down
                //    valueChange = Math.abs(value - lastValue) * -normal;
                //} else {
                //    // Going up
                //    valueChange = Math.abs(value - lastValue) * normal;
                //}

                double oneThirdEnd = (double) 1/3;

                //System.out.printf(
                //    "%n Value Change: %s, one third of end: %s, Is Over: %s%n",
                //    valueChange,
                //    oneThirdEnd,
                //    valueChange > oneThirdEnd
                //);

                this.realValue += ValueChangeToReal(valueChange);
                this.percentageValue += valueChange;

                //if (valueChange < oneThirdEnd) {
                //    this.realValue += ValueChangeToReal(valueChange * this.endValue);
                //    this.percentageValue += valueChange;
                //} else {
                //    // Value has changed too much
                //    // Protection against high value change
                //    double change = valueChange/2;
                //    this.realValue += ValueChangeToReal(change);
                //    this.percentageValue += change;
                //}
            }
        }
    }

    /**
     * Gets the change in value
     * @param change The change in value.
     */
    private double ValueChangeToReal(double change) {
        //System.out.println(change);
        if (this.normal > 0) {
            // Going up
            return change * (this.endValue - this.startValue);
        } else {
            // Going down
            //return -Math.abs(this.startValue - (this.startValue * (1-change)));
            System.out.println(change);
            return -Math.abs((this.startValue - (change * (this.startValue - this.endValue))) - this.startValue);
        }
    }

    /**
     * Get the current value at this time. 0 - End Value.
     */
    public double GetValue() {
        return this.endValue * this.percentageValue;
    }

    /**
     * Get the current completion Percentage.
     */
    public double GetPercentage() {
        return this.percentageValue;
    }

    /**
     * Get the actual value (Adds onto previous operation value).
     */
    public double GetActualValue() {
        return this.realValue;
    }

    /**
     * Sets the value.
     * @param value The starting value.
     * @param endValue The ending value.
     */
    public void SetValue(double value, double endValue) {
        this.startTime = System.currentTimeMillis();
        this.realValue = value;
        this.startValue = value;
        this.endValue = endValue;
        this.normal = (value < endValue) ? 1 : -1;
        this.percentageValue = 0;
    }

    /**
     * Changes the Value.
     * @param endValue The ending value.
     */
    public void ChangeValue(double endValue) {
        this.startTime = System.currentTimeMillis();
        this.startValue = this.realValue;
        this.endValue = endValue;
        this.isBeingUpdated = true;
        this.normal = (this.realValue < endValue) ? 1 : -1;
        this.percentageValue = 0;
    }

    /**
     * Checks if it has reached its end value.
     */
    public boolean IsComplete() {
        return !this.isBeingUpdated;
    }
}

enum EasingDirection {
    In,
    Out,
    InOut
}
enum EasingType {
    Sine,
    Cubic;
    //Quint,
    //Circ,
    //Elastic,
    //Quad,
    //Quart,
    //Expo,
    //Back,
    //Bounce,
    //Linear;

    /**
     * @param x The current progress 0-1.
     * @param direction The direction of the ease type.
     */
    public double Ease(double x, EasingDirection direction) {
        switch(this) {
            case Sine:
                switch(direction) {
                    case In:
                        return 1 - Math.cos((x * Math.PI) / 2);
                    case Out:
                        return Math.sin((x * Math.PI) / 2);
                    case InOut:
                        return -(Math.cos(Math.PI * x) - 1) / 2;
                }
            case Cubic:
                switch(direction) {
                    case In:
                        return x * x * x;
                    case Out:
                        return 1 - Math.pow(1 - x, 3);
                    case InOut:
                        return x < 0.5 ? 4 * x * x * x : 1 - Math.pow(-2 * x + 2, 3) / 2;
                }
        }
        return x;
    }
}