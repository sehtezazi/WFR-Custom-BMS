package states

import IDs

class ChargingState : IDs(CHARGING) {
    fun balance(): Int {
        return CHARGING + 1
    }
}