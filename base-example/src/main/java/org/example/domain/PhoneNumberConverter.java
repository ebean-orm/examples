package org.example.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

  @Override
  public String convertToDatabaseColumn(PhoneNumber attribute) {
    return attribute.getValue();
  }

  @Override
  public PhoneNumber convertToEntityAttribute(String dbData) {
    return new PhoneNumber(dbData);
  }
}
