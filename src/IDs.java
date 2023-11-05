/*******************************************************************
 Name: Aly Ashour
 Student Number: 251 292 647
 Date: November 03, 2023,
 Description:
 This is a data class that generates IDs
 ********************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public abstract class IDs {
    private int state;

    /* Default state-independent methods go here*/
    public int start() {
        return state + 0b111_111_111;
    }

    public int stop() {
        return state + 0b111_111_110;
    }

    /* State-independent methods end here */

    /**
     * Primary Constructor
     */
    public IDs(int state) {
        this.state = state;
        if (isStateValid(state))
            this.state *= (int) Math.pow(2, COMMAND_NUM_BITS);
        else throw new IllegalArgumentException("Illegal state. It's 1-4 and you gave me " + state + ". Not allowed bbgl.");
    }

    private static boolean isStateValid(int state) {
        return STATES.contains(state);
    }

    public static final int STATE_NUM_BITS = 2;
    public static final int COMMAND_NUM_BITS = 9;
    public static final int ACTIVE = 0b00, CHARGING = 0b01, IDLE = 0b10, DEBUG = 0b11;

    // this is for exception checking, so we don't have to change the code if another mode were to be added.
    private static final ArrayList<Integer> STATES = new ArrayList<>(Arrays.asList(ACTIVE, CHARGING, IDLE, DEBUG));

    /**
     * This returns a full IDs given the needed subcomponents state and command.
     * This is the primary method of this class.
     *
     * @param state   the mode. in [0, 2^2]: active, CHARGING, IDLE, or DEBUG.
     * @param command the command. in [0, 2^9]. specific to the current state.
     * @return integer of binary id (11 bits),
     * this could be changed to Bitfield in the future to enforce the length
     */
    public static int makeID(int state, int command) {
        if (isStateValid(state)) {
            return state + command;
        } else {
            throw new IllegalArgumentException("State parameter not valid. " + state + " is not a valid state");
        }
    }

    public static int getTotalBitCount() {
        return STATE_NUM_BITS + COMMAND_NUM_BITS;
    }
}
