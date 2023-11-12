/*******************************************************************
 Name: Aly Ashour
 Date: October 29, 2023,
 Description:
 A bitfield is a set of binary digits with number of digits n.
 If the digits do not take up the field, the rest are filled with 0.
 Bitfields must be positive.
 ********************************************************************/

public class BitField {
    private long value;
    public final int numBits;

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
                    "value %d requires more bits than allocated: %d of %d allowed.",
                    value, bitsRequired(value), numBits
            ));
        if (value < 0)
            throw new IllegalArgumentException("Value (\"" + value + "\") cannot be negative!");
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    /**
     * The bits required for any number n.
     * Equal to the ceiling of log_2(n+1)
     * @param num
     */
    public static int bitsRequired(int num){
        return (int)Math.ceil(Math.log(num + 1) / Math.log(2));
    }

    public BitField plus(BitField bf){
        return new BitField(numBits + bf.numBits, (int) (value * Math.pow(2, bf.numBits) + bf.value));
    }

    @Override
    public String toString(){
        String binaryString = Long.toBinaryString(value);
        StringBuilder s = new StringBuilder();

        int additional0s = numBits - binaryString.length();
        if (additional0s > 0) s.append("0".repeat(additional0s));
        return s + binaryString;
    }
}
