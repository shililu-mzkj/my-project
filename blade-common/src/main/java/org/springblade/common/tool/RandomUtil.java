package org.springblade.common.tool;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {


	public static ThreadLocalRandom getRandom() {
		return ThreadLocalRandom.current();
	}

	public static String randomNumbers(int length) {
		return randomString("0123456789", length);
	}

	public static String randomString(String baseString, int length) {
		StringBuilder sb = new StringBuilder(length);
		if (length < 1) {
			length = 1;
		}

		int baseLength = baseString.length();

		for(int i = 0; i < length; ++i) {
			int number = randomInt(baseLength);
			sb.append(baseString.charAt(number));
		}

		return sb.toString();
	}

	public static int randomInt(int limit) {
		return getRandom().nextInt(limit);
	}
}
