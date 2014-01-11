package com.volkoval.jest.apache.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 11.01.14
 * Time: 18:35
 */
public class ConstructTokenStream {

    @Test
    public void constructTokenStream() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(
                    new SimpleFSDirectory(new File("index")),
                    new StandardAnalyzer(Version.LUCENE_30),
                    IndexWriter.MaxFieldLength.UNLIMITED);

            Document doc = new Document();
            doc.add(new Field("id", "1", Field.Store.YES, Field.Index.NO));
            doc.add(new Field("name", "Oleg", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("city", "Pavlov Posad", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("description", "He is living there.", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            String fullSearchableText = "Oleg Pavlov Posad He is living there";
            doc.add(new Field("content", fullSearchableText, Field.Store.NO, Field.Index.ANALYZED));
            indexWriter.addDocument(doc);
            indexWriter.commit();

            Analyzer analyzer = indexWriter.getAnalyzer();
            TokenStream tokenStream = analyzer.tokenStream("content", new Reader() {

                int i = 0;

                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {
                    if (i == 10) {
                        return -1;
                    }
                    return 'a' + i++;
                }

                @Override
                public void close() throws IOException {
                    //To change body of implemented methods use File | Settings | File Templates.
                }
            });

            while (tokenStream.incrementToken()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
