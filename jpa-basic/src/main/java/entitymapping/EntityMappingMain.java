package entitymapping;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EntityMappingMain {
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
            User user = new User();
            user.setId(101L);
            user.setUsername("helloABC");

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
