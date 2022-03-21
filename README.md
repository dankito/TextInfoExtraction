# TextInfoExtractor
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.dankito.text.extraction/text-info-extractor/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.dankito.text.extraction/text-info-extractor)


Extract data from texts like Dates, Amounts of money, IBANs and BICs.


## Setup

Maven:
```
<dependency>
   <groupId>net.dankito.text.extraction</groupId>
   <artifactId>text-info-extractor</artifactId>
   <version>1.0.2</version>
</dependency>
```

Gradle:
```
dependencies {
  implementation("net.dankito.text.extraction:text-info-extractor:1.0.2")
}
```


## Usage

```java
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
```