package br.com.app.service.report.factory;

import br.com.app.service.report.PDF;
import br.com.app.service.report.impl.PdfCarrosAlugados;

public class AluguelCarrosReport extends Report{

     public PDF createPDF() {
        return new PdfCarrosAlugados();
    }
}
