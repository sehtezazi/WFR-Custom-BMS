/*******************************************************************
 * Name: Aly Ashour
 * Student Number: 251 292 647
 * Date: October 29, 2023,
 * Description: Charging state commands go here.
 *******************************************************************/

class ChargingStates extends ID_Generator{
    public ChargingStates(){
        super(ID.CHARGING);
    }

    public ID balance() {
        return new ID(state, 0b000_000_001);
    }
}