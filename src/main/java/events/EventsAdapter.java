package events;

import events.library.HotelEvent;
import events.library.HotelEventListener;

public class EventsAdapter implements HotelEventListener {
    @Override
    public void Notify(HotelEvent event) {
        switch(event.Type){
            case NONE:
                break;
            case CHECK_IN:
                break;
            case CHECK_OUT:
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
}
