/*******************************************************************
 Name: Aly Ashour
 Student Number: 251 292 647
 Date: November 03, 2023,
 Description: This is a super class that generates IDs.
 ********************************************************************/

public abstract class ID_Generator {
    protected int state;

    /**
     * Makes an ID generator with some state
     */
    public ID_Generator(int state) {
        if (ID.isStateValid(state))
            this.state = state;
        else throw new IllegalArgumentException("Illegal state. State: " + state + ".");
    }

    /* Default state-independent methods go here */

    public ID start() {
        return new ID(state, 0b111_111_111);
    }

    public ID stop() {
        return new ID(state, 0b111_111_110);
    }

    /* Default state-independent methods end */
}
