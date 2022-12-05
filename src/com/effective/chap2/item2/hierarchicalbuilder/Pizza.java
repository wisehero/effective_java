package com.effective.chap2.item2.hierarchicalbuilder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
	final Set<Topping> toppings;

	Pizza(Builder<?> builder) {
		toppings = builder.toppings.clone();
	}

	public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

	abstract static class Builder<T extends Builder<T>> {
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}

		abstract Pizza build();

		protected abstract T self();
	}
}
