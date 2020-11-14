package net.lecigne.codingkatas.ck0018.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {

    private static final EntityManagerFactory EMF;

    private JpaUtil() {
    }

    static {
        EMF = Persistence.createEntityManagerFactory("jpa-simple-dao");
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

}
