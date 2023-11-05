class ChargingStates extends ID_Generator{
    public ChargingStates(){
        super(ID.CHARGING);
    }

    public ID balance() {
        return new ID(state, 0b000_000_001);
    }
}