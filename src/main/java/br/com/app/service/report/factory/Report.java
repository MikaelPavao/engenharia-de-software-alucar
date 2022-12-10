package br.com.app.service.report.factory;

import br.com.app.entity.Carro;
import br.com.app.service.report.PDF;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public abstract class Report {

    public void buildPDF(Carro carro) throws DocumentException, FileNotFoundException {
        PDF pdf = createPDF();
        pdf.addContent(carro);
        pdf.buildContent();
        pdf.buildReport();
    }

    public abstract PDF createPDF();

}
