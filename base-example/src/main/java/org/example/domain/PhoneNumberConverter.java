package org.example.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

  @Override
  public String convertToDatabaseColumn(PhoneNumber attribute) {
    return attribute.value();
  }

  @Override
  public PhoneNumber convertToEntityAttribute(String dbData) {
    return new PhoneNumber(dbData);
  }
}
