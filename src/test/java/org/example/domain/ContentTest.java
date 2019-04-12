package org.example.domain;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentTest {


  @Test
  public void insert() {

    Content content = new Content("hello");
    content.save();

    assertThat(content.getId()).isNotNull();
  }
}
