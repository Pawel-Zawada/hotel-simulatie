package events;

import events.library.HotelEvent;
import events.library.HotelEventListener;
import events.library.HotelEventManager;
import simulation.Hotel;
import simulation.IObserver;

import java.util.LinkedList;
import java.util.Queue;

public class EventsAdapter implements HotelEventListener, IObserver {

    private HotelEventManager hotelEventManager;
    private Hotel hotel;

    private Queue<HotelEvent> eventQueue = new LinkedList<>();

    private int tickCount;

    public EventsAdapter(Hotel hotel){
        this.hotel = hotel;
        hotelEventManager = new HotelEventManager();
        // Give me all events immediately
        hotelEventManager.changeSpeed(10000);
        hotelEventManager.register(this);
        hotelEventManager.start();

        // After a tick event has fired, we should collect the events that we will need to process for the next tick.
        // This means we should start out at a tick count of 1.
        hotel.getHotelTimer().addAfterEventObserver(this);
        tickCount = 1;

    }
    @Override
    public void Notify(HotelEvent event) {
        eventQueue.add(event);
    }


    @Override
    public void observe() {
        while(eventQueue.size() > 0 && eventQueue.peek().Time <= tickCount){
            // We have events to process.
            handleEvent(eventQueue.remove());
        }
        tickCount++;
    }

    private void handleEvent(HotelEvent event) {
        switch (event.Type) {
            case NONE:
                break;
            case CHECK_IN:
                var checkInEvent = parseCheckInEvent(event);
                hotel.newGuest(checkInEvent.guestNumber, checkInEvent.classification);
            case CHECK_OUT:
                var checkOutEvent = parseCheckOutEvent(event);
                hotel.checkOut(checkOutEvent.guestNumber);
                break;
            case CLEANING_EMERGENCY:
                break;
            case EVACUATE:
                break;
            case GODZILLA:
                break;
            case NEED_FOOD:
                break;
            case GOTO_CINEMA:
                break;
            case GOTO_FITNESS:
                break;
            case START_CINEMA:
                break;
        }
    }

    private CheckOutEvent parseCheckOutEvent(HotelEvent event) {
        return new CheckOutEvent();
    }

    private static CheckInEvent parseCheckInEvent(HotelEvent event){

        var eventKey = event.Data.keySet().iterator().next();
        var eventValue = event.Data.get(eventKey).split(" ");

        var classification = Integer.parseInt(eventValue[0]);
        var guestNumber = Integer.parseInt(eventKey.split(" ")[1]);

        return new CheckInEvent(guestNumber, classification);
    }

    private static class CheckInEvent {
        private final int guestNumber;
        private final int classification;

        public CheckInEvent(int guestNumber, int classification){

            this.guestNumber = guestNumber;
            this.classification = classification;
        }
    }

    private class CheckOutEvent {
        public int guestNumber;
    }
}
