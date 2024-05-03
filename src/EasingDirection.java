/*
    Easing Package
    Package Made by Mason Huber
    Formula's Made by Robert Penner
*/

public enum EasingTypes {
    Sine;
    //Cubic,
    //Quint,
    //Circ,
    //Elastic,
    //Quad,
    //Quart,
    //Expo,
    //Back,
    //Bounce,
    //Linear;

    public double Ease(double x, EasingDirection direction) {
        switch(this) {
            case Sine:
                switch(direction) {
                    case EaseingDirection.In:
                        break;
                    case EaseingDirection.Out:   
                        break; 
                    case EasingDirection.InOut:
                        return -(Math.cos(Math.PI * x) - 1) / 2;
                }
        }
    }
}

public enum EasingDirection {
    In,
    Out,
    InOut
}

public class Easing {
    private double value;
    private double endValue;
    private double duration;
    private long startTime;
    private boolean isBeingUpdated;
    private EasingType easeType;
    private EasingDirection easeDirection;

    public Easing(EasingType easeType, EasingDirection easeDirection, double value, double endValue, double duration) {
        this.easeType = easeType;
        this.easeDirection = easeDirection;
        this.value = value;
        this.endValue = endValue;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
        isBeingUpdated = true;
    }

    public void Update() {
        if (isBeingUpdated) {
            double progress = (System.currentTimeMillis() - this.startTime) / (duration*1000);
            if (progress > 1) {
                // FINISHED
                isBeingUpdated = false;
            } else {
                double lastValue = this.value;
            
                this.value = -(Math.cos(Math.PI * progress) - 1) / 2;
                double valueChange = this.value - lastValue;
                double oneThirdEnd = (double) 1/3;
                System.out.printf(
                    "%n Value Change: %s, one third of end: %s, Is Over: %s%n",
                    valueChange,
                    oneThirdEnd,
                    valueChange > oneThirdEnd
                );
                if (valueChange > oneThirdEnd) {
                    // Value has changed too much
                    // Protection against high value change
                    this.value += valueChange/2;
                }
            }
        } else {
            this.value = 1;
        }
    }

    public double GetValue() {
        return this.endValue * this.value;
    }

    public void ChangeValue(double endValue) {
        this.startTime = System.currentTimeMillis();
        this.endValue = endValue;
        isBeingUpdated = true;
    }
}

