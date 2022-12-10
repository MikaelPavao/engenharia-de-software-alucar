package br.com.app.service.report;

import br.com.app.entity.Carro;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public interface PDF {
    Document createDocument();

    void addContent(Carro carro);

    void buildContent() throws FileNotFoundException, DocumentException;

    void buildReport();
}
