# CAN-Protocol
The primary Data Class is the Frame class.
I've added as much exception handling as I can think of, it should explain the error like 95% of the time.
See the Main file for a short example of how the Frame class works.
# Usage
## Adding CAN Frames to the lib
Add new command IDs in their respective state class (ActiveStates, ChargingStates...)
Add generic IDs (that do not depend on state) in the ID_Generator class (see start & stop as examples).
All IDs should be in the form 
```java
class some_id_class {
    ...
    public int someCommandName() {
        return new ID(state, some_command); // put the bits here.
    }
    ...
}
```
## Using Frames
See main for an example.
Make a CAN frame using the Frame constructor. 
It takes 2 params: some ID and some data.
Use the 4 state classes as objects to access any ID you need. For example, 
- you could access the `.start()` id (which is available in all 4 states) by writing `new SomeState().start()`.
- you can access specific ids the same way `new SomeState().someCommand()`.
