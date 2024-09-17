package com.aforo.project.service;

import com.aforo.project.dto.ProducerDTO;
import java.util.List;

public interface ProducerService {
    ProducerDTO createProducer(ProducerDTO producerDTO);
    ProducerDTO getProducerById(Long id);
    ProducerDTO updateProducer(Long id, ProducerDTO producerDTO);
    ProducerDTO deleteProducer(Long id);
    List<ProducerDTO> getAllProducers();
}
