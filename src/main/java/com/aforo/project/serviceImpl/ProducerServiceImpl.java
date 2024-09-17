package com.aforo.project.serviceImpl;
import com.aforo.project.dto.ProducerDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.model.Producer;
import com.aforo.project.repository.ProducerRepository;
import com.aforo.project.service.ProducerService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository producerRepository;
    @Override
    @Transactional
    public ProducerDTO createProducer(ProducerDTO producerDTO) {
        // Validate if name already exists
        Optional<Producer> existingProducer = producerRepository.findByNameIgnoreCase(producerDTO.getName());
        if (existingProducer.isPresent()) {
            throw new IllegalArgumentException("Producer with name '" + producerDTO.getName() + "' already exists.");
        }
        Producer producer = mapToEntity(producerDTO);
        // Validate if created_at is earlier than updated_at
        if (producer.getCreated_at().after(producer.getUpdated_at())) {
            throw new IllegalArgumentException("created_at must be earlier than updated_at.");
        }

        Producer savedProducer = producerRepository.save(producer);
        return mapToDTO(savedProducer); // Return saved producer DTO
    }
    @Override
    @Transactional
    public ProducerDTO getProducerById(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + id));
        return mapToDTO(producer); // Return DTO
    }
    @Override
    @Transactional
    public ProducerDTO updateProducer(Long id, ProducerDTO producerDTO) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + id));
        // Validate if the name already exists and is not the same as the current producer
        Optional<Producer> existingProducer = producerRepository.findByNameIgnoreCase(producerDTO.getName());
        if (existingProducer.isPresent() && !existingProducer.get().getId().equals(id)) {
        	throw new IllegalArgumentException("Producer with name '" + producerDTO.getName() + "' already exists. Please choose a different name.");

        }
        // Update fields
        producer.setName(producerDTO.getName());
        producer.setCreated_at(producerDTO.getCreated_at());
        producer.setUpdated_at(producerDTO.getUpdated_at());
        // Validate if created_at is earlier than updated_at
        if (producer.getCreated_at() != null && producer.getUpdated_at() != null && producer.getCreated_at().after(producer.getUpdated_at())) {
            throw new IllegalArgumentException("created_at must be earlier than updated_at.");
        }


        Producer updatedProducer = producerRepository.save(producer); // Save updated producer

        return mapToDTO(updatedProducer); // Return updated producer DTO
    }
    @Override
    @Transactional
    public ProducerDTO deleteProducer(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + id));

        // Convert producer to DTO before deleting
        ProducerDTO producerDTO = mapToDTO(producer);

        // Delete producer
        producerRepository.delete(producer);

        // Return the deleted producer DTO
        return producerDTO;
    }
    @Override
    @Transactional
    public List<ProducerDTO> getAllProducers() {
        List<Producer> producers = producerRepository.findAll();
        return producers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    // Helper methods for mapping
    private ProducerDTO mapToDTO(Producer producer) {
        ProducerDTO producerDTO = new ProducerDTO();
        producerDTO.setId(producer.getId());
        producerDTO.setName(producer.getName());
        producerDTO.setCreated_at(producer.getCreated_at());
        producerDTO.setUpdated_at(producer.getUpdated_at());
        // Add additional fields as needed, for example, organizations
        return producerDTO;
    }
    private Producer mapToEntity(ProducerDTO producerDTO) {
        Producer producer = new Producer();
        producer.setName(producerDTO.getName());
        producer.setCreated_at(producerDTO.getCreated_at());
        producer.setUpdated_at(producerDTO.getUpdated_at());
        // Add additional fields as needed
        return producer;
    }
}
