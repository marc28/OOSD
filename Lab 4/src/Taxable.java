
public interface Taxable {
	double defaultTaxRate = .12;
	double electronicTaxRate = .1;
	double tvTaxRate = defaultTaxRate + .05;
	double plasticTaxRate = .17;
	
	String taxReturn(int id, int quantity);
}
