package net.lecigne.codingkatas.ck0020;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {

    @Test
    void eagerSingleton_shouldInstantiateAtClassLoading() {
        assertThat(EagerSingletonField.isInstantiated()).isTrue();
        assertThat(EagerSingletonStaticBlock.isInstantiated()).isTrue();
    }

    @Test
    void lazySingleton_shouldInstantiateWhenAskedForIt() {
        assertThat(LazySingletonNotThreadSafe.isInstantiated()).isFalse();
        LazySingletonNotThreadSafe.getInstance();
        assertThat(LazySingletonNotThreadSafe.isInstantiated()).isTrue();
    }

}
