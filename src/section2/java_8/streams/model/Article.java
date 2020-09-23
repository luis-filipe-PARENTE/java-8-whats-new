package section2.java_8.streams.model;

import java.util.Date;
import java.util.Set;

public class Article {

  private String author;
  private Date date;
  private Set<String> tags;
  private int id;

  public Article() {};

  public Article(String author, Date date, Set<String> tags, int id) {
    super();
    this.author = author;
    this.date = date;
    this.tags = tags;
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Article [author=" + author + ", date=" + date + ", tags=" + tags + "]";
  }



}
