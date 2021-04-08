package net.lecigne.codingkatas.ck0020;

public final class EagerSingletonField {

    private static boolean instantiated = false;

    private static final EagerSingletonField INSTANCE = new EagerSingletonField();

    @SuppressWarnings("squid:S3010")
    private EagerSingletonField() {
        // Updating static fields in the constructor is a code smell - this is for pedagogical purposes (see unit test)
        instantiated = true;
    }

    public static EagerSingletonField getInstance() {
        return INSTANCE;
    }

    public static boolean isInstantiated() {
        return instantiated;
    }
}
