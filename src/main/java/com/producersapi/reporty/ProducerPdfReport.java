package com.producersapi.reporty;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.producersapi.model.Producer;
import com.producersapi.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProducerPdfReport {

    private static final Logger logger = LoggerFactory.getLogger(ProducerPdfReport.class);
    private static ByteArrayOutputStream out = new ByteArrayOutputStream();

    public static ByteArrayInputStream ProducersReport(List<Producer> prodcuers, boolean showProducts) {
    	
        try {
        	Document document = new Document();
    	
	        PdfWriter.getInstance(document, out);
	        document.open();
	        
	        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.NORMAL, BaseColor.WHITE);
	        
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			Image img = Image.getInstance("logo.png");
			document.add(img);		
	        
			PdfPTable tableHeader = new PdfPTable(1);
	        tableHeader.setWidthPercentage(90);
	        tableHeader.setWidths(new int[]{1});
	        
	        PdfPCell hcell1;
	        hcell1 = new PdfPCell(new Phrase("Lista de Produtores", font));
	        hcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tableHeader.addCell(hcell1);
	        document.add(tableHeader);
	        
            for (Producer producer : prodcuers) {
            	
                PdfPTable tableProducers = new PdfPTable(2);
                tableProducers.setWidthPercentage(90);
                tableProducers.setWidths(new int[]{1, 8});

                PdfPCell hcell = new PdfPCell(new Phrase(" ", font));
                hcell.setColspan(3);
                tableProducers.addCell(hcell);

                hcell.setColspan(1);
                
                hcell = new PdfPCell(new Phrase("ID#: "+producer.getId().toString(), headFont));
                hcell.setBackgroundColor(BaseColor.BLACK);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableProducers.addCell(hcell);
                
                hcell = new PdfPCell(new Phrase(producer.getName(), headFont));
                hcell.setBackgroundColor(BaseColor.BLACK);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableProducers.addCell(hcell);
                
                document.add(tableProducers);                
           	
            	tableProducers = new PdfPTable(4);
            	tableProducers.setWidthPercentage(90);
                tableProducers.setWidths(new int[]{1, 2, 2, 3});
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Apelido: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(producer.getNickname()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Data de Nasc.: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(""+producer.getBirthDate().toGMTString().substring(0, 10)));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase("CPF: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(producer.getCpf()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase("E-mail: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(producer.getEmail()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Telefone: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(producer.getPhone()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);

                cell = new PdfPCell(new Phrase("Qtd. Produtos: "));
                cell.setPaddingLeft(5);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                cell = new PdfPCell(new Phrase(""+producer.getProducts().size()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableProducers.addCell(cell);
                
                document.add(tableProducers);
                
                if(showProducts && producer.getProducts().size() > 0) {
                	
                	PdfPTable tableProducts = new PdfPTable(2);
                	tableProducts.setWidthPercentage(90);
                	tableProducts.setWidths(new int[]{3, 5});
                    
                    hcell = new PdfPCell(new Phrase("Id", font));
                    hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableProducts.addCell(hcell);
                    
                    hcell = new PdfPCell(new Phrase("Nome", font));
                    hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableProducts.addCell(hcell);
                    
                    document.add(tableProducts);
                    
                	for (Product product : producer.getProducts()) {
                		
                		tableProducts = new PdfPTable(2);
                    	tableProducts.setWidthPercentage(90);
                    	tableProducts.setWidths(new int[]{3, 5});

                        cell = new PdfPCell(new Phrase(product.getValue().toString()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tableProducts.addCell(cell);

                        cell = new PdfPCell(new Phrase(product.getLabel()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tableProducts.addCell(cell);
                        
                        document.add(tableProducts);
                	}

                }

            }
            
            document.close();

        } catch (Exception ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
}