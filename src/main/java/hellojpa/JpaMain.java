package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //비영속 상태
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            //영속 상태 : entityManager안에 있는 영속성 컨텍스트를 통해서 member가 관리가 되는 상태이다.

//            em.persist(member);
            //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            //영속 엔티티의 동일성 보장
            System.out.println("result = " + (findMember1 == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
