package org.epam.jwd.observer;

import org.epam.jwd.model.Plain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventManagerTest {

    private final String eventType = "eventType";
    private final String firstEventType = "firstEventType";
    private final String secondEventType = "secondEventType";
    private final String thirdEventType = "thirdEventType";
    private final EventManager eventManager = new EventManager(eventType);
    private final EventManager mainEventManager = new EventManager(firstEventType, secondEventType, thirdEventType);

    @Mock
    private EventListener firstMockListener;

    @Mock
    private EventListener secondMockListener;

    @Mock
    private EventListener thirdMockListener;

    @Mock
    private EventListener mockListener;

    private int id;

    @Test
    public void subscribe_shouldAddListenerWithEventType_always() {
        eventManager.subscribe(eventType, mockListener);

        eventManager.notify(eventType, id);
        verify(mockListener, times(1)).update(eventType, id);
    }

    @Test
    public void unsubscribe_shouldRemoveListenerWithEventType_always() {
        eventManager.subscribe(eventType, mockListener);

        eventManager.unsubscribe(eventType, mockListener);

        eventManager.notify(eventType, id);
        verify(mockListener, never()).update(anyString(), anyInt());
    }

    @Test
    public void notify_shouldNotifySpecificSubscriber_whenCorrectIsCalled() {
        mainEventManager.subscribe(firstEventType, firstMockListener);
        mainEventManager.subscribe(secondEventType, secondMockListener);
        mainEventManager.subscribe(thirdEventType, thirdMockListener);

        mainEventManager.notify(firstEventType, id);

        verify(firstMockListener, times(1)).update(firstEventType, id);
        verify(secondMockListener, never()).update(anyString(), anyInt());
        verify(thirdMockListener, never()).update(anyString(), anyInt());
    }
}