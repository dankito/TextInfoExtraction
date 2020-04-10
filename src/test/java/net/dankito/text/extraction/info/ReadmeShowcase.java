package net.dankito.text.extraction.info;

import net.dankito.text.extraction.info.bank.BicExtractor;
import net.dankito.text.extraction.info.bank.IBicExtractor;
import net.dankito.text.extraction.info.bank.IIbanExtractor;
import net.dankito.text.extraction.info.bank.IbanExtractor;
import net.dankito.text.extraction.info.invoice.IInvoiceDataExtractor;
import net.dankito.text.extraction.info.invoice.InvoiceDataExtractor;
import net.dankito.text.extraction.info.model.AmountOfMoney;
import net.dankito.text.extraction.info.model.DateData;
import net.dankito.text.extraction.info.model.InvoiceData;
import net.dankito.text.extraction.info.model.StringSearchResult;

import java.util.List;

public class ReadmeShowcase {

    public static void main(String[] args) {
        new ReadmeShowcase().showcase();
    }


    public void showcase() {
        IDateExtractor dateExtractor = new DateExtractor();
        List<DateData> foundDates = dateExtractor.extractDates("04/11/2020\n12/24/2019");

        IAmountExtractor amountExtractor = new AmountExtractor();
        List<AmountOfMoney> foundAmounts = amountExtractor.extractAmountsOfMoney("23.45 $\n678,90 €");

        IIbanExtractor ibanExtractor = new IbanExtractor();
        List<StringSearchResult> foundIbans = ibanExtractor.extractIbans("GB00 1234 5678 9012 3456 78");

        IBicExtractor bicExtractor = new BicExtractor();
        List<StringSearchResult> foundBics = bicExtractor.extractBics("CHASGB2LXXX");

        IInvoiceDataExtractor invoiceDataExtractor = new InvoiceDataExtractor();
        InvoiceData extractedInvoiceData = invoiceDataExtractor.extractInvoiceData("04/11/2020\nItem 1 23.45 $\n" +
                "Item 2 678.90 $\nTotal 702.35 $\nNot existing bank CHASGB2LXXX GB00123456789012345678");
    }
}
