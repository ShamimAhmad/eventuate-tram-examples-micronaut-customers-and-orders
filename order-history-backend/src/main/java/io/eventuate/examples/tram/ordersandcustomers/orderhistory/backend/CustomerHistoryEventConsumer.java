package io.eventuate.examples.tram.ordersandcustomers.orderhistory.backend;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.CustomerCreatedEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomerHistoryEventConsumer {

  @Inject
  private OrderHistoryViewService orderHistoryViewService;

  public DomainEventHandlers domainEventHandlers() {
    return DomainEventHandlersBuilder
            .forAggregateType("io.eventuate.examples.tram.ordersandcustomers.customers.domain.Customer")
            .onEvent(CustomerCreatedEvent.class, this::customerCreatedEventHandler)
            .build();
  }

  private void customerCreatedEventHandler(DomainEventEnvelope<CustomerCreatedEvent> domainEventEnvelope) {
    CustomerCreatedEvent customerCreatedEvent = domainEventEnvelope.getEvent();
    orderHistoryViewService.createCustomer(Long.parseLong(domainEventEnvelope.getAggregateId()),
            customerCreatedEvent.getName(), customerCreatedEvent.getCreditLimit());
  }
}
