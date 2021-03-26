package com.nttdata;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Path("/invoice")
public class InvoiceServiceAPI {

    @Inject
    Logger LOG;

    private Set<Beer> beers = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TotalSum invoiceBeerCalculation(List<Beer> beerList) {

        LOG.info("invoiceBeerCalculation(List<Beer> beerList) was called.");

        for(Beer beer:beerList){
            beers.add(beer);
        }

        double sum = 0;
        for(Beer beer:beerList){
            sum = sum + (beer.price + (beer.price * (beer.vat/100)));
        }

        return new TotalSum(sum);
    }


    public static class Beer {
        public String kind;
        public double price;
        public double vat;

        public Beer() {
        }

        public Beer(String kind, double price, double vat) {
            this.kind = kind;
            this.price = price;
            this.vat = vat;
        }
    }

    public static class TotalSum {
        public double totalSum;

        public TotalSum(double totalSum) {
            this.totalSum = totalSum;
        }
    }

}
