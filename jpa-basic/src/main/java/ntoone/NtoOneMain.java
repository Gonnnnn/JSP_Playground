package ntoone;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class NtoOneMain {
    public static void main(String[] args) {
        // application 실행시
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 하나의 연속된 행위를 할 때 마다
        EntityManager em = emf.createEntityManager();
        // 데이터를 변경하는 모든 작업은 트랜잭션이라는 단위 안에서 이루어진다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            MemberN member = new MemberN();
            member.setTeam(team);
            member.setUsername("Gon");
            em.persist(member);

            MemberN fm = em.find(MemberN.class, member.getId());
            Team team1 = fm.getTeam();
            System.out.println("team1 = " + team1.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
