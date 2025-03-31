package learn.Domain;

import learn.Data.LocationRepositoryDouble;
import learn.Model.Location;
import learn.Model.User;
import learn.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {
    LocationService service;
    private LocationRepositoryDouble repository = new LocationRepositoryDouble();

    @BeforeEach
    void setup() {
        service = new LocationService(new LocationRepositoryDouble());
    }

    @Test
    void findAll() {
        List<Location> locations = service.findAll();
        assertEquals(3, locations.size());
    }

    @Nested
    class findById {
        @Test
        void findById() {
            LocationResult<Location> result = service.findById(1);
            assertTrue(result.isSuccess());
        }

        @Test
        void findByIdFail() {
            LocationResult<Location> result = service.findById(4);
            assertFalse(result.isSuccess());
        }
    }

    @Nested
    class findByStateTests {
        @Test
        void findByState() {
            LocationResult<Location> result = service.findByState(2);
            assertTrue(result.isSuccess());
        }

        @Test
        void findByStateFail() {
            LocationResult<Location> result = service.findByState(10);
            assertFalse(result.isSuccess());
        }

        @Test
        void findByStateFailNull() {
            LocationResult<Location> result = service.findByState(0);
            assertFalse(result.isSuccess());
        }
    }

    @Nested
    class findByCityTests {
        @Test
        void findByCity() {
            LocationResult<Location> result = service.findByCity(TestData.existingCity);
            assertTrue(result.isSuccess());
        }

        @Test
        void findByCityFail() {
            LocationResult<Location> result = service.findByCity(TestData.NonExistingCity);
            assertFalse(result.isSuccess());
        }

        @Test
        void findByCityFailNull() {
            LocationResult<Location> result = service.findByCity(null);
            assertFalse(result.isSuccess());
        }

    }

    @Nested
    class findByUserID {
        @Test
        void findByUser() {
            User user = TestData.user;
            LocationResult<Location> result = service.findByUserId(user);
            assertTrue(result.isSuccess());
            assertFalse(result.getLocationList().isEmpty());
            assertEquals(1, result.getLocationList().size());

        }

        @Test
        void findByUserFail() {
            User user = TestData.NonExistingUser;
            LocationResult<Location> result = service.findByUserId(user);
            assertFalse(result.isSuccess());
        }
    }


        @Nested
        class findRates {
            @Test
            void findStandardRate() {
                LocationResult<Location> result = service.findStandardRate(1);
                assertTrue(result.isSuccess());
            }

            @Test
            void findStandardRateFail() {
                LocationResult<Location> result = service.findStandardRate(8);
                assertFalse(result.isSuccess());
            }

            @Test
            void findWeekendRate() {
                LocationResult<Location> result = service.findWeekendRate(3);
                assertTrue(result.isSuccess());
            }

            @Test
            void findWeekendRateFail() {
                LocationResult<Location> result = service.findWeekendRate(9);
                assertFalse(result.isSuccess());
            }
        }
    }






