package com.experis.course.pizzeria.service;

import com.experis.course.pizzeria.exceptions.PizzaNotFoundException;
import com.experis.course.pizzeria.model.Offer;
import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.repository.OfferRepository;
import com.experis.course.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OfferService {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    OfferRepository offerRepository;

    public Offer createNewOffer(Pizza pizza) throws PizzaNotFoundException {
        Offer offer = new Offer();
        offer.setPizza(pizza);
        offer.setStartDate(LocalDate.now());
        offer.setEndDate(LocalDate.now().plusDays(7));
        return offer;
    }

    public Offer saveOffer(Offer offer){
        return offerRepository.save(offer);
    }

    public Offer getOffer(Integer id) throws PizzaNotFoundException{
        return offerRepository.findById(id).orElseThrow( () -> new PizzaNotFoundException("Offer with id " + id + " not found"));
    }

    public Offer deleteOffer(Integer id) throws PizzaNotFoundException{
        Offer offer = getOffer(id);
        offerRepository.delete(offer);
        return offer;
    }
}
