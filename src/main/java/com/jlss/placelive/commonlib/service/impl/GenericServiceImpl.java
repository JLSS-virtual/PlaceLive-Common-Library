package com.jlss.placelive.commonlib.service.impl;


import com.jlss.placelive.commonlib.specification.impl.GenericSpecification;
import com.jlss.placelive.commonlib.specification.impl.SearchCriteria;
import com.jlss.placelive.commonlib.enums.ErrorCode;
import com.jlss.placelive.commonlib.exceptions.BadRequestException;
import com.jlss.placelive.commonlib.exceptions.NotFoundException;
import com.jlss.placelive.commonlib.service.GenericService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class GenericServiceImpl<T, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>> implements GenericService<T> {
    private static final Logger logger = Logger.getLogger(GenericServiceImpl.class.getName());

    protected final R repository;

    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    public void deleteObject(Integer id) {
        if (id == null || id <= 0) {
            throw new BadRequestException(ErrorCode.BAD0001.getCode(), ErrorCode.BAD0001.getMessage());
        }
        this.repository.deleteById(id.longValue());
        try {
            this.repository.deleteById(id.longValue());
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.ERR404.getCode(),ErrorCode.ERR404.getMessage());
        }
        logger.info("test");
    }


    @Override
    public T createObject(T object) {
        return repository.save(object);
    }

    @Override
    public T objectsIdPut(Integer id, T object) {
        // Ok we can do some more logic before updating the data like imp info and other mappings
        // like id should not be changed , and giving warnings before updating the qty and sku or category
        // so that user can freely edit the data .
        // but for now let oit be .
        if (id == null || id <= 0) {
            throw new BadRequestException(ErrorCode.BAD0001.getCode(), ErrorCode.BAD0001.getMessage());
        }


        repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException(ErrorCode.ERR404.getCode(), ErrorCode.ERR404.getMessage()));

        return repository.save(object);
    }

    @Override
    public T objectsIdGet(Integer id) {
        if (id == null || id <= 0) {
            throw new BadRequestException(ErrorCode.BAD0001.getCode(), ErrorCode.BAD0001.getMessage());
        }
        return repository.findById(Long.valueOf(id)).orElseThrow(() ->
                new NotFoundException(ErrorCode.ERR404.getCode(),ErrorCode.ERR404.getMessage()));
    }

    @Override
    public Page<T> getListOfObjects(Integer page, Integer size, String filter, String search) {
       // now  i have my source entity and target entity.
        //and also have the type of entity of objects gonna came
        // so now we are some steps behind to create the query and finding the data.
        // and returning it.
        // next test

        //step 1. combing filter with search.
        logger.info("Starting getListOfObjects method with parameters - Page: " + page + ", Size: " + size + ", Filter: " + filter + ", Search: " + search);

        String combinedQuery = Stream.of(filter,search)
               .filter(Objects::nonNull)
               .filter(s -> !s.isEmpty())
               .collect(Collectors.joining(","));
        logger.info("Combined Query: " + combinedQuery);
       //Step 2. creating a pattern acc to querying so it should be
        // key-operation-value
        Pattern pattern = Pattern.compile("([a-zA-Z0-9_]+)(:|!=|>|<)([^,]+)");
        // Step 3. initialising a mapper with the generated combinedQuery.
        Matcher matcher = pattern.matcher(combinedQuery);
        // Step 4. find the groups and respectively generate the builder query.
        Specification<T> specification = null;
        // test chnages
        while(matcher.find()){

            // Step 5. create the searchCriteria
            // and send it to the GenericSpecification to build the query.

            SearchCriteria searchCriteria = new SearchCriteria(
                    matcher.group(1), matcher.group(2), matcher.group(3)
            );
            logger.info("Parsed SearchCriteria: " + searchCriteria);
            // Step 6. send the searchCriteria object to the GenericSpecification

            GenericSpecification<T> genericSpecification = new GenericSpecification<>(searchCriteria);

            // Step 7. now adding the query conditions to specifications
            specification = specification==null?Specification.where(genericSpecification):specification.and(genericSpecification);
        }
        // step 8. call the pagination logic methode with specification query that were generated.
        logger.info("Final Specification ready for querying.");
        return getPaginatedData(page,size,specification);
    }

    public Page<T> getPaginatedData(Integer page, Integer size,Specification<T> spec) {
        logger.info("Fetching paginated data with Page: " + page + ", Size: " + size);
        Pageable pageable = PageRequest.of(page, size);
        Page<T> dataPage = repository.findAll(spec,pageable);
        if (dataPage.isEmpty()) {
            throw new NotFoundException(ErrorCode.ERR404.getCode(),ErrorCode.ERR404.getMessage());
        }
        logger.info("Data fetched successfully. Total Elements: " + dataPage.getTotalElements());
        return dataPage;
    }


}

