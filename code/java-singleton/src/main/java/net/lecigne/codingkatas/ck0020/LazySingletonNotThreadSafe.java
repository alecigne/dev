package net.lecigne.codingkatas.ck0020;

import java.util.Objects;

public final class LazySingletonNotThreadSafe {

	private static boolean instantiated = false;

	private static LazySingletonNotThreadSafe instance;

	@SuppressWarnings("squid:S3010")
	private LazySingletonNotThreadSafe() {
		// Updating static fields in the constructor is a code smell - this is for pedagogical purposes (see unit test)
		instantiated = true;
	}

	public static LazySingletonNotThreadSafe getInstance() {
		// Not thread-safe
		if (Objects.isNull(instance)) {
			instance = new LazySingletonNotThreadSafe();
		}
		return instance;
	}

	public static boolean isInstantiated() {
		return instantiated;
	}

}
