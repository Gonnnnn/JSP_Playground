package inflearnSpring.springCore.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

//    실제로는 동시성 이슈가 있을 수 있어서 concurrentHashMap을 써야할 것이지만, 단순하게 간다.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long MemberId) {
        return store.get(MemberId);
    }
}