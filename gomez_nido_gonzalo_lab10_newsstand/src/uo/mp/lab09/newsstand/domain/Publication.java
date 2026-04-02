package uo.mp.lab09.newsstand.domain;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

public abstract class Publication {
	
	protected final static int MIN_STOCK = 5;
    private String name;
    private int stock;
    private int sales;

    /**
     * Constructor of the superclass Publication
     * @param name name of the publication
     * @param stock stock left in the newsstand
     * @param sales number of objects sold
     */
	protected Publication(String name, int stock, int sales) {
		ArgumentChecks.isTrue(stock >= 0, "Trying to create a new Publication: Illegal stock under zero");
		ArgumentChecks.isTrue(sales >= 0, "Trying to create a new Publication: Illegal sales under zero");
		ArgumentChecks.isNotEmpty(name, "Trying to create a new Publication: Illegal name");

		this.stock = stock;
		this.sales = sales;
		this.name = name;
	}

	// Abstract methods to be implemented by subclasses
    public abstract Order generateOrder();
    public abstract String serialize();
    
    public String getName() {
        return name;
    }

    protected int getStock() {
        return stock;
    }

    protected int getSales() {
        return sales;
    }
    
    public boolean needsOrder() {
    	return (this.getStock() < MIN_STOCK);
    }
    

    @Override
    public String toString() {
        return getName() + "\t" + getStock() + "\t" + getSales();
    }

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		return Objects.equals(name, other.name);
	}
    
    

}
