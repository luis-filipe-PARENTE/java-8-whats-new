package section2.java_8.streams.composing.functions;

import java.util.Date;
import java.util.Set;

public class Article {

  private String author;
  private Date date;
  private Set<String> tags;

  public Article() {};

  public Article(String author, Date date, Set<String> tags) {
    super();
    this.author = author;
    this.date = date;
    this.tags = tags;
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

  @Override
  public String toString() {
    return "Article [author=" + author + ", date=" + date + ", tags=" + tags + "]";
  }



}
