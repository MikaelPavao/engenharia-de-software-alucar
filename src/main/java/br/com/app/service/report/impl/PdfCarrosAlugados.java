package br.com.app.service.report.impl;

import br.com.app.entity.Carro;
import br.com.app.service.report.PDF;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.util.Assert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfCarrosAlugados implements PDF {
    private Document document;
    private final String CAMINHO_ARQUIVO = "C:\\Users\\pimen\\IdeaProjects\\engenharia-de-software-alucar\\src\\main\\java\\br\\com\\app\\service\\report\\";

    private final Font NORMAL_UNDERLINE = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD | Font.UNDERLINE);
    private Carro carro;

    @Override
    public Document createDocument() {
        this.document = new Document(PageSize.A4);

        this.document.addCreationDate();
        this.document.addCreator("Mikael pavão");
        this.document.addTitle("Lista Carros alugados");
        this.document.addAuthor("MK Pavão");
        this.document.setMargins(30, 30, 20, 20);
        this.document.setMarginMirroring(Boolean.TRUE);
        return this.document;
    }

    public void buildHeader() {

    }

    public void buildBody() throws DocumentException {

        this.document.add(getParagraphWithUnderline("Teste sublinhado"));
        this.document.add(getParagraphWithUnderline("Outro teste"));
    }

    public String getNameFile() {

        return String.format("%s.%s", carro.getPlaca(), "pdf");
    }


    @Override
    public void addContent(Carro carro) {
        this.carro = carro;
    }

    @Override
    public void buildContent() throws FileNotFoundException, DocumentException {
        PdfWriter.getInstance(createDocument(), new FileOutputStream(CAMINHO_ARQUIVO + getNameFile()));

        this.document.open();
        buildHeader();
        buildBody();

    }

    @Override
    public void buildReport() {
        Assert.state(this.document.getPageNumber() == 0, "Documento não possui paginas");
        Assert.notNull(this.document, "Pdf não pode ser gerado, pois não foi criado");
        this.document.close();
    }

    private Paragraph getParagraphWithUnderline(String texto) {
        StringBuilder text = new StringBuilder(texto);
        float width = this.document.getPageSize().getWidth() - (this.document.leftMargin() + this.document.rightMargin());

        while (text.length() <= width) {
            text.append(" ");
        }
        Paragraph paragraph = new Paragraph(text.toString(), NORMAL_UNDERLINE);

        paragraph.setIndentationLeft(this.document.leftMargin());
        paragraph.setIndentationRight(this.document.rightMargin());
        paragraph.setSpacingBefore(this.document.topMargin() + 10f);
        paragraph.setLeading(0f);

        return paragraph;
    }

}
