package com.n26.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.ToString
@AllArgsConstructor
public class Transactions {
   private double amount;
   private Date timestamp; 
}
