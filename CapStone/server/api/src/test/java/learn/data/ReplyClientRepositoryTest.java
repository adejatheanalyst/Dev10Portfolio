package learn.data;

import learn.TestHelper;
import learn.models.Reply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ReplyClientRepositoryTest {

    @Autowired
    JdbcClient client;
    @Autowired
    ReplyRepository repository;


    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }


    @Nested
    class findByTests {
        @Test
        void findByReplyId() {
            Reply actual = repository.findByReplyId(1);
            assertNotNull(actual);
            assertEquals(1, actual.getReplyId());
        }
        @Test
        void findByReplyIdFailNotFound() {
            Reply actual = repository.findByReplyId(0);
            assertNull(actual);
        }
        @Test
        void findByReplyMoodViceId() {
            List<Reply> actual = repository.findByReplyMoodViceId(1);
            assertNotNull(actual);
            assertEquals(5, actual.size());
        }
        @Test
        void findByMoodViceIdFailNotFound() {
            List<Reply> actual = repository.findByReplyMoodViceId(0);
            assertEquals(0, actual.size());
        }


        @Test
        void findByReplyUserId() {
            List<Reply> actual = repository.findByReplyUserId(1);
            assertNotNull(actual);
            assertEquals(2, actual.size());
        }
        @Test
        void findByReplyUserIdFailNotFounf() {
            List<Reply> actual = repository.findByReplyUserId(0);
            assertEquals(0, actual.size());
        }
        @Test
        void findByKey() {
            Reply reply = repository.findByKey(1, 1, 1);
            assertEquals("testTitle", reply.getTitle());
        }
    }

    @Nested class createReplyTests {
        @Test
        void addReply () {
            Reply newReply = TestHelper.makeReply();
            newReply.setMoodViceId(2);
           Reply expected = repository.addReply(newReply);
           assertEquals(6, expected.getReplyId());
        }
    }

    @Nested class updateReplyTests {
        @Test
        void updateReply() {
            Reply reply = TestHelper.makeReply();
            reply.setReplyId(1);
            assertTrue(repository.updateReply(reply));
        }
        @Test
        void updateReplyFail() {
            Reply reply = TestHelper.makeReply();
            reply.setReplyId(0);
            assertFalse(repository.updateReply(reply));
        }
    }
    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
    }
    @Test
    void deleteByIdFail() {
        assertFalse(repository.deleteById(10));
    }
}