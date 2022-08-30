package hello.servlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려
public class MemberRepository {
	private static Map<Long, Member> store = new HashMap<>();
	private static Long sequence = 0L;
	//싱글톤 설계
	private static final MemberRepository instance = new MemberRepository();

	public static MemberRepository getInstance(){
		return instance;
	}
	private MemberRepository() {
	}
	
	public Member save(Member member){
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id){
		return store.get(id);
	}
	
	public List<Member> findAll(){
		//저장소 보호를 위해 Map의 값을 받아서 ArrayList에 담아서 반환
		return new ArrayList<>(store.values());
	}

	public void clearStore(){
		store.clear();
	}
}
