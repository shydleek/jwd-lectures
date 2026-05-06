package org.epam.jwd.observer;

import org.epam.jwd.model.Plain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventManagerTest {

    private String eventType = "eventType";
    private String firstEventType = "firstEventType";
    private String secondEventType = "secondEventType";
    private String thirdEventType = "thirdEventType";
    private EventManager eventManager = new EventManager(eventType);
    private EventManager mainEventManager = new EventManager(firstEventType, secondEventType, thirdEventType);

    @Mock
    private EventListener mockListener;

    @Mock
    private EventListener firstMockListener;

    @Mock
    private EventListener secondMockListener;

    @Mock
    private EventListener thirdMockListener;

    @Mock
    private Plain mockNewPlain;

    @Mock
    private Plain mockOldPlain;

    @Test
    public void subscribe_shouldAddListenerWithEventType_always() {
        eventManager.subscribe(eventType, mockListener);

        eventManager.notify(eventType, mockNewPlain, mockOldPlain);
        verify(mockListener, times(1)).update(eventType, mockNewPlain, mockOldPlain);
    }

    @Test
    public void unsubscribe_shouldRemoveListenerWithEventType_always() {
        eventManager.subscribe(eventType, mockListener);

        eventManager.unsubscribe(eventType, mockListener);

        eventManager.notify(eventType, mockNewPlain, mockOldPlain);
        verify(mockListener, never()).update(anyString(), any(), any());
    }

    @Test
    public void notify_shouldNotifySpecificSubscriber_whenCorrectIsCalled() {
        mainEventManager.subscribe(firstEventType, firstMockListener);
        mainEventManager.subscribe(secondEventType, secondMockListener);
        mainEventManager.subscribe(thirdEventType, thirdMockListener);

        mainEventManager.notify(firstEventType, mockNewPlain, mockOldPlain);

        verify(firstMockListener, times(1)).update(firstEventType, mockNewPlain, mockOldPlain);
        verify(secondMockListener, never()).update(anyString(), any(), any());
        verify(thirdMockListener, never()).update(anyString(), any(), any());
    }
}
