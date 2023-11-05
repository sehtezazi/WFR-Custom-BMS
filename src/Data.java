/*******************************************************************
 Name: Aly Ashour
 Date: October 29, 2023,
 Description:
 Represents the data part of a CAN frame.
 Consists of an n number of bytes and the number n.
 I have capped this at 4 bytes for now. Increasing this limit shouldn't
 be too hard.
 ********************************************************************/
import java.util.Arrays;
import java.util.logging.*;

public class Data {
    private final int length; // number of bytes of data
    private final byte[] data; // bytes of data

    /**
     * Default, no param constructor
     * Needed this because varargs constructors below depend on there being at least 1 argument
     * Also I should not leave the data[] nullptr
     */
    public Data(){
        length = 0;
        data = new byte[]{};
    }

    /**
     * Makes a data object using a bunch of bytes
     */
    public Data(byte...bytes){
        // set parameters
        this.length = bytes.length;
        data = bytes;

        // idk if data is allowed to be bigger than 4 bytes, but my implementation so far uses Integer
        // which depends on that size. in the future this requirement can be removed by changing the code
        // in this class specifically.
        // this is a non-lethal warning, it does not crash the program.
        if (length > 4){
            Logger logger = Logger.getLogger(getClass().getName()); // for debugging
            logger.warning("Warning: Data param should not be larger than 4 bytes");
        }
    }

    public Data(int... ints){
        this.length = ints.length; // save value

        // convert int array to byte array
        byte[] bytes = new byte[length];
        for(int i = 0; i < length; i++) {
            int value = ints[i];
            if (value > 127 || value < -128)
                throw new IllegalArgumentException("Input too large to be converted to Byte: "
                        + value + " must be <127");
            bytes[i] = (byte)value;
        }

        // idk if data is allowed to be bigger than 4 bytes, but my implementation so far uses Integer
        // which depends on that size. in the future this requirement can be removed by changing the code
        // in this class specifically.
        // this is a non-lethal warning, it does not crash the program.
        if (length > 4){
            Logger logger = Logger.getLogger(getClass().getName()); // for debugging
            logger.warning("Data param should not be larger than 4 bytes");
        }

        data = bytes; // save value
    }

    /**
     * Converts the data into a bitfield
     * The length of the bitfield is 8 * length of the data
     */
    public BitField toBitField(){
        // convert data (byte array) to integer
        return new BitField(length * 8, convertDataToInt());
    }

    /**
     * Converts the data array field to an integer by concatenating all the bytes in order
     * E.g., 1011000, 00011011 -> 101100000011011 or 29,787
     */
    private int convertDataToInt(){
        // since we are packing into an integer, the dataset must contain less than 4 bytes
        if (length > 4) throw new RuntimeException(
                "Cannot convert data to integer too large for 4 bytes."
                + Arrays.toString(data) + " contains " + length + " bytes");
        // Integer.parseInt will raise exception if array is empty
        else if (length == 0) return 0;

        StringBuilder s = new StringBuilder(); //  make string
        for (byte b : data) {
            s.append(new BitField(8, b)); // append each bit (up to a max of 4)
        }
        return Integer.parseInt(s.toString(), 2); // convert huge int string into an integer
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Byte b : data) s.append(new BitField(8, b));
        return s.toString();
    }
}
