package Window;

import DBElements.Movie;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;

public class CreatePDF {
    String fileName = "movieList.pdf";
    Movie movie = new Movie();

    public String getFileName(){
        return fileName;
    }

    public void PDFcreate(ArrayList<Movie> movies) throws IOException {
        this.fileName = fileName;
        int textY=710;
        int fontSize=15;

        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, fontSize);
            content.moveTextPositionByAmount(230, 750);
            content.drawString("Movies metadata");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, fontSize);
            content.moveTextPositionByAmount(20, 730);
            content.drawString("movie_ID / name / year / rating / type / director / country");
            content.endText();

            for (int i = 0; i < movies.size(); i++){
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, fontSize);
                content.moveTextPositionByAmount(20, textY-(i*fontSize));
                content.drawString(String.valueOf(movies.get(i)));
                content.endText();
            }
            content.close();

            doc.save(fileName);
            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
