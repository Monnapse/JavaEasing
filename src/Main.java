public class Main {
    public static void main(String[] args) throws InterruptedException {
        Easing TestTween = new Easing(EasingType.Sine, EasingDirection.InOut, 0.5);
        TestTween.SetValue(0, 15);
        //EaseInOutSine TestTween2 = new EaseInOutSine(0, 15, 30);
        //int cps = 1; // Cycles Per Second
        boolean hasUpdated = false;
        while (true) {
            TestTween.Update();

            System.out.printf(
                    "%nPercentage: %s, Value: %s %n",
                    TestTween.GetPercentage(),
                    (int) TestTween.GetActualValue()
            );

            if (TestTween.IsComplete() && hasUpdated) {
                break;
            } else if (TestTween.IsComplete()) {
                //System.out.println("COMPLETED");
                //TestTween.ChangeValue(10);
                hasUpdated = true;
            }
            Thread.sleep(10);
        }
    }
}