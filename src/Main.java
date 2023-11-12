/*******************************************************************
 * Name: Aly Ashour
 * Date: October 29, 2023,
 ******************************************************************/

public class Main {
    public static void main(String[] args) {
        // make three frames
        Frame f = new Frame(new ActiveStates().start(), new Data(10)); // generic start command inherited to ActiveState
        Frame g = new Frame(new IdleStates().stop(), new Data(20, 30)); // generic stop command inherited to IdleState
        Frame h = new Frame(new ChargingStates().balance(), new Data(40, 50, 60)); // ChargingState specific command .balance()

        // print three frames
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);

        System.out.println(Long.toBinaryString(f.toLong()));
    }
}

