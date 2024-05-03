public class EaseInOutSine extends Easing {
    public EaseInOutSine(double value, double endValue, double time) {
        super(value, endValue, time);
    }

    public void Update() {
        if (isBeingUpdated) {
            double progress = (System.currentTimeMillis() - this.startTime) / (duration*1000);
            if (progress > 1) {
                // FINISHED
                isBeingUpdated = false;
            } else {
                double lastValue = this.GetValue();
                this.value = -(Math.cos(Math.PI * progress) - 1) / 2;
                double valueChange = this.GetValue() - lastValue;
                double oneThirdEnd = this.endValue/3;
                System.out.printf(
                        "%n Value Change: %s, one third of end: %s, Is Over: %s%n",
                        valueChange,
                        oneThirdEnd,
                        valueChange > oneThirdEnd
                );
                if (valueChange > oneThirdEnd) {
                    System.out.println("PROTECTION");
                    // Value has changed too much
                    // Protection against high value change
                    this.value += valueChange/2;
                }
            }
        } else {
            this.value = this.endValue;
        }

        //if (isBeingUpdated) {
        //    long time = System.currentTimeMillis() - this.startTime;
        //    this.currentDelta += this.lastTime - this.startTime;
//
        //    this.lastTime = System.currentTimeMillis();
//
        //    if (currentDelta <= this.duration*1000) {
        //        //System.out.printf("%nCurrent Time: %d", this.currentDelta);
        //        this.value = this.startValue + 0.5 * (this.endValue - this.startValue) * (1 - Math.cos(Math.PI * time/this.duration));
        //    } else {
        //        isBeingUpdated = false;
        //        this.value = this.endValue;
        //    }
        //}
    }
}
