package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // application 실행시
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 하나의 연속된 행위를 할 때 마다
        EntityManager em = emf.createEntityManager();
        // 데이터를 변경하는 모든 작업은 트랜잭션이라는 단위 안에서 이루어진다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("helloABC");

            //영속
            em.persist(member);

            //1차 캐시에 있을 것이므로 쿼리가 안나간다.
            Member member1 = em.find(Member.class, 101L);

            Member newMember = new Member();
            newMember.setId(1L);
            newMember.setName("newOne");
            em.persist(newMember);

            //SQL 저장소에 있던 쿼리들이 먼저 나가게 된다.
            em.flush();
            System.out.println("==========flushed==========");

            //컨텍스트를 비워버린다. 1차 캐시 등 모두 초기화
            em.clear();

            System.out.println("==========first try : find member w id1==========");
            //컨텍스트를 비웠었기 때문에 해당 엔티티는 컨텍스트에 존재 x 따라서 쿼리가 나간다.
            Member member2 = em.find(Member.class, 1L);
            System.out.println("==========second try : find member w id 1==========");
            //방금 1차 캐시에 저장한 엔티티이므로 쿼리가 안나간다.
            Member member3 = em.find(Member.class, 1L);

            //같은 엔티티의 동일성이 보장된다. 출력 결과는 true
            System.out.println("equal?: " + (member2 == member3));

            //JPQL
            List<Member> foundMembers = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();
            for (Member foundMember : foundMembers) {
                System.out.println("foundMember = " + foundMember);
            }

            //commit이 불리기 전까지는 쓰기 지연 SQL 저장소에 쿼리를 쌓아두었다가 불리고 나서 반영
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
