package com.effective.chap2.item2.hierarchicalbuilder;

public class Calzone extends Pizza {
	private final boolean sauceInside;

	private Calzone(Builder builder) {
		super(builder);
		sauceInside = builder.sauceInside;
	}

	@Override
	public String toString() {
		return String.format("Calzone with %s and sauce on the %s", toppings, sauceInside ? "inside" : "outside");
	}

	public static class Builder extends Pizza.Builder<Builder> {
		private boolean sauceInside = false;

		public Builder sauceInside() {
			sauceInside = true;
			return this;
		}

		@Override
		public Calzone build() {
			return new Calzone(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
}

