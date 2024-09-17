// src/services/api.test.js
import axios from 'axios';
import { fetchUsers, createUser } from './api';

jest.mock('axios');

test('fetchUsers', async () => {
  const users = [{ id: 1, name: 'John Doe' }];
  axios.get.mockResolvedValue({ data: users });

  const result = await fetchUsers();

  expect(result).toEqual(users);
  expect(axios.get).toHaveBeenCalledWith('https://jsonplaceholder.typicode.com/posts');
});

test('createUser', async () => {
  const user = { name: 'John Doe', email: 'john@example.com' };
  const createdUser = { id: 1, ...user };
  axios.post.mockResolvedValue({ data: createdUser });

  const result = await createUser(user);

  expect(result).toEqual(createdUser);
  expect(axios.post).toHaveBeenCalledWith('', user);
});