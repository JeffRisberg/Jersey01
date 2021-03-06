package com.company.jersey01.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Data
@Entity
@Table(name = "charities")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharityEntity extends AbstractDatedEntity {

  @Column(name = "name")
  protected String name;

  @Column(name = "ein")
  protected String ein;

  @Column(name = "website")
  protected String website;

  // The Lombok @AllArgsConstructor doesn't include superclass fields, so we need this
  public CharityEntity(Long id, String name, String ein, String website) {
    this.id = id;
    this.name = name;
    this.ein = ein;
    this.website = website;
  }
}
