package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

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
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockPTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_First_Empty_Second_Not(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_First_Empty_Second_Empty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_First_Success_Second_Success(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean resultOne = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultTwo = ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    assertEquals(true, resultOne);
    assertEquals(true, resultTwo);

    verify(mockPTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).fire(1);
    verify(mockPTS, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_First_Success_Second_Empty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean resultOne = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultTwo = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, resultOne);
    assertEquals(true, resultTwo);

    verify(mockPTS, times(2)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(0)).fire(1);
    verify(mockPTS, times(2)).fire(1);
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
  public void fireTorpedo_Single_Primary_Empty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).fire(1);
  }

    @Test
  public void fireTorpedo_Single_All_Empty(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(true);
    when(mockSTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).isEmpty();
  }

  @Test
  public void fireLaser() {
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.SINGLE);

    // Assert
    assertEquals(false,result);
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

  @Test
  public void fireTorpedo_All_Failures(){
    // Arrange
    when(mockPTS.isEmpty()).thenReturn(false);
    when(mockPTS.fire(1)).thenReturn(false);
    when(mockSTS.isEmpty()).thenReturn(false);
    when(mockSTS.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPTS, times(1)).isEmpty();
    verify(mockPTS, times(1)).fire(1);
    verify(mockSTS, times(1)).isEmpty();
    verify(mockSTS, times(1)).fire(1);
  }

}
