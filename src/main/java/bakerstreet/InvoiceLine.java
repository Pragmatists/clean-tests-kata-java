package bakerstreet;

public class InvoiceLine {

    public final String product;
    public final PoundsShillingsPence value;

    public InvoiceLine(String product, PoundsShillingsPence value) {
        this.product = product;
        this.value = value;
    }

}
