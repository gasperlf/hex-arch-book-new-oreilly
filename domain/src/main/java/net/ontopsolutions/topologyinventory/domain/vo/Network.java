package net.ontopsolutions.topologyinventory.domain.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Network {

    private final IP networkAddress;
    private final String networkName;
    private final int networkCidr;

    public Network(IP networkAddress, String networkName, int networkCidr){

          if(networkCidr <1 || networkCidr>32){
               throw new IllegalArgumentException("Invalid CIDR value");
          }

          this.networkAddress = networkAddress;
          this.networkCidr = networkCidr;
          this.networkName = networkName;
     }

}
