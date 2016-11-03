package bakerstreet;

public class    PoundsShillingsPence {

    public static final PoundsShillingsPence ZERO = new PoundsShillingsPence(0, 0, 0);
    public final int pound;
    public final int shilling;
    public final int pence;

    public PoundsShillingsPence(int pound, int shilling, int pence) {
        this.pound = pound;
        this.shilling = shilling;
        this.pence = pence;
    }

}
