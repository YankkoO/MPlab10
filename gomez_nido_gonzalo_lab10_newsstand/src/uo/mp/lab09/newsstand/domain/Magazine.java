package uo.mp.lab09.newsstand.domain;

import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

public class Magazine extends Publication {

	public static final int MIN_ORDER = 20;

	public static enum Frequency {
		WEEKLY, MONTHLY, QUARTERLY;

		public static List<String> getNames() {
			List<String> result = new ArrayList<>();
			result.add("weekly");
			result.add("monthly");
			result.add("quarterly");
			return result;
		}
	}

	private Frequency frequency;

	/**
	 * Public magazine constructor
	 * @param name name of the magazine
	 * @param stock quantity left in the stand
	 * @param sales amount of sold magazines
	 * @param freq how frequent is the magazine released
	 */
	public Magazine(String name, int stock, int sales, Frequency freq) {
		super(name, stock, sales);
		setFrequency(freq);
	}

	private void setFrequency(Frequency freq) {
		ArgumentChecks.isNotNull(freq, "Trying to create a new Magazine: Invalid null frequency");
		this.frequency = freq;
	}

	private Frequency getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return super.toString() + "\t" + getFrequency();
	}

	@Override
	public String serialize() {
		return "magazine" + "\t" + getName() + "\t" + getStock() + "\t" + getSales() + "\t" + getFrequency();
	}

	/**
	 * Depending on many factors, the necessary order its created
	 */
	@Override
	public Order generateOrder() {
		String name = this.getName();

		if (this.getStock() < Publication.MIN_STOCK) {
			return new Order(name, MIN_ORDER);
		} else {
			if (this.frequency.equals(Frequency.WEEKLY)) {
				return new Order(name, this.getSales());
			} else {
				return new Order(name, (this.getSales() + this.getStock()));
			}
		}

	}

}
