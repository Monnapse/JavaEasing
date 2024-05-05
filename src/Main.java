public class Main {
    public static void main(String[] args) throws InterruptedException {
        Easing TestTween = new Easing(EasingType.Sine, EasingDirection.InOut, 2);
        TestTween.SetValue(0, 5);
        //EaseInOutSine TestTween2 = new EaseInOutSine(0, 15, 30);
        //int cps = 1; // Cycles Per Second
        boolean hasUpdated = false;
        while (true) {
            TestTween.Update();

            System.out.printf(
                    "%nPercentage: %s, Value: %s %n",
                    TestTween.GetPercentage(),
                    TestTween.GetActualValue()
            );

            if (TestTween.IsComplete() && hasUpdated) {
                break;
            } else if (TestTween.IsComplete()) {
                System.out.println("COMPLETED");
                TestTween.ChangeValue(5);
                hasUpdated = true;
            }
            Thread.sleep(10);
        }
    }
}