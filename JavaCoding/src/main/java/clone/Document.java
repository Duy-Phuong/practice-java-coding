package clone;

import java.util.UUID;

public class Document implements Cloneable {
  private String name;
  private UUID uuid;

  @Override
  public String toString() {
    return "Document{" +
        "name='" + name + '\'' +
        ", uuid=" + uuid +
        '}';
  }

  public Document() {

  }

  public Document(String name, UUID uuid) {
    this.name = name;
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Object clone() throws CloneNotSupportedException {
    return (Document) super.clone();
  }
}
