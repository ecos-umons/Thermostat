import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import mythermostat.thermostat.ThermostatStatemachine;

class SmokeTest {
    private ThermostatStatemachine therm;

    @BeforeEach
    public void init() {
        this.therm = new ThermostatStatemachine();
    }

    @AfterEach
    public void close() {
        this.therm = null;
    }

    @Test
    void smokeTest() {
        long t = this.therm.getTime();
        assertEquals(0, t);
    }
}
