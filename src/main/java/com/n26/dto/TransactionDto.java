package com.n26.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.ToString
@AllArgsConstructor
public class TransactionDto {
   private String amount;
   private String timestamp; 
}
