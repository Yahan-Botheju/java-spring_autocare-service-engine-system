package lk.autocare.vehicle_service_system.infrastructure.customer.persistence;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.jpa.JpaCustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.mapper.CustomerPersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    //inject jpa repo
    private final JpaCustomerRepository jpaCustomerRepository;

    //inject customer persistence mapper
    private final CustomerPersistenceMapper customerPersistenceMapper;

    //get all customers
    @Override
    public List<Customer> getAllCustomers(){
        //get all from db, through mapper turn into domain model
        return jpaCustomerRepository.findAll().stream()
                .map(customerPersistenceMapper::toDomainModel).toList();
    }

    //save new customer
    @Override
    public void saveCustomer(Customer customer){
        //turn domain model to entity
        CustomerEntity entity = customerPersistenceMapper.toEntity(customer);
        //save in db
        jpaCustomerRepository.save(entity);
    }

    //update customer
    @Override
    public void updateCustomer(Long customerId, Customer customer){
        CustomerEntity currentEntity = jpaCustomerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Invalid customer id"));

        //use just updated fields only method
       CustomerEntity updatedEntity =  customerPersistenceMapper
               .updateEntityWithNewDomainModel(customer, currentEntity);
       //save in db
       jpaCustomerRepository.save(updatedEntity);
    }
}
