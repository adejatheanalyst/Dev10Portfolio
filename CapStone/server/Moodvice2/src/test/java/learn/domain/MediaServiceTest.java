package learn.domain;

import learn.TestHelper;
import learn.data.mappers.MediaRepository;
import learn.domain.Results.MediaResult;
import learn.domain.Results.ResultType;
import learn.models.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MediaServiceTest {
    @Autowired
    MediaService service;
    @MockBean
    MediaRepository repository;

    @Test
    void findByMoodId() {
        List<Media> expected = List.of(TestHelper.makeMedia());
        when(repository.findByMoodId(1)).thenReturn(expected);
        MediaResult actual = service.findByMoodId(1);
        assertEquals(expected, actual.getMediaList());
    }
    @Test
    void findByMoodIdFailNonexistentMood() {
        List<Media> expected = List.of(TestHelper.makeMedia());
        when(repository.findByMoodId(0)).thenReturn(expected);
        MediaResult actual = service.findByMoodId(0);
        actual.addErrorMessage("No media found for that mood.");
        actual.setResultType(ResultType.NOT_FOUND);
        assertEquals(expected, actual.getMediaList());
    }

    @Test
    void findByMediaId() {
        Media expected = TestHelper.makeMedia();
        when(repository.findById(1)).thenReturn(expected);
        MediaResult actual = service.findByMediaId(1);
        assertEquals(expected, actual.getMedia());
    }
    @Test
    void findByMediaIdFailNonexistentMedia() {
        Media expected = TestHelper.makeMedia();
        when(repository.findById(0)).thenReturn(expected);
        MediaResult actual = service.findByMediaId(0);
        actual.addErrorMessage("Media not found.");
        actual.setResultType(ResultType.NOT_FOUND);
        assertEquals(expected, actual.getMedia());
    }

    @Test
    void getRandomMediaByMoodId() {
        List<Media> expected = List.of(TestHelper.makeMedia());
        when(repository.findByMoodId(1)).thenReturn(expected);
        MediaResult actual = service.getRandomMediaByMoodId(1);
        assertEquals(expected, actual.getMediaList());
    }
}