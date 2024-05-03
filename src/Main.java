public class Main {
    public static void main(String[] args) throws InterruptedException {
        EaseInOutSine TestTween = new EaseInOutSine(0, 5, 3);
        int cps = 1; // Cycles Per Second

        while (true) {
            TestTween.Update();
            System.out.println(TestTween.GetValue());
            //Thread.sleep((1/cps)*1000);
            Thread.sleep(10);
        }
    }
}