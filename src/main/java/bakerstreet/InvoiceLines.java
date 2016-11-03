package bakerstreet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceLines {

    public final List<InvoiceLine> invoiceLines;

    public InvoiceLines(InvoiceLine... invoiceLines) {
        this.invoiceLines = Arrays.stream(invoiceLines).collect(Collectors.toList());
    }

}
