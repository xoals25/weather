package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JpaMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional // 테스트에서 사용되는 트랜잭션은 커밋을 하지않고 다 롤백처리한다.
public class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){
        //given
        Memo newMemo = new Memo(10, "This is Jpa memo");

        //when
        jpaMemoRepository.save(newMemo);

        //then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size()>0);
    }

    @Test
    void findByIdTest(){
        //given
        Memo newMemo = new Memo(11, "Jpa memo");

        //when
        Memo memo = jpaMemoRepository.save(newMemo);

        //then
        Optional<Memo> optionalMemo = jpaMemoRepository.findById(memo.getId());
        assertEquals(optionalMemo.get().getText(), "Jpa memo");
    }
}
