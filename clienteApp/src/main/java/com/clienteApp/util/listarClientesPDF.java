package com.clienteApp.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.clienteApp.models.entity.Cliente;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/views/clientes/listar")
public class listarClientesPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Cliente> listadoCliente = (List<Cliente>) model.get("clientes");
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 40, 20);
		document.open();
		
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		
		Font fuenteTitulo = FontFactory.getFont("Arial", 12, Color.BLUE);
		Font fuenteTituloColumnas = FontFactory.getFont("Arial", 12, Color.BLUE);
		Font fuenteDataCeldas = FontFactory.getFont("Arial", 12, Color.BLACK);
		
		
		/* TABLA PARA EL TITULO DEL PDF */
		celda = new PdfPCell(new Phrase("LISTADO GENRAL DE CLIENTES", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(73, 196, 36));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
				
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(10);
		
		/* TABLA PARA MOSTRAR EL LISTADO DE CLIENTES */				
		PdfPTable tablaCliente = new PdfPTable(6);
		tablaCliente.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f});
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRES", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDOS", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		celda = new PdfPCell(new Phrase("TELEFONO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		celda = new PdfPCell(new Phrase("CIUDAD", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaCliente.addCell(celda);
		
		/* BUCLE FOR, MOSTRAR LOS DATOS DE LOS CLIENTES */
		for(Cliente cliente : listadoCliente) {
			celda = new PdfPCell(new Phrase(cliente.getId().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getNombres(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getApellidos(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getTelefono(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getEmail(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
			
			celda = new PdfPCell(new Phrase(cliente.getCiudad().getCiudad().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaCliente.addCell(celda);
		}
		
		
		/*
		 * listadoCliente.forEach(cliente ->{
		 * tablaCliente.addCell(cliente.getId().toString());
		 * tablaCliente.addCell(cliente.getNombres());
		 * tablaCliente.addCell(cliente.getApellidos());
		 * tablaCliente.addCell(cliente.getTelefono());
		 * tablaCliente.addCell(cliente.getEmail());
		 * tablaCliente.addCell(cliente.getCiudad().getCiudad().toString()); });
		 */
		document.add(tablaTitulo);
		document.add(tablaCliente);
		
	}

}
