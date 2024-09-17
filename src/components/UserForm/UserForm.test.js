
// src/components/UserForm/UserForm.test.js
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import UserForm from './UserForm';

test('submits form with user data', () => {
  const mockSubmit = jest.fn();
  render(<UserForm onSubmit={mockSubmit} />);

  fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'John Doe' } });
  fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'john@example.com' } });
  fireEvent.click(screen.getByText(/submit/i));

  expect(mockSubmit).toHaveBeenCalledWith({
    name: 'John Doe',
    email: 'john@example.com',
  });
});


