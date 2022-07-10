package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

//            어찌저찌 order를 하나 찾았다고 치자. 해당 order을 한 member를 찾고 싶은 상태이다.
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();
            Member member = em.find(Member.class, memberId);
//            객체지향스럽지 못하다. order.getMember()과 같은식으로 가져오는게 맞을듯

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
