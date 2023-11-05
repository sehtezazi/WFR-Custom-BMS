/*******************************************************************
 Name: Aly Ashour
 Date: October 29, 2023,
 Description:
 A bitfield is a set of binary digits with number of digits n.
 If the digits do not take up the field, the rest are filled with 0.
 Bitfields must be positive.
 ********************************************************************/

public class BitField {
    private int value;
    private final int numBits;

    public BitField(int numBits){
        if (numBits < 0 || numBits > 32) // 32 is the number of bits in an int
            throw new IllegalArgumentException("Invalid Number of Bits, must be between 0 and 32");

        this.numBits = numBits;
    }

    public BitField(int numBits, int value){
        this(numBits);
        this.setValue(value);
    }

    public void setValue(int value) {
        if (bitsRequired(value) > numBits)
            throw new IllegalArgumentException(String.format(
                    "%d requires more bits than allocated: %d/%d",
                    value, bitsRequired(value), numBits
            ));
        if (value < 0)
            throw new IllegalArgumentException("Value (\"" + value + "\") cannot be negative!");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * The bits required for any number n.
     * Equal to the ceiling of log_2(n+1)
     * @param num
     * @return
     */
    public static int bitsRequired(int num){
        return (int)Math.ceil(Math.log(num + 1) / Math.log(2));
    }

    /**
     * Joins n bitfields together into a larger bitfield
     * @param fields
     * @return
     */
    private static BitField join(BitField...fields){
        StringBuilder s = new StringBuilder();
        for (BitField field : fields) {
            s.append(field);
        }

        if (s.length() > 32) // if too many bits for an integer
            throw new IllegalArgumentException("Joint bitfields too big to parse: " + s);

        final int newValue = Integer.parseInt(s.toString(), 2);
        return new BitField(fields.length * 8, newValue);
    }

    @Override
    public String toString(){
        String binaryString = Integer.toBinaryString(value);
        StringBuilder s = new StringBuilder();

        int additional0s = numBits - binaryString.length();
        if (additional0s > 0) s.append("0".repeat(additional0s));
        return s + binaryString;
    }
}
