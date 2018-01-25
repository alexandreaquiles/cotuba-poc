package cotuba.pdf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

import cotuba.domain.Capitulo;
import cotuba.domain.CotubaException;
import cotuba.domain.Ebook;
import cotuba.domain.GeradorDeEbook;

public class GeradorDePDF implements GeradorDeEbook {

	@Override
	public void gera(Ebook ebook) {
		Path pdfFile = ebook.getArquivoDeSaida();
		try {
			PdfWriter writer = new PdfWriter(Files.newOutputStream(pdfFile));
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);

			for (Capitulo capitulo : ebook.getCapitulos()) {
				List<IElement> convertToElements = HtmlConverter.convertToElements(capitulo.getConteudoHTML());
				for (IElement element : convertToElements) {
					document.add((IBlockElement) element);
				}
				if (capitulo != ebook.ultimoCapitulo()) {
					document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				}
			}
			document.close();
		} catch (Exception ex) {
			throw new CotubaException("Erro ao criar arquivo PDF: " + pdfFile.toAbsolutePath(), ex);
		}

	}

}
