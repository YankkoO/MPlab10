package uo.mp.lab09.newsstand.domain;

public class Newspaper extends Publication {

    public Newspaper(String name, int stock, int sales) {
        super(name, stock, sales);
    }

    /**
     * Depending on many factors, a new order is created
     */
    @Override
    public Order generateOrder() {
        String name = this.getName();
        int quantity = this.getSales() + (2 * this.getStock());
        return new Order(name, quantity);
    }

    @Override
    public String serialize() {
		return "newspaper" + "\t" + getName() + "\t" + getStock() + "\t" + getSales();
    }
}
