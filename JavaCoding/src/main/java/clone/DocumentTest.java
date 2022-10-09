package clone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class DocumentTest {

  public static void main(String[] args) throws CloneNotSupportedException {
    Document document1 = new Document("Phuong", UUID.randomUUID());
    Document document2 = new Document("Phuong2", UUID.randomUUID());

    ArrayList<Document> documents = new ArrayList<Document>(Arrays.asList(document1, document2));

    // print ArrayList
    DocumentBasket documentBasket = new DocumentBasket("Document", documents);

    DocumentBasket newDocumentBasket = (DocumentBasket) documentBasket.clone();
    newDocumentBasket.setName("new doc");
    newDocumentBasket.getDocuments().get(0).setName("new name");
    newDocumentBasket.getDocuments().get(0).setUuid(UUID.randomUUID());

    System.out.println("Before: ");
    System.out.println(documentBasket);
    System.out.println("After: ");
    System.out.println(newDocumentBasket);
  }
}
