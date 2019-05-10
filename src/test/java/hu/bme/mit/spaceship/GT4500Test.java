package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockPTS;
  private TorpedoStore mockSTS;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockPTS = mock(TorpedoStore.class);
    mockSTS = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPTS, mockSTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockPTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockPTS, times(1)).fire(1);
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).fire(1);
  }

}
