package learn.domain;

import learn.TestHelper;
import learn.data.ReplyRepository;
import learn.data.UserRepository;
import learn.domain.Results.ReplyResult;
import learn.domain.Results.ResultType;
import learn.models.Reply;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ReplyServiceTest {
    @Autowired
    ReplyService service;
    @MockBean
    ReplyRepository repository;
    @MockBean
    UserRepository userRepository;



    @Nested class findTests {
    @Test
    void findByMoodViceId() {
        List<Reply> reply = List.of(TestHelper.ReplyExisting());
        when(repository.findByReplyMoodViceId(1)).thenReturn(reply);
        ReplyResult result = new ReplyResult();
        result.setReplyList(reply);
        ReplyResult actual = service.findByMoodViceId(1);
        assertEquals(actual.getResultType(), result.getResultType());
    }
    @Test
    void findByMoodViceIdFail() {
        List<Reply> reply = List.of(TestHelper.makeReply());
        when(repository.findByReplyMoodViceId(0)).thenReturn(reply);
        ReplyResult result = new ReplyResult();
        result.setReplyList(reply);
        ReplyResult actual = service.findByMoodViceId(1);
        assertEquals(actual.getResultType(), result.getResultType());
    }

        @Test
        void findByKey() {
        Reply reply = TestHelper.ReplyExisting();
        when(repository.findByKey(reply.getReplyId(),reply.getUserId(),reply.getMoodViceId()))
                .thenReturn(reply);
        Reply actual = service.findByKey(1,1,1);
        assertEquals(reply, actual);
        }
}
    @Nested class CreateTests {
        @Test
        void create() {
            Reply reply = TestHelper.makeReply();
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(TestHelper.ReplyExisting());
            ReplyResult result = new ReplyResult();
            result.setReply(TestHelper.ReplyExisting());
            ReplyResult actual = service.create(reply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void createFailIdSet() {
            Reply reply = TestHelper.makeReply();
            reply.setMoodViceId(2);
            reply.setReplyId(2);
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("Id should not be set");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getResultType(), actual.getResultType());

        }
        @Test
        void createFailNull() {
            Reply reply = null;
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("Reply cannot be null.");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void createFailNullMoodViceId() {
            Reply reply = TestHelper.makeReply();
            reply.setMoodViceId(0);
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("No MoodVice to reply to");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFailNullBody() {
            Reply reply = TestHelper.makeReply();
            reply.setBody("");
            reply.setMoodViceId(2);
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("Body is required");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFailNullTitle() {
            Reply reply = TestHelper.makeReply();
            reply.setMoodViceId(2);
            reply.setTitle("");
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("Title is Required");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFailNullUser() {
            Reply reply = TestHelper.makeReply();
            reply.setMoodViceId(2);
            reply.setUserId(0);
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(null);
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("User does not exist");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getErrorMessages(), actual.getErrorMessages());
        }
        @Test
        void createFailNullMoodType() {
            Reply reply = TestHelper.makeReply();
            reply.setMoodId(0);
            reply.setMoodViceId(2);
            when(userRepository.findByUserId(reply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.addReply(reply)).thenReturn(null);
            ReplyResult result = new ReplyResult();
            result.addErrorMessage("MoodType is required..");
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.create(reply);
            assertEquals(result.getErrorMessages(), actual.getErrorMessages());
        }
    }


    @Nested class updateTests {
        @Test
        void update() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("NewTitle");
            updatedReply.setBody("NewBody");
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.updateReply(updatedReply)).thenReturn(true);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result, actual);
        }

        @Test
        void updateFailNoMoodVice() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("NewTitle");
            updatedReply.setBody("NewBody");
            updatedReply.setMoodViceId(0);
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.updateReply(updatedReply)).thenReturn(false);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void updateFailNullMoodType() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("NewTitle");
            updatedReply.setBody("NewBody");
            updatedReply.setMoodId(0);
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.updateReply(updatedReply)).thenReturn(false);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void updateFailNullBody() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("NewTitle");
            updatedReply.setBody("");
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.updateReply(updatedReply)).thenReturn(false);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void updateFailNullTitle() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("");
            updatedReply.setBody("NewBody");
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(TestHelper.makeUserExisting());
            when(repository.updateReply(updatedReply)).thenReturn(false);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            result.setResultType(ResultType.INVALID);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
        @Test
        void updateFailNullUser() {
            Reply updatedReply = TestHelper.ReplyExisting();
            updatedReply.setTitle("NewTitle");
            updatedReply.setBody("NewBody");
            updatedReply.setUserId(0);
            when(userRepository.findByUserId(updatedReply.getUserId())).thenReturn(null);
            when(repository.updateReply(updatedReply)).thenReturn(false);
            ReplyResult result = new ReplyResult();
            result.setReply(updatedReply);
            result.setResultType(ResultType.NOT_FOUND);
            ReplyResult actual = service.update(updatedReply);
            assertEquals(result.getResultType(), actual.getResultType());
        }
    }
}