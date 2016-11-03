package $4_test_objects_creation;

import bakerstreet.*;
import org.junit.Test;

import java.util.Arrays;

import static $4_test_objects_creation.Builders.AddressBuilder.anAddress;
import static $4_test_objects_creation.Builders.InvoiceBuilder.anInvoice;
import static $4_test_objects_creation.Builders.InvoiceLineBuilder.anInvoiceLine;
import static $4_test_objects_creation.Builders.RecipientBuilder.aRecipient;
import static org.assertj.core.api.Assertions.assertThat;

public class Builders {

    @Test
    public void build_invoice() {
        Invoice invoice1 = new Invoice(
                new Recipient("Sherlock Holmes",
                        new Address("222b Baker Street",
                                "London",
                                new PostCode("NW1", "3RX"))),
                new InvoiceLines(
                        new InvoiceLine("Deerstalker Hat",
                                new PoundsShillingsPence(0, 3, 10)),
                        new InvoiceLine("Tweed Cape",
                                new PoundsShillingsPence(0, 4, 12))));
        assertThat(invoice1.lines.invoiceLines)
                .extracting(l -> l.product)
                .containsExactly("Deerstalker Hat", "Tweed Cape");

        Invoice invoice2 = TestInvoices.newDeerstalkerAndCapeAndSwordstickInvoice();
        assertThat(invoice2.lines.invoiceLines)
                .extracting(l -> l.product)
                .containsExactly("Deerstalker Hat", "Tweed Cape", "Sword Stick");

        Invoice invoice3 = TestInvoices.newDeerstalkerAndBootsInvoice();
        assertThat(invoice3.lines.invoiceLines)
                .extracting(l -> l.product)
                .containsExactly("Deerstalker Hat", "Boots");
    }

    @Test
    public void build_default_invoice() {
        Invoice anInvoice = new InvoiceBuilder().build();
        assertThat(anInvoice.lines.invoiceLines).isNotEmpty();
    }

    @Test
    public void build_invoice_with_no_postcode() {
        Invoice invoiceWithNoPostcode = anInvoice()
                .withRecipient(new RecipientBuilder()
                        .withAddress(new AddressBuilder()
                                .withNoPostcode()
                                .build())
                        .build())
                .build();

        assertThat(invoiceWithNoPostcode.recipient.address.postCode).isNull();
    }

    @Test
    public void build_invoice_with_nested_builders() {
        Invoice invoice = anInvoice()
                .withRecipient(aRecipient().withAddress(anAddress().withCity("London")))
                .withInvoiceLines(anInvoiceLine().withProduct("Boots"), anInvoiceLine().withProduct("Cape"))
                .build();

        assertThat(invoice.recipient.address.city).isEqualTo("London");
        assertThat(invoice.lines.invoiceLines.get(1).product).isEqualTo("Cape");
    }

    // Object Mother pattern
    public static class TestInvoices {

        public static Invoice newDeerstalkerAndCapeAndSwordstickInvoice() {
            return new Invoice(
                    new Recipient("Sherlock Holmes",
                            new Address("222b Baker Street",
                                    "London",
                                    new PostCode("NW1", "3RX"))),
                    new InvoiceLines(
                            new InvoiceLine("Deerstalker Hat",
                                    new PoundsShillingsPence(0, 3, 10)),
                            new InvoiceLine("Tweed Cape",
                                    new PoundsShillingsPence(0, 4, 12)),
                            new InvoiceLine("Sword Stick",
                                    new PoundsShillingsPence(0, 6, 9))));
        }

        public static Invoice newDeerstalkerAndBootsInvoice() {
            return new Invoice(
                    new Recipient("Sherlock Holmes",
                            new Address("222b Baker Street",
                                    "London",
                                    new PostCode("NW1", "3RX"))),
                    new InvoiceLines(
                            new InvoiceLine("Deerstalker Hat",
                                    new PoundsShillingsPence(0, 3, 10)),
                            new InvoiceLine("Boots",
                                    new PoundsShillingsPence(0, 5, 0))));
        }

    }

    public static class InvoiceBuilder {

        private Recipient recipient = aRecipient().build();

        private InvoiceLines lines = new InvoiceLines(anInvoiceLine().build());

        private PoundsShillingsPence discount = PoundsShillingsPence.ZERO;

        public static InvoiceBuilder anInvoice() {
            return new InvoiceBuilder();
        }

        public InvoiceBuilder withRecipient(Recipient recipient) {
            this.recipient = recipient;
            return this;
        }

        public InvoiceBuilder withInvoiceLines(InvoiceLineBuilder... lines) {
            this.lines = new InvoiceLines(
                    Arrays.stream(lines).map(InvoiceLineBuilder::build)
                            .toArray(InvoiceLine[]::new));
            return this;
        }

        public InvoiceBuilder withDiscount(PoundsShillingsPence discount) {
            this.discount = discount;
            return this;
        }

        public Invoice build() {
            return new Invoice(recipient, lines, discount);
        }

        public InvoiceBuilder withRecipient(RecipientBuilder recipientBuilder) {
            recipient = recipientBuilder.build();
            return this;
        }
    }

    public static class RecipientBuilder {

        private Address address = anAddress().build();

        private String name = "John H. Watson";

        public static RecipientBuilder aRecipient() {
            return new RecipientBuilder();
        }

        public Recipient build() {
            return new Recipient(name, address);
        }

        public RecipientBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public RecipientBuilder withAddress(AddressBuilder addressBuilder) {
            address = addressBuilder.build();
            return this;
        }
    }

    public static class AddressBuilder {

        private PostCode postCode = new PostCode("P1R", "0AX");

        private String street = "Larch Avenue";

        private String city = "Preston";

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withNoPostcode() {
            postCode = null;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(street, city, postCode);
        }
    }

    public static class InvoiceLineBuilder {
        private String product = "Default widget";

        private PoundsShillingsPence value = new PoundsShillingsPence(1, 0, 0);

        public static InvoiceLineBuilder anInvoiceLine() {
            return new InvoiceLineBuilder();
        }

        public InvoiceLineBuilder withProduct(String product) {
            this.product = product;
            return this;
        }

        public InvoiceLine build() {
            return new InvoiceLine(product, value);
        }
    }
}
