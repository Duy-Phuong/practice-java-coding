package clone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DocumentBasket implements Cloneable {
  private String name;
  private List<Document> documents;

  public DocumentBasket() {

  }

  public DocumentBasket(String name, List<Document> documents) {
    this.name = name;
    this.documents = documents;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Document> getDocuments() {
    return documents;
  }

  @Override
  public String toString() {
    return "DocumentBasket{" +
        "name='" + name + '\'' +
        ", documents=" + documents +
        '}';
  }

  public void setDocuments(List<Document> documents) {
    this.documents = documents;
  }

  public Object clone() throws CloneNotSupportedException {
    DocumentBasket documentBasketEntity = (DocumentBasket) super.clone();
    List<Document> documents = new ArrayList<Document>();
    Iterator<Document> iterator = this.documents.iterator();
    while (iterator.hasNext()) {
      documents.add((Document) iterator.next().clone());
    }
    documentBasketEntity.setDocuments(documents);
    return documentBasketEntity;
  }
}
