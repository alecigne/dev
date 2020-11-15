package net.lecigne.codingkatas.ck0020;

public class EagerSingletonStaticBlock {

    private static boolean instantiated = false;

    private static EagerSingletonStaticBlock instance;

    @SuppressWarnings("squid:S3010")
    private EagerSingletonStaticBlock() {
        // Updating static fields in the constructor is a code smell - this is for pedagogical purposes (see unit test)
        instantiated = true;
    }

    static {
        try {
            instance = new EagerSingletonStaticBlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EagerSingletonStaticBlock getInstance() {
        return instance;
    }

    public static boolean isInstantiated() {
        return instantiated;
    }

}
