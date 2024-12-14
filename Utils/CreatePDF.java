package Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreatePDF {
    public static void createPDF(String fileName, String content) {
        try {
            // Kiểm tra đường dẫn File đã chính xác chưa
            File dir = new File("../HoaDonPDF");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the full file path
            File fileOutput = new File(dir, fileName);

            // Create and write to the PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileOutput));
            document.open();
            document.add(new Paragraph(content));
            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            System.err.println("\tLỗi khi in file hoá đơn: " + e.getMessage());
        }
    }
}
