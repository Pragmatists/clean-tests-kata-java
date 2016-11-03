package bakerstreet;

public class Invoice {

    public final Recipient recipient;
    public final InvoiceLines lines;
    public final PoundsShillingsPence discount;

    public Invoice(Recipient recipient, InvoiceLines invoiceLines) {
        this(recipient, invoiceLines, PoundsShillingsPence.ZERO);
    }

    public Invoice(Recipient recipient, InvoiceLines lines, PoundsShillingsPence discount) {
        this.recipient = recipient;
        this.lines = lines;
        this.discount = discount;
    }

}
