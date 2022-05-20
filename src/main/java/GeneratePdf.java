import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.Leading;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;


public class GeneratePdf{

    public static void gen2() throws FileNotFoundException, MalformedURLException {
        String imgPath = "./src/main/resources/watermark.jpg";
        System.out.println("Succesfull");
        String pdfPath= "PDFs/gen2.pdf";
        float width = 1023;
        float height=681;
        PdfWriter pdfWriter = new PdfWriter(pdfPath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        PdfPage pdfPage = pdfDocument.addNewPage();
        PdfCanvas canvas = new PdfCanvas(pdfPage);
        Document document = new Document(pdfDocument, PageSize.A4);

        ImageData imageData = ImageDataFactory.create(imgPath);
        Image image = new Image(imageData);
        image.setFixedPosition((pdfDocument.getDefaultPageSize().getWidth()-width)/2, (pdfDocument.getDefaultPageSize().getHeight()-height)/2);
        image.setOpacity(0.3f);
        document.add(image);
        image = new Image(ImageDataFactory.create("./src/main/resources/visualization.png"));
        image.setWidth(30);
        image.setHeight(30);
        document.add(image);
        canvas.saveState()
                .setLineWidth(1)
                .setStrokeColor(ColorConstants.BLACK)
                .moveTo(0, 400 )
                .lineTo(500, 400)
                .stroke()
                .restoreState();

        System.out.println(pdfDocument.getDefaultPageSize().getHeight());
        System.out.println(pdfDocument.getDefaultPageSize().getWidth());

        String p = "Eduard Fraenkel (1888–1970) was a German classical scholar who served as Corpus Christi Professor of Latin at the University of Oxford from 1935 until 1953. Born to a family of assimilated Jews in the German Empire, he studied classics at the Universities of Berlin and Göttingen. He established his academic reputation in 1922 with the publication of a monograph on Plautus, a Roman comedian. In 1934, antisemitic legislation introduced by the Nazi Party forced him to seek refuge in England. He published a three-volume commentary in 1950 on Agamemnon by the Greek playwright Aeschylus (pictured), and a monograph in 1957 on the Roman poet Horace. Biographers place particular emphasis on the impact of his teaching at Oxford, where he led a weekly classical seminar that influenced the intellectual development of many undergraduates. The Hellenist Hugh Lloyd-Jones described Fraenkel as \"one of the most learned classical scholars of his time";
        p = p.repeat(2).toLowerCase();
        Paragraph paragraph = new Paragraph(p);
        document.add(paragraph);
        SolidLine line = new SolidLine(1f);
        line.setColor(ColorConstants.RED);
        LineSeparator ls = new LineSeparator(line);
        ls.setWidth(500);
        ls.setMarginTop(5);
        Table table = new Table(2);
        table.addHeaderCell(new Cell().add(new Paragraph("Item")));
        table.addHeaderCell(new Cell().add(new Paragraph("Price")));
        table.addCell(new Cell().add(new Paragraph("Rice")));
        table.addCell(new Cell().add(new Paragraph("200")));
        table.addCell(new Cell().add(new Paragraph("Juice")));
        table.addCell(new Cell().add(new Paragraph("40")));
        table.addFooterCell(new Cell().add(new Paragraph("Total Cost:")));
        table.addFooterCell(new Cell().add(new Paragraph("240")));

        document.add(table);
        document.add(ls);
        document.close();
    }
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        gen3();
    }

    public static void gen3() throws FileNotFoundException {
        String imgPath = "./src/main/resources/watermark1.png";
        System.out.println("Succesfull");
        String pdfPath= "PDFs/gen3.pdf";
        PdfWriter pdfWriter = new PdfWriter(pdfPath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);
        //Spacing between elements
//        document.setProperty(Property.LEADING, new Leading(Leading.MULTIPLIED, 2f));
        document.add(new Paragraph("Just some text"));
        float threecol=190f;
        float twocol=286f;
        float twocol150= twocol+150f;
        float twocolumnWidth[]={twocol150, twocol};
        float fullWidth[]={threecol*3};

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add(new Paragraph("No voice")).setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[]{twocol/2, twocol/2});
        nestedTable.addCell(new Cell().add(new Paragraph("Number:")).setBold().setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph("12345")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph("Date:")).setBold().setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph("24 Jan 2100")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Come here")).setBorder(Border.NO_BORDER));
        document.add(table);

        Border border = new SolidBorder(ColorConstants.GRAY, 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(border);
        document.add(divider);

        float eqcol = 570f/2;
        Table info = new Table(new float[]{eqcol, eqcol});
        Table billingInfo = new Table(1);
        billingInfo.addCell(new Cell().add(new Paragraph("Billing Information")).setFontSize(15).setBold().setBorder(Border.NO_BORDER));
        billingInfo.addCell(new Cell().add(new Paragraph("Company")).setBold().setBorder(Border.NO_BORDER));
        billingInfo.addCell(new Cell().add(new Paragraph("Secret Developers")).setBorder(Border.NO_BORDER));
        billingInfo.addCell(new Cell().add(new Paragraph("Name")).setBold().setBorder(Border.NO_BORDER));
        billingInfo.addCell(new Cell().add(new Paragraph("Secret Coder")).setBorder(Border.NO_BORDER));


        Table shoppingInfo = new Table(1);
        shoppingInfo.addCell(new Cell().add(new Paragraph("Shipping Information")).setFontSize(15).setBold().setBorder(Border.NO_BORDER));
        shoppingInfo.addCell(new Cell().add(new Paragraph("Name")).setBold().setBorder(Border.NO_BORDER));
        shoppingInfo.addCell(new Cell().add(new Paragraph("Software")).setBorder(Border.NO_BORDER));
        shoppingInfo.addCell(new Cell().add(new Paragraph("Address")).setBold().setBorder(Border.NO_BORDER));
        shoppingInfo.addCell(new Cell().add(new Paragraph("114/6 south teikerdom street, borishal street, 7th building north of holmes, dhaka district, bangladesh")).setBorder(Border.NO_BORDER));

        info.addCell(new Cell().add(billingInfo).setBorder(Border.NO_BORDER));
        info.addCell(new Cell().add(shoppingInfo).setBorder(Border.NO_BORDER));

        document.add(info);

        Table address = new Table(new float[]{eqcol-40});
        address.addCell(new Cell().add(new Paragraph("Address")).setBold().setBorder(Border.NO_BORDER));
        address.addCell(new Cell().add(new Paragraph("1515 2nd building north of easterbrook of himalaya, racoon city, tustakon, Iceland, south of greenland")).setBorder(Border.NO_BORDER));
        address.addCell(new Cell().add(new Paragraph("Email")).setBold().setBorder(Border.NO_BORDER));
        address.addCell(new Cell().add(new Paragraph("blackbookfakeemail@fakeemail.com")).setBorder(Border.NO_BORDER));
        document.add(address);

        Border border2 = new SolidBorder(ColorConstants.GRAY, 0.2f);
        Table divider2 = new Table(fullWidth);
        divider2.setBorder(border2);
        document.add(divider2);

        Paragraph p2 = new Paragraph("Products").setSpacingRatio(2F);
        document.add(p2.setFontSize(15f));

        Table priceTable = new Table(new float[]{threecol, threecol, threecol});
        priceTable.addHeaderCell(new Cell().add(new Paragraph("Description").setTextAlignment(TextAlignment.LEFT)).setBackgroundColor(ColorConstants.BLACK, 0.9f).setFontColor(ColorConstants.WHITE).setBorder(Border.NO_BORDER));
        priceTable.addHeaderCell(new Cell().add(new Paragraph("Quantity").setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(ColorConstants.BLACK, 0.9f).setFontColor(ColorConstants.WHITE).setBorder(Border.NO_BORDER));
        priceTable.addHeaderCell(new Cell().add(new Paragraph("Price").setTextAlignment(TextAlignment.RIGHT)).setBackgroundColor(ColorConstants.BLACK, 0.9f).setFontColor(ColorConstants.WHITE).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("Hand Sanitizer").setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("2").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("40").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("Iphone 13").setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("1").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("170000").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("GPU").setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("2").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));
        priceTable.addCell(new Cell().add(new Paragraph("80000").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        priceTable.addCell(new Cell().add(new Paragraph("").setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(ColorConstants.GRAY, 0.3f)));
        priceTable.addCell(new Cell().add(new Paragraph("Total").setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(ColorConstants.GRAY, 0.3f)));
        priceTable.addCell(new Cell().add(new Paragraph("500000").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER).setBorderTop(new SolidBorder(ColorConstants.GRAY, 0.3f)));


        document.add(priceTable);


        document.close();
    }
}