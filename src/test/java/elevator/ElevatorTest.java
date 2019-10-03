package elevator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class ElevatorTest {
    @Test
    public void constructor_startsElevatorAtBottomFloor(){
        Elevator elevator = new Elevator(4);

        assertThat(elevator.getCurrentFloor()).isEqualTo(0);
    }

    @Test
    public void requestUp_fromTopFloor_throwsElevatorException() {
        Elevator elevator = new Elevator(4);

        assertThatThrownBy(() -> elevator.requestUp(3))
                .isInstanceOf(ElevatorException.class);
    }

    @Test
    public void requestDown_fromBottomFloor_throwsElevatorException() {
        Elevator elevator = new Elevator(4);

        assertThatThrownBy(() -> elevator.requestDown(0))
                .isInstanceOf(ElevatorException.class);
    }

    @Test
    public void requestUp_noOtherRequests_callsElevator() throws ElevatorException {
        Elevator elevator = new Elevator(4);

        elevator.requestUp(1);
        elevator.step();

        assertThat(elevator.getCurrentFloor()).isEqualTo(1);
    }

    @Test
    public void requestDown_fromThirdFloor_takesThreeStepsToArrive() throws ElevatorException {
        Elevator elevator = new Elevator(4);

        elevator.requestDown(3);
        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(1);
        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(2);
        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(3);
    }

    @Test
    public void requestFloor_elevatorWasCalled_handlesFloorRequestFirst() throws ElevatorException{
        Elevator elevator = new Elevator(4, 2, ElevatorDirection.NONE);
        elevator.requestDown(3);

        elevator.requestFloor(1);
        elevator.step();

        assertThat(elevator.getCurrentFloor()).isEqualTo(1);
    }

    @Test
    public void requestFloor_requestedFloorAlongDestination_removesRequestWhenPassed() throws ElevatorException{
        Elevator elevator = new Elevator(4, 0, ElevatorDirection.NONE);

        elevator.requestFloor(2);
        elevator.requestFloor(1);

        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(1);
        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(2);
        // Confirm that, if we give the elevator one more step,
        // it does not attempt to go back to the first floor to drop off the passenger there,
        // because they were already able to get out.
        elevator.step();
        assertThat(elevator.getCurrentFloor()).isEqualTo(2);
    }
}