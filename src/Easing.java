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
                double valueChange = value - lastValue;
                double oneThirdEnd = (double) 1/3;

                this.realValue += ValueChangeToReal(valueChange);
                this.percentageValue += valueChange;
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
    Cubic,
    Quint,
    Circ,
    Elastic,
    Quad,
    Quart,
    Expo,
    Back,
    Bounce,
    Linear;

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
            case Quint:
                switch(direction) {
                    case In:
                        return x * x * x * x * x;
                    case Out:
                        return 1 - Math.pow(1 - x, 5);
                    case InOut:
                        return x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2;
                }
            case Circ:
                switch(direction) {
                    case In:
                        return 1 - Math.sqrt(1 - Math.pow(x, 2));
                    case Out:
                        return Math.sqrt(1 - Math.pow(x - 1, 2));
                    case InOut:
                        return x < 0.5
                                ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2
                                : (Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
                }
            case Elastic:
                switch(direction) {
                    case In:
                        final double c4 = (2 * Math.PI) / 3;
                        return x == 0
                                ? 0
                                : x == 1
                                ? 1
                                : -Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4);
                    case Out:
                        final double c5 = (2 * Math.PI) / 3;
                        return x == 0
                                ? 0
                                : x == 1
                                ? 1
                                : Math.pow(2, -10 * x) * Math.sin((x * 10 - 0.75) * c5) + 1;
                    case InOut:
                        final double c6 = (2 * Math.PI) / 4.5;
                        return x == 0
                                ? 0
                                : x == 1
                                ? 1
                                : x < 0.5
                                ? -(Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c6)) / 2
                                : (Math.pow(2, -20 * x + 10) * Math.sin((20 * x - 11.125) * c6)) / 2 + 1;
                }
            case Quad:
                switch(direction) {
                    case In:
                        return x * x;
                    case Out:
                        return 1 - (1 - x) * (1 - x);
                    case InOut:
                        return x < 0.5 ? 2 * x * x : 1 - Math.pow(-2 * x + 2, 2) / 2;
                }
            case Quart:
                switch(direction) {
                    case In:
                        return x * x * x * x;
                    case Out:
                        return 1 - Math.pow(1 - x, 4);
                    case InOut:
                        return x < 0.5 ? 8 * x * x * x * x : 1 - Math.pow(-2 * x + 2, 4) / 2;
                }
            case Expo:
                switch(direction) {
                    case In:
                        return x == 0 ? 0 : Math.pow(2, 10 * x - 10);
                    case Out:
                        return x == 1 ? 1 : 1 - Math.pow(2, -10 * x);
                    case InOut:
                        return x == 0
                                ? 0
                                : x == 1
                                ? 1
                                : x < 0.5 ? Math.pow(2, 20 * x - 10) / 2
                                : (2 - Math.pow(2, -20 * x + 10)) / 2;
                }
            case Back:
                switch(direction) {
                    case In:
                        final double c1 = 1.70158;
                        final double c3 = c1 + 1;
                        return c3 * x * x * x - c1 * x * x;
                    case Out:
                        final double c2 = 1.70158;
                        final double c4 = c2 + 1;
                        return 1 + c4 * Math.pow(x - 1, 3) + c2 * Math.pow(x - 1, 2);
                    case InOut:
                        final double c6 = 1.70158;
                        final double c7 = c6 * 1.525;
                        return x < 0.5
                                ? (Math.pow(2 * x, 2) * ((c7 + 1) * 2 * x - c7)) / 2
                                : (Math.pow(2 * x - 2, 2) * ((c7 + 1) * (x * 2 - 2) + c7) + 2) / 2;
                }
            case Bounce:
                switch(direction) {
                    case In:
                        return this.Ease(1 - x, EasingDirection.Out);
                    case Out:
                        final double n1 = 7.5625;
                        final double d1 = 2.75;
                        if (x < 1 / d1) {
                            return n1 * x * x;
                        } else if (x < 2 / d1) {
                            return n1 * (x -= 1.5 / d1) * x + 0.75;
                        } else if (x < 2.5 / d1) {
                            return n1 * (x -= 2.25 / d1) * x + 0.9375;
                        } else {
                            return n1 * (x -= 2.625 / d1) * x + 0.984375;
                        }
                    case InOut:
                        return x < 0.5
                                ? (1 - this.Ease(1 - 2 * x, EasingDirection.Out)) / 2
                                : (1 + this.Ease(2 * x - 1, EasingDirection.Out)) / 2;
                }
            case Linear:
                return x;
        }
        return x;
    }
}