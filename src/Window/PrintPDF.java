package Window;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class PrintPDF {
    public void printPDF(String docName) throws IOException {
        PDDocument document = PDDocument.load(new File(docName));
        try {
            printWithDialog(document);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    private static void printWithDialog(PDDocument document) throws PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        if (job.printDialog())
        {
            job.print();
        }
    }
}
