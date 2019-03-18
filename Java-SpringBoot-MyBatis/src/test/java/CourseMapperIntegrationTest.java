import com.example.domain.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;



@RunWith(SpringJUnit4ClassRunner.class)
public class CourseMapperIntegrationTest {

    @Autowired
    private UserMapper courseMapper;

    @Test
    public void exec() throws Exception {
        List courses = courseMapper.findAll();
        //MatcherAssert.assertThat(courses, hasSize(2));
    }

}