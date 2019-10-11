package events;

import events.library.HotelEvent;
import events.library.HotelEventListener;
import events.library.HotelEventManager;
import simulation.Hotel;
import simulation.HteObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static javax.swing.UIManager.put;

/**
 * @author Johan Geluk
 * Collects events from the library, rewrites them, and forwards them to the hotel to be processed.
 */
public class EventsAdapter implements HotelEventListener, HteObserver {
    private HotelEventManager hotelEventManager;
    private Hotel hotel;

    // Collected, unprocessed events get queued up here.
    private Queue<HotelEvent> eventQueue = new LinkedList<>();
    // Lets us keep track of where we are in the game.
    private int tickCount;

    public EventsAdapter(Hotel hotel){
        this.hotel = hotel;
        hotelEventManager = new HotelEventManager();
        // Give me all events immediately
        hotelEventManager.changeSpeed(10000);
        hotelEventManager.register(this);
        hotelEventManager.start();

        // After a tick event has fired, we should collect the events that we will need to process for the next tick.
        // This means we should start out at a tick count of 1,
        // since we will be processing the events that will occur during that tick.
        hotel.getHotelTimer().addAfterEventObserver(this);
        tickCount = 1;

    }
    @Override
    /**
     * Queues an event to be processed as soon as it is meant to occur.
     */
    public void Notify(HotelEvent event) {
        eventQueue.add(event);
    }


    @Override
    /**
     * Checks which events need to be dispatched for the upcoming HTE tick.
     */
    public void observeHTE() {
        while(eventQueue.size() > 0 && eventQueue.peek().Time <= tickCount){
            // We have events to process.
            // Make sure that if an exception should occur during processing,
            // we don't kill the entire application.
            try{
                handleEvent(eventQueue.remove());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        tickCount++;
    }

    /**
     * Dispatches events based on their type, transforming them into a data type that the application can deal with.
     */
    private void handleEvent(HotelEvent event) {
        switch (event.Type) {
            case CHECK_IN:
                var checkInEvent = parseCheckInEvent(event);
                hotel.newGuest(checkInEvent.guestNumber, checkInEvent.classification);
                break;
            case CHECK_OUT:
                var checkOutEvent = parseCheckOutEvent(event);
                hotel.requestCheckOut(checkOutEvent.guestNumber);
                break;
            case CLEANING_EMERGENCY:
                var emergencyEvent = parseCleaningEmergencyEvent(event);
                hotel.handleCleaningEmergency(emergencyEvent.guestNumber);
                break;
            case EVACUATE:
                hotel.handleEvacuation();
                break;
            case GODZILLA:
                hotel.handleGodzilla();
                break;
            case NEED_FOOD:
                var needFoodEvent = parseNeedFoodEvent(event);
                hotel.handleDinnerRequest(needFoodEvent.guestNumber, new ArrayList<>());
                break;
            case GOTO_CINEMA:
                var goToCinemaEvent = parseGoToCinemaEvent(event);
                hotel.handleGoToCinema(goToCinemaEvent.guestNumber);
                break;
            case GOTO_FITNESS:
                var goToFitnessEvent = parseGoToFitnessEvent(event);
                hotel.handleGoToFitness(goToFitnessEvent.guestNumber, goToFitnessEvent.duration);
                break;
            case START_CINEMA:
                var startCinemaEvent = parseStartCinemaEvent(event);
                hotel.handleStartCinema(startCinemaEvent.cinemaId);
                break;
        }
    }

    /**
     * Parses the required information from a cinema start event.
     */
    private StartCinemaEvent parseStartCinemaEvent(HotelEvent event) {
        return new StartCinemaEvent(Integer.parseInt(event.Data.get("ID")));
    }

    /**
     * Parses the required information from a go to cinema event.
     */
    private GoToCinemaEvent parseGoToCinemaEvent(HotelEvent event) {
        String guest = event.Data.get("Guest");
        return new GoToCinemaEvent(Integer.parseInt(guest));
    }

    /**
     * Parses the required information from a go to fitness event.
     */
    private GoToFitnessEvent parseGoToFitnessEvent(HotelEvent event) {
        var eventKey = event.Data.keySet().iterator().next();
        var eventValue = event.Data.get(eventKey).split(" ");

        int guestNumber = Integer.parseInt(eventKey.split(" ")[1]);
        int duration = Integer.parseInt(eventValue[0]);
        return new GoToFitnessEvent(guestNumber, duration);
    }

    /**
     * Parses the required information from a need food event.
     */
    private NeedFoodEvent parseNeedFoodEvent(HotelEvent event) {
        String guest = event.Data.get("Guest");
        return new NeedFoodEvent(Integer.parseInt(guest));
    }

    /**
     * Parses the required information from a cleaning emergency event.
     */
    private CleaningEmergencyEvent parseCleaningEmergencyEvent(HotelEvent event) {
        String guest = event.Data.get("Guest");
        return new CleaningEmergencyEvent(Integer.parseInt(guest));
    }

    /**
     * Parses the required information from a check out event.
     */
    private CheckOutEvent parseCheckOutEvent(HotelEvent event) {
        var guestNumber = Integer.parseInt(event.Data.get("Guest"));
        return new CheckOutEvent(guestNumber);
    }

    /**
     * Parses the required information from a check in event.
     */
    private static CheckInEvent parseCheckInEvent(HotelEvent event){

        var eventKey = event.Data.keySet().iterator().next();
        var eventValue = event.Data.get(eventKey).split(" ");

        var classification = Integer.parseInt(eventValue[0]);
        var guestNumber = Integer.parseInt(eventKey.split(" ")[1]);

        return new CheckInEvent(guestNumber, classification);
    }

    /**
     * Represents a check in event.
     */
    private static class CheckInEvent {
        private final int guestNumber;
        private final int classification;

        public CheckInEvent(int guestNumber, int classification){

            this.guestNumber = guestNumber;
            this.classification = classification;
        }
    }

    /**
     * Represents a check out event.
     */
    private class CheckOutEvent {
        public int guestNumber;

        public CheckOutEvent(int guestNumber) {
            this.guestNumber = guestNumber;
        }
    }

    /**
     * Represents a cleaning emergency event.
     */
    private class CleaningEmergencyEvent {
        public int guestNumber;

        public CleaningEmergencyEvent(int guestNumber) {
            this.guestNumber = guestNumber;
        }
    }

    /**
     * Represents a need food event.
     */
    private class NeedFoodEvent {
        private int guestNumber;

        public NeedFoodEvent(int guestNumber) {

            this.guestNumber = guestNumber;
        }
    }

    /**
     * Represents a go to fitness event.
     */
    private class GoToFitnessEvent {
        private final int guestNumber;
        private final int duration;

        public GoToFitnessEvent(int guestNumber, int duration) {
            this.guestNumber = guestNumber;
            this.duration = duration;
        }
    }

    /**
     * Represents a go to cinema event.
     */
    private class GoToCinemaEvent {
        private int guestNumber;

        public GoToCinemaEvent(int guestNumber) {
            this.guestNumber = guestNumber;
        }
    }

    /**
     * Represents a start cinema event.
     */
    private class StartCinemaEvent {
        private int cinemaId;

        public StartCinemaEvent(int cinemaId) {
            this.cinemaId = cinemaId;
        }
    }
}
