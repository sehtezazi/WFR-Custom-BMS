import java.util.ArrayList;
import java.util.Arrays;

/*******************************************************************
 Name: Aly Ashour
 Date: October 29, 2023,
 Description:
 This class represents an ID.
 It also encapsulates all static ID info (like # of bits & state codes). 
 ********************************************************************/

public class ID {
    private BitField state;
    private BitField command;

    public ID(BitField state, BitField command){
        // make sure state and command are of correct format
        if (state.numBits != STATE_NUM_BITS || command.numBits != COMMAND_NUM_BITS) {
            String s = String.format("BitField invalid format. Got %s, %s bits expected %s, %s", state.numBits, command.numBits, STATE_NUM_BITS, COMMAND_NUM_BITS);
            throw new IllegalArgumentException(s);
        }
        this.state = state;
        this.command = command;
    }

    public ID(int state, int command){
        this(   
                new BitField(STATE_NUM_BITS, state), 
                new BitField(COMMAND_NUM_BITS, command)
            );
    }

    public BitField getState(){
        return state;
    }

    public BitField getCommand(){
        return command;
    }

    public BitField getBitField(){
        return state.plus(command);
    }

    @Override
    public String toString(){
        return state.toString() + command.toString();
    }

    /* Static Fields and Methods */

    public static final int STATE_NUM_BITS = 2;
    public static final int COMMAND_NUM_BITS = 9;
    public static final int ACTIVE = 0b00, CHARGING = 0b01, IDLE = 0b10, DEBUG = 0b11;

    public static boolean isStateValid(int state) {
        return STATES.contains(state);
    }

    // this is for exception checking, so we don't have to change the code if another mode were to be added.
    public static final ArrayList<Integer> STATES = new ArrayList<>(Arrays.asList(ACTIVE, CHARGING, IDLE, DEBUG));

}
