package com.restaurant.service.courier;

import com.restaurant.dto.DtoCourier;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.model.Courier;
import com.restaurant.model.Customer;
import com.restaurant.model.Pay;
import com.restaurant.repository.CourierRepository;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.repository.PayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    private final CourierRepository courierRepository;
    private final CustomerRepository customerRepository;
    private final PayRepository payRepository;

    public CourierService(CourierRepository courierRepository, CustomerRepository customerRepository, PayRepository payRepository) {
        this.courierRepository = courierRepository;
        this.customerRepository = customerRepository;
        this.payRepository = payRepository;
    }

    public String courierProcess(String username) throws InterruptedException {

        Courier courier = new Courier();
        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isPresent()) {

            List<Pay> pays = payRepository.findByCustomer_Username(username);
            courier.setCustomer(customer.get());
            courier.setPrice(pays.get(0).getPrice());

            for(int i = 0; i < 3 ; i++) {

                courier.setTime(i);

                if(i == 0){
                    courier.setTime(i);
                    courier.setDelivered(false);
                    courierRepository.save(courier);
                    Thread.sleep(1000);
                }
                else if (i == 1){
                    courier.setTime(i);
                    courier.setDelivered(false);
                    courierRepository.save(courier);
                    Thread.sleep(1000);
                }
                else if (i == 2){
                    courier.setTime(i);
                    courier.setDelivered(true);
                    courierRepository.save(courier);
                    Thread.sleep(1000);
                    return "Courier is delivered";
                }
            }

            return "Courier process is failed";
        }

            return "Courier process is failed";
    }

    public List<DtoCourier> getCourierByUsername(String username){

        Optional<Customer> customer = customerRepository.findByUsername(username);
        List<Courier> couriers = courierRepository.findByCustomer_Username(username);

        List<DtoCourier> dtoCouriers = couriers.stream().map(courier -> {
            DtoCourier dtoCourier = new DtoCourier();
            dtoCourier.setCourierId(courier.getCourierId());
            dtoCourier.setPrice(courier.getPrice());
            dtoCourier.setTime(courier.getTime());
            dtoCourier.setDelivered(courier.isDelivered());

            DtoCustomer dtoCustomer = new DtoCustomer();
            dtoCustomer.setUsername(customer.get().getUsername());
            dtoCourier.setCustomer(dtoCustomer);

            return dtoCourier;
        }).toList();

        return dtoCouriers;


    }

}
