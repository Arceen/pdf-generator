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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;


public class GeneratePdf{
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
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
        document.add(ls);
        document.close();
    }
}