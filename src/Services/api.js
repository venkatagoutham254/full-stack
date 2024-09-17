// src/services/api.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:5000';

// Fetching posts as mock data
export const fetchPosts = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/posts`);
    return response.data;
  } catch (error) {
    console.error('Error fetching posts:', error);
    throw error;
  }
};

// Simulating user creation
export const createUser = async (userData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/users`, userData); // This simulates user creation
    return response.data; // This will return the created user data
  } catch (error) {
    console.error('Error creating user:', error);
    throw error;
  }
};
