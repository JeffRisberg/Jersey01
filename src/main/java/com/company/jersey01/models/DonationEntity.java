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
  protected CharityEntity charityEntity;

  @Column(name = "amount")
  protected float amount;
}
