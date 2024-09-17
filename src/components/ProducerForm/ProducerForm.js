// src/components/ProducerForm.js
import React, { useState } from 'react';
import './ProducerForm.css';
const ProducerForm = ({ producers = [], addProducer }) => {
  const [producerName, setProducerName] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const newProducer = { producerName, email, id: Date.now() }; // Generate unique ID using Date.now()
    addProducer(newProducer);
    setProducerName('');
    setEmail('');
  };

  return (
    <div>
      <h2>Producer Form</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Producer Name:
          <input type="text" value={producerName} onChange={(e) => setProducerName(e.target.value)} />
        </label>
        <label>
          Email:
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </label>
        <button type="submit">Submit</button>
      </form>

      <h3>Producer List</h3>
      <ul>
        {producers.length > 0 ? (
          producers.map((producer) => (
            <li key={producer.id}>
              {producer.producerName} ({producer.email})
            </li>
          ))
        ) : (
          <li>No producers available.</li>
        )}
      </ul>
    </div>
  );
};

export default ProducerForm;
