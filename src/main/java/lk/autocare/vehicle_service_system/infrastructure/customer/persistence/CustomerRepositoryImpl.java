package lk.autocare.vehicle_service_system.infrastructure.customer.persistence;

import lk.autocare.vehicle_service_system.domain.models.Customer;
import lk.autocare.vehicle_service_system.domain.models.Vehicle;
import lk.autocare.vehicle_service_system.domain.repositories.CustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.entity.CustomerEntity;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.jpa.JpaCustomerRepository;
import lk.autocare.vehicle_service_system.infrastructure.customer.persistence.mapper.CustomerPersistenceMapper;
import lk.autocare.vehicle_service_system.GlobalExceptionHandler.ResourceNotFoundException;
import lk.autocare.vehicle_service_system.web.vehicle.DTOs.VehicleResponseDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

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
    public Customer saveCustomer(Customer customer){
        //turn domain model to entity
        CustomerEntity entity = customerPersistenceMapper.toEntity(customer);

        //save in db
        jpaCustomerRepository.save(entity);

        //return entity by turning into domain model
        return customerPersistenceMapper.toDomainModel(entity);
    }

    //update customer
    @Override
    public Customer updateCustomer(Long customerId, Customer customer){
        //check customer by id
        CustomerEntity currentEntity = jpaCustomerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Customer Id" + " " +customerId));

        //use just updated fields only method
       CustomerEntity updatedEntity =  customerPersistenceMapper
               .updateEntityWithNewDomainModel(customer, currentEntity);
       //save in db
       jpaCustomerRepository.save(updatedEntity);

        //return updated data as domain model
        return customerPersistenceMapper.toDomainModel(updatedEntity);
    }

    //delete customer
    @Override
    public Customer deleteCustomer(Long customerId){
        //check customer availability
        CustomerEntity customerEntity = jpaCustomerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Customer Id" + " " +customerId));

        //then delete customer
        jpaCustomerRepository.deleteById(customerId);

        //turn taken customer data into domain mode then return
        return customerPersistenceMapper.toDomainModel(customerEntity);
    }

    //find customer by id, turn into domain model
    @Override
    public Optional<Customer> findById(Long customerId){
        return jpaCustomerRepository.findById(customerId)
                .map(customer -> customerPersistenceMapper.toDomainModel(customer));
    }
}
