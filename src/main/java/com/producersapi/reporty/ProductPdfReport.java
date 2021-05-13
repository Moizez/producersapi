package com.producersapi.reporty;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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
import java.util.List;

public class ProductPdfReport {

    private static final Logger logger = LoggerFactory.getLogger(ProductPdfReport.class);
    private static ByteArrayOutputStream out = new ByteArrayOutputStream();

    public static ByteArrayInputStream ProductsReport(List<Product> products, boolean showProducers) {
    	
        try {
        	Document document = new Document();
    	
	        PdfWriter.getInstance(document, out);
	        document.open();
	        
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			Image img = Image.getInstance("logo.png");
			document.add(img);		
	        
			PdfPTable tableHeader = new PdfPTable(1);
	        tableHeader.setWidthPercentage(90);
	        tableHeader.setWidths(new int[]{1});
	        
	        PdfPCell hcell1;
	        hcell1 = new PdfPCell(new Phrase("Lista de Produtos", font));
	        hcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        tableHeader.addCell(hcell1);
	        document.add(tableHeader);
	        
	        if(!showProducers) {document.add(productsHeader());};
	        
            for (Product product : products) {
            	
            	if(showProducers) {document.add(productsHeader());};
            	
            	PdfPTable tableProducts = new PdfPTable(2);
            	tableProducts.setWidthPercentage(90);
                tableProducts.setWidths(new int[]{1, 3});
                PdfPCell cell;

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
                
                if(showProducers && product.getProducers().size() > 0) {
                	
                	PdfPTable tableProducers = new PdfPTable(3);
                	tableProducers.setWidthPercentage(90);
                	tableProducers.setWidths(new int[]{3, 3, 3});
                    
                	PdfPCell hcell;
                    hcell = new PdfPCell(new Phrase("Nome", font));
                    hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableProducers.addCell(hcell);
                    
                    hcell = new PdfPCell(new Phrase("e-mail", font));
                    hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableProducers.addCell(hcell);
                    
                    hcell = new PdfPCell(new Phrase("Telefone", font));
                    hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tableProducers.addCell(hcell);
                    
                    document.add(tableProducers);
                    
                	for (Producer producer : product.getProducers()) {
                		
                		tableProducers = new PdfPTable(3);
                    	tableProducers.setWidthPercentage(90);
                    	tableProducers.setWidths(new int[]{3, 3, 3});

                        cell = new PdfPCell(new Phrase(producer.getName().toString()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tableProducers.addCell(cell);

                        cell = new PdfPCell(new Phrase(producer.getEmail()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tableProducers.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(producer.getPhone()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tableProducers.addCell(cell);
                        
                        document.add(tableProducers);
                	}

                }

            }
            
            document.close();

        } catch (Exception ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private static PdfPTable productsHeader() throws Exception {
    	Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.NORMAL, BaseColor.WHITE);
    	
        PdfPTable tableProducts = new PdfPTable(2);
        tableProducts.setWidthPercentage(90);
        tableProducts.setWidths(new int[]{1, 3});

        PdfPCell hcell = new PdfPCell(new Phrase("-", headFont));
        hcell.setColspan(2);
        tableProducts.addCell(hcell);

        hcell.setColspan(1);
        
        hcell = new PdfPCell(new Phrase("Id", headFont));
        hcell.setBackgroundColor(BaseColor.BLACK);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableProducts.addCell(hcell);
        
        hcell = new PdfPCell(new Phrase("Name", headFont));
        hcell.setBackgroundColor(BaseColor.BLACK);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableProducts.addCell(hcell);
        
        return tableProducts;
    }
}