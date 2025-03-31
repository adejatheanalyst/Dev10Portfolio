package learn.solarfarm.domain;

import learn.solarfarm.TestHelper;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.data.UserRepository;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static learn.solarfarm.TestHelper.*;
import static learn.solarfarm.domain.SolarPanelService.MAX_ROW_COLUMN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SolarPanelServiceTest {

    @MockBean
    SolarPanelRepository repository;
    @MockBean
    UserRepository  userRepository;

    @Autowired
    SolarPanelService service;

    @Test
    void shouldFindBySection_red() {
        var expected = List.of(makePanel(1), makePanel(2), makePanel(3));

        when(repository.findBySection(any())).thenReturn(
                List.of(makePanel(1), makePanel(2), makePanel(3))
        );

        var actual = service.findBySection("red");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindBySection_yellow() {
        var expected = List.of();
        var actual = service.findBySection("yellow");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByKey_red_1_1() {
        SolarPanel expected = makePanel(1);

        when(repository.findByKey("red", 1, 1))
                .thenReturn(makePanel(1));

        SolarPanel actual = service.findByKey("red", 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByKey_yellow_1_1() {
        SolarPanel actual = service.findByKey("yellow", 1, 1);
        assertNull(actual);
    }
//    @Test
////    void shouldFindByUserId() {
////        when(repository.findByUserId(1)).thenReturn(List.of(TestHelper.makePanel(1)));
////
////        SolarPanel actual = service.findByKey("red", 1, 1);
////        assertEquals(expected, actual);
////    }

    @Test
    void shouldNotCreateNull() {
        SolarPanelResult expected = makeResult("SolarPanel cannot be null.", null);
        SolarPanelResult actual = service.create(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateNullSection() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setSection(null);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `section` is required.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateEmptySection() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setSection(" ");
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `section` is required.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateNullMaterial() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setMaterial(null);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `material` is required.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateNonPositiveRow() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setRow(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        String message = String.format(
                "SolarPanel `row` must be a positive number less than or equal to %s.",
                MAX_ROW_COLUMN);
        SolarPanelResult expected = makeResult(message, null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateGreaterThanMaxRow() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setRow(MAX_ROW_COLUMN + 1);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());

        String message = String.format(
                "SolarPanel `row` must be a positive number less than or equal to %s.",
                MAX_ROW_COLUMN);
        SolarPanelResult expected = makeResult(message, null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateNonPositiveColumn() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setColumn(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        String message = String.format(
                "SolarPanel `column` must be a positive number less than or equal to %s.",
                MAX_ROW_COLUMN);
        SolarPanelResult expected = makeResult(message, null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateGreaterThanMaxColumn() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setColumn(MAX_ROW_COLUMN + 1);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        String message = String.format(
                "SolarPanel `column` must be a positive number less than or equal to %s.",
                MAX_ROW_COLUMN);
        SolarPanelResult expected = makeResult(message, null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateYearInstalledInTheFuture() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        arg.setYearInstalled(LocalDate.now().getYear() + 1);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `yearInstalled` must be in the past.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreateNonUniqueSectionRowColumn() {

        SolarPanel mock = makePanel(1);
        when(repository.findByKey(any(), anyInt(), anyInt())).thenReturn(mock);

        SolarPanel arg = makePanel(1);
        arg.setId(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `section`, `row`, and `column` must be unique.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotCreatePositiveId() {
        SolarPanel arg = makePanel(1);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `id` should not be set.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }
    @Test
    void shouldNotCreateWithNonExistentUser() {
        SolarPanel arg = makePanel(1);
        arg.setId(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(null);

        SolarPanelResult expected = makeResult("User does not exist.", null);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCreate() {

        SolarPanel mockPanel = makePanel(5);
        SolarPanel expectedPanel = makePanel(5);

        when(repository.create(any())).thenReturn(mockPanel);
        when(userRepository.findById(5)).thenReturn(TestHelper.makeUser());

        SolarPanel arg = makePanel(5);
        arg.setId(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());

        SolarPanelResult expected = makeResult(null, expectedPanel);
        SolarPanelResult actual = service.create(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptySection() {
        SolarPanel arg = makePanel(1);
        arg.setSection(" ");
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `section` is required.", null);
        SolarPanelResult actual = service.update(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNonPositiveId() {
        SolarPanel arg = makePanel(1);
        arg.setSection("yellow");
        arg.setId(0);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = makeResult("SolarPanel `id` should be set.", null);
        SolarPanelResult actual = service.update(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNonExistentSolarPanel() {
        SolarPanel arg = makePanel(MISSING_ID);
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());
        SolarPanelResult expected = new SolarPanelResult();
        expected.setNotFound();
//        SolarPanelResult expected = makeResult("Could not update panel id 25.", null);
        SolarPanelResult actual = service.update(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        SolarPanel arg = makePanel(1);
        arg.setSection("yellow");
        when(userRepository.findById(arg.getUserId())).thenReturn(TestHelper.makeUser());

        SolarPanelResult expected = makeResult(null, null);
        SolarPanelResult actual = service.update(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteNonExistentSolarPanel() {
        SolarPanelResult expected = makeResult("Could not delete panel id 25.", null);
        SolarPanelResult actual = service.deleteById(MISSING_ID);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        SolarPanelResult expected = makeResult(null, null);
        SolarPanelResult actual = service.deleteById(5);
        assertEquals(expected, actual);
    }
}
