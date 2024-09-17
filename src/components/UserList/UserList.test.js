// src/components/UserList/UserList.test.js
import React from 'react';
import { render, screen } from '@testing-library/react';
import UserList from './UserList';

test('renders user list', () => {
  const users = [
    { id: 1, name: 'John Doe', email: 'john@example.com' },
    { id: 2, name: 'Jane Doe', email: 'jane@example.com' },
  ];

  render(<UserList users={users} />);

  expect(screen.getByText('John Doe')).toBeInTheDocument();
  expect(screen.getByText('Jane Doe')).toBeInTheDocument();
});
