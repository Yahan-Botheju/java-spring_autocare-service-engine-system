package lk.autocare.vehicle_service_system.infrastructure.customer.persistence;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.jpa.JpaCustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.mapper.CustomerPersistenceMapper;
import lk.autocare.vehicle_service_system.web.customer.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        //check customer by id
        CustomerEntity currentEntity = jpaCustomerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Customer Id" + " " +customerId));

        //use just updated fields only method
       CustomerEntity updatedEntity =  customerPersistenceMapper
               .updateEntityWithNewDomainModel(customer, currentEntity);
       //save in db
       jpaCustomerRepository.save(updatedEntity);
    }

    //delete customer
    @Override
    public void deleteCustomer(Long customerId){
        //check customer availability
        if(!jpaCustomerRepository.existsById(customerId)){
            throw new ResourceNotFoundException("Invalid customer id" + " " + customerId);
        }
        //then delete customer
        jpaCustomerRepository.deleteById(customerId);
    }

    //find customer by id, turn into domain model
    @Override
    public Optional<Customer> findById(Long customerId){
        return jpaCustomerRepository.findById(customerId)
                .map(customer -> customerPersistenceMapper.toDomainModel(customer));
    }
}
