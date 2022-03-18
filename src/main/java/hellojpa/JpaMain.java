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
//            addMember(em);
//            findMember(em);
//            removeMember(em);
//            updateMember(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }


    private static void addMember(EntityManager em) {
        Member member = new Member();
        member.setId(2L);
        member.setUsername("helloB");
        em.persist(member);
    }

    private static void findMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember.getId() = " + findMember.getId());
        System.out.println("findMember.getName() = " + findMember.getUsername());
    }

    private static void removeMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        em.remove(findMember);
    }

    private static void updateMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        findMember.setUsername("HelloChangeJPA");
    }


}
