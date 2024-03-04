import com.zxrail.app.report.utils.SnowFlakeIDGenerator;

/**
 * @Author: Li.Wang
 * Date: 2024/1/2 17:00
 */
// @SpringBootTest
// @RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {
    public static void main(String[] args) {
        System.out.println(SnowFlakeIDGenerator._nextId());
    }
}
