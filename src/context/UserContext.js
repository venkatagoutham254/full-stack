// src/context/UserContext.js
import React, { createContext, useState, useContext } from 'react';
import { fetchUsers, createUser, updateUser, deleteUser } from '../Services/api';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const loadUsers = async () => {
    setLoading(true);
    try {
      const data = await fetchUsers();
      setUsers(data);
      setError(null);
    } catch (err) {
      setError('Failed to load users');
    } finally {
      setLoading(false);
    }
  };

  const addUser = async (userData) => {
    setLoading(true);
    try {
      const newUser = await createUser(userData);
      setUsers([...users, newUser]);
      setError(null);
    } catch (err) {
      setError('Failed to add user');
    } finally {
      setLoading(false);
    }
  };

  // Add similar functions for updateUser and deleteUser

  return (
    <UserContext.Provider value={{ users, loading, error, loadUsers, addUser }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUserContext = () => useContext(UserContext);
