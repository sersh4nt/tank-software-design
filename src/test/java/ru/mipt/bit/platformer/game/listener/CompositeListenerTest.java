package ru.mipt.bit.platformer.game.listener;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.game.Entity;
import ru.mipt.bit.platformer.game.GameListener;

import static org.mockito.Mockito.*;

class CompositeListenerTest {
    @Test
    public void testWhenListenerAddedItWasCalledOnListen() {
        var listener = new CompositeListener();

        var a = mock(GameListener.class);
        var e = mock(Entity.class);
        listener.addListener(a);
        listener.onEntityAdded(e);

        verify(a, times(1)).onEntityAdded(e);
    }
}