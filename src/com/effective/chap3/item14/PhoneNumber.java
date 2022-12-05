package com.effective.chap3.item14;

import static java.util.Comparator.*;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class PhoneNumber {
	private static final Comparator<PhoneNumber> COMPARATOR =
			comparingInt((PhoneNumber pn) -> pn.areaCode)
					.thenComparingInt(pn -> pn.prefix)
					.thenComparingInt(pn -> pn.lineNum);
	private final short areaCode, prefix, lineNum;

	public PhoneNumber(int areaCode, int prefix, int lineNum) {
		this.areaCode = rangeCheck(areaCode, 999, "area code");
		this.prefix = rangeCheck(prefix, 999, "prefix");
		this.lineNum = rangeCheck(lineNum, 9999, "line num");
	}

	private static short rangeCheck(int val, int max, String arg) {
		if (val < 0 || val > max)
			throw new IllegalArgumentException(arg + ": " + val);
		return (short)val;
	}

	private static PhoneNumber randomPhoneNumber() {
		Random rnd = ThreadLocalRandom.current();
		return new PhoneNumber((short)rnd.nextInt(1000),
				(short)rnd.nextInt(1000),
				(short)rnd.nextInt(10000));
	}

	public static void main(String[] args) {
		NavigableSet<PhoneNumber> s = new TreeSet<PhoneNumber>();
		for (int i = 0; i < 10; i++) {
			s.add(randomPhoneNumber());
		}
		System.out.println(s);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof com.effective.chap3.item11.PhoneNumber))
			return false;

		PhoneNumber pn = (PhoneNumber)o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}

	@Override
	public int hashCode() {
		int result = Short.hashCode(areaCode);
		result = 31 * result + Short.hashCode(prefix);
		result = 31 * result + Short.hashCode(lineNum);
		return result;
	}

	@Override
	public String toString() {
		return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
	}

	public int compareTo(PhoneNumber pn) {
		return COMPARATOR.compare(this, pn);
	}
}
