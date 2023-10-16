package com.company.jersey01.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Data
@Entity
@Table(name = "donors")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationEntity extends AbstractEntity {

  @ManyToOne()
  @JoinColumn(name = "donor_id")
  protected DonorEntity donor;

  @ManyToOne()
  @JoinColumn(name = "charity_id")
  protected CharityEntity charity;

  @Column(name = "amount")
  protected float amount;

  // The Lombok @AllArgsConstructor doesn't include superclass fields, so we need this
  public DonationEntity(Long id, DonorEntity donor, CharityEntity charity, float amount) {
    this.id = id;
    this.donor = donor;
    this.charity = charity;
    this.amount = amount;
  }
}
