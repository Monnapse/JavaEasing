public class Main {
    public static void main(String[] args) throws InterruptedException {
        Easing TestTween = new Easing(EasingType.Sine, EasingDirection.InOut, 3);
        TestTween.changeValue(5);
        //EaseInOutSine TestTween2 = new EaseInOutSine(0, 15, 30);
        int cps = 60; // Cycles Per Second
        long t = System.currentTimeMillis();
        boolean changedValue = false;
        boolean hasUpdated = false;
        while (true) {
            TestTween.update();

            System.out.printf(
                    "%nPercentage: %s, Value: %s %n",
                    TestTween.getPercentage(),
                    TestTween.getActualValue()
            );

            if (!changedValue && System.currentTimeMillis() - t >= 2000) {
                changedValue = true;
                TestTween.changeValue(10);
            }

            if (TestTween.isComplete() && hasUpdated) {
                break;
            } else if (TestTween.isComplete()) {
                System.out.println("COMPLETED");
                TestTween.changeValue(-5);
                hasUpdated = true;
            }
            Thread.sleep((1/cps)*1000);
        }
    }
}