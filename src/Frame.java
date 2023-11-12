/*******************************************************************
 Name: Aly Ashour
 Date: October 29, 2023
 ********************************************************************/

/**
 * A data class representing a CAN frame
 */
public class Frame {
    private final ID id;
    private final Data data;

    /**
     * Main constructor.
     * @param id    in integer id
     * @param data  a data field containing data
     */
    public Frame(ID id, Data data){
        this.id = id;
        this.data = data;
    }

    /**
     * Converts to a long.
     * @return a long representation of the frame (if possible).
     */
    public long toLong(){
        BitField idbf = id.getBitField(), databf = data.toBitField();
        return idbf.plus(databf).getValue();
    }

    @Override
    public String toString(){
        return String.format(
                "Frame Binary | id: %s, data: %s",
                id, data
        );
    }
}
